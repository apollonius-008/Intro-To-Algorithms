package com.sarit.test_utility;

import java.util.Scanner;
import java.util.function.Function;

public class TestUtility {

    public static int[] generateIntArrayAndFill(int size, int fillValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = fillValue;
        return arr;
    }

    public static int[] generateIntArrayInRange(int size, int start, int step) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = start + i * step;
        }

        return arr;
    }

    public static void haltProgram() {
        System.out.println("Press enter to continue ...");
        new Scanner(System.in).nextLine();
    }

    public static long elapsedTime(AnyFunction function) {
        long start = System.nanoTime();
        function.run();
        long end = System.nanoTime();
        return end - start;
    }
}
