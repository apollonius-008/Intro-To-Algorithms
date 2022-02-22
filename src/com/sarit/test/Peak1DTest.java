package com.sarit.test;
import com.sarit.peak_problem.Peak1D;
import com.sarit.peak_problem.Peak1DFunction;

import java.util.*;
import static com.sarit.peak_problem.Peak1D.*;

public class Peak1DTest {

    public static void testArrayOfSize1(Random r, Peak1DFunction f) {
        int[] arr = new int[1];
        arr[0] = r.nextInt();
        int peak = f.execute(arr);
        assert peak == 0 : "0 must be the peak in array of length 1. Peak=" + peak + " .Array=" + Arrays.toString(arr);
    }

    public static void testPeakIsInMid(Random r, int size, Peak1DFunction f) {
        int peak = (size - 1) / 2;
        testPeakBySettingPeak(r, size, peak, f);
    }

    public static void testPeakBySettingPeak(Random r, int size, int peak, Peak1DFunction f) {
        int[] arr = TestUtility.generateIntArrayInRange(size, r.nextInt(100), 1 + r.nextInt(99));
        int upperBound = size * 100 + 1;
        arr[peak] = upperBound;
        int actualPeak = f.execute(arr);
        assert peak == actualPeak || actualPeak == arr.length - 1 : "Array=" + Arrays.toString(arr) + ", Expected=" + peak + ", Actual=" + actualPeak;
    }

    private static void printExecutionTimeReport(Random r, int size, int numExecutions, Peak1DFunction f) {
        long totalTime = 0, maxTime = Long.MIN_VALUE, minTime = Long.MAX_VALUE;

        for (int i = 0; i < numExecutions; i++) {
            int[] arr = TestUtility.generateIntArrayInRange(size, r.nextInt(100), 1 + r.nextInt(99));
            int upperBound = size * 100 + 1;
            int peak = r.nextInt(size);
            arr[peak] = upperBound;

            long time = TestUtility.elapsedTime(() -> f.execute(arr));

            totalTime += time;
            if (time > maxTime)
                maxTime = time;
            if (time < minTime)
                minTime = time;
        }

        System.out.printf("ARRAY SIZE : %d NUM EXECUTION : %d%n", size, numExecutions);
        System.out.printf("AVG TIME : %d ns, MAX TIME : %d ns, MIN TIME : %d ns%n", totalTime / numExecutions, maxTime, minTime);
    }

    public static void main(String[] args) {

        Map<String, Peak1DFunction> functions = new HashMap<>();
        functions.put("Naive Implementation Iterative", Peak1D::naiveImplementationIterative);
        functions.put("Naive Implementation Recursive", (arr) -> naiveImplementationRecursive(arr, arr.length - 1));
        functions.put("Divide And Conquer", (arr) -> divideAndConquer(arr, 0, arr.length - 1));

        functions.forEach((name, f) -> {
            long seed = 1000000;
            Random r = new Random(seed);

            System.out.println("####### TESTING : " + name + " #######\n");

            Peak1DTest.testArrayOfSize1(r, f);
            Peak1DTest.testArrayOfSize1(r, f);
            Peak1DTest.testArrayOfSize1(r, f);
            Peak1DTest.testArrayOfSize1(r, f);

            Peak1DTest.testPeakIsInMid(r, 10, f);
            Peak1DTest.testPeakIsInMid(r, 10, f);
            Peak1DTest.testPeakIsInMid(r, 10, f);
            Peak1DTest.testPeakIsInMid(r, 10000, f);
            Peak1DTest.testPeakIsInMid(r, 10000, f);

            int countTests = 100;
            for (int i = 0; i < countTests; i++) {
                Peak1DTest.testPeakBySettingPeak(r, 100000, r.nextInt(100000), f);
            }

            printExecutionTimeReport(r, 100000, 1000, f);
            System.out.println();
        });
    }
}
