package org.example.eatwarsaw.mapper;

import org.example.eatwarsaw.dto.UserDto;
import org.example.eatwarsaw.dto.UserShortDto;
import org.example.eatwarsaw.dto.create.UserProfileDto;
import org.example.eatwarsaw.model.user.User;
import org.springframework.stereotype.Component;

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


    public UserShortDto toShortDto(User user) {
        if (user == null) return null;
        return new UserShortDto(user.getId(), user.getName(), user.getUsername(), user.getAvatarUrl());
    }

    public static UserProfileDto toProfileDto(User user) {
        UserProfileDto dto = new UserProfileDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAvatarUrl(user.getAvatarUrl());
        dto.setBio(user.getBio());
        dto.setBirthDate(user.getBirthDate());
        dto.setGender(user.getGender());
        return dto;
    }

    public static void updateUserFromDto(User user, UserProfileDto dto) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAvatarUrl(dto.getAvatarUrl());
        user.setBio(dto.getBio());
        user.setBirthDate(dto.getBirthDate());
        user.setGender(dto.getGender());
    }

}
