package com.banking.paypulse_banking.service;

import com.banking.paypulse_banking.dto.request.QrRequestDto;
import com.banking.paypulse_banking.dto.response.AccountLookupResponseDto;

public interface AccountService {
    AccountLookupResponseDto resolveQrCode(QrRequestDto qrRequestDto);

}
