package org.example.eatwarsaw.repository;

import org.example.eatwarsaw.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findByCategories_NameIgnoreCase(String name);

}