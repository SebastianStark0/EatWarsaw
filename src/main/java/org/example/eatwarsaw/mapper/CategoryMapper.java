package org.example.eatwarsaw.mapper;

import org.example.eatwarsaw.dto.CategoryDto;
import org.example.eatwarsaw.model.Category;
import org.example.eatwarsaw.model.Place;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDto toDto(Category category) {
        if (category == null) return null;
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setImageUrl(category.getImageUrl());
        if (category.getAuthor() != null) {
            dto.setAuthorId(category.getAuthor().getId());
            dto.setAuthorName(category.getAuthor().getName());
        }

        if (category.getPlaces() != null) {
            List<Long> placeIds = category.getPlaces()
                    .stream()
                    .map(Place::getId)
                    .toList();
            dto.setPlaceIds(placeIds);
        } else {
            dto.setPlaceIds(Collections.emptyList());
        }

        return dto;
    }

    public Category toEntity(CategoryDto dto) {
        if (dto == null) return null;

        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setImageUrl(dto.getImageUrl());
        return category;
    }

    public List<CategoryDto> toDtoList(List<Category> categories) {
        if (categories == null) return Collections.emptyList();
        return categories.stream()
                .map(this::toDto)
                .toList();
    }

    public Set<Category> toEntityList(Set<CategoryDto> dtos) {
        if (dtos == null) return Collections.emptySet();
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}
