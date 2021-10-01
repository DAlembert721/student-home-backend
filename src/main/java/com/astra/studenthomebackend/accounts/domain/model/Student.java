package com.astra.studenthomebackend.accounts.domain.model;

import com.astra.studenthomebackend.locations.domain.model.District;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "account_id")
@Table(name = "students")
@Data
public class Student extends Account {

    @NotNull
    @Size(max = 100)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "district_id", nullable = false)
    @JsonIgnore
    private District district;

    @Column(name = "education_center", nullable = false)
    private String educationCenter;
}
