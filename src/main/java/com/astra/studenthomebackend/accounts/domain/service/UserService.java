package com.astra.studenthomebackend.accounts.domain.service;

import com.astra.studenthomebackend.accounts.domain.model.auth.User;

public interface UserService  {

    User create(User user);
    User getUserById(Long id);
}
