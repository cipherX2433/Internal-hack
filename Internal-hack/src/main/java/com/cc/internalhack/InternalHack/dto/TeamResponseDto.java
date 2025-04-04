package com.cc.internalhack.InternalHack.dto;

import com.cc.internalhack.InternalHack.entity.User;
import com.cc.internalhack.InternalHack.entity.enums.Theme;
import lombok.Data;

import java.util.Set;

@Data
public class TeamResponseDto {
    private Long id;
    private String teamName;
    private String description;
    private String leaderName;
    private Set<UserDto> member;
    private Set<Theme> theme;
}
