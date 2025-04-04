package com.cc.internalhack.InternalHack.service;

import com.cc.internalhack.InternalHack.dto.ProjectSubmissionDto;
import com.cc.internalhack.InternalHack.entity.ProjectSubmission;
import com.cc.internalhack.InternalHack.entity.Team;
import com.cc.internalhack.InternalHack.entity.User;
import com.cc.internalhack.InternalHack.repository.ProjectRepository;
import com.cc.internalhack.InternalHack.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.cc.internalhack.InternalHack.Utils.AppUtils.getCurrentUser;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;
    private final TeamRepository teamRepository;


    @Override
    @Transactional
    public ProjectSubmission createSubmission(ProjectSubmissionDto projectSubmissionDto) {

        User user = getCurrentUser();

        if (user == null) {
            throw new RuntimeException("User not authenticated!");
        }

        Team team = user.getTeam();

        if(team == null){
            throw new RuntimeException("User is not a part of any team");
        }

        ProjectSubmission projectSubmission = modelMapper.map(projectSubmissionDto, ProjectSubmission.class);
        projectSubmission.setTeam(team);
        team.setProjectSubmissions(Set.of(projectSubmission));

        projectRepository.save(projectSubmission);
        teamRepository.save(team);

        return projectSubmission;
    }
}
