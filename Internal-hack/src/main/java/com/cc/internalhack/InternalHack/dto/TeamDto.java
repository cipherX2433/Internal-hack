package com.cc.internalhack.InternalHack.dto;

import com.cc.internalhack.InternalHack.entity.User;
import com.cc.internalhack.InternalHack.entity.enums.Theme;
import lombok.Data;

import java.util.Set;

@Data
public class TeamDto {

    private String teamName;
    private String description;
//    private User teamLeader;
    private Set<Theme> theme;
}
