package com.banking.paypulse_banking.service;

import com.banking.paypulse_banking.dto.paginated.PaginatedResponseTransaction;
import com.banking.paypulse_banking.dto.request.TransferRequestDto;
import com.banking.paypulse_banking.dto.response.DashboardSummaryResponseDto;
import com.banking.paypulse_banking.dto.response.TransferResponseDto;

import java.time.LocalDateTime;

public interface TransactionService {
    TransferResponseDto transferMony(TransferRequestDto transferRequestDto);

    PaginatedResponseTransaction getAllTransaction(String accNo, int page, int size);

    PaginatedResponseTransaction getTransactionsByDateRange(String accountNumber, LocalDateTime fromDate, LocalDateTime toDate, int page, int size);

    DashboardSummaryResponseDto getDashboardSummary(String accountNumber);
}
