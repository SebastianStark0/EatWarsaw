package org.example.eatwarsaw.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.example.eatwarsaw.enums.FriendshipStatus;
import org.example.eatwarsaw.model.user.User;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "friendships",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "friend_id"}))
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private FriendshipStatus status; // PENDING, ACCEPTED, BLOCKED
}
