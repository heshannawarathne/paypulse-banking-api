package com.banking.paypulse_banking.dto.paginated;

import com.banking.paypulse_banking.dto.response.GetAllTransactionByUserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseTransaction {

    private List<GetAllTransactionByUserResponseDto> transactionData;
    private Long count;

}
