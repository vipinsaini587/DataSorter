package com.DataSorter.DataSorter.Dto;

import lombok.Data;

@Data
public class ImsiDto {
    private String mobileCountryCode;
    private String mobileNetworkCode;
    private String locationAreaCode;
    private String cellId;
}
