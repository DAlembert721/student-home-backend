package com.astra.studenthomebackend.accounts.domain.service;

import com.astra.studenthomebackend.accounts.domain.model.Account;
import com.astra.studenthomebackend.accounts.domain.model.auth.UserType;

public interface AccountService {

    Account create(Account account, UserType type);

}
