package org.example.eatwarsaw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eatwarsaw.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String avatarUrl;
    private String bio;
    private LocalDate birthDate;
    private Gender gender;
    private String provider;
    private String providerId;
    private LocalDateTime createdAt;
}