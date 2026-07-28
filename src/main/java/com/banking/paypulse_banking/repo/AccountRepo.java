package com.banking.paypulse_banking.repo;

import com.banking.paypulse_banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface AccountRepo extends JpaRepository<Account, Long> {
    boolean existsByAccountNumber(String accountNumber);
}
