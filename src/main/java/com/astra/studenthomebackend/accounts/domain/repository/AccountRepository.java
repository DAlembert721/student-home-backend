package com.astra.studenthomebackend.accounts.domain.repository;

import com.astra.studenthomebackend.accounts.domain.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Page<Account> findByUserId(Long userId, Pageable pageable);

    Optional<Account> findByIdAndUserId(Long accountId, Long userId);
}
