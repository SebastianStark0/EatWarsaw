package org.example.eatwarsaw.service;

import org.example.eatwarsaw.dto.CategoryDto;
import org.example.eatwarsaw.dto.PlaceDto;
import org.example.eatwarsaw.dto.create.PlaceCreateDto;
import org.example.eatwarsaw.exception.PlaceNotFoundException;
import org.example.eatwarsaw.mapper.CategoryMapper;
import org.example.eatwarsaw.mapper.PlaceMapper;
import org.example.eatwarsaw.model.Category;
import org.example.eatwarsaw.model.Place;
import org.example.eatwarsaw.repository.CategoryRepository;
import org.example.eatwarsaw.repository.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final CategoryRepository categoryRepository;
    private final PlaceMapper placeMapper;
    private final CategoryMapper categoryMapper;
    private final ImageStorageService imageStorageService;

    public PlaceService(PlaceRepository placeRepository, CategoryRepository categoryRepository, PlaceMapper placeMapper, CategoryMapper categoryMapper, ImageStorageService imageStorageService) {
        this.placeMapper = placeMapper;
        this.categoryMapper = categoryMapper;
        this.placeRepository = placeRepository;
        this.categoryRepository = categoryRepository;
        this.imageStorageService = imageStorageService;
    }

    public PlaceDto createPlace(PlaceCreateDto dto, MultipartFile imageFile) {
        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(dto.getCategoryIds()));

        String imageUrl = imageFile != null ? imageStorageService.saveImage(imageFile, "places") : "";

        Place place = new Place();
        place.setName(dto.getName());
        place.setAddress(dto.getAddress());
        place.setDescription(dto.getDescription());
        place.setImageUrl(imageUrl);
        place.setCategories(categories);
        place.setGoogleRatingsCount(0);
        place.setGoogleRating(0.0);
        place.setAppRatingsCount(0);
        place.setAppRating(0.0);

        place = placeRepository.save(place);
        return placeMapper.toDto(place);
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
