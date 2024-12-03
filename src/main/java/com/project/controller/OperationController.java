package com.project.controller;

import com.project.model.Operation;
import com.project.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/operations")
public class OperationController {
    @Autowired
    private OperationService operationService;


    @GetMapping
    public ResponseEntity<List<Operation>> getAllOperations() {
        return ResponseEntity.ok(operationService.getAllOperations());
    }

    @PostMapping(value = "/sort", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sort(@RequestParam("stringData") String stringData, @RequestParam("type") String type) {
        try {
            List<Integer> data = Arrays.stream(stringData.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
            List<Integer> operation = operationService.createOperation(data, type);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(operation);
        }catch (Exception e) {
            System.out.println("Error processing data: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<List<Integer>> updateOperationById(@PathVariable String id, @RequestBody Operation operation) {
        try {
            return ResponseEntity.ok(operationService.updateOperation(id, operation));
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @DeleteMapping("{id}")
    public Operation deleteOperationById(@PathVariable String id) {
        return operationService.deleteOperationById(id);
    }


    @GetMapping("greet")
    public String greet() {
        return "Hello World";
    }
}
