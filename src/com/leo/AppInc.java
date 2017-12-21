package com.leo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by apple on 2017/12/21.
 */
public class AppInc {

    public List<Point> calc(Point query, Graph graph, int k) {
        List<Point> queue = new LinkedList<>();
        List<Point> S = new LinkedList<>();
        List<Point> T = new LinkedList<>();
        List<Point> result = new LinkedList<>();
        queue.add(query);
        while (queue.size() > 0) {
            Point p = queue.remove(0);
            S.add(query);
            List<Point> neighborsOfQ = graph.getNb(query);
            List<Point> neighborsOfP = graph.getNb(p);
            for (Point v: neighborsOfP) {
                int size = graph.vertexList.get(v).size();
                if (size >= k) {
                    if (query.getDistance(v) <= query.getDistance(p)) {
                        S.add(v);
                    } else if(!T.contains(v)) {
                        queue.add(v);
                        T.add(v);
                    }
                }
            }
            List<Point> tempQ = new LinkedList<>();
            List<Point> tempP = new LinkedList<>();
            tempQ.addAll(neighborsOfQ);
            tempP.addAll(neighborsOfP);
            tempQ.retainAll(S);
            tempP.retainAll(S);
            if (tempQ.size() > k && tempP.size() > k) {
                Graph subGraph = graph.induceSubGraph(S);
                KCore kcore = new KCore(subGraph, k, query);
                List<Point> tempResult = kcore.findKCore();
                if (tempResult.size() > 0) {
                    result = tempResult;
                    break;
                }
            }
        }

        return result;
    }


}
