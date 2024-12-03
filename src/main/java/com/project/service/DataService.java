package com.project.service;

import com.project.model.Operation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {
    private List<Operation> dataList = new ArrayList<>();

    public List<Operation> getAllOperations() {
        return new ArrayList<>(dataList);
    }

    public Operation addOperation(Operation operation) {
        dataList.add(operation);
        return operation;
    }

    public Object getOperationById(int index) {
        return index < dataList.size() ? dataList.get(index) : null;
    }

    public void deleteOperation(int index) {
        if (index < dataList.size()) {
            dataList.remove(index);
        }
    }

    public Operation findById(String id) {
        for (Operation operation : dataList)
            if (operation.getId().equals(id))
                return operation;
        return null;
    }

    public Operation removeById(String id) {
        for (Operation operation : dataList)
            if (operation.getId().equals(id)) {
                dataList.remove(operation);
                return operation;
            }
        return null;
    }

    public Object updateOperation(int index, Operation newItem) {
        if (index < dataList.size()) {
            dataList.set(index, newItem);
            return newItem;
        }
        return null;
    }
}
