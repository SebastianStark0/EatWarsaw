package org.example.eatwarsaw.controller;

import org.example.eatwarsaw.dto.CategoryDto;
import org.example.eatwarsaw.dto.create.UserProfileDto;
import org.example.eatwarsaw.service.FriendshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendshipController {

    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }


    @PostMapping("/{friendId}")
    public ResponseEntity<Void> addFriend(@PathVariable Long friendId) {
        friendshipService.addFriend(friendId);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<UserProfileDto>> getFriends() {
        return ResponseEntity.ok(friendshipService.getFriends());
    }

    @GetMapping("/friends/categories")
    public ResponseEntity<List<CategoryDto>> getFriendsCategories() {
        return ResponseEntity.ok(friendshipService.getFriendsCategories());
    }
}
