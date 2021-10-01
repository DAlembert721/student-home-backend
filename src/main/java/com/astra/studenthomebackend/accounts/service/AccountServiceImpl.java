package com.astra.studenthomebackend.accounts.service;

import com.astra.studenthomebackend.accounts.domain.model.Account;
import com.astra.studenthomebackend.accounts.domain.model.auth.UserType;
import com.astra.studenthomebackend.accounts.domain.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public Account create(Account account, UserType type) {
        return null;
    }

}
