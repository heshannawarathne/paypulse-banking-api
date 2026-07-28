package com.banking.paypulse_banking.service;

import com.banking.paypulse_banking.dto.request.TransferRequestDto;
import com.banking.paypulse_banking.dto.response.TransferResponseDto;

public interface TransactionService {
    TransferResponseDto transferMony(TransferRequestDto transferRequestDto);
}
