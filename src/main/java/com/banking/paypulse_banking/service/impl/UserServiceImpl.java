package com.banking.paypulse_banking.service.impl;

import com.banking.paypulse_banking.dto.UsersDto;
import com.banking.paypulse_banking.entity.Users;
import com.banking.paypulse_banking.mapper.UserMapper;
import com.banking.paypulse_banking.repo.UserRepo;
import com.banking.paypulse_banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public String saveUser(UsersDto usersDto) {

        Users user = userMapper.dtoToUser(usersDto);
//        String hashCode = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hashCode);

        if (!userRepo.existsByNicEquals(user.getNic())) {
            userRepo.save(user);
            return usersDto.getNic() + " successfully saved";

        } else {
            throw new RuntimeException("User already exists");
        }

    }
}
