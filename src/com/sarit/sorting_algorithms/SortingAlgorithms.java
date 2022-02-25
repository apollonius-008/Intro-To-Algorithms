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

    public static void mergeSort(int[] arr, int s, int e) {
        if (s < e) {
            int mid = (s + e) / 2;
            mergeSort(arr, s, mid);
            mergeSort(arr, mid + 1, e);
            merge(arr, s, mid, e);
        }
    }

    public static void merge(int[] arr, int p, int q, int r) {
        int[] left = new int[q - p + 2];
        int[] right = new int[r - q + 1];

        for (int i = 0; i < left.length - 1; i++) {
            left[i] = arr[i + p];
        }
        left[left.length - 1] = Integer.MAX_VALUE;

        for (int j = 0; j < right.length - 1; j++) {
            right[j] = arr[j + q + 1];
        }
        right[right.length - 1] = Integer.MAX_VALUE;

        int i = 0, j = 0;
        for (int k = p; k <= r; k++) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i += 1;
            }
            else {
                arr[k] = right[j];
                j += 1;
            }
        }
    }
}
