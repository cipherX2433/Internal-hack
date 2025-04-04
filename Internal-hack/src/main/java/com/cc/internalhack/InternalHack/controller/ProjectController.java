package com.cc.internalhack.InternalHack.controller;

import com.cc.internalhack.InternalHack.dto.ProjectSubmissionDto;
import com.cc.internalhack.InternalHack.entity.ProjectSubmission;
import com.cc.internalhack.InternalHack.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ModelMapper modelMapper;
    private final ProjectService projectService;

    @PostMapping("/submission")
    public ResponseEntity<ProjectSubmission> createSubmission(@RequestBody ProjectSubmissionDto projectSubmissionDto){
        ProjectSubmission projectSubmission = projectService.createSubmission(projectSubmissionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectSubmission);
    }
}
