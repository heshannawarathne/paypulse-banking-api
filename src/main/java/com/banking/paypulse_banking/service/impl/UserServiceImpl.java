package com.banking.paypulse_banking.service.impl;

import com.banking.paypulse_banking.Exception.DataIntegrityViolationException;
import com.banking.paypulse_banking.Exception.NotFoundException;
import com.banking.paypulse_banking.dto.UsersDto;
import com.banking.paypulse_banking.dto.request.UsersUpdateDto;
import com.banking.paypulse_banking.entity.Users;
import com.banking.paypulse_banking.entity.enums.UserType;
import com.banking.paypulse_banking.mapper.UserMapper;
import com.banking.paypulse_banking.repo.UserRepo;
import com.banking.paypulse_banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    //save user
    @Override
    public String saveUser(UsersDto usersDto) {

        Users user = userMapper.dtoToUser(usersDto);
        user.setRole(UserType.USER);
        if (!userRepo.existsByNicEquals(user.getNic())) {
            userRepo.save(user);
            return usersDto.getNic() + " successfully saved";

        } else {
            throw new DataIntegrityViolationException(user.getNic() + " User already exists");
        }

    }

    //update user by Nic
    @Override
    public String UpdateUserByNic(UsersUpdateDto usersUpdateDto) {

        Users existingUser = userRepo.findAllByNicEquals(usersUpdateDto.getNic());

        if (existingUser != null) {
            existingUser.setEmail(usersUpdateDto.getEmail());
            existingUser.setPassword(usersUpdateDto.getPassword());
            existingUser.setMobileNumber(usersUpdateDto.getMobileNumber());

            userRepo.save(existingUser);
            return existingUser.getEmail() + " successfully updated";
        } else {
            throw new NotFoundException("User not found");
        }
    }
}
