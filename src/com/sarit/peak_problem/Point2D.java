package com.sarit.peak_problem;

public class Point2D {
    public final int x, y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static boolean withinBounds(Point2D point, int minX, int maxX, int minY, int maxY) {
        return point.x >= minX && point.x <= maxX && point.y >= minY && point.y <= maxY;
    }

    public static Point2D[] getNeighbours(Point2D point) {
        return new Point2D[] {
          new Point2D(point.x - 1, point.y),
          new Point2D(point.x + 1, point.y),
                new Point2D(point.x, point.y + 1),
                new Point2D(point.x, point.y - 1)
        };
    }
}
