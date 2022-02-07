package com.sarit.peak_problem;

import com.sarit.test_utility.TestUtility;
import java.util.*;

public class Peak1D {

    public static int naiveImplementationIterative(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] >= arr[i + 1])
                return i;
        }
        return arr.length - 1;
    }

    public static int naiveImplementationRecursive(int[] arr, int n) {
        if (n == 0)
            return 0;

        if (arr[n] >= arr[n - 1])
            return n;
        else
            return naiveImplementationRecursive(arr, n - 1);
    }

    public static int divideAndConquer(int[] arr, int s, int e) {
        int mid = (s + e) / 2;
        if (mid > s && arr[mid - 1] > arr[mid])
            return divideAndConquer(arr, s, mid - 1);
        else if (mid < e && arr[mid + 1] > arr[mid])
            return divideAndConquer(arr, mid + 1, e);
        else
            return mid;
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

            testArrayOfSize1(r, f);
            testArrayOfSize1(r, f);
            testArrayOfSize1(r, f);
            testArrayOfSize1(r, f);

            testPeakIsInMid(r, 10, f);
            testPeakIsInMid(r, 10, f);
            testPeakIsInMid(r, 10, f);
            testPeakIsInMid(r, 10000, f);
            testPeakIsInMid(r, 10000, f);

            int countTests = 100;
            for (int i = 0; i < countTests; i++) {
                testPeakBySettingPeak(r, 100000, r.nextInt(100000), f);
            }

            printExecutionTimeReport(r, 100000, 1000, f);
            System.out.println();
        });
    }

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
}

interface Peak1DFunction { int execute(int[] arr);}
