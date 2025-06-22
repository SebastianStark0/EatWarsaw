package org.example.eatwarsaw.service;

import org.example.eatwarsaw.dto.UserDto;
import org.example.eatwarsaw.dto.create.UserProfileDto;
import org.example.eatwarsaw.dto.request.LoginRequest;
import org.example.eatwarsaw.dto.request.RegisterRequest;
import org.example.eatwarsaw.mapper.UserMapper;
import org.example.eatwarsaw.model.User;
import org.example.eatwarsaw.repository.UserRepository;
import org.example.eatwarsaw.util.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       EmailService emailService,
                       UserMapper userMapper,
                       JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }


    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDto);
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserProfileDto getProfile(Long userId) {
        User user = findUserById(userId);
        return UserMapper.toProfileDto(user);

    }
    public UserProfileDto updateMyProfile(UserProfileDto dto) {
        User user = getCurrentUser();
        UserMapper.updateUserFromDto(user, dto);
        userRepository.save(user);
        return UserMapper.toProfileDto(user);
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return userRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }
        throw new UsernameNotFoundException("User not authenticated");
    }




    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
