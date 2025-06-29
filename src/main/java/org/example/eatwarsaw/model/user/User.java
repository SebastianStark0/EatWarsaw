package org.example.eatwarsaw.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eatwarsaw.enums.Gender;
import org.example.eatwarsaw.model.Category;
import org.example.eatwarsaw.model.Friendship;
import org.example.eatwarsaw.model.Place;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String name;
    private String email;
    private String avatarUrl;
    private String password;
    private String bio;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String provider;
    private String providerId;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "author")
    private Set<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "user_liked_places",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id")
    )
    private Set<Place> likedPlaces;

    @OneToMany(mappedBy = "user")
    private Set<Friendship> friendships = new HashSet<>();

    @OneToMany(mappedBy = "friend")
    private Set<Friendship> friendsOf = new HashSet<>();

}


