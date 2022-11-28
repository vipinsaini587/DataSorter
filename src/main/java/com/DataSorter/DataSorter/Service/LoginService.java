package com.DataSorter.DataSorter.Service;

import com.DataSorter.DataSorter.Dto.LoginDto;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public String login(LoginDto loginDto)throws NullPointerException{
        if (loginDto == null) {
            return "false";
        } else {
            String username = loginDto.getUsername();
            String password = loginDto.getPassword();
            if (username.equals("admin") && password.equals("admin")) {
                return "true";
            } else {
                return "false";
            }
        }
    }
}