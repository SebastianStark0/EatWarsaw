package org.example.eatwarsaw.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    private String author;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Min(1)
    @Max(5)
    private Integer rating;

    private LocalDateTime createdAt = LocalDateTime.now();

}