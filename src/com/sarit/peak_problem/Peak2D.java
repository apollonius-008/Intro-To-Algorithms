package com.sarit.peak_problem;

import com.sarit.test_utility.TestUtility;

public class Peak2D {

    public static int[] naive2DPeak(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                if (i - 1 >= 0 && arr[i - 1][j] > arr[i][j])
                    continue;
                if (i + 1 < arr.length && arr[i + 1][j] > arr[i][j])
                    continue;
                if (j - 1 >= 0 && arr[i][j - 1] > arr[i][j])
                    continue;
                if (j + 1 < arr[i].length && arr[i][j + 1] > arr[i][j])
                    continue;

                return new int[]{i, j};
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {

    }
}
