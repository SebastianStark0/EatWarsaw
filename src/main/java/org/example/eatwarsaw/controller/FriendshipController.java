package org.example.eatwarsaw.controller;

import lombok.RequiredArgsConstructor;
import org.example.eatwarsaw.dto.FriendshipDto;
import org.example.eatwarsaw.dto.UserShortDto;
import org.example.eatwarsaw.model.user.User;
import org.example.eatwarsaw.service.FriendshipService;
import org.example.eatwarsaw.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendshipController {

    private final FriendshipService friendshipService;

    @PostMapping("/request/{friendId}")
    public ResponseEntity<String> sendFriendRequest(@PathVariable Long friendId) {
        friendshipService.sendFriendRequest(friendId);
        return ResponseEntity.ok("Friend request sent");
    }

    @PostMapping("/accept/{requestId}")
    public ResponseEntity<String> acceptFriendRequest(@PathVariable Long requestId) {
        friendshipService.acceptFriendRequest(requestId);
        return ResponseEntity.ok("Friend request accepted");
    }

    @DeleteMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectFriendRequest(@PathVariable Long requestId) {
        friendshipService.rejectFriendRequest(requestId);
        return ResponseEntity.ok("Friend request rejected");
    }

    @GetMapping
    public List<UserShortDto> getFriends() {
        return friendshipService.getFriends();
    }

    @DeleteMapping("/{friendId}")
    public ResponseEntity<String> removeFriend(@PathVariable Long friendId) {
        friendshipService.removeFriend(friendId);
        return ResponseEntity.ok("Friend removed");
    }

    @GetMapping("/requests/incoming")
    public List<FriendshipDto> getIncomingRequests() {
        return friendshipService.getIncomingRequests();
    }

    @GetMapping("/outgoing")
    public List<FriendshipDto> getOutgoingRequests() {
        return friendshipService.getOutgoingRequests();
    }

    @GetMapping("/status/{userId}")
    public ResponseEntity<FriendshipDto> getFriendshipStatus(@PathVariable Long userId) {
        return ResponseEntity.ok(friendshipService.getFriendshipWith(userId));
    }
}

