package com.leo.algorithm;

import com.leo.bean.AppFastResult;
import com.leo.bean.Graph;
import com.leo.utils.KCore;
import com.leo.utils.KNN;
import com.leo.bean.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by apple on 2017/12/21.
 */
public class AppFast extends AppAbstract {

    public AppFastResult calc(Point query, Graph graph, int k, double epsilon) {
        List<Point> result = new LinkedList<>();
        AppFastResult appFastResult = new AppFastResult();
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
            appFastResult.reuslt = result;
            appFastResult.delta = u;
            appFastResult.gamma = l;
            KCore kcore1 = new KCore(subGraph, k, query);
            List<Point> tempResult = kcore1.findKCore();
            double alpha = (r * epsilon) / (2 + epsilon);
            if (tempResult.size() > 0) {
                result = tempResult;
                if (r - l <= alpha) {
                    appFastResult.reuslt = result;
                    return appFastResult;
                }
                u = getMaxdist(result, query);
                appFastResult.delta = u;
            } else {
                if (u - r <= alpha) {
                    appFastResult.reuslt = result;
                    return appFastResult;
                }
                List<Point> temp = new ArrayList<>();
                temp.addAll(result);
                temp.removeAll(vertices);
                l = getMindist(temp, query);
                appFastResult.gamma = l;
            }
        }
        return appFastResult;
    }

}
