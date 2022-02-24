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

    public static void insertion_sort(int[] arr) {
        for (int i = 1; i < arr.length; i++)
            insert_maintaining_order(arr, i, arr[i]);
    }

    public static void insert_maintaining_order(int[] arr, int i, int e) {
        int j;
        for (j = i - 1; j >= 0 && arr[j] >= e; j--) {
            arr[j + 1] = arr[j];
        }
        arr[j + 1] = e;
    }
}
