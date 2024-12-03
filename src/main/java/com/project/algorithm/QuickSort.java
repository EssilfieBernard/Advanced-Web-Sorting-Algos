package com.project.algorithm;

import java.util.List;

public class QuickSort {
    public static List<Integer> quickSort(List<Integer> list) {
        quickSortHelper(list, 0, list.size() - 1);
        return list;
    }

    private static void quickSortHelper(List<Integer> list, int low, int high) {
        if (low < high) {
            // Partition the list and get the pivot index.
            int pivotIndex = partition(list, low, high);

            // Recursively sort elements before and after the partition.
            quickSortHelper(list, low, pivotIndex - 1);
            quickSortHelper(list, pivotIndex + 1, high);
        }
    }

    private static int partition(List<Integer> list, int low, int high) {
        int pivot = list.get(high); // Choose the last element as the pivot.
        int i = low - 1; // Index of the smaller element.

        for (int j = low; j < high; j++) {
            // If the current element is smaller than or equal to the pivot.
            if (list.get(j) <= pivot) {
                i++;
                swap(list, i, j);
            }
        }

        // Swap the pivot element with the element at i+1.
        swap(list, i + 1, high);

        return i + 1;
    }

    private static void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}

