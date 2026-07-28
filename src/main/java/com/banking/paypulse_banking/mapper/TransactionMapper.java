package com.banking.paypulse_banking.mapper;


import com.banking.paypulse_banking.dto.response.GetAllTransactionByUserResponseDto;
import com.banking.paypulse_banking.dto.response.TransferResponseDto;
import com.banking.paypulse_banking.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "sourceAccount.accountNumber", target = "sourceAccountNumber")
    @Mapping(source = "destinationAccount.accountNumber", target = "destinationAccountNumber")
    TransferResponseDto transactionToTransactionDto(Transaction tr);

    @Mapping(source = "sourceAccount.accountNumber", target = "sourceAccount")
    @Mapping(source = "destinationAccount.accountNumber", target = "destinationAccount")
    GetAllTransactionByUserResponseDto transactionToGetAllTransactionByUserDto(Transaction tr);

    List<GetAllTransactionByUserResponseDto> pageToGetAllTransactionByUserDtoList(List<Transaction> trList);

}
