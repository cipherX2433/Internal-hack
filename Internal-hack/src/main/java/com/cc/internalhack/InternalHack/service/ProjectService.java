package com.cc.internalhack.InternalHack.service;


import com.cc.internalhack.InternalHack.dto.ProjectSubmissionDto;
import com.cc.internalhack.InternalHack.entity.ProjectSubmission;

public interface ProjectService {
    ProjectSubmission createSubmission(ProjectSubmissionDto projectSubmissionDto);
}
