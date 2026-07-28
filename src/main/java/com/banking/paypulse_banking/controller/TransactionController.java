package com.banking.paypulse_banking.controller;

import com.banking.paypulse_banking.dto.request.TransferRequestDto;
import com.banking.paypulse_banking.dto.response.TransferResponseDto;
import com.banking.paypulse_banking.service.TransactionService;
import com.banking.paypulse_banking.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(path = "/transfer")
    public ResponseEntity<StandardResponse> generateTransaction(@RequestBody TransferRequestDto transferRequestDto) {

        TransferResponseDto transferResponseDto = transactionService.transferMony(transferRequestDto);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "transfer Successfull", transferResponseDto), HttpStatus.CREATED
        );
    }
}
