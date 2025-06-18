package org.example.eatwarsaw.controller;

import lombok.RequiredArgsConstructor;
import org.example.eatwarsaw.dto.UserDto;
import org.example.eatwarsaw.dto.create.UserProfileDto;
import org.example.eatwarsaw.dto.request.LoginRequest;
import org.example.eatwarsaw.dto.request.RegisterRequest;
import org.example.eatwarsaw.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest request) {
        UserDto createdUser = userService.registerUser(request);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token = userService.loginAndGetToken(loginRequest);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfileDto> getProfile(@PathVariable Long id) {
        UserProfileDto profile = userService.getProfile(id);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileDto> updateMyProfile(@RequestBody UserProfileDto dto) {
        UserProfileDto updatedProfile = userService.updateMyProfile(dto);
        return ResponseEntity.ok(updatedProfile);
    }
}
