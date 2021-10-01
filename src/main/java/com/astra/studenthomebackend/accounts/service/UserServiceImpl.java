package com.astra.studenthomebackend.accounts.service;

import com.astra.studenthomebackend.accounts.domain.model.auth.User;
import com.astra.studenthomebackend.accounts.domain.repository.UserRepository;
import com.astra.studenthomebackend.accounts.domain.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            // Lanzar error
        }
        return userRepository.save(user);
    }

}
