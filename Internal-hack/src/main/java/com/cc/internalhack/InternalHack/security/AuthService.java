package com.cc.internalhack.InternalHack.security;

import com.cc.internalhack.InternalHack.dto.LoginDto;
import com.cc.internalhack.InternalHack.dto.SignUpRequestDto;
import com.cc.internalhack.InternalHack.dto.UserDto;
import com.cc.internalhack.InternalHack.entity.User;
import com.cc.internalhack.InternalHack.entity.enums.Role;
import com.cc.internalhack.InternalHack.exception.ResourceNotFoundException;
import com.cc.internalhack.InternalHack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public UserDto signUp(SignUpRequestDto signUpRequestDto){
        User user = userRepository.findByEmail(signUpRequestDto.getEmail()).orElse(null);

        if(user != null){
            throw new RuntimeException("User is already present with this email");
        }

        User newUser = modelMapper.map(signUpRequestDto, User.class);
        Role userRole = (signUpRequestDto.getRole() != null) ? signUpRequestDto.getRole() : Role.Participant;
        newUser.setRoles(Set.of(userRole));
        newUser.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        newUser = userRepository.save(newUser);

        return modelMapper.map(newUser, UserDto.class);
    }

    public String[] login(LoginDto loginDto) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                loginDto.getEmail(), loginDto.getPassword()
//        ));
//
//        User user = (User) authentication.getPrincipal();

        // Fetch the user by email
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid credentials: Email not found"));

        // Verify the password manually
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials: Incorrect password");
        }

        String[] arr = new String[2];
        arr[0] = jwtService.generateAccessToken(user);
        arr[1] = jwtService.generateRefreshToken(user);

        return arr;
    }

    public String refreshToken(String refreshToken){
        Long id = jwtService.getUserIdFromToken(refreshToken);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with this id"));

        return jwtService.generateAccessToken(user);
    }

}
