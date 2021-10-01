package com.astra.studenthomebackend.accounts.domain.model.auth;

import com.astra.studenthomebackend.shared.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email"),
})
public class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    private String username;

    @NotNull
    @Email
    @Size(max = 50)
    private String email;

    @NotNull
    @Size(max = 120)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserType role;

}
