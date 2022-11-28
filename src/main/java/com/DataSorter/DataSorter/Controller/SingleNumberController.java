package com.DataSorter.DataSorter.Controller;

import com.DataSorter.DataSorter.Dto.DateDto;
import com.DataSorter.DataSorter.Dto.ImsiDto;
import com.DataSorter.DataSorter.Service.CaseFileService;
import com.DataSorter.DataSorter.Service.SingleNumberCsvService;
import com.DataSorter.DataSorter.Service.CsvService;
import com.DataSorter.DataSorter.Service.ImsiLocationService;
import com.DataSorter.DataSorter.entities.SingleNumberCsv;
import com.DataSorter.DataSorter.entities.SingleNumberFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/single")
public class SingleNumberController {
    @Autowired
    private SingleNumberCsvService singleNumberCsvService;
    @Autowired
    private CsvService csvFilterService;
    @Autowired
    private ImsiLocationService imsiLocationService;
    @Autowired
    private CaseFileService caseFileService;

    @PostMapping(value ="/csvupload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> csvUpload(@RequestParam("csvfile") MultipartFile file,@RequestHeader("case_number") String case_number) {
        System.out.println("request to upload single ipdr file");
        if (file.isEmpty()) {
            ResponseEntity emptyResponse = new ResponseEntity("please select a file!", HttpStatus.OK);
            System.out.println("file empty");
            return emptyResponse;
        } else if (case_number == null) {
            ResponseEntity caseEmpty = new ResponseEntity("please select a case name!", HttpStatus.OK);
            System.out.println("case number empty");
            return caseEmpty;
        } else {
           try{ singleNumberCsvService.readCsv(case_number, file);
            ResponseEntity response = new ResponseEntity("Successfully uploaded - " + file.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
            String file_name= file.getOriginalFilename();
            caseFileService.saveFileName(case_number,file_name);
            System.out.println("csv file " + file_name+ " uploaded");
            return response;
           }catch (NullPointerException nullPointerException){
               return new ResponseEntity("no such case found",HttpStatus.OK);
           }
        }
    }

    @GetMapping("/getcasefiles")
    public ResponseEntity<?> getCaseFiles(@RequestHeader("case_number")String case_number) {
        System.out.println("request received for case files list");
        caseFileService.getCaseFiles(case_number);
        System.out.println("case files returned");
        return new ResponseEntity<List<SingleNumberFiles>>(caseFileService.getCaseFiles(case_number), HttpStatus.OK);
    }

    @PostMapping("/getlocation")
    public ResponseEntity findLocation(@RequestHeader("mobileCountryCode") String mobileCountryCode,
                                       @RequestHeader("mobileNetworkCode") String mobileNetworkCode,
                                       @RequestHeader("locationAreaCode") String locationAreaCode,
                                       @RequestHeader("cellId") String cellId) {
        System.out.println("request for imsi location");
        ImsiDto obj = new ImsiDto();
        obj.setMobileCountryCode(mobileCountryCode);
        obj.setMobileNetworkCode(mobileNetworkCode);
        obj.setLocationAreaCode(locationAreaCode);
        obj.setCellId(cellId);
//        System.out.println(obj);
        ResponseEntity location = null;
        try {
            System.out.println(obj);
            location = imsiLocationService.findLocationMCC(obj);
            System.out.println(location);
        } catch (Exception e) {
            System.out.println("nothing");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Match Found !");
        }
        return location;
    }

    @GetMapping("/getcasedata")
    public ResponseEntity<?> getCaseData(@RequestHeader String case_number) {
        System.out.println("Request to get single ipdr data");
        List<SingleNumberCsv> single_case_data = singleNumberCsvService.singleCaseAllData(case_number);
        return new ResponseEntity<List<SingleNumberCsv>>(singleNumberCsvService.singleCaseAllData(case_number),HttpStatus.OK);
    }

    @GetMapping("/sourceip")
    public ResponseEntity<?> sourceIP(@RequestHeader String case_number,@RequestHeader String sourceIP) {
        System.out.println("Request to sort based on source ip");
        return new ResponseEntity<List<SingleNumberCsv>>(singleNumberCsvService.dataBySourceIP(case_number,sourceIP),HttpStatus.OK);
    }
    @PostMapping("/datefilter")
    public ResponseEntity<?> startDate(@RequestBody DateDto dateDto, @RequestHeader String case_number) throws ParseException {
        return new ResponseEntity<List<SingleNumberCsv>>(singleNumberCsvService.filterByDate(dateDto,case_number),HttpStatus.OK);
    }
}