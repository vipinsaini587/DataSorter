package com.DataSorter.DataSorter.Service;

import com.DataSorter.DataSorter.Dto.DateDto;
import com.DataSorter.DataSorter.entities.Cases;
import com.DataSorter.DataSorter.entities.SingleNumberCsv;
import com.DataSorter.DataSorter.repository.SingleNumberCsvRepository;
import com.DataSorter.DataSorter.repository.CaseRepository;
import com.DataSorter.DataSorter.repository.SingleFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SingleNumberCsvService {
    @Autowired
    private SingleFileRepository singleFileRepository;
    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private SingleNumberCsvRepository singleNumberCsvRepository;
    String line = "";

//    reading single number ipdr csv file
    public String readCsv(String case_number, MultipartFile file){

        try {
            Cases cases = caseRepository.findByCaseNumber(case_number);
            if(case_number.equals(cases.getCase_number())){
            InputStream fileToBeRead = new BufferedInputStream(file.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileToBeRead, StandardCharsets.UTF_8));
            for (int i = 0; i <= 13; i++) {
                reader.readLine();
                String line = null;
            }
            while ((line = reader.readLine()) != null) {
                String[] csvData = line.split(",");
                if (csvData.length == 0 || csvData.length < 2) {
                    continue;
                }
                 String application = null;
                if (csvData[2].equals("1720")){
                    application = "Signal";
                } else if (csvData[2].equals("5222")) {
                    application = "Whatsapp";
                }else if (csvData[2].equals("8443")){
                    application = "Telegram";
                }if (csvData[2].equals("9")){
                    application = "Discord";
                } else {
//                    application = null;
                }

                //DateFormat formatter2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");


                DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                Date start = inputFormatter.parse(csvData[7]);
                Date end = inputFormatter.parse(csvData[9]);
                DateFormat formatters = new SimpleDateFormat("dd/MM/yyyy");
                String startDate = formatters.format(start);
                String endDate = formatters.format(end);


//                Date date2 = inputFormatter.parse(csvData[9]);
//                DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
//                String date3 = outputFormatter.format(date1);
//                System.out.println(date3);
//                String date4 = outputFormatter.format(date2);
//                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//                Date startDate = formatter.parse(date3);
//                System.out.println(startDate);
//                Date endDate = formatter.parse(date4);



                SingleNumberCsv singleNumberCsv1 = new SingleNumberCsv(case_number,csvData[0], csvData[1], csvData[2], startDate, csvData[8], endDate, csvData[10], csvData[11], csvData[12], csvData[13], csvData[14], csvData[15], csvData[17],csvData[20],application);
                singleNumberCsvRepository.save(singleNumberCsv1);
                System.out.println(singleNumberCsv1);
                }
            }else{
                return "No such case";
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException nullPointerException) {
            return "No such case found";
        }
        return "success";
    }

    public List<SingleNumberCsv> singleCaseAllData(String case_number){
        try { Cases cases = caseRepository.findByCaseNumber(case_number);
            List<SingleNumberCsv> singleNumberCsvData = singleNumberCsvRepository.singleCaseData(case_number);
            return singleNumberCsvData;
        } catch (NullPointerException nullPointerException) {
            return new ArrayList<>();
        }
    }

        public List<SingleNumberCsv> dataBySourceIP(String case_number, String sourceIP){
        try{
        List<SingleNumberCsv> singleNumberCsvSourceIP = singleNumberCsvRepository.findBySourceIP(case_number,sourceIP);
        return singleNumberCsvSourceIP;
        }catch (NullPointerException nullPointerException){
            return new ArrayList<>();
        }
    }

        public List<SingleNumberCsv> filterByDate(DateDto dateDto, String case_number) throws ParseException {
        try{
            DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy");
            Date start = inputFormatter.parse(dateDto.getStartDate1());
            Date end = inputFormatter.parse(dateDto.getStartDate2());
            DateFormat formatters = new SimpleDateFormat("dd/MM/yyyy");
            String startDate = formatters.format(start);
            String endDate = formatters.format(end);
        List<SingleNumberCsv> dateFilter = singleNumberCsvRepository.startDateIpAllocation(startDate,endDate,case_number);
        return dateFilter;}catch (NullPointerException nullPointerException){
            return new ArrayList<>();
        }
    }
}

