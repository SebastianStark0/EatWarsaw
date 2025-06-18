package org.example.eatwarsaw.mapper;

import org.example.eatwarsaw.dto.PlaceDto;
import org.example.eatwarsaw.dto.UserDto;
import org.example.eatwarsaw.dto.create.PlaceCreateDto;
import org.example.eatwarsaw.dto.create.UserProfileDto;
import org.example.eatwarsaw.model.Category;
import org.example.eatwarsaw.model.Place;
import org.example.eatwarsaw.model.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setAvatarUrl(user.getAvatarUrl());
        return dto;
    }

    public static UserProfileDto toProfileDto(User user) {
        if (user == null) return null;
        UserProfileDto dto = new UserProfileDto();
        dto.setName(user.getName());
        dto.setAvatarUrl(user.getAvatarUrl());
        dto.setBio(user.getBio());
        dto.setBirthDate(user.getBirthDate());
        dto.setGender(user.getGender());
        return dto;
    }

    public static void updateUserFromDto(User user, UserProfileDto dto) {
        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getAvatarUrl() != null) user.setAvatarUrl(dto.getAvatarUrl());
        if (dto.getBio() != null) user.setBio(dto.getBio());
        if (dto.getBirthDate() != null) user.setBirthDate(dto.getBirthDate());
        if (dto.getGender() != null) user.setGender(dto.getGender());
    }

}
