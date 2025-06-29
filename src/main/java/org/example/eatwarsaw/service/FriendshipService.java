package org.example.eatwarsaw.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.eatwarsaw.dto.FriendshipDto;
import org.example.eatwarsaw.dto.UserShortDto;
import org.example.eatwarsaw.enums.FriendshipStatus;
import org.example.eatwarsaw.mapper.FriendshipMapper;
import org.example.eatwarsaw.mapper.UserMapper;
import org.example.eatwarsaw.model.Friendship;
import org.example.eatwarsaw.model.user.User;
import org.example.eatwarsaw.repository.FriendshipRepository;
import org.example.eatwarsaw.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final UserMapper userMapper;
    private final UserService userService;
    private final FriendshipMapper friendshipMapper;

    public void sendFriendRequest(Long friendId) {
        User currentUser = userService.getCurrentUser();
        if (currentUser.getId().equals(friendId)) {
            throw new IllegalArgumentException("You cannot add yourself as a friend.");
        }
        if (friendshipRepository.findByUserIdAndFriendId(currentUser.getId(), friendId).isPresent() ||
                friendshipRepository.findByUserIdAndFriendId(friendId, currentUser.getId()).isPresent()) {
            throw new IllegalStateException("Friend request already sent or already friends.");
        }
        User friend = userService.findUserById(friendId);
        Friendship friendship = Friendship.builder()
                .user(currentUser)
                .friend(friend)
                .status(FriendshipStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        friendshipRepository.save(friendship);
    }

    public void acceptFriendRequest(Long requestId) {
        User currentUser = userService.getCurrentUser();
        Friendship friendship = findById(requestId);

        if (!friendship.getFriend().getId().equals(currentUser.getId())) {
            throw new SecurityException("You are not allowed to accept this request.");
        }

        friendship.setStatus(FriendshipStatus.ACCEPTED);
        friendshipRepository.save(friendship);
    }

    public void rejectFriendRequest(Long requestId) {
        User currentUser = userService.getCurrentUser();

        Friendship friendship = findById(requestId);

        if (!friendship.getFriend().getId().equals(currentUser.getId())) {
            throw new SecurityException("You are not allowed to reject this request.");
        }
        friendship.setStatus(FriendshipStatus.REJECTED);
        friendshipRepository.save(friendship);
    }

    public FriendshipStatus getFriendshipStatus(Long otherUserId) {
        User currentUser = userService.getCurrentUser();
        return friendshipRepository.findByUsers(currentUser.getId(), otherUserId)
                .map(Friendship::getStatus)
                .orElse(null);
    }

    public List<UserShortDto> getFriends() {
        User currentUser = userService.getCurrentUser();
        List<Friendship> friendships = friendshipRepository
                .findByUserIdOrFriendIdAndStatus(currentUser.getId(), currentUser.getId(), FriendshipStatus.ACCEPTED);
        return friendships.stream()
                .map(f -> {
                    User friend = f.getUser().getId().equals(currentUser.getId()) ? f.getFriend() : f.getUser();
                    return userMapper.toShortDto(friend);
                })
                .toList();
    }

    @Transactional
    public void removeFriend(Long friendId) {
        User currentUser = userService.getCurrentUser();
        Optional<Friendship> f1 = friendshipRepository.findByUserIdAndFriendId(currentUser.getId(), friendId);
        Optional<Friendship> f2 = friendshipRepository.findByFriendIdAndUserId(currentUser.getId(), friendId);
        if (f1.isPresent()) {
            friendshipRepository.delete(f1.get());
        } else if (f2.isPresent()) {
            friendshipRepository.delete(f2.get());
        } else {
            throw new EntityNotFoundException("Friendship not found");
        }
    }

    public Friendship findById(Long requestId) {
        return friendshipRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Friend request not found"));
    }

    public List<FriendshipDto> getIncomingRequests() {
        User currentUser = userService.getCurrentUser();
        List<Friendship> requests = friendshipRepository.findByFriendIdAndStatus(currentUser.getId(), FriendshipStatus.PENDING);
        return requests.stream()
                .map(friendshipMapper::toDto)
                .toList();

    }

    public List<FriendshipDto> getOutgoingRequests() {
        User currentUser = userService.getCurrentUser();
        return friendshipRepository.findByUserIdAndStatus(currentUser.getId(), FriendshipStatus.PENDING).stream()
                .map(friendshipMapper::toDto)
                .toList();
    }

    public FriendshipDto getFriendshipWith(Long userId) {
        User currentUser = userService.getCurrentUser();
        return friendshipRepository.findByUsers(currentUser.getId(), userId)
                .map(friendshipMapper::toDto)
                .orElse(null);
    }
}
