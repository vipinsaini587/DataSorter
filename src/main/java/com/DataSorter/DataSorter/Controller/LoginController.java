package com.DataSorter.DataSorter.Controller;

import com.DataSorter.DataSorter.Dto.LoginDto;
import com.DataSorter.DataSorter.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController{
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String indexLogin(@RequestBody LoginDto loginDto) {

        try {
            String result = loginService.login(loginDto);
            return result;
        } catch (NullPointerException e) {
            return "false";
        }
    }
}
