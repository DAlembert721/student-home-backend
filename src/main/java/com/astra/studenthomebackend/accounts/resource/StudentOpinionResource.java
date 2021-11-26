package com.astra.studenthomebackend.accounts.resource;


import lombok.Data;

import java.util.Date;


@Data
public class StudentOpinionResource {

    private Long id;
    private Long score;
    private String content;
    private Date createdAt;
    private String landlordFirstName;
    private String landlordLastName;

}
