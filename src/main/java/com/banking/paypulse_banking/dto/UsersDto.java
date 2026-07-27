package com.banking.paypulse_banking.dto;

import com.banking.paypulse_banking.entity.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersDto {


    private String fullName;

    private String email;

    private String password;

    private String mobileNumber;

    private String nic;

//    private UserType role;


}
