package com.banking.paypulse_banking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersUpdateDto {
    private String nic;
    private String email;
    private String password;
    private String mobileNumber;

}
