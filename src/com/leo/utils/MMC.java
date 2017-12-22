package com.leo.utils;

import com.leo.bean.Point;

import java.util.List;

/**
 * Created by apple on 2017/12/19.
 */
public class MMC {

    double radius = -1;
    Point center;
    List<Point> points;

    public MMC(List<Point> points) {
        this.points = points;
        center = new Point(0, 0);
    }

    public Point solve(double a, double b, double c, double d, double e, double f) {
        double y = (f * a - c * d) / (b * d - e * a);
        double x = (f * b - c * e) / (a * e - b * d);
        return new Point(x, y);
    }

    public double getRadius() {
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            if (!inCircle(point)) {
                center.longitude = point.longitude;
                center.latitude = point.latitude;
                radius = 0;
                for (int j = 0; j < i; j++) {
                    Point temp = points.get(j);
                    if (!inCircle(temp)) {
                        center.latitude = (point.latitude + temp.latitude) / 2;
                        center.longitude = (point.longitude + temp.longitude) / 2;
                        radius = center.getDistance(point);
                        for (int k = 0; k < j; k++) {
                            Point tempK = points.get(j);
                            if (!inCircle(tempK)) {
                                center = solve(point.latitude - temp.latitude, point.longitude - temp.longitude, (Math.sqrt(temp.latitude) + Math.sqrt(temp.longitude) - Math.sqrt(point.latitude) - Math.sqrt(point.longitude)) / 2,
                                        point.latitude - tempK.latitude, point.longitude - tempK.longitude, (Math.sqrt(tempK.latitude) + Math.sqrt(tempK.longitude) - Math.sqrt(point.latitude) - Math.sqrt(point.longitude)) / 2);
                                radius=center.getDistance(point);
                            }
                        }
                    }
                }

            }
        }
        return radius;
    }

    private boolean inCircle(Point point) {
        if (point.getDistance(center) <= radius) {
            return true;
        }
        return false;
    }


}
