package org.example.eatwarsaw.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.eatwarsaw.model.user.User;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "categories")
    private Set<Place> places;
}
