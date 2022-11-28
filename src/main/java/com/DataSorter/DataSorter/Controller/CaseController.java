package com.DataSorter.DataSorter.Controller;

import com.DataSorter.DataSorter.Service.CaseFileService;
import com.DataSorter.DataSorter.Service.CaseService;
import com.DataSorter.DataSorter.entities.SingleNumberFiles;
import com.DataSorter.DataSorter.entities.Cases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/case")
public class CaseController {
    @Autowired
    private CaseService caseService;
    @Autowired
    private CaseFileService caseFileService;

    @PostMapping("/addcase")
    public ResponseEntity<?> caseAdd(@RequestBody Cases cases) {
        System.out.println("Request received to add case "+ cases.getCase_number());
        if (cases.getCase_number().equals(caseService.searchByCaseNumber(cases.getCase_number()))) {
            System.out.println("case "+cases.getCase_number()+" already exist");
            ResponseEntity caseExist = new ResponseEntity("Case Already Exist",HttpStatus.ALREADY_REPORTED);
            return caseExist;
        } else {
            caseService.addCase(cases);
            ResponseEntity caseAdded = new ResponseEntity("Case Added Successfully",HttpStatus.OK);
            System.out.println("case added");
            return caseAdded ;
        }
    }

    @GetMapping("/allcases")
    public ResponseEntity<?> getAllCases() {
        System.out.println("request received for all cases");
        caseService.getAll();
        System.out.println("all cases returned");
        return new ResponseEntity<List<Cases>>(caseService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/deletecase")
    public String deleteCase(@RequestHeader("case_number") String case_number){
        System.out.println("request received to delete case");
        try {
            if (caseService.searchByCaseNumber(case_number).equals(case_number)) {
                String deletion_result = caseService.deletecase(case_number);
                System.out.println(case_number + " deleted");
                return deletion_result;
            } else {
                return "no case found";
            }
        } catch (NullPointerException nullException) {
            return "no such case";
        }
}
    @PostMapping("/casestatus")
    public String caseStatus(@RequestHeader("case_number")String case_number,@RequestHeader("case_status")String case_status){
        System.out.println("request received to update "+case_number+"  ,"+case_status);
        caseService.upDateCaseStatus(case_number,case_status);
        return "case status updated successfully";
    }

  }
