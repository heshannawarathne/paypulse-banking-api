package com.banking.paypulse_banking.mapper;


import com.banking.paypulse_banking.dto.response.TransferResponseDto;
import com.banking.paypulse_banking.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransferResponseDto transactionToTransactionDto(Transaction tr);
}
