package com.cc.internalhack.InternalHack.service;

import com.cc.internalhack.InternalHack.entity.User;
import com.cc.internalhack.InternalHack.exception.ResourceNotFoundException;
import com.cc.internalhack.InternalHack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with this id"));
    }
}
