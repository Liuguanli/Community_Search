package com.leo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by apple on 2017/12/21.
 */
public class AppFast {

    public List<Point> calc(Point query, Graph graph, int k, double epsilon) {
        List<Point> result = new LinkedList<>();
        KNN knn = new KNN();
        List<Point> knnPoints = knn.getKNN(graph.points, k, query);
        double l = query.getDistance(knnPoints.get(k - 1));
        KCore kcore = new KCore(graph, k, query);
        List<Point> kCorepoints = kcore.findKCore();
        double u = getMaxdist(kCorepoints, query);

        while (u > l) {
            double r = (u + l) / 2;
            List<Point> vertices = findRangePoints(query, graph.points, r);
            Graph subGraph = graph.induceSubGraph(vertices);

            KCore kcore1 = new KCore(subGraph, k, query);
            List<Point> tempResult = kcore1.findKCore();
            double alpha = (r * epsilon) / (2 + epsilon);
            if (tempResult.size() > 0) {
                result = tempResult;
                if (r - l <= alpha) {
                    return result;
                }
                u = getMaxdist(result, query);
            } else {
                if (u - r <= alpha) {
                    return result;
                }
                List<Point> temp = new ArrayList<>();
                temp.addAll(result);
                temp.removeAll(vertices);
                l = getMindist(temp, query);
            }
        }

        return result;
    }

    private List<Point> findRangePoints(Point query, List<Point> points, double r) {
        List<Point> result = new LinkedList<>();
        for (Point point : points) {
            if (query.getDistance(point) < r) {
                result.add(point);
            }
        }
        return result;
    }

    private double getMaxdist(List<Point> points, Point query) {
        double maxDist = Double.MIN_VALUE;
        for (Point point : points) {
            double dist = query.getDistance(point);
            if (dist > maxDist) {
                maxDist = dist;
            }
        }
        return maxDist;
    }

    private double getMindist(List<Point> points, Point query) {
        double minDist = Double.MAX_VALUE;
        for (Point point : points) {
            double dist = query.getDistance(point);
            if (dist < minDist) {
                minDist = dist;
            }
        }
        return minDist;
    }

}
