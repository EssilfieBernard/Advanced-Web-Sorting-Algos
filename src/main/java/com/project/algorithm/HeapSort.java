package com.project.algorithm;

import java.util.List;

public class HeapSort {
    public static List<Integer> heapSort(List<Integer> list) {
        int n = list.size();

        // Build the heap (rearrange the array into a max-heap).
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i);
        }

        // Extract elements from the heap one by one.
        for (int i = n - 1; i > 0; i--) {
            // Swap the root (largest element) with the last element.
            swap(list, 0, i);

            // Heapify the reduced heap.
            heapify(list, i, 0);
        }

        return list;
    }

    private static void heapify(List<Integer> list, int n, int i) {
        int largest = i; // Initialize the largest as root.
        int left = 2 * i + 1; // Left child index.
        int right = 2 * i + 2; // Right child index.

        // If the left child is larger than the root.
        if (left < n && list.get(left) > list.get(largest)) {
            largest = left;
        }

        // If the right child is larger than the largest so far.
        if (right < n && list.get(right) > list.get(largest)) {
            largest = right;
        }

        // If the largest is not the root.
        if (largest != i) {
            swap(list, i, largest);

            // Recursively heapify the affected sub-tree.
            heapify(list, n, largest);
        }
    }

    private static void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}

