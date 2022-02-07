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

    public static int peak1D(int[] arr, int s, int e) {
        int mid = (s + e) / 2;
        if (mid > s && arr[mid - 1] > arr[mid])
            return peak1D(arr, s, mid - 1);
        else if (mid < e && arr[mid + 1] > arr[mid])
            return peak1D(arr, mid + 1, e);
        else
            return mid;
    }

    public static int activeImplementation(int[] arr) {
        return peak1D(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        long seed = 1000000;
        Random r = new Random(seed);

        testArrayOfSize1(r);
        testArrayOfSize1(r);
        testArrayOfSize1(r);
        testArrayOfSize1(r);

        testPeakIsInMid(r, 10);
        testPeakIsInMid(r, 10);
        testPeakIsInMid(r, 10);
        testPeakIsInMid(r, 10000);
        testPeakIsInMid(r, 10000);

        int countTests = 100;
        for (int i = 0; i < countTests; i++) {
            testPeakBySettingPeak(r, 100000, r.nextInt(100000));
        }

        printExecutionTimeReport(r, 100000, 1000);
    }

    public static void testArrayOfSize1(Random r) {
        int[] arr = new int[1];
        arr[0] = r.nextInt();
        int peak = activeImplementation(arr);
        assert peak == 0 : "0 must be the peak in array of length 1. Peak=" + peak + " .Array=" + Arrays.toString(arr);
    }

    public static void testPeakIsInMid(Random r, int size) {
        int peak = (size - 1) / 2;
        testPeakBySettingPeak(r, size, peak);
    }

    public static void testPeakBySettingPeak(Random r, int size, int peak) {
        int[] arr = TestUtility.generateIntArrayInRange(size, r.nextInt(100), 1 + r.nextInt(99));
        int upperBound = size * 100 + 1;
        arr[peak] = upperBound;
        int actualPeak = activeImplementation(arr);
        assert peak == actualPeak || actualPeak == arr.length - 1 : "Array=" + Arrays.toString(arr) + ", Expected=" + peak + ", Actual=" + actualPeak;
    }

    private static void printExecutionTimeReport(Random r, int size, int numExecutions) {
        long totalTime = 0, maxTime = Long.MIN_VALUE, minTime = Long.MAX_VALUE;

        for (int i = 0; i < numExecutions; i++) {
            int[] arr = TestUtility.generateIntArrayInRange(size, r.nextInt(100), 1 + r.nextInt(99));
            int upperBound = size * 100 + 1;
            int peak = r.nextInt(size);
            arr[peak] = upperBound;

            long time = TestUtility.elapsedTime(() -> activeImplementation(arr));

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
