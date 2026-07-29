package com.banking.paypulse_banking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QrRequestDto {
    private String qrCodePayload;

}
