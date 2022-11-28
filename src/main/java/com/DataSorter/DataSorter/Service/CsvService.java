package com.DataSorter.DataSorter.Service;

import com.DataSorter.DataSorter.Dto.DateDto;
import com.DataSorter.DataSorter.entities.Cases;
import com.DataSorter.DataSorter.entities.SingleNumberCsv;
import com.DataSorter.DataSorter.repository.CaseRepository;
import com.DataSorter.DataSorter.repository.SingleFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Service
public class CsvService {
    @Autowired
    private SingleFileRepository singleFileRepository;
    @Autowired
    private CaseRepository caseRepository;

}
