package com.astra.studenthomebackend.properties.resource;

import lombok.Data;

import java.util.Date;


@Data
public class PaymentResource {

    private Long id;

    private Float pay;

    private String image;

    private String comment;

    private Boolean checked;

    private Date createdAt;

    private Date updatedAt;
}
