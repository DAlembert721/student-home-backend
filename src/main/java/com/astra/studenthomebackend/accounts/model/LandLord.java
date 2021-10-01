package com.astra.studenthomebackend.accounts.model;

import com.astra.studenthomebackend.subscritions.model.Subscription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "account_id")
@Table(name = "land_lords")
@Data
public class LandLord extends Account{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subscription_id", nullable = false)
    @JsonIgnore
    private Subscription subscription;
}
