package com.DataSorter.DataSorter.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "multiple_number_files")
public class MultipleNumberFiles {
    private String case_number;
    private String case_fies;

    public MultipleNumberFiles(String case_number, String case_fies) {
        this.case_number = case_number;
        this.case_fies = case_fies;
    }
}
