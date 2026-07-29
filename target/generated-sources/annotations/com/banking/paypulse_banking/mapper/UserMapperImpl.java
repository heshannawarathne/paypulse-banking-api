package com.banking.paypulse_banking.mapper;

import com.banking.paypulse_banking.dto.UsersDto;
import com.banking.paypulse_banking.dto.response.UserAccountDitailsResponse;
import com.banking.paypulse_banking.entity.Account;
import com.banking.paypulse_banking.entity.Users;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-29T19:23:22+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.18 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public Users dtoToUser(UsersDto usersDto) {
        if ( usersDto == null ) {
            return null;
        }

        Users users = new Users();

        users.setFullName( usersDto.getFullName() );
        users.setEmail( usersDto.getEmail() );
        users.setPassword( usersDto.getPassword() );
        users.setMobileNumber( usersDto.getMobileNumber() );
        users.setNic( usersDto.getNic() );

        return users;
    }

    @Override
    public UsersDto dtoToUserDto(Users users) {
        if ( users == null ) {
            return null;
        }

        UsersDto usersDto = new UsersDto();

        usersDto.setFullName( users.getFullName() );
        usersDto.setEmail( users.getEmail() );
        usersDto.setPassword( users.getPassword() );
        usersDto.setMobileNumber( users.getMobileNumber() );
        usersDto.setNic( users.getNic() );

        return usersDto;
    }

    @Override
    public UserAccountDitailsResponse responseAccountDetails(Account account) {
        if ( account == null ) {
            return null;
        }

        UserAccountDitailsResponse userAccountDitailsResponse = new UserAccountDitailsResponse();

        userAccountDitailsResponse.setAccountNumber( account.getAccountNumber() );
        userAccountDitailsResponse.setBalance( account.getBalance() );

        return userAccountDitailsResponse;
    }
}
