package com.project.algorithm;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static List<Integer> mergeSort(List<Integer> list) {
        // Base case: A list with 0 or 1 element is already sorted.
        if (list.size() <= 1) {
            return list;
        }

        // Split the list into two halves.
        int middle = list.size() / 2;
        List<Integer> left = list.subList(0, middle);
        List<Integer> right = list.subList(middle, list.size());

        // Recursively sort both halves.
        left = mergeSort(new ArrayList<>(left));
        right = mergeSort(new ArrayList<>(right));

        // Merge the sorted halves.
        return merge(left, right);
    }

    private static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> result = new ArrayList<>();
        int leftIndex = 0, rightIndex = 0;

        // Compare elements from both lists and add the smaller one to the result.
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex) <= right.get(rightIndex)) {
                result.add(left.get(leftIndex));
                leftIndex++;
            } else {
                result.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        // Add remaining elements from the left list.
        while (leftIndex < left.size()) {
            result.add(left.get(leftIndex));
            leftIndex++;
        }

        // Add remaining elements from the right list.
        while (rightIndex < right.size()) {
            result.add(right.get(rightIndex));
            rightIndex++;
        }

        return result;
    }
}
