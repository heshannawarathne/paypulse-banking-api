package com.banking.paypulse_banking.mapper;

import com.banking.paypulse_banking.dto.UsersDto;
import com.banking.paypulse_banking.dto.request.UsersUpdateDto;
import com.banking.paypulse_banking.dto.response.UserAccountDitailsResponse;
import com.banking.paypulse_banking.entity.Account;
import com.banking.paypulse_banking.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users dtoToUser(UsersDto usersDto);

    UsersDto  dtoToUserDto(Users users);

    UserAccountDitailsResponse responseAccountDetails(Account account);

}
