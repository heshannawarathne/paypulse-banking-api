package com.banking.paypulse_banking.repo;

import com.banking.paypulse_banking.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<Users, Long> {


    boolean existsByNicEquals(String nic);

    Users findAllByNicEquals(String nic);

    Users findByNic(String nic);
}
