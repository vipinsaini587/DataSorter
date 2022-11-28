package com.DataSorter.DataSorter.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "multiple_number_csv")
@Data
public class MultipleNumberCsv {

    private String case_number;
    private String landLine;
    private String imsi;
    private String imei;
    private String sourceIP;
    private String source_port;
    private String destination_ip;
    private String destination_port;
    private String translated_ip;
    private String translated_port;
    private String first_cell_id;
    private String session_start;
    private String session_end;
    private String session_duration;
    private String pgwIpAddress;
    private String cgi_id;
    private String application;

    public MultipleNumberCsv(String case_number, String landLine, String imsi, String imei, String sourceIP, String source_port, String destination_ip, String destination_port, String translated_ip, String translated_port, String first_cell_id, String session_start, String session_end, String session_duration, String pgwIpAddress, String cgi_id, String application) {
        this.case_number = case_number;
        this.landLine = landLine;
        this.imsi = imsi;
        this.imei = imei;
        this.sourceIP = sourceIP;
        this.source_port = source_port;
        this.destination_ip = destination_ip;
        this.destination_port = destination_port;
        this.translated_ip = translated_ip;
        this.translated_port = translated_port;
        this.first_cell_id = first_cell_id;
        this.session_start = session_start;
        this.session_end = session_end;
        this.session_duration = session_duration;
        this.pgwIpAddress = pgwIpAddress;
        this.cgi_id = cgi_id;
        this.application = application;
    }
}

