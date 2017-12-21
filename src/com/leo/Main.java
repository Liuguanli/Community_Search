package com.leo;

import com.leo.utils.DataUtil;

import java.util.List;

/**
 * Created by apple on 2017/12/18.
 */
public class Main {

    public static void main(String[] args) {

//        DataUtil.extractData();

        Graph graph = new Graph();
        graph.buildGraph();
        Point query = new Point(0);
        int k = 2;
        KCore kcore = new KCore(graph, k, query);
        List<Point> points = kcore.findKCore();
        Exact exact = new Exact();
        List<Point> result = exact.calc(points, query, graph, k);
        System.out.println("result->" + result);
    }

}
