package com.project.service;

import com.project.algorithm.BucketSort;
import com.project.algorithm.HeapSort;
import com.project.algorithm.MergeSort;
import com.project.algorithm.QuickSort;
import com.project.algorithm.RadixSort;
import com.project.exception.NullDataException;
import com.project.model.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class OperationService {
    @Autowired
    DataService dataService;

    public List<Operation> getAllOperations() {
        return dataService.getAllOperations();
    }

    public List<Integer> createOperation(List<Integer> data, String type) {
        Operation operation = new Operation();
        operation.setData(new ArrayList<>(data));
        List<Integer> copyData = new ArrayList<>(data);

        performOperationByType(type, operation, copyData);
        dataService.addOperation(operation);
        return operation.getResult();
    }

    private void performOperationByType(String type, Operation operation, List<Integer> data) {
        List<Integer> sortedData;
        switch (type) {
            case "QUICK_SORT":
                sortedData = QuickSort.quickSort(data);
                operation.setResult(sortedData);
                operation.setType("QUICK SORT");
                break;
            case "HEAP_SORT":
                sortedData = HeapSort.heapSort(data);
                operation.setResult(sortedData);
                operation.setType("HEAP SORT");
                break;
            case "MERGE_SORT":
                sortedData = MergeSort.mergeSort(data);
                operation.setResult(sortedData);
                operation.setType("MERGE SORT");
                break;
            case "BUCKET_SORT":
                sortedData = BucketSort.bucketSort(data);
                operation.setResult(sortedData);
                operation.setType("BUCKET SORT");
                break;
            case "RADIX_SORT":
                validateRadixSortData(data);
                sortedData = RadixSort.radixSort(data);
                operation.setResult(sortedData);
                operation.setType("RADIX SORT");
                break;
            default:
                throw new IllegalArgumentException("Unsupported sort type: " + type);
        }
    }

    public List<Integer> updateOperation(String id, Operation updatedOperation) {
        var operation = dataService.findById(id);
        if (operation == null)
            throw new NullDataException("Operation not found with id: " + id);

        if (updatedOperation.getData() == null || updatedOperation.getData().isEmpty())
            throw new NullDataException("Data to sort cannot be null or empty");

        operation.setData(updatedOperation.getData());
        String type = updatedOperation.getType();
        var sortedData = new ArrayList<>(updatedOperation.getData());

        performOperationByType(type, operation, sortedData);
        return operation.getResult();
    }

    public Operation deleteOperationById(String id) {
        Operation operation = dataService.removeById(id);
        if (operation == null)
            throw new NullDataException("Operation not found with id: " + id);
        return operation;
    }

    private void validateRadixSortData(List<Integer> data) {
        for (int number : data)
            if (number < 0)
                throw new IllegalArgumentException("Radix Sort only supports non-negative integers.");
    }

}