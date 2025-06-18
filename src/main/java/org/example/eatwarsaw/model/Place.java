package org.example.eatwarsaw.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String address;

    private Integer googleRatingsCount;
    private Double googleRating;

    private Integer appRatingsCount;
    private Double appRating;

    @NonNull
    private String imageUrl;

    private String description;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "place_category",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Place(String name, Set<Category> categories, String address, String imageUrl, String description) {
        this.name = name;
        this.categories = categories;
        this.address = address;
        this.googleRatingsCount = 0;
        this.googleRating = 0.00;
        this.appRatingsCount = 0;
        this.appRating = 0.00;
        this.imageUrl = imageUrl;
        this.description = description;
    }
}

