package com.astra.studenthomebackend.accounts.domain.model;


import com.astra.studenthomebackend.subscriptions.domain.model.Subscription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "account_id")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "land_lords")
@Data
public class LandLord extends Account{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subscription_id", nullable = false)
    @JsonIgnore
    private Subscription subscription;
}
