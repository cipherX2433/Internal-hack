package com.cc.internalhack.InternalHack.controller;

import com.cc.internalhack.InternalHack.dto.JoinTeamRequest;
import com.cc.internalhack.InternalHack.dto.TeamDto;
import com.cc.internalhack.InternalHack.dto.TeamResponseDto;
import com.cc.internalhack.InternalHack.entity.Team;
import com.cc.internalhack.InternalHack.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto teamDto){
        TeamDto team = teamService.createNewTeam(teamDto);
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @PostMapping("/joinTeam")
    public ResponseEntity<TeamResponseDto> joinTeam(@RequestParam Long teamId){
        TeamResponseDto team = teamService.joinTeam(teamId);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
}
