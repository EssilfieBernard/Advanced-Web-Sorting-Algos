package com.project.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Operation {
    private String id = UUID.randomUUID().toString();
    private String type;
    private List<Integer> data;
    private List<Integer> result;


}
