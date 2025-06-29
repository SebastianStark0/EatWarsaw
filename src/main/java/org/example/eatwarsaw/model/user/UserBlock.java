package org.example.eatwarsaw.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UserBlock {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User blocker;

    @ManyToOne
    private User blocked;
}
