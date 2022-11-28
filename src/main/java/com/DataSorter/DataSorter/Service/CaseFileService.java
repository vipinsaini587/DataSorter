package com.DataSorter.DataSorter.Service;

import com.DataSorter.DataSorter.entities.MultipleNumberFiles;
import com.DataSorter.DataSorter.entities.SingleNumberFiles;
import com.DataSorter.DataSorter.entities.Cases;
import com.DataSorter.DataSorter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaseFileService {
    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private SingleNumberCsvRepository singleNumberCsvRepository;
    @Autowired
    private SingleFileRepository singleFileRepository;
    @Autowired
    private MultipleFileRepository multipleFileRepository;

//    single case files
    public List<SingleNumberFiles> getCaseFiles(String case_number){
        try {
            List<SingleNumberFiles> caseAllFiles = singleFileRepository.findBy(case_number);
            return caseAllFiles;
        }catch (NullPointerException nullPointerException){
            return new ArrayList<>();
        }
    }
//
    public String saveFileName(String case_number,String file_name){
        Cases cases = caseRepository.findByCaseNumber(case_number);
        if(case_number.equals(cases.getCase_number())){
        SingleNumberFiles case_file = new SingleNumberFiles(case_number,file_name);
        singleFileRepository.save(case_file);
        return "file name saved";}else {
            return "no such case";
        }
    }


//     multiple number case files

public List<MultipleNumberFiles> getMultipleNumberFiles(String case_number){
    try {
        List<MultipleNumberFiles> multipleCaseAllFiles = multipleFileRepository.findBy(case_number);
        return multipleCaseAllFiles;
    }catch (NullPointerException nullPointerException){
        return new ArrayList<>();
    }
}

    public String saveMultipleNumberFile(String case_number,String file_name){
        Cases cases = caseRepository.findByCaseNumber(case_number);
        if(case_number.equals(cases.getCase_number())){
        MultipleNumberFiles case_file = new MultipleNumberFiles(case_number,file_name);
        multipleFileRepository.save(case_file);
        return "file name saved";}else {
            return "no such case";
        }
    }

}
