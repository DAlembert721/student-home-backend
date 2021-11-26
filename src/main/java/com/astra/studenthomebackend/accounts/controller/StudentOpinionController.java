package com.astra.studenthomebackend.accounts.controller;

import com.astra.studenthomebackend.accounts.domain.model.StudentOpinion;
import com.astra.studenthomebackend.accounts.domain.service.StudentOpinionService;
import com.astra.studenthomebackend.accounts.resource.SaveStudentOpinionResource;
import com.astra.studenthomebackend.accounts.resource.StudentOpinionResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class StudentOpinionController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StudentOpinionService studentOpinionService;

    @GetMapping("opinions")
    public Page<StudentOpinionResource> getAllStudentOpinion(Pageable pageable) {
        Page<StudentOpinion> studentOpinionPage =
                studentOpinionService.getAllStudentOpinions(pageable);
        List<StudentOpinionResource> resources = studentOpinionPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("landlords/{landLordId}/opinions")
    public Page<StudentOpinionResource> getAllStudentOpinionByLandLord(
            @PathVariable Long landLordId, Pageable pageable) {
        Page<StudentOpinion> studentOpinionPage =
                studentOpinionService.getAllStudentOpinionsByLandlordId(landLordId, pageable);
        List<StudentOpinionResource> resources = studentOpinionPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @GetMapping("students/{studentId}/opinions")
    public Page<StudentOpinionResource> getAllStudentOpinionByStudent(
            @PathVariable Long studentId, Pageable pageable) {
        Page<StudentOpinion> studentOpinionPage =
                studentOpinionService.getAllStudentOpinionsByStudentId(studentId, pageable);
        List<StudentOpinionResource> resources = studentOpinionPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @GetMapping("landlords/{landLordId}/students/{studentId}/opinions")
    public Page<StudentOpinionResource> getAllStudentOpinionByLandLordAndStudent(
            @PathVariable (name = "landLordId") Long landLordId,
            @PathVariable (name = "studentId") Long studentId, Pageable pageable) {
        Page<StudentOpinion> studentOpinionPage = studentOpinionService.
                getAllStudentOpinionsByStudentIdAndLandlordId(studentId,landLordId, pageable);
        List<StudentOpinionResource> resources = studentOpinionPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("landlords/{landLordId}/students/{studentId}/opinions/{studentOpinionsId}")
    public StudentOpinionResource getStudentOpinionsByIdAndLandLordIdAndStudentId(
            @PathVariable (name = "landLordId") Long landLordId,
            @PathVariable (name = "studentId") Long studentId,
            @PathVariable(name = "studentOpinionsId") Long studentOpinionsId) {
        return convertToResource(studentOpinionService.
                getStudentOpinionByIdAndStudentIdAndLandlordId(
                        studentOpinionsId,studentId,landLordId));
    }

    @PostMapping("landlords/{landLordId}/students/{studentId}/opinions")
    public StudentOpinionResource createStudentOpinion(
            @PathVariable (name = "landLordId") Long landLordId,
            @PathVariable (name = "studentId") Long studentId,
            @Valid @RequestBody SaveStudentOpinionResource resource) {
        return convertToResource(studentOpinionService.createStudentOpinion(
                studentId,landLordId, convertToEntity(resource)));
    }

    @PutMapping("landlords/{landLordId}/students/{studentId}/opinions/{studentOpinionsId}")
    public StudentOpinionResource updateStudentOpinion(
            @PathVariable (name = "landLordId") Long landLordId,
            @PathVariable (name = "studentId") Long studentId,
            @PathVariable(name = "studentOpinionsId") Long studentOpinionsId,
            @Valid @RequestBody SaveStudentOpinionResource resource) {
        return convertToResource(studentOpinionService.updateStudentOpinion(
                studentOpinionsId,studentId,landLordId,convertToEntity(resource)));
    }

    @DeleteMapping("landlords/{landLordId}/students/{studentId}/opinions/{studentOpinionsId}")
    public ResponseEntity<?> deleteStudentOpinion(
            @PathVariable (name = "landLordId") Long landLordId,
            @PathVariable (name = "studentId") Long studentId,
            @PathVariable(name = "studentOpinionsId") Long studentOpinionsId) {
        return studentOpinionService.deleteStudentOpinion(studentOpinionsId,studentId,landLordId);
    }

    private StudentOpinion convertToEntity(SaveStudentOpinionResource resource) {
        return mapper.map(resource, StudentOpinion.class);
    }

    private StudentOpinionResource convertToResource(StudentOpinion entity) {
        return mapper.map(entity, StudentOpinionResource.class);
    }

}
