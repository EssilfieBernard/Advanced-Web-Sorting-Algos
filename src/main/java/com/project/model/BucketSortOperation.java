package com.project.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BucketSortOperation {
    private int id;
    private String type = "BUCKET SORT";
    private List<Double> data;
    private List<Double> result;
}
