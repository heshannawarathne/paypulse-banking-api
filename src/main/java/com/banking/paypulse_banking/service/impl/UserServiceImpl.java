package com.banking.paypulse_banking.service.impl;

import com.banking.paypulse_banking.Exception.DataIntegrityViolationException;
import com.banking.paypulse_banking.Exception.NotFoundException;
import com.banking.paypulse_banking.dto.UsersDto;
import com.banking.paypulse_banking.dto.request.UsersUpdateDto;
import com.banking.paypulse_banking.dto.response.UserAccountDitailsResponse;
import com.banking.paypulse_banking.entity.Account;
import com.banking.paypulse_banking.entity.Users;
import com.banking.paypulse_banking.entity.enums.AccountStatus;
import com.banking.paypulse_banking.entity.enums.UserType;
import com.banking.paypulse_banking.mapper.UserMapper;
import com.banking.paypulse_banking.repo.AccountRepo;
import com.banking.paypulse_banking.repo.UserRepo;
import com.banking.paypulse_banking.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AccountRepo accountRepo;


    //account number ganarate
    private static final SecureRandom random = new SecureRandom();

    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            // Prefix (1010) + Random 10 digits
            long randomNum = 1000000000L + (long) (random.nextDouble() * 9000000000L);
            accountNumber = "1010" + randomNum;
        } while (accountRepo.existsByAccountNumber(accountNumber)); // DB එකේ නැති එකක් එනකම් Loop වෙනවා

        return accountNumber;
    }


    //I will have to password encode
    //save user
    @Transactional
    @Override
    public UserAccountDitailsResponse saveUser(UsersDto usersDto) {

        Users user = userMapper.dtoToUser(usersDto);
        user.setRole(UserType.USER);

        String newAccountNumber = generateUniqueAccountNumber();


        if (!userRepo.existsByNicEquals(user.getNic())) {
            userRepo.save(user);

            Account account = new Account();
            account.setAccountNumber(newAccountNumber);
            account.setBalance(BigDecimal.ZERO);
            account.setAccountStatus(AccountStatus.ACTIVE);
            account.setUser(user);

            accountRepo.save(account);
            UsersDto usersDto1 = userMapper.dtoToUserDto(user);

            UserAccountDitailsResponse userAccountDitailsResponse = userMapper.responseAccountDetails(account);
            return userAccountDitailsResponse;

        } else {
            throw new DataIntegrityViolationException(user.getNic() + " User already exists");
        }

    }

    //I will have to password encode
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


    //update-account-state
    @Override
    public String UpdateUserState(String nic, String state) {
        Users existUser = userRepo.findByNic(nic);

        if (existUser != null) {
            Account account = accountRepo.findByUserId(existUser.getId())
                    .orElseThrow(() -> new NotFoundException("Account not found for this user"));

            try {
                account.setAccountStatus(AccountStatus.valueOf(state.toUpperCase()));
            } catch (Exception e) {
                throw new RuntimeException("Invalid Account Status: " + state);
            }
            accountRepo.save(account);
            return "Account State successfully updated";

        } else {
            throw new NotFoundException("User not found");
        }


    }
}
