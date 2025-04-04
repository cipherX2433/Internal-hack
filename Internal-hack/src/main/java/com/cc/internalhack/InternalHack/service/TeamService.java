package com.cc.internalhack.InternalHack.service;

import com.cc.internalhack.InternalHack.dto.TeamDto;
import com.cc.internalhack.InternalHack.dto.TeamResponseDto;

public interface TeamService {

    TeamDto createNewTeam(TeamDto teamDto);
    TeamResponseDto joinTeam(Long teamId);
}
