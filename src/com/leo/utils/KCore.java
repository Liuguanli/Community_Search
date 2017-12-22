package com.leo.utils;

import com.leo.bean.Edge;
import com.leo.bean.Graph;
import com.leo.bean.Point;

import java.util.*;

/**
 * Created by apple on 2017/12/18.
 * 论文中提到的KCore算法
 */
public class KCore {

    private Graph graph;

    private int k;

    private Point queryPoint;

    Map<Point, Integer> coreNum;

    public KCore(Graph graph, int k, Point queryPoint) {
        this.k = k;
        this.graph = graph;
        this.queryPoint = queryPoint;

        coreNum = new HashMap<>();
    }

    public List<Point> findKCore() {
        int[] maxDegreeOfVertex = new int[graph.numOfVertex];
        Iterator iterator = graph.vertexList.keySet().iterator();

        while (iterator.hasNext()) {
            Point pointIndex = (Point) iterator.next();
            coreNum.put(pointIndex, graph.vertexList.get(pointIndex).size());
        }

        int maxCore = 0;
        Point maxCoreIndex = null;

        List<Edge> connectedEdges = graph.vertexList.get(queryPoint);
        if (connectedEdges == null) {
            return new LinkedList();
        }
        for (int i = 0; i < connectedEdges.size(); i++) {
            Point point = connectedEdges.get(i).v2;

            boolean isFlag = true;

            List<Edge> edges = graph.vertexList.get(point);
            if (coreNum.containsKey(point) && coreNum.get(point) >= k) {
                for (Edge edge : edges) {
                    if (coreNum.containsKey(edge.v2) && coreNum.get(edge.v2) < k) {
                        isFlag = false;
                        break;
                    }
                }
                if (edges.size() >= k && isFlag) {
                    if (edges.size() > maxCore) {
                        maxCore = edges.size();
                        maxCoreIndex = point;
                    }
                }
            }
        }

        List<Edge> edges = graph.vertexList.get(maxCoreIndex);
        List<Point> points = new LinkedList();
        points.add(maxCoreIndex);
        for (int i = 0; i < edges.size(); i++) {
            points.add(edges.get(i).v2);
        }

//        System.out.println("size->" + graph.vertexList.get(maxCoreIndex).size());
//        System.out.println("maxCore->" + maxCore);
//        System.out.println("maxCoreIndex->" + maxCoreIndex);

        return points;
    }

}
