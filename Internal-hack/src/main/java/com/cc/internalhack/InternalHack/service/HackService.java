package com.cc.internalhack.InternalHack.service;

import com.cc.internalhack.InternalHack.dto.HackDto;
import com.cc.internalhack.InternalHack.entity.HackDetails;

public interface HackService {
    HackDetails hackDetails(HackDto hackDto);

    HackDetails getHackDetailsById(Long id);
}
