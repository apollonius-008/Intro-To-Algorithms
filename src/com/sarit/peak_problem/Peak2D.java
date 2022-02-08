package com.sarit.peak_problem;

import java.util.Random;

public class Peak2D {

    public static int getValueFromPos(int[][] matrix, Point2D p) {
        return matrix[p.x][p.y];
    }

    public static Point2D naive2DPeak(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j])
                    continue;
                if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j])
                    continue;
                if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j])
                    continue;
                if (j + 1 < matrix[i].length && matrix[i][j + 1] > matrix[i][j])
                    continue;

                return new Point2D(i, j);
            }
        }

        return null;
    }

    public static Point2D greedyAscent2DPeak(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        Point2D currentPoint = new Point2D(0, 0);
        boolean done = false;
        while (!done) {
            done = true;
            /*
            choose the neighbour with the highest value whose value is
            higher than currentPoint and set currentPoint to it and we're not done
            if no such point exists then we're done and currentPoint is the peak
             */
            for (Point2D p : Point2D.getNeighbours(currentPoint)) {
                if (Point2D.withinBounds(p, 0, rows - 1, 0, cols - 1) && getValueFromPos(matrix, p) > getValueFromPos(matrix, currentPoint)) {
                    currentPoint = p;
                    done = false;
                }
            }
        }
        return currentPoint;
    }
}
