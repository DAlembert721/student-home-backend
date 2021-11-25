package com.astra.studenthomebackend.accounts.domain.service;

import org.springframework.stereotype.Service;

public interface AuthService {
    boolean haveAccess(String path);
}
