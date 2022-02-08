package com.sarit.peak_problem;

public class Peak2D {

    public static Point2D naive2DPeak(int[][] arr) {
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

                return new Point2D(i, j);
            }
        }

        return null;
    }
}
