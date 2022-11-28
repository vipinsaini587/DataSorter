package com.DataSorter.DataSorter.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "single_number_files")
@Data
public class SingleNumberFiles {
    private String case_number;
    private String file_name;

    public SingleNumberFiles(String case_number, String file_name) {
        this.case_number = case_number;
        this.file_name = file_name;
    }
}
