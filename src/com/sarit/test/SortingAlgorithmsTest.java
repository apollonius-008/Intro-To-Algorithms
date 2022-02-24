package com.sarit.test;

import com.sarit.sorting_algorithms.SortingAlgorithms;
import com.sarit.sorting_algorithms.SortingFunction;

import java.util.*;

public class SortingAlgorithmsTest {

    private static void reportTime(SortingFunction f, int numExecutions, int size) {
        Random r = new Random(100);
        long maxTime = Long.MIN_VALUE,
                minTime = Long.MAX_VALUE,
                totalTime = 0;
        for (int i = 0; i < numExecutions; i++) {
            int[] arr = TestUtility.generateRandomArray(size, r);

            long time = TestUtility.elapsedTime(() -> f.sort(arr));

            totalTime += time;

            if (time > maxTime)
                maxTime = time;

            if (time < minTime)
                minTime = time;
        }

        System.out.println(String.format("MAX TIME : %d ns, MIN TIME : %d ns, AVERAGE TIME : %d ns.", maxTime, minTime, totalTime / numExecutions));
    }

    public static void main(String[] args) {
        Map<String, SortingFunction> functionMap = new HashMap<>();

        functionMap.put("Selection sort", SortingAlgorithms::selection_sort);

        functionMap.put("Insertion Sort",
                SortingAlgorithms::insertion_sort);

        functionMap.put("Merge Sort", (arr) -> SortingAlgorithms.mergeSort(arr, 0, arr.length - 1));

        functionMap.forEach((name, f) -> {
            System.out.println("Testing " + name);

            int[] arr;

            arr = new int[]{1};
            f.sort(arr);
            System.out.println(Arrays.toString(arr));

            arr = new int[]{1, 2};
            f.sort(arr);
            System.out.println(Arrays.toString(arr));

            arr = new int[]{2, 1};
            f.sort(arr);
            System.out.println(Arrays.toString(arr));

            arr = new int[]{1, 3, 5, 4, 2};
            f.sort(arr);
            System.out.println(Arrays.toString(arr));

            reportTime(f, 1000, 10000);
        });
    }
}
