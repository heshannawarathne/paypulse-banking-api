package com.banking.paypulse_banking.service;

import com.banking.paypulse_banking.dto.UsersDto;
import com.banking.paypulse_banking.dto.request.UsersUpdateDto;
import com.banking.paypulse_banking.dto.response.UserAccountDitailsResponse;

public interface UserService {
    UserAccountDitailsResponse saveUser(UsersDto userDto);

    String UpdateUserByNic(UsersUpdateDto userUpdateDto);
}
