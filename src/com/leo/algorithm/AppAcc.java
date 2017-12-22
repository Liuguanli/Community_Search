package com.leo.algorithm;

import com.leo.bean.AppFastResult;
import com.leo.bean.Graph;
import com.leo.utils.KCore;
import com.leo.bean.Point;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2017/12/21.
 */
public class AppAcc extends AppAbstract {

    public List<Point> calc(Point query, Graph graph, int k, AppFastResult appFastResult, double epsilon) {
        double delta = appFastResult.delta;
        double gamma = appFastResult.gamma;

        // S ← vertices of the k-c ore, containing q, in O(q, 2γ);

        List<Point> points = findRangePoints(query, graph.points, 2 * gamma);
        Graph subGraph = graph.induceSubGraph(points);
        KCore kcore = new KCore(subGraph, k, query);
        List<Point> tempResult = kcore.findKCore();

        List<Point> Γ = appFastResult.reuslt;

        double beta = gamma;
        double rCur = gamma;
        List<Point> achList = new LinkedList() {{
            add(query);
        }};
        while (beta > delta * epsilon / (Math.sqrt(2) * (2 + epsilon))) {

            Map<Point, Double> map = new HashMap<>();

            for (Point point : achList) {

            }

        }
        return null;
    }
}
