package com.cc.internalhack.InternalHack.controller;

import com.cc.internalhack.InternalHack.dto.HackDto;
import com.cc.internalhack.InternalHack.entity.HackDetails;
import com.cc.internalhack.InternalHack.repository.HackRepository;
import com.cc.internalhack.InternalHack.service.HackService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HackController {

    private final HackRepository hackRepository;
    private final HackService hackService;
    private final ModelMapper modelMapper;

    @PostMapping("/createHack")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<HackDto> createHackDetails(@RequestBody HackDto hackDto){
        HackDetails hackDetails = hackService.hackDetails(hackDto);
        return ResponseEntity.ok(modelMapper.map(hackDetails, HackDto.class));
    }

    @GetMapping("/hackDetails")
    public ResponseEntity<HackDto> getHackDetailsById(@RequestParam Long id){
        HackDetails hackDetails = hackService.getHackDetailsById(id);
        return ResponseEntity.ok(modelMapper.map(hackDetails, HackDto.class));
    }

}
