package com.astra.studenthomebackend.accounts.resource;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveEducationCenterResource {

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 100)
    private String address;

    @NotNull
    private Long districtId;
}
