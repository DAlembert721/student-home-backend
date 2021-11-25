package com.astra.studenthomebackend.accounts.resource;


import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class StudentResource extends AccountResource {

    private String address;

    private String image;

    private String districtName;

    private String educationCenterName;
}
