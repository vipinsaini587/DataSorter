package com.DataSorter.DataSorter.Controller;

import com.DataSorter.DataSorter.Service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@Controller
public class ViewController {

    @Autowired
    private CsvService csvFilterService;

    @GetMapping("/findLocation")
    public String findLocation(){
        return "FindLocation";
    }

    @GetMapping("/")
    public String read(){
        return "CsvRead";
    }

    @GetMapping("/results")
    public String result(){
        return "Results";
    }

    @GetMapping("/sort_results")
    public String sortResult(){
        return "Sort-Results";
    }

    @GetMapping("/ip")
    public String ip(){
        return "FindIP";
    }

    @GetMapping("/sort")
    public String sort(){
        return "Sort";
    }


}
