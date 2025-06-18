package org.example.eatwarsaw.dto.create;


import lombok.Data;
import org.example.eatwarsaw.enums.Gender;

import java.time.LocalDate;

@Data
public class UserProfileDto {
    private String name;
    private String avatarUrl;
    private String bio;
    private LocalDate birthDate;
    private Gender gender;
}