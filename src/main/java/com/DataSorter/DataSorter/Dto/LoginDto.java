package com.DataSorter.DataSorter.Dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LoginDto {
    private String username;
    private String password;
}
