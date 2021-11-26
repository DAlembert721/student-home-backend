package com.astra.studenthomebackend.properties.resource;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SavePropertyCommentResource {

    @NotNull
    private Long score;

    @NotNull
    @Size(max = 250)
    private String comment;
}
