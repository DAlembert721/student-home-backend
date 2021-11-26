package com.astra.studenthomebackend.accounts.resource;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class SaveStudentOpinionResource {

    @NotNull
    private Long score;

    @NotNull
    @Size(max = 250)
    private String content;
}
