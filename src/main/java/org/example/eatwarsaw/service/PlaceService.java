package org.example.eatwarsaw.service;

import org.example.eatwarsaw.dto.CategoryDto;
import org.example.eatwarsaw.dto.PlaceDto;
import org.example.eatwarsaw.exception.PlaceNotFoundException;
import org.example.eatwarsaw.mapper.CategoryMapper;
import org.example.eatwarsaw.mapper.PlaceMapper;
import org.example.eatwarsaw.model.Category;
import org.example.eatwarsaw.model.Place;
import org.example.eatwarsaw.repository.CategoryRepository;
import org.example.eatwarsaw.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final CategoryRepository categoryRepository;
    private final PlaceMapper placeMapper;
    private final CategoryMapper categoryMapper;

    public PlaceService(PlaceRepository placeRepository, CategoryRepository categoryRepository, PlaceMapper placeMapper, CategoryMapper categoryMapper) {
        this.placeMapper = placeMapper;
        this.categoryMapper = categoryMapper;
        this.placeRepository = placeRepository;
        this.categoryRepository = categoryRepository;
    }

    public PlaceDto createPlace(PlaceDto dto) {

        Set<Category> categories = Optional.ofNullable(dto.getCategoryIds())
                .orElse(Collections.emptySet())
                .stream()
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Category not found: " + id)))
                .collect(Collectors.toSet());

        Place place = placeMapper.toEntity(dto, categories);
        Place saved = placeRepository.save(place);
        return placeMapper.toDto(saved);
    }

    public PlaceDto updatePlace(Long id, PlaceDto dto) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Place not found: " + id));
        Set<Category> categories = Optional.ofNullable(dto.getCategoryIds())
                .orElse(Collections.emptySet())
                .stream()
                .map(catId -> categoryRepository.findById(catId)
                        .orElseThrow(() -> new RuntimeException("Category not found: " + catId)))
                .collect(Collectors.toSet());
        placeMapper.updateEntityFromDto(dto, place, categories);
        return placeMapper.toDto(placeRepository.save(place));
    }

    public List<PlaceDto> getAll() {
        List<Place> places = placeRepository.findAll();
        return placeMapper.toDtoList(places);
    }

    public PlaceDto getById(Long id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Place not found"));
        return placeMapper.toDto(place);
    }

    public void delete(Long id) {
        if (!placeRepository.existsById(id)) {
            throw new PlaceNotFoundException(id);
        }
        placeRepository.deleteById(id);
    }
}
