package com.DataSorter.DataSorter.entities;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "single_number_csv")
@Data
public class SingleNumberCsv {

    private String case_number;
    private String landLine;
    private String sourceIP;
    private String port;
    private String startDateIpAllocation;
    private String startTimeIpAllocation;
    private String endDateIpAllocation;
    private String endTimeIpAllocation;
    private String ipType;
    private String userIdAuthentication;
    private String deviceIdNumber;
    private String imsi;
    private String pgwIpAddress;
    private String cgiId;
    private String circle;
    private String application;
    public SingleNumberCsv(String case_number, String landLine, String sourceIP, String port, String startDateIpAllocation, String startTimeIpAllocation, String endDateIpAllocation, String endTimeIpAllocation, String ipType, String userIdAuthentication, String deviceIdNumber, String imsi, String pgwIpAddress, String cgiId, String circle, String application) {
        this.case_number = case_number;
        this.landLine = landLine;
        this.sourceIP = sourceIP;
        this.port = port;
        this.startDateIpAllocation = startDateIpAllocation;
        this.startTimeIpAllocation = startTimeIpAllocation;
        this.endDateIpAllocation = endDateIpAllocation;
        this.endTimeIpAllocation = endTimeIpAllocation;
        this.ipType = ipType;
        this.userIdAuthentication = userIdAuthentication;
        this.deviceIdNumber = deviceIdNumber;
        this.imsi = imsi;
        this.pgwIpAddress = pgwIpAddress;
        this.cgiId = cgiId;
        this.circle = circle;
        this.application = application;
    }

    public SingleNumberCsv() {
    }
}