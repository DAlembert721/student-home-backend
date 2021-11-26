package com.astra.studenthomebackend.properties.resource;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SavePaymentResource {


    @NotNull
    private Float pay;

    @NotNull
    @Size(max = 250)
    private String image;

    @Size(max = 250)
    private String comment;

}
