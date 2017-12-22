package com.leo;

import com.leo.algorithm.AppFast;
import com.leo.bean.AppFastResult;
import com.leo.algorithm.AppInc;
import com.leo.algorithm.Exact;
import com.leo.bean.Graph;
import com.leo.bean.Point;
import com.leo.utils.KCore;

import java.util.List;

/**
 * Created by apple on 2017/12/18.
 */
public class Main {

    public static void main(String[] args) {

//        DataUtil.extractData();

        Graph graph = new Graph();
        graph.buildGraph("loc-brightkite_edges.txt", "loc-brightkite_totalCheckins_extract.txt");
        Point query = new Point(0);
        int k = 2;
        KCore kcore = new KCore(graph, k, query);
        List<Point> points = kcore.findKCore();
//        Exact exact = new Exact();
//        List<Point> result = exact.calc(points, query, graph, k);
//        System.out.println("result->" + result);
//        executeAppInc(query, graph, k);
        executeAppFast(query, graph, k, 0.5);

        executeExact(points, query, graph, k);
    }

    public static AppFastResult executeAppFast(Point query, Graph graph, int k, double epsilon) {
        AppFast appFast = new AppFast();
        AppFastResult result = appFast.calc(query, graph, k, epsilon);
        System.out.println("result->" + result.reuslt);
        return result;
    }

    public static void executeAppInc(Point query, Graph graph, int k) {
        AppInc appInc = new AppInc();
        List<Point> result = appInc.calc(query, graph, k);
        System.out.println("result->" + result);
    }

    public static void executeExact(List<Point> points, Point query, Graph graph, int k) {
        Exact exact = new Exact();
        List<Point> result = exact.calc(points, query, graph, k);
        System.out.println("result->" + result);
    }

}
