package com.banking.paypulse_banking.controller;

import com.banking.paypulse_banking.dto.paginated.PaginatedResponseTransaction;
import com.banking.paypulse_banking.dto.request.TransferRequestDto;
import com.banking.paypulse_banking.dto.response.TransferResponseDto;
import com.banking.paypulse_banking.service.TransactionService;
import com.banking.paypulse_banking.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @GetMapping(path = "get-all-transaction-by-user"
            , params = {"accountNumber", "page", "size"})
    public ResponseEntity<StandardResponse> getAllTransactionByUser(
            @RequestParam("accountNumber") String accNo,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {

        PaginatedResponseTransaction prt = transactionService.getAllTransaction(accNo, page, size);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "All transaction", prt), HttpStatus.OK
        );


    }

    @GetMapping(path = "/history/filter")
    public ResponseEntity<StandardResponse> getFilteredTransactions(
            @RequestParam String accountNumber,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        PaginatedResponseTransaction response = transactionService
                .getTransactionsByDateRange(accountNumber, fromDate, toDate, page, size);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "All transaction", response), HttpStatus.OK
        );

    }
}
