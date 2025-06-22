package org.example.eatwarsaw.service.login;

import org.example.eatwarsaw.dto.UserDto;
import org.example.eatwarsaw.dto.request.LoginRequest;
import org.example.eatwarsaw.dto.request.RegisterRequest;
import org.example.eatwarsaw.mapper.UserMapper;
import org.example.eatwarsaw.model.User;
import org.example.eatwarsaw.repository.UserRepository;
import org.example.eatwarsaw.service.EmailService;
import org.example.eatwarsaw.service.UserService;
import org.example.eatwarsaw.util.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;


    public LoginService(UserRepository userRepository,
                        PasswordEncoder passwordEncoder,
                        EmailService emailService,
                        UserService userService,
                        UserMapper userMapper,
                        JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }


    public UserDto registerUser(RegisterRequest request) {
        checkEmailNotTaken(request.getEmail());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setAvatarUrl(request.getAvatarUrl());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }


    public UserDto login(LoginRequest request) {
        User user = authenticate(request.getEmail(), request.getPassword());
        return userMapper.toDto(user);
    }

    public String loginAndGetToken(LoginRequest request) {
        User user = authenticate(request.getEmail(), request.getPassword());
        return jwtUtil.generateToken(user.getEmail());
    }


    public void updatePassword(String username, String newPassword) {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }


    public boolean sendPasswordResetEmail(String email) {
        Optional<UserDto> userDtoOpt = userService.findByEmail(email);
        if (userDtoOpt.isEmpty()) {
            return false;
        }
        UserDto userDto = userDtoOpt.get();

        String token = jwtUtil.generateToken(userDto.getName());  // або getEmail() залежно від логіки
        String resetLink = "https://yourfrontend/reset-password?token=" + token;

        emailService.sendSimpleMessage(email, "Password Reset Request",
                "Click the link to reset your password: " + resetLink);

        return true;
    }

    public boolean resetPassword(String token, String newPassword) {
        if (jwtUtil.isTokenExpired(token)) {
            return false;
        }
        String username = jwtUtil.extractUsername(token);
        updatePassword(username, newPassword);
        return true;
    }

    public String loginOrRegisterFirebaseUser(String email, String name) {
        if (email == null) {
            throw new IllegalArgumentException("Email is required");
        }

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setName(name != null ? name : "User from Firebase");
                    return userRepository.save(newUser);
                });

        return jwtUtil.generateToken(user.getEmail());
    }

    private void checkEmailNotTaken(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already in use");
        }
    }

    private User authenticate(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return user;
    }
}
