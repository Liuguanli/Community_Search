package com.leo;

import com.leo.utils.DataUtil;

import java.util.*;

/**
 * Created by apple on 2017/12/18.
 */
public class Graph {

    public Map<Point, List<Edge>> vertexList;//存储点的链表
    public int numOfEdges;//边的数目
    public int numOfVertex;
    public List<Point> points;

    public Graph() {
        vertexList = new LinkedHashMap<>();
        points = new LinkedList<>();
    }

    public void addEdge(Point p1, Point p2) {
        Edge edge = new Edge(p1, p2);
        if (!vertexList.containsKey(p1)) {
            vertexList.put(p1, new LinkedList<>());
        }
        vertexList.get(p1).add(edge);
        numOfEdges++;
    }

    public void buildGraph() {
        List<String> edges = DataUtil.readFileByLines("loc-brightkite_edges.txt");
        List<String> positions = DataUtil.readFileByLines("loc-brightkite_totalCheckins_extract.txt");

        for (String string : positions) {
            String[] result = string.split("\t");
            points.add(new Point(Integer.valueOf(result[0]), Double.valueOf(result[1]), Double.valueOf(result[2])));
        }

        for (String string : edges) {
            String[] result = string.split("\t");
            int p1 = Integer.valueOf(result[0]);
            int p2 = Integer.valueOf(result[1]);
            if (p1 < points.size() && p2 < points.size()) {
                addEdge(points.get(p1), points.get(p2));
            }
        }

        numOfVertex = vertexList.size();
        System.out.println("numOfEdges->" + numOfEdges);
        System.out.println("vertexs->" + numOfVertex);
    }

    public Graph induceSubGraph(List<Point> points) {
        Graph graph = new Graph();
        for (int i = 0; i < numOfVertex; i++) {
            List<Edge> edges = vertexList.get(new Point(i));
            if (edges == null) {
                continue;
            }
            for (int j = 0; j < edges.size(); j++) {
                Edge edge = edges.get(j);
                if (points.contains(edge.v2)) {
                    graph.addEdge(edge.v1, edge.v2);
                }
            }
        }
        return graph;
    }

}
