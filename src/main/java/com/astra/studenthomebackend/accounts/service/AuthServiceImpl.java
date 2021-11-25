package com.astra.studenthomebackend.accounts.service;

import com.astra.studenthomebackend.accounts.domain.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public boolean haveAccess(String path) {
        boolean response = false;
        String rolMethod = "";
        switch (path) {
            case "list":
                rolMethod = "ADMIN";
                break;
            case "listId":
                rolMethod = "ADMIN,USER";
                break;
        }
        String[] rolesMethod = rolMethod.split(",");
        Authentication loggedUser = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(loggedUser.getName());

        for (GrantedAuthority auth : loggedUser.getAuthorities()) {
            String rolUser = auth.getAuthority();
            System.out.println(rolUser);

            for (String rolMet : rolesMethod) {
                if (rolUser.equalsIgnoreCase(rolMet)) {
                    response = true;
                }
            }
        }

        return response;
    }
}
