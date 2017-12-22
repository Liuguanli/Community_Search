package com.leo.bean;

/**
 * Created by apple on 2017/12/18.
 */
public class Point {

    public int index;

    public double latitude;
    public double longitude;

    public Point(int index) {
        this.index = index;
    }

    public Point(double latitude,double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Point(int index, double latitude,double longitude) {
        this.index = index;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    private static final double EARTH_RADIUS = 6378.137; //地球半径,单位千米

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public double getDistance(Point target) {

        if (target == null) {
            return 0;
        }

        double radLat1 = rad(this.latitude);
        double radLat2 = rad(target.latitude);
        double a = radLat1 - radLat2;
        double b = rad(this.longitude) - rad(target.longitude);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return index == point.index;
    }

    @Override
    public int hashCode() {
        return index;
    }
}