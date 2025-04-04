package com.cc.internalhack.InternalHack.dto;

import com.cc.internalhack.InternalHack.entity.Team;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectSubmissionDto {

    private String fileName;
    private String fileUrl;

    private Team team;

    private LocalDateTime submissionTime = LocalDateTime.now();
}
