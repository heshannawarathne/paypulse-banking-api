package com.banking.paypulse_banking.mapper;

import com.banking.paypulse_banking.dto.response.GetAllTransactionByUserResponseDto;
import com.banking.paypulse_banking.dto.response.TransferResponseDto;
import com.banking.paypulse_banking.entity.Account;
import com.banking.paypulse_banking.entity.Transaction;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-29T19:23:22+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.18 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransferResponseDto transactionToTransactionDto(Transaction tr) {
        if ( tr == null ) {
            return null;
        }

        TransferResponseDto transferResponseDto = new TransferResponseDto();

        transferResponseDto.setSourceAccountNumber( trSourceAccountAccountNumber( tr ) );
        transferResponseDto.setDestinationAccountNumber( trDestinationAccountAccountNumber( tr ) );
        transferResponseDto.setTransactionReference( tr.getTransactionReference() );
        transferResponseDto.setAmount( tr.getAmount() );
        transferResponseDto.setStatus( tr.getStatus() );
        transferResponseDto.setDescription( tr.getDescription() );
        transferResponseDto.setTimestamp( tr.getTimestamp() );

        return transferResponseDto;
    }

    @Override
    public GetAllTransactionByUserResponseDto transactionToGetAllTransactionByUserDto(Transaction tr) {
        if ( tr == null ) {
            return null;
        }

        GetAllTransactionByUserResponseDto getAllTransactionByUserResponseDto = new GetAllTransactionByUserResponseDto();

        getAllTransactionByUserResponseDto.setSourceAccount( trSourceAccountAccountNumber( tr ) );
        getAllTransactionByUserResponseDto.setDestinationAccount( trDestinationAccountAccountNumber( tr ) );
        getAllTransactionByUserResponseDto.setTransactionReference( tr.getTransactionReference() );
        getAllTransactionByUserResponseDto.setAmount( tr.getAmount() );
        getAllTransactionByUserResponseDto.setType( tr.getType() );
        getAllTransactionByUserResponseDto.setStatus( tr.getStatus() );
        getAllTransactionByUserResponseDto.setDescription( tr.getDescription() );
        getAllTransactionByUserResponseDto.setTimestamp( tr.getTimestamp() );

        return getAllTransactionByUserResponseDto;
    }

    @Override
    public List<GetAllTransactionByUserResponseDto> pageToGetAllTransactionByUserDtoList(List<Transaction> trList) {
        if ( trList == null ) {
            return null;
        }

        List<GetAllTransactionByUserResponseDto> list = new ArrayList<GetAllTransactionByUserResponseDto>( trList.size() );
        for ( Transaction transaction : trList ) {
            list.add( transactionToGetAllTransactionByUserDto( transaction ) );
        }

        return list;
    }

    private String trSourceAccountAccountNumber(Transaction transaction) {
        Account sourceAccount = transaction.getSourceAccount();
        if ( sourceAccount == null ) {
            return null;
        }
        return sourceAccount.getAccountNumber();
    }

    private String trDestinationAccountAccountNumber(Transaction transaction) {
        Account destinationAccount = transaction.getDestinationAccount();
        if ( destinationAccount == null ) {
            return null;
        }
        return destinationAccount.getAccountNumber();
    }
}
