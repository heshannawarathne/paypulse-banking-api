package com.banking.paypulse_banking.service;

import com.banking.paypulse_banking.dto.UsersDto;
import com.banking.paypulse_banking.dto.request.UsersUpdateDto;

public interface UserService {
    String saveUser(UsersDto userDto);

    String UpdateUserByNic(UsersUpdateDto userUpdateDto);
}
