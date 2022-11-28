package com.DataSorter.DataSorter.Controller;

import com.DataSorter.DataSorter.Dto.DateDto;
import com.DataSorter.DataSorter.Service.CaseFileService;
import com.DataSorter.DataSorter.Service.MultipleNumberCsvService;
import com.DataSorter.DataSorter.entities.MultipleNumberCsv;
import com.DataSorter.DataSorter.entities.MultipleNumberFiles;
import com.DataSorter.DataSorter.entities.SingleNumberCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/multiple")
public class MultipleNumberController {
    @Autowired
    private MultipleNumberCsvService multipleNumberCsvService;
    @Autowired
    private CaseFileService caseFileService;

    @PostMapping("/csvupload")
    public ResponseEntity<?> readMultipleCsv(@RequestHeader("case_number") String case_number,@RequestParam("csvfile") MultipartFile file) {
        System.out.println("request to upload multiple ipdr");
        if (file.isEmpty()) {
            ResponseEntity emptyResponse = new ResponseEntity("please select a file!", HttpStatus.OK);
            System.out.println("file empty");
            return emptyResponse;
        } else if (case_number == null) {
            ResponseEntity caseEmpty = new ResponseEntity("please select a case name!", HttpStatus.OK);
            System.out.println("case number empty");
            return caseEmpty;
        } else {
            try {
                multipleNumberCsvService.readMultipleNumberCsv(case_number, file);
                ResponseEntity response = new ResponseEntity("Successfully uploaded - " + file.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
                String file_name = file.getOriginalFilename();
                caseFileService.saveMultipleNumberFile(case_number, file_name);
                return response;
            } catch (NullPointerException nullPointerException) {
                return new ResponseEntity("no such case found", HttpStatus.OK);
            }
        }
    }

    @GetMapping("/getcasefiles")
    public ResponseEntity<?> getCaseFiles(@RequestHeader("case_number")String case_number) {
        System.out.println("request received for case files list");
        caseFileService.getMultipleNumberFiles(case_number);
        System.out.println("case files returned");
        return new ResponseEntity<List<MultipleNumberFiles>>(caseFileService.getMultipleNumberFiles(case_number), HttpStatus.OK);
    }

    @GetMapping("/getcasedata")
    public ResponseEntity<?> getCaseData(@RequestHeader String case_number) {
        System.out.println("Request for multiple ipdr data for case");
       List<MultipleNumberCsv> multi_case_data = multipleNumberCsvService.caseAllData(case_number);
        System.out.println("completed");
        return new ResponseEntity<List<MultipleNumberCsv>>(multipleNumberCsvService.caseAllData(case_number),HttpStatus.OK);
    }

    @GetMapping("/getbymsidn")
    public ResponseEntity<?> getbymsidn(@RequestHeader String case_number,@RequestHeader String landLine) {
        System.out.println("Request for msidn sorting");
        List<MultipleNumberCsv> find_by_msidn = multipleNumberCsvService.findByLandline(case_number,landLine);
        System.out.println("completed");
        return new ResponseEntity<List<MultipleNumberCsv>>(multipleNumberCsvService.findByLandline(case_number,landLine),HttpStatus.OK);
    }

    @PostMapping("/datefilter")
    public ResponseEntity<?> startDate(@RequestBody DateDto dateDto, @RequestHeader String case_number) throws ParseException {
        return new ResponseEntity<List<MultipleNumberCsv>>(multipleNumberCsvService.filterByDate(dateDto,case_number),HttpStatus.OK);
    }
}
