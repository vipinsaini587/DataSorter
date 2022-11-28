package com.DataSorter.DataSorter.Service;

import com.DataSorter.DataSorter.entities.Cases;
import com.DataSorter.DataSorter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CaseService {
    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private SingleFileRepository singleFileRepository;
    @Autowired
    private SingleNumberCsvRepository singleNumberCsvRepository;
    @Autowired
    private MultipleFileRepository multipleFileRepository;
    @Autowired
    private MultipleNumberCsvRepository multipleNumberCsvRepository;
    public  String addCase(Cases cases){
        caseRepository.save(cases);
        return"Case added successfully";
    }
    public List<Cases> getAll(){
       List<Cases> allCases = caseRepository.findAll();
        return allCases ;
    }
    public String searchByCaseNumber(String case_number){
        if(caseRepository.findByCaseNumber(case_number)!=null) {
            String casenumber = caseRepository.findByCaseNumber(case_number).getCase_number();
            return casenumber;
        }else {
            return "null";
        }
    }
    public String upDateCaseStatus(String case_number,String case_status){
        Cases cases = caseRepository.findByCaseNumber(case_number);
        cases.setCase_status(case_status);
        caseRepository.deleteByCaseNumber(case_number);
        caseRepository.save(cases);
        return"updated status";
    }
    @Transactional
    public  String deletecase(String case_number) {
        Cases cases = caseRepository.findByCaseNumber(case_number);
        if (caseRepository.findByCaseNumber(case_number).getCase_number()!= null) {
            singleNumberCsvRepository.deleteByCaseNumber(case_number);
            singleFileRepository.deleteByCaseNumber(case_number);
            multipleFileRepository.deleteByCaseNumber(case_number);
            multipleNumberCsvRepository.deleteByCaseNumber(case_number);
            caseRepository.deleteByCaseNumber(case_number);
            return "case deleted successfully";
        } else {
            return "case doesn't exist";
        }
    }
}
