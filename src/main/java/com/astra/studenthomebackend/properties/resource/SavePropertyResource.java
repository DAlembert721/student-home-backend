package com.astra.studenthomebackend.properties.resource;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SavePropertyResource {
    @NotNull
    private Long rooms;

    @NotNull
    private Float size;

    @NotNull
    private Float cost;

    @NotNull
    private Boolean active;

    @NotNull
    @Size(max = 100)
    private String address;

    @Size(max = 100)
    private String title;

    @Size(max = 400)
    private String description;

    private Long place;
}
