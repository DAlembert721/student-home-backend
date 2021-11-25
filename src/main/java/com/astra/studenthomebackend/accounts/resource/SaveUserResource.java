package com.astra.studenthomebackend.accounts.resource;

import com.astra.studenthomebackend.accounts.domain.model.auth.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SaveUserResource {
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotNull
    @NotBlank
    private String password;

    static public User resourceToEntity(SaveUserResource resource) {
        User user = new User();
        user.setUsername(resource.getUsername());
        user.setEmail(resource.getEmail());
        user.setPassword(resource.getPassword());
        user.setIsEnabled(true);
        return user;
    }
}
