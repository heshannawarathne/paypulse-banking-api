package com.banking.paypulse_banking.mapper;

import com.banking.paypulse_banking.dto.response.TransferResponseDto;
import com.banking.paypulse_banking.entity.Transaction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-28T14:41:26+0530",
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

        transferResponseDto.setTransactionReference( tr.getTransactionReference() );
        transferResponseDto.setSourceAccount( tr.getSourceAccount() );
        transferResponseDto.setDestinationAccount( tr.getDestinationAccount() );
        transferResponseDto.setAmount( tr.getAmount() );
        transferResponseDto.setStatus( tr.getStatus() );
        transferResponseDto.setDescription( tr.getDescription() );
        transferResponseDto.setTimestamp( tr.getTimestamp() );

        return transferResponseDto;
    }
}
