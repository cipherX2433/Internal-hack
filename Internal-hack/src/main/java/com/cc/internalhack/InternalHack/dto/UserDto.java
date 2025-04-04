package com.cc.internalhack.InternalHack.dto;

import com.cc.internalhack.InternalHack.entity.enums.Gender;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private Gender gender;
}
