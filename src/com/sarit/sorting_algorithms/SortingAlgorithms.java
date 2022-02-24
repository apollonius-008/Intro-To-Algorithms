package com.sarit.sorting_algorithms;

public class SortingAlgorithms {

    public static int get_min_index(int[] arr, int s, int e) {
        int minIndex = -1;
        if (s <= e) {
            for (int i = s; i <= e; i++) {
                if (minIndex == -1 || arr[minIndex] > arr[i])
                    minIndex = i;
            }
        }
        return minIndex;
    }

    public static void selection_sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = get_min_index(arr, i, arr.length - 1);
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
