package com.banking.paypulse_banking.controller;

import com.banking.paypulse_banking.dto.request.QrRequestDto;
import com.banking.paypulse_banking.dto.response.AccountLookupResponseDto;
import com.banking.paypulse_banking.service.AccountService;
import com.banking.paypulse_banking.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(path = "qr/resolve")
    public ResponseEntity<StandardResponse> getTransactionByQrCode(@RequestBody QrRequestDto qrRequestDto) {

        AccountLookupResponseDto qrBase64 = accountService.resolveQrCode(qrRequestDto);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "QR Code", qrBase64), HttpStatus.OK
        );


    }
}
