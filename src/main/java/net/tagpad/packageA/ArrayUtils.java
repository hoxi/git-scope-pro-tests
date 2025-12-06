package net.tagpad.packageA;

import java.util.Arrays;

public class ArrayUtils {
    public static int[] reverse(int[] array) {
        if (array == null) {
            return null;
        }
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[array.length - 1 - i];
        }
        return result;
    }

    public static int findMax(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        return Arrays.stream(array).max().getAsInt();
    }

    public static int findMin(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        return Arrays.stream(array).min().getAsInt();
    }

    public static double average(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        return Arrays.stream(array).average().getAsDouble();
    }
}
