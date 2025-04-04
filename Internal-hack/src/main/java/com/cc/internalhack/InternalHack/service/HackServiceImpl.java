package com.cc.internalhack.InternalHack.service;

import com.cc.internalhack.InternalHack.dto.HackDto;
import com.cc.internalhack.InternalHack.entity.HackDetails;
import com.cc.internalhack.InternalHack.entity.User;
import com.cc.internalhack.InternalHack.exception.ResourceNotFoundException;
import com.cc.internalhack.InternalHack.repository.HackRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.cc.internalhack.InternalHack.Utils.AppUtils.getCurrentUser;

@Service
@RequiredArgsConstructor
public class HackServiceImpl implements HackService {

    private final ModelMapper modelMapper;
    private final HackRepository hackRepository;

    @Override
    public HackDetails hackDetails(HackDto hackDto) {
        User user = getCurrentUser();

        if(user == null){
            throw new RuntimeException("Not a Valid User");
        }

        HackDetails hackDetails = modelMapper.map(hackDto, HackDetails.class);

          hackDetails.setUpdatedAt(LocalDateTime.now());

          hackRepository.save(hackDetails);

        return hackDetails;
    }

    @Override
    public HackDetails getHackDetailsById(Long id) {
        HackDetails hackDetails = hackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hack with this id not found"+ id));

        return hackDetails;
    }
}
