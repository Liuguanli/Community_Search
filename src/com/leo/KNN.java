package com.leo;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by apple on 2017/12/21.
 */
public class KNN {

    public List<Point> getKNN(List<Point> points, int k, Point query) {
        List<Point> result = new LinkedList();
        for (Point point : points) {
            double temp = query.getDistance(point);
            if (result.size() <= k) {
                result.add(point);
            } else {
                double dist = query.getDistance(result.get(k - 1));
                if (dist < temp) {
                    continue;
                } else {
                    result.remove(k - 1);
                    result.add(point);
                }
            }
            Collections.sort(result, new Comparator<Point>() {
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
        }
        return result;
    }

}
