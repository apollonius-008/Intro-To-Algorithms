package com.sarit.peak_problem;

import java.util.Arrays;

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

    public static void main(String[] args) {
        int[] arr = new int[1];
    }
}
