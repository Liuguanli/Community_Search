package com.leo.algorithm;

import com.leo.bean.Point;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by apple on 2017/12/21.
 */
public abstract class AppAbstract {
    public List<Point> findRangePoints(Point query, List<Point> points, double r) {
        List<Point> result = new LinkedList<>();
        for (Point point : points) {
            if (query.getDistance(point) < r) {
                result.add(point);
            }
        }
        return result;
    }

    public double getMaxdist(List<Point> points, Point query) {
        double maxDist = Double.MIN_VALUE;
        for (Point point : points) {
            double dist = query.getDistance(point);
            if (dist > maxDist) {
                maxDist = dist;
            }
        }
        return maxDist;
    }

    public double getMindist(List<Point> points, Point query) {
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
