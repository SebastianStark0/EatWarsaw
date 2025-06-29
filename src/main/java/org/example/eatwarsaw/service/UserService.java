package org.example.eatwarsaw.service;

import org.example.eatwarsaw.dto.UserDto;
import org.example.eatwarsaw.dto.UserShortDto;
import org.example.eatwarsaw.dto.create.UserProfileDto;
import org.example.eatwarsaw.mapper.UserMapper;
import org.example.eatwarsaw.model.user.CustomUserDetails;
import org.example.eatwarsaw.model.user.User;
import org.example.eatwarsaw.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserDto findByEmail(String email) {
        return userMapper.toDto(findUserByEmail(email));
    }

    public UserDto getUserById(Long id) {
        return userMapper.toDto(findUserById(id));
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

    public List<UserShortDto> searchUsers(String query) {
        List<User> users = userRepository.searchUsers(query);
        return users.stream().map(userMapper::toShortDto).toList();
    }


    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails userDetails) {
            return findUserById(userDetails.getId());
        }
        throw new UsernameNotFoundException("User not authenticated");
    }

}
