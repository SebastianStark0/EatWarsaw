package org.example.eatwarsaw.service;

import org.example.eatwarsaw.dto.CategoryDto;
import org.example.eatwarsaw.dto.create.UserProfileDto;
import org.example.eatwarsaw.mapper.UserMapper;
import org.example.eatwarsaw.model.Friendship;
import org.example.eatwarsaw.model.User;
import org.example.eatwarsaw.repository.FriendshipRepository;
import org.example.eatwarsaw.repository.UserRepository;
import org.example.eatwarsaw.util.JwtUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FriendshipService {
    private final UserService userService;
    private final UserRepository userRepository;

    private final FriendshipRepository friendshipRepository;

    public FriendshipService(UserService userService,
                             UserRepository userRepository,
                             FriendshipRepository friendshipRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
    }

    public void addFriend(Long friendId) {
        User user = userService.getCurrentUser();
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new UsernameNotFoundException("Friend not found"));

        if (friend.equals(user)) {
            throw new IllegalArgumentException("Cannot add yourself as a friend");
        }

        boolean exists = friendshipRepository.existsByUserAndFriend(user, friend);
        if (exists) {
            throw new IllegalArgumentException("Friend already added");
        }

        Friendship friendship = new Friendship();
        friendship.setUser(user);
        friendship.setFriend(friend);
        friendship.setCreatedAt(LocalDateTime.now());
        friendshipRepository.save(friendship);
    }

    public List<UserProfileDto> getFriends() {
        User user = userService.getCurrentUser();
        List<Friendship> friendships = friendshipRepository.findAllByUser(user);
        return friendships.stream()
                .map(f -> UserMapper.toProfileDto(f.getFriend()))
                .toList();
    }

    public List<CategoryDto> getFriendsCategories() {
        List<UserProfileDto> friends = getFriends();
        List<CategoryDto> categories = new ArrayList<>();

        for (UserProfileDto friend : friends) {
//            List<CategoryDto> friendCategories = getCategoriesByUserId(friend.getId());
//            categories.addAll(friendCategories);
        }

        return categories;
    }
}
