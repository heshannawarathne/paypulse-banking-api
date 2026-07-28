package com.banking.paypulse_banking.repo;

import com.banking.paypulse_banking.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@EnableJpaRepositories
public interface TransactionRep extends JpaRepository<Transaction, Long> {
    boolean existsByTransactionReference(String reference);


    Page<Transaction> findBySourceAccount_AccountNumber(String accNo, Pageable pageable);

    Page<Transaction> findBySourceAccount_AccountNumberAndTimestampBetween(String accNo, LocalDateTime fromDate, LocalDateTime toDate, PageRequest of);
}
