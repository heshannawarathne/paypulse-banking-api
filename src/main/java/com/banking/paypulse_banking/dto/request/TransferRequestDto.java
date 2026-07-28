package com.banking.paypulse_banking.dto.request;

import com.banking.paypulse_banking.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferRequestDto {

    private String sourceAccount;
    private String destinationAccount;
    private BigDecimal amount;
    private String description;

}
