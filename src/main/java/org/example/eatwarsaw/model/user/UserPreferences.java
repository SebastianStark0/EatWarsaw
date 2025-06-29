package org.example.eatwarsaw.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPreferences {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    private User user;

    private boolean darkMode = false;
    private boolean notificationsEnabled = true;
}
