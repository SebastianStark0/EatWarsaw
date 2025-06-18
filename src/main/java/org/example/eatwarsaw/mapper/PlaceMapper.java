package org.example.eatwarsaw.mapper;

import org.example.eatwarsaw.dto.PlaceDto;
import org.example.eatwarsaw.dto.create.PlaceCreateDto;
import org.example.eatwarsaw.model.Category;
import org.example.eatwarsaw.model.Place;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PlaceMapper {

    public PlaceDto toDto(Place place) {
        if (place == null) return null;

        PlaceDto dto = new PlaceDto();
        dto.setId(place.getId());
        dto.setName(place.getName());
        dto.setAddress(place.getAddress());
        dto.setGoogleRatingsCount(place.getGoogleRatingsCount());
        dto.setGoogleRating(place.getGoogleRating());
        dto.setAppRatingsCount(place.getAppRatingsCount());
        dto.setAppRating(place.getAppRating());
        dto.setImageUrl(place.getImageUrl());

        Set<Long> categoryIds = place.getCategories() != null
                ? place.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet())
                : Collections.emptySet();

        dto.setCategoryIds(categoryIds);

        return dto;
    }

    public Place toEntity(PlaceDto dto, Set<Category> categories) {
        if (dto == null) return null;

        Place place = new Place();
        place.setId(dto.getId());
        place.setName(dto.getName());
        place.setAddress(dto.getAddress());
        place.setGoogleRatingsCount(dto.getGoogleRatingsCount());
        place.setGoogleRating(dto.getGoogleRating());
        place.setAppRatingsCount(dto.getAppRatingsCount());
        place.setAppRating(dto.getAppRating());
        place.setImageUrl(dto.getImageUrl());
        place.setCategories(categories);

        return place;
    }

    public List<PlaceDto> toDtoList(List<Place> places) {
        if (places == null) return Collections.emptyList();
        return places.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateEntityFromDto(PlaceDto dto, Place place, Set<Category> categories) {
        if (dto.getName() != null) place.setName(dto.getName());
        if (dto.getAddress() != null) place.setAddress(dto.getAddress());
        if (dto.getGoogleRatingsCount() != null) place.setGoogleRatingsCount(dto.getGoogleRatingsCount());
        if (dto.getGoogleRating() != null) place.setGoogleRating(dto.getGoogleRating());
        if (dto.getAppRatingsCount() != null) place.setAppRatingsCount(dto.getAppRatingsCount());
        if (dto.getAppRating() != null) place.setAppRating(dto.getAppRating());
        if (dto.getImageUrl() != null) place.setImageUrl(dto.getImageUrl());
        if (categories != null) place.setCategories(categories);
    }

    public static Place toEntity(PlaceCreateDto dto, Set<Category> categories) {
        Place place = new Place();
        place.setName(dto.getName());
        place.setAddress(dto.getAddress());
        place.setDescription(dto.getDescription());
        place.setImageUrl(dto.getImageUrl());
        place.setCategories(categories);
        place.setGoogleRatingsCount(0);
        place.setGoogleRating(0.0);
        place.setAppRatingsCount(0);
        place.setAppRating(0.0);
        return place;
    }
}
