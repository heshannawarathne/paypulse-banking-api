package com.banking.paypulse_banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountLookupResponseDto {

    private String accountNumber;
    private String fullName;

}
