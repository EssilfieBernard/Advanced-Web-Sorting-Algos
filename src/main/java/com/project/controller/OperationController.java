package com.project.controller;

import com.project.exception.InvalidInputException;
import com.project.model.Operation;
import com.project.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
        List<Integer> data;
        try {
            data = Arrays.stream(stringData.split(","))
                    .map(String::trim)
                    .map(s -> {
                        try {
                            return Integer.parseInt(s);
                        } catch (NumberFormatException e) {
                            throw new InvalidInputException("Invalid input: '" + s + "' is not a valid integer.");
                        }
                    })
                    .toList();
        } catch (InvalidInputException e) {
            throw e; // Custom exception to be handled globally
        }

        List<Integer> operation = operationService.createOperation(data, type);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(operation);
    }

    @PutMapping("{id}")
    public ResponseEntity<List<Integer>> updateOperationById(@PathVariable String id, @RequestBody Operation operation) {
        return ResponseEntity.ok(operationService.updateOperation(id, operation));
    }



    @DeleteMapping("{id}")
    public Operation deleteOperationById(@PathVariable String id) {
        return operationService.deleteOperationById(id);
    }


    @GetMapping("/hateoas/getAllOperations")
    public ResponseEntity<CollectionModel<EntityModel<Operation>>> getOperations() {
        List<Operation> operations = operationService.getAllOperations();
        List<EntityModel<Operation>> operationModels = operations.stream()
                .map(operation -> EntityModel.of(operation,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OperationController.class).getOperations()).withSelfRel()))
                .toList();

        CollectionModel<EntityModel<Operation>> collectionModel = CollectionModel.of(operationModels,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OperationController.class).getOperations()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @PostMapping(value = "/hateoas/sort", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<List<Integer>>> sortData(@RequestParam("stringData") String stringData, @RequestParam("type") String type) {
        List<Integer> data;
        try {
            data = Arrays.stream(stringData.split(","))
                    .map(String::trim)
                    .map(s -> {
                        try {
                            return Integer.parseInt(s);
                        } catch (NumberFormatException e) {
                            throw new InvalidInputException("Invalid input: '" + s + "' is not a valid integer.");
                        }
                    })
                    .toList();
        } catch (InvalidInputException e) {
            throw e; // Custom exception to be handled globally
        }

        List<Integer> sortedData = operationService.createOperation(data, type);
        EntityModel<List<Integer>> resource = EntityModel.of(sortedData);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OperationController.class).sortData(stringData, type)).withSelfRel();
        resource.add(selfLink);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(resource);
    }

    @PutMapping("/hateoas/updateOperation/{id}")
    public ResponseEntity<EntityModel<List<Integer>>> updateOperation(@PathVariable String id, @RequestBody Operation operation) {
        List<Integer> updatedOperation = operationService.updateOperation(id, operation);
        EntityModel<List<Integer>> resource = EntityModel.of(updatedOperation);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OperationController.class).updateOperation(id, operation)).withSelfRel();
        Link allOperationsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OperationController.class).getOperations()).withRel("all-operations");

        resource.add(selfLink);
        resource.add(allOperationsLink);

        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/hateoas/deleteOperation/{id}")
    public ResponseEntity<EntityModel<Operation>> deleteOperation(@PathVariable String id) {
        Operation deletedOperation = operationService.deleteOperationById(id);
        EntityModel<Operation> resource = EntityModel.of(deletedOperation);

        Link allOperationsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OperationController.class).getOperations()).withRel("all-operations");

        resource.add(allOperationsLink);

        return ResponseEntity.ok(resource);
    }


}
