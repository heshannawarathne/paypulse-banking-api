package com.banking.paypulse_banking.service.impl;

import com.banking.paypulse_banking.Exception.NotFoundException;
import com.banking.paypulse_banking.dto.request.QrRequestDto;
import com.banking.paypulse_banking.dto.response.AccountLookupResponseDto;
import com.banking.paypulse_banking.entity.Account;
import com.banking.paypulse_banking.repo.AccountRepo;
import com.banking.paypulse_banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepo accountRepo;


    @Override
    public AccountLookupResponseDto resolveQrCode(QrRequestDto qrRequestDto) {
        Account account = accountRepo.findByQrCodePayload(qrRequestDto.getQrCodePayload());

        if (account == null) {
            throw new NotFoundException("Invalid QR Code!");
        }

        String ownerName = account.getUser().getFullName();
        return new AccountLookupResponseDto(account.getAccountNumber(), ownerName);
    }
}
