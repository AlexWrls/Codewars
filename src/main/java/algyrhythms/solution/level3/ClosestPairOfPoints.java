package algyrhythms.solution.level3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClosestPairOfPoints {
    @Test
    public void test01_Example() {

        List<Point> points = Arrays.asList(
                new Point(2, 2), //A
                new Point(2, 8), //B
                new Point(5, 5), //C
                new Point(6, 3), //D
                new Point(6, 7), //E
                new Point(7, 4), //F
                new Point(7, 9)  //G
        );

        List<Point> result = ClosestPairOfPoints.closestPair(points);
        List<Point> expected = Arrays.asList(new Point(6, 3), new Point(7, 4));
        verify(expected, result);
    }

    @Test
    public void test02_TwoPoints() {

        List<Point> points = Arrays.asList(
                new Point(2, 2),
                new Point(6, 3)
        );

        List<Point> result = ClosestPairOfPoints.closestPair(points);
        List<Point> expected = Arrays.asList(new Point(6, 3), new Point(2, 2));
        verify(expected, result);
    }

    @Test
    public void test03_DuplicatedPoint() {

        List<Point> points = Arrays.asList(
                new Point(2, 2), //A
                new Point(2, 8), //B
                new Point(5, 5), //C
                new Point(5, 5), //C
                new Point(6, 3), //D
                new Point(6, 7), //E
                new Point(7, 4), //F
                new Point(7, 9)  //G
        );

        List<Point> result = ClosestPairOfPoints.closestPair(points);
        List<Point> expected = Arrays.asList(new Point(5, 5), new Point(5, 5));
        verify(expected, result);
    }

    private void verify(List<Point> expected, List<Point> actual) {
        Comparator<Point> comparer = Comparator.<Point>comparingDouble(p -> p.x);

        Assert.assertNotNull("Returned array cannot be null.", actual);
        Assert.assertEquals("Expected exactly two points.", 2, actual.size());
        Assert.assertFalse("Returned points must not be null.", actual.get(0) == null || actual.get(1) == null);

        expected.sort(comparer);
        actual.sort(comparer);
        boolean eq = expected.get(0).x == actual.get(0).x && expected.get(0).y == actual.get(0).y
                && expected.get(1).x == actual.get(1).x && expected.get(1).y == actual.get(1).y;
        Assert.assertTrue(String.format("Expected: %s, Actual: %s", expected.toString(), actual.toString()), eq);
    }

    public static List<Point> closestPair(List<Point> points) {
        List<Point> temp = new ArrayList<>();
        double min = Integer.MAX_VALUE;
        for (int j = 0; j < points.size(); j++) {
            for (int k = 0; k < points.size(); k++) {
                if (j != k) {
                    double x = Math.abs(points.get(j).x - points.get(k).x);
                    double y = Math.abs(points.get(j).y - points.get(k).y);
                    if ((x + y) < min) {
                        min = x + y;
                        temp = Arrays.asList(points.get(j), points.get(k));
                    }
                }
            }
        }
        return temp;
    }

    static class Point {
        public double x, y;

        public Point() {
            x = y = 0.0;
        }

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("(%f, %f)", x, y);
        }

        @Override
        public int hashCode() {
            return Double.hashCode(x) ^ Double.hashCode(y);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                Point other = (Point) obj;
                return x == other.x && y == other.y;
            } else {
                return false;
            }
        }
    }
}
