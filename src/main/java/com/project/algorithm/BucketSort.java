package com.project.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    public static List<Integer> bucketSort(List<Integer> list) {
        if (list == null || list.size() < 2) {
            return list; // If the list has fewer than 2 elements, it is already sorted
        }

        int max = list.stream().max(Integer::compare).orElse(Integer.MAX_VALUE);
        int min = list.stream().min(Integer::compare).orElse(Integer.MIN_VALUE);

        // Create buckets
        int bucketCount = (max - min) / list.size() + 1;  // Calculate bucket count
        List<List<Integer>> buckets = new ArrayList<>();

        // Initialize empty buckets
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // Distribute the numbers into the buckets
        for (int num : list) {
            int bucketIndex = (num - min) / list.size();  // Bucket index is based on the range of values
            buckets.get(bucketIndex).add(num);
        }

        // Sort individual buckets and concatenate the result
        List<Integer> sortedList = new ArrayList<>();
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);  // You can use a sorting algorithm like quicksort or merge sort here
            sortedList.addAll(bucket);  // Add the sorted bucket elements to the result
        }

        return sortedList;
    }
}

