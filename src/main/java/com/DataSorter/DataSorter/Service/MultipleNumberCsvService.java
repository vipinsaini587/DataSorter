package com.DataSorter.DataSorter.Service;

import com.DataSorter.DataSorter.Dto.DateDto;
import com.DataSorter.DataSorter.entities.Cases;
import com.DataSorter.DataSorter.entities.MultipleNumberCsv;
import com.DataSorter.DataSorter.repository.CaseRepository;
import com.DataSorter.DataSorter.repository.MultipleNumberCsvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MultipleNumberCsvService {
    @Autowired
    private MultipleNumberCsvRepository multipleNumberCsvRepository;
    @Autowired
    private CaseRepository caseRepository;
    String line = "";

    //    reading multipart csv file
    public String readMultipleNumberCsv(String case_number, MultipartFile file){
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
                String application = csvData[8];
                String application_name = null;
//                System.out.println(application);
                if (application.equals("1720")){
                    application_name = "Signal";
                } else if (application.equals("5222")) {
                    application_name = "Whatsapp";
                }else if (application.equals("8443")){
                    application_name = "Telegram";
                }if (application.equals("9")){
                    application_name = "Discord";
                } else {
//                    System.out.println("was here");
                }
//                if (csvData[8].equals("1720")||csvData[8].equals("5222")||csvData[8].equals("8443")||csvData[8].equals("9")){

//                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//                Date startDate = formatter.parse(csvData[12]);
//                Date endDate = formatter.parse(csvData[13]);



                DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                Date start = inputFormatter.parse(csvData[12]);
                Date end = inputFormatter.parse(csvData[13]);
                DateFormat formatters = new SimpleDateFormat("dd/MM/yyyy");
                String startDate = formatters.format(start);
                String endDate = formatters.format(end);
                MultipleNumberCsv csvData1 = new MultipleNumberCsv(case_number,csvData[3], csvData[1], csvData[2], csvData[5], csvData[6], csvData[7], csvData[8], csvData[9], csvData[10], csvData[11], startDate,endDate, csvData[14], csvData[17], csvData[22],application_name);
                multipleNumberCsvRepository.save(csvData1);
                System.out.println(csvData1);
//                }else {
//                    continue;
//                }
            }
            System.out.println("file uploaded");}else {
                return "no such case";
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (NullPointerException nullPointerException) {
            return "no such case found";
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }

    public List<MultipleNumberCsv> caseAllData(String case_number){
        List<MultipleNumberCsv>  case_all_data = multipleNumberCsvRepository.findByCaseNumber(case_number);
        return case_all_data;
    }

    public List<MultipleNumberCsv> findByLandline(String case_number, String landLine){
        List<MultipleNumberCsv>  case_by_landline = multipleNumberCsvRepository.findByLandline(case_number,landLine);
        return case_by_landline;
    }

    public List<MultipleNumberCsv> filterByDate(DateDto dateDto, String case_number) throws ParseException {
        try{
            DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy");
            Date start = inputFormatter.parse(dateDto.getStartDate1());
            Date end = inputFormatter.parse(dateDto.getStartDate2());
            DateFormat formatters = new SimpleDateFormat("dd/MM/yyyy");
            String startDate = formatters.format(start);
            String endDate = formatters.format(end);
            List<MultipleNumberCsv> dateFilter = multipleNumberCsvRepository.sessionStart(startDate,endDate,case_number);
            return dateFilter;
        }catch (NullPointerException nullPointerException){
            return new ArrayList<>();
        }
    }
}
