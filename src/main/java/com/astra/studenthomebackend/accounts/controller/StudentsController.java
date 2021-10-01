package com.astra.studenthomebackend.accounts.controller;

import com.astra.studenthomebackend.accounts.domain.model.Student;
import com.astra.studenthomebackend.accounts.domain.service.StudentService;
import com.astra.studenthomebackend.accounts.resource.SaveStudentResource;
import com.astra.studenthomebackend.accounts.resource.StudentResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class StudentsController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StudentService studentService;

    @GetMapping("/students/{studentId}")
    public StudentResource getStudentById(@PathVariable(name = "studentId") Long studentId) {
        return convertToResource(studentService.getStudentById(studentId));
    }

    private Student convertToEntity(SaveStudentResource resource) {
        return objectMapper.convertValue(resource, Student.class);
    }

    private StudentResource convertToResource(Student entity) {
        return objectMapper.convertValue(entity, StudentResource.class);
    }
}
