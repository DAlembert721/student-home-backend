package com.astra.studenthomebackend.properties.resource;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SavePropertyImageResource {
    @NotNull
    private String url;
}
