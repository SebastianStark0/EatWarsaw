package org.example.eatwarsaw.repository;

import org.example.eatwarsaw.enums.FriendshipStatus;
import org.example.eatwarsaw.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    Optional<Friendship> findByUserIdAndFriendId(Long userId, Long friendId);
    List<Friendship> findByFriendIdAndStatus(Long friendId, FriendshipStatus status);
    List<Friendship> findByUserIdAndStatus(Long userId, FriendshipStatus status);
    List<Friendship> findByUserIdOrFriendIdAndStatus(Long userId, Long friendId, FriendshipStatus status);

    @Query("SELECT f FROM Friendship f WHERE ((f.user.id = :userId AND f.friend.id = :friendId) OR (f.user.id = :friendId AND f.friend.id = :userId)) AND f.status = 'ACCEPTED'")
    Optional<Friendship> findAcceptedByUserAndFriend(Long userId, Long friendId);

    @Query("SELECT f FROM Friendship f WHERE ((f.user.id = :userId AND f.friend.id = :otherUserId) OR (f.user.id = :otherUserId AND f.friend.id = :userId))")
    Optional<Friendship> findByUsers(Long userId, Long otherUserId);

    Optional<Friendship> findByFriendIdAndUserId(Long friendId, Long userId);

}