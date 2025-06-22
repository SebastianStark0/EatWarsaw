package org.example.eatwarsaw.controller;

import lombok.RequiredArgsConstructor;
import org.example.eatwarsaw.config.services.UserDetailsImpl;
import org.example.eatwarsaw.dto.UserDto;
import org.example.eatwarsaw.dto.create.UserProfileDto;
import org.example.eatwarsaw.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<UserDto> user = userService.findByEmail(userDetails.getUsername());
        return ResponseEntity.ok(user.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userDetails.getUsername())));
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
