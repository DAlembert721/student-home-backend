package com.astra.studenthomebackend.properties.resource;

import lombok.Data;

import java.util.Date;

@Data
public class PropertyCommentResource {

    private Long id;
    private Long score;
    private String comment;
    private Date createdAt;
    private String studentFirstName;
    private String studentLastName;
    private String studentImage;
    private Long studentId;
}
