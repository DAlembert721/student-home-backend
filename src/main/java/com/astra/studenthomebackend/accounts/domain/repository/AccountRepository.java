package com.astra.studenthomebackend.accounts.domain.repository;

import com.astra.studenthomebackend.accounts.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository<T extends Account, ID> extends JpaRepository<T, ID> {
}
