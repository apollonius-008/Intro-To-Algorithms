package com.sarit.test;

import com.sarit.peak_problem.Peak2D;
import com.sarit.peak_problem.Peak2DFunction;
import com.sarit.peak_problem.Point2D;

import java.util.*;

public class Peak2DTest {

    public static void testMatrixWithSinglePeak(Random r, int rows, int cols, Peak2DFunction f) {
        Point2D peak = new Point2D(r.nextInt(rows), r.nextInt(cols));
        int[][] matrix = TestUtility.generateMatrixWithSinglePeak(rows, cols, peak, r.nextInt(Integer.MAX_VALUE));

        Point2D answer = f.execute(matrix);
        assert answer == peak : "Matrix=" + Arrays.deepToString(matrix) + ", Peak=" + peak + ", Answer=" + answer.toString();
    }

    private static void printRunningTimeExecution(Random r, int rows, int cols, int numExecutions, Peak2DFunction f) {
        long totalTime = 0, minTime = Long.MAX_VALUE, maxTime = Long.MIN_VALUE;

        for (int i = 0; i < numExecutions; i++) {
            Point2D peak = new Point2D(r.nextInt(rows), r.nextInt(cols));
            int[][] matrix = TestUtility.generateMatrixWithSinglePeak(rows, cols, peak, r.nextInt(Integer.MAX_VALUE));

            long time = TestUtility.elapsedTime(() -> f.execute(matrix));
            totalTime += time;
            if (time < minTime)
                minTime = time;
            if (time > maxTime)
                maxTime = time;
        }

        System.out.printf("Average Time=%d ns, Min Time=%d ns, Max Time=%d ns\n", totalTime / numExecutions, minTime, maxTime);
    }

    public static void main(String[] args) {
        Map<String, Peak2DFunction> functions = new HashMap<>();
        functions.put("NAIVE ITERATIVE ALGORITHM", Peak2D::naive2DPeak);
        functions.put("GREEDY ASCENT ALGORITHM", Peak2D::greedyAscent2DPeak);
        functions.put("DIVIDE AND CONQUER ALGORITHM", (matrix) -> Peak2D.divideAndConquer(matrix, 0, matrix[0].length - 1));

        functions.forEach((name, f) -> {
            long seed = 100000;
            Random r = new Random(seed);

            testMatrixWithSinglePeak(r, 1, 1, f);
            testMatrixWithSinglePeak(r, 1, 1, f);
            testMatrixWithSinglePeak(r, 1, 1, f);

            testMatrixWithSinglePeak(r, 10, 10, f);
            testMatrixWithSinglePeak(r, 10, 10, f);
            testMatrixWithSinglePeak(r, 10, 10, f);
            testMatrixWithSinglePeak(r, 10, 10, f);

            testMatrixWithSinglePeak(r, 100, 100, f);
            testMatrixWithSinglePeak(r, 100, 100, f);
            testMatrixWithSinglePeak(r, 100, 100, f);
            testMatrixWithSinglePeak(r, 100, 100, f);

            testMatrixWithSinglePeak(r, 1000, 1000, f);
            testMatrixWithSinglePeak(r, 1000, 1000, f);
            testMatrixWithSinglePeak(r, 1000, 1000, f);
            testMatrixWithSinglePeak(r, 1000, 1000, f);

            System.out.println("######## " + name + " ########");
            printRunningTimeExecution(r, 1000, 1000, 10000, f);
            System.out.println();
        });
    }
}
