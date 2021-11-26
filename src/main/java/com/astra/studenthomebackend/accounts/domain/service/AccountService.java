package com.astra.studenthomebackend.accounts.domain.service;

import com.astra.studenthomebackend.accounts.domain.model.Account;
import com.astra.studenthomebackend.accounts.domain.model.auth.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    Page<Account> getAllAccountsByUserId(Long userId, Pageable pageable);

    Account createAccount(Long userId, Account account);

    Account getAccountByIdAndUserId(Long accountId, Long userId);

    Account updateAccount(Long accountId, Long userId, Account accountRequest);

    ResponseEntity<?> deleteAccount(Long accountId, Long userId);
}
