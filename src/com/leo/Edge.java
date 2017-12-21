package com.leo;

/**
 * Created by apple on 2017/12/18.
 */
public class Edge {

    public Edge(Point v1, Point v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Point v1;
    public Point v2;

    public Edge next;

    @Override
    public String toString() {
        return "Edge{" +
                "v1=" + v1 +
                ", v2=" + v2 +
                '}';
    }
}
