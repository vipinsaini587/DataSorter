package com.DataSorter.DataSorter.entities;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "cases")
@Data
public class Cases {
    private String case_number;
    private String case_status;
    private String investigating_officer_name;
    private String date;
}