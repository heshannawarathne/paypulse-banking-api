package com.banking.paypulse_banking.mapper;

import com.banking.paypulse_banking.dto.UsersDto;
import com.banking.paypulse_banking.dto.request.UsersUpdateDto;
import com.banking.paypulse_banking.entity.Users;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-27T14:39:37+0530",
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
    public Users usersDtoUpdateToUser(UsersUpdateDto usersUpdateDto) {
        if ( usersUpdateDto == null ) {
            return null;
        }

        Users users = new Users();

        users.setEmail( usersUpdateDto.getEmail() );
        users.setPassword( usersUpdateDto.getPassword() );
        users.setMobileNumber( usersUpdateDto.getMobileNumber() );
        users.setNic( usersUpdateDto.getNic() );

        return users;
    }
}
