package com.banking.paypulse_banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DashboardSummaryResponseDto {

    private String accountNumber;
    private BigDecimal currentBalance;
    private BigDecimal totalIncome;  // මේ මාසේ හෝ overall ආපු සල්ලි ගණන
    private BigDecimal totalOutcome; // මේ මාසේ හෝ overall පිටවුණු සල්ලි ගණන
    private List<GetAllTransactionByUserResponseDto> recentTransactions;
}
