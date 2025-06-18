package org.example.eatwarsaw.repository;

import org.example.eatwarsaw.model.Friendship;
import org.example.eatwarsaw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    boolean existsByUserAndFriend(User user, User friend);
    List<Friendship> findAllByUser(User user);
}