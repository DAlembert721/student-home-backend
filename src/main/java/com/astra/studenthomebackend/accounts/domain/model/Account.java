package com.astra.studenthomebackend.accounts.domain.model;

import com.astra.studenthomebackend.accounts.domain.model.auth.User;
import com.astra.studenthomebackend.shared.model.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "accounts")
public abstract class Account extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    private String firstName;

    @NotNull
    @Size(max = 30)
    private String lastName;

    @Size(max = 250)
    private String image;

    @NotNull
    @Size(max = 15)
    @Column(unique = true)
    private String dni;

    @NotNull
    @Size(max = 20)
    private String phone;

    @Size(max = 250)
    private String description;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
