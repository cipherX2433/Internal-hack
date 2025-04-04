package com.cc.internalhack.InternalHack.service;

import com.cc.internalhack.InternalHack.dto.TeamDto;
import com.cc.internalhack.InternalHack.dto.TeamResponseDto;
import com.cc.internalhack.InternalHack.dto.UserDto;
import com.cc.internalhack.InternalHack.entity.Team;
import com.cc.internalhack.InternalHack.entity.User;
import com.cc.internalhack.InternalHack.entity.enums.Role;
import com.cc.internalhack.InternalHack.exception.ResourceNotFoundException;
import com.cc.internalhack.InternalHack.repository.TeamRepository;
import com.cc.internalhack.InternalHack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

import static com.cc.internalhack.InternalHack.Utils.AppUtils.getCurrentUser;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Override
    public TeamDto createNewTeam(TeamDto teamDto) {
        User user = getCurrentUser();

        if (user == null) {
            throw new RuntimeException("Current user not found.");
        }

        Team team = modelMapper.map(teamDto, Team.class);
        team.setTeamLeader(user);
        team.setTeamName(teamDto.getTeamName());
        team.setDescription(teamDto.getDescription());
        team.setTheme(teamDto.getTheme());
        team.setMembers(Set.of(user));

        team = teamRepository.save(team);

        user.setTeam(team);
        user.setRoles(Set.of(Role.Teamleader));
        userRepository.save(user);

        return modelMapper.map(team, TeamDto.class);
    }

    @Override
    @Transactional
    public TeamResponseDto joinTeam(Long teamId) {
        User user = getCurrentUser();

        if (user == null) {
            throw new RuntimeException("Current user not found.");
        }

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found"));

        if(team.getMembers().size() >= 4){
            throw new RuntimeException("Team is already full (max 4 members)");
        }

        if(!team.getMembers().contains(user)){
            team.getMembers().add(user);
            user.setTeam(team);
        }

        user.setRoles(Set.of(Role.Participant));

        userRepository.save(user);
        teamRepository.save(team);

        TeamResponseDto dto = new TeamResponseDto();
        dto.setId(team.getId());
        dto.setTeamName(team.getTeamName());
        dto.setDescription(team.getDescription());
        dto.setLeaderName(team.getTeamLeader().getName());
        dto.setTheme(team.getTheme());

        // ✅ Map `User` entities to `UserDto`
        Set<UserDto> members = team.getMembers().stream()
                .map(this::convertToUserDto)  // Use a mapping method
                .collect(Collectors.toSet());

        dto.setMember(members);   // ✅ Avoid Hibernate proxies

        return dto;
    }

    // ✅ Mapping method: Convert User to UserDto
    private UserDto convertToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setGender(user.getGender());
        return dto;
    }
}
