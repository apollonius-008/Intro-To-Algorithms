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

    private static void test_sorting(Random r, int size, SortingFunction f) {
        int[] array = TestUtility.generateRandomArray(size, r);
        int[] cpy = Arrays.copyOf(array, size);
        f.sort(array);
        Arrays.sort(cpy);
        assert Arrays.equals(array, cpy);
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

            reportTime(f, 1000, 10000);
        });
    }
}
