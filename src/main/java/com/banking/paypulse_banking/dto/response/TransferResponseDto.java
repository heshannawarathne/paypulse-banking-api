package com.banking.paypulse_banking.dto.response;

import com.banking.paypulse_banking.entity.Account;
import com.banking.paypulse_banking.entity.enums.TransactionStatus;
import com.banking.paypulse_banking.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferResponseDto {
    private String transactionReference;
    private Account sourceAccount;
    private Account destinationAccount;
    private BigDecimal amount;
    private TransactionStatus status;
    private String description;
    private LocalDateTime timestamp;

}
