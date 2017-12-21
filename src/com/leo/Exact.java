package com.leo;

import java.util.*;

import static com.sun.tools.internal.xjc.reader.Ring.add;

/**
 * Created by apple on 2017/12/18.
 */
public class Exact {

    public List<Point> calc(List<Point> points, Point query, Graph graph, int k) {

        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.getDistance(query) < p2.getDistance(query)) {
                    return 1;
                } else if (p1.getDistance(query) < p2.getDistance(query)) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        double radius = Double.MAX_VALUE;
        List<Point> result = null;
//        for (int i = 3; i < points.size(); i++) {
        for (int i = 33; i < points.size(); i++) {
            System.out.println("--------------" + i + "--------------");
            for (int j = 1; j < i - 2; j++) {
                for (int h = j + 1; h < i - 1; h++) {
                    List<Point> temp = new ArrayList();
                    temp.add(points.get(i));
                    temp.add(points.get(j));
                    temp.add(points.get(h));
                    MMC mmc = new MMC(temp);
                    double mmcRadius = mmc.getRadius();
                    if (mmcRadius < radius) {
                        // R ← a set of vertices in mcc
                        List<Point> set = getVertexsInMMC(mmcRadius, query, graph);

                        Graph subGraph = graph.induceSubGraph(set);

                        KCore kcore = new KCore(subGraph, k, query);
                        List<Point> tempResult = kcore.findKCore();
                        if (tempResult.size() > 0) {
                            result = tempResult;
                            radius = mmcRadius;
                        }
                    }
                }
            }
            if (points.get(i).getDistance(query) > 2 * radius) {
                break;
            }
        }
        return result;
    }

    public List<Point> getVertexsInMMC(double radius, Point query, Graph graph) {
        List<Point> points = new LinkedList<>();
        List<Point> vertexs = graph.points;
        for (int i = 0; i < vertexs.size(); i++) {
            if (vertexs.get(i).getDistance(query) <= radius) {
                points.add(vertexs.get(i));
            }
        }
        return points;
    }

}
