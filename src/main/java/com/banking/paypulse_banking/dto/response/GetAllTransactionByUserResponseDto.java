package com.banking.paypulse_banking.dto.response;

import com.banking.paypulse_banking.entity.Account;
import com.banking.paypulse_banking.entity.enums.TransactionStatus;
import com.banking.paypulse_banking.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllTransactionByUserResponseDto {

    private String transactionReference;
    private String sourceAccount;
    private String destinationAccount;
    private BigDecimal amount;
    private TransactionType type;
    private TransactionStatus status;
    private String description;
    private LocalDateTime timestamp;
}
