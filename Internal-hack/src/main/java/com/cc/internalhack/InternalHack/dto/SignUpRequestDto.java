package com.cc.internalhack.InternalHack.dto;

import com.cc.internalhack.InternalHack.entity.enums.Role;
import lombok.Data;

@Data
public class SignUpRequestDto {

    private String email;
    private String password;
    private String name;
    private String gender;
    private Role role;
}
