package com.sarit.test;

import com.sarit.peak_problem.Peak2D;
import com.sarit.peak_problem.Point2D;

import java.util.Scanner;

public class TestUtility {

    public static int[] generateIntArrayAndFill(int size, int fillValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = fillValue;
        return arr;
    }

    public static int[] generateIntArrayInRange(int size, int start, int step) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = start + i * step;
        }

        return arr;
    }

    public static int[][] generateMatrixWithSinglePeak(int rows, int cols, Point2D peak, int peakValue) {
        int[][] matrix = new int[rows][cols];

        Point2D currentPoint = peak;
        int currentValue = peakValue;

        int delX = 1, delY = 1;

        while (delX != 0) {
            matrix[currentPoint.x][currentPoint.y] = currentValue;

            Point2D nP = new Point2D(currentPoint.x + delX, currentPoint.y);
            if (!Point2D.withinBounds(nP, 0, rows - 1, 0, cols - 1)) {
                nP = new Point2D(currentPoint.x, currentPoint.y + delY);
                if (!Point2D.withinBounds(nP, 0, rows - 1, 0, cols - 1)) {
                    if (delY == 1) {
                        delY = -1;
                        nP = new Point2D(currentPoint.x, currentPoint.y + delY);

                        if (!Point2D.withinBounds(nP, 0, rows - 1, 0, cols - 1))
                            delX = 0;
                    }
                    else
                        delX = 0;
                }
            }

            currentValue -= 1;
            currentPoint = nP;
        }

        return matrix;
    }

    public static void fillMatrixDFS(int[][] matrix, boolean[][] visited, int rows, int cols, Point2D pos, int value) {

        visited[pos.x][pos.y] = true;
        matrix[pos.x][pos.y] = value;

        for (Point2D p : Point2D.getNeighbours(pos)) {
            if (Point2D.withinBounds(p, 0, rows - 1, 0, cols - 1) && !visited[p.x][p.y])
                fillMatrixDFS(matrix, visited, rows, cols, p, value - 1);
        }
    }

    public static void haltProgram() {
        System.out.println("Press enter to continue ...");
        new Scanner(System.in).nextLine();
    }

    public static long elapsedTime(AnyFunction function) {
        long start = System.nanoTime();
        function.run();
        long end = System.nanoTime();
        return end - start;
    }
}
