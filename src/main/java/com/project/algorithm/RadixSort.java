package com.project.algorithm;

import java.util.Arrays;
import java.util.List;

public class RadixSort {
    public static List<Integer> radixSort(List<Integer> list) {
        if (list == null || list.size() < 2) {
            return list;
        }

        int max = list.stream().max(Integer::compare).orElse(Integer.MAX_VALUE);

        for (int place = 1; max / place > 0; place *= 10) {
            list = countingSort(list, place);
        }
        return list;
    }

    private static List<Integer> countingSort(List<Integer> list, int place) {
        Integer[] output = new Integer[list.size()];
        int[] count = new int[10];

        // Store count of occurrences
        for (int num : list) {
            int digit = (num / place) % 10;
            count[digit]++;
        }

        // Calculate actual positions
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = list.size() - 1; i >= 0; i--) {
            int num = list.get(i);
            int digit = (num / place) % 10;
            output[count[digit] - 1] = num;
            count[digit]--;
        }

        return Arrays.asList(output);
    }
}
