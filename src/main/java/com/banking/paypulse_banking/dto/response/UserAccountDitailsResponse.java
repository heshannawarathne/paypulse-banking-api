package com.banking.paypulse_banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAccountDitailsResponse {
    private String accountNumber;
    private BigDecimal balance;

}
