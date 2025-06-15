package org.example.eatwarsaw.controller;

import jakarta.validation.Valid;
import org.example.eatwarsaw.dto.CategoryDto;
import org.example.eatwarsaw.dto.PlaceDto;
import org.example.eatwarsaw.model.Place;
import org.example.eatwarsaw.service.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/places")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    public List<PlaceDto> getAll() {
        return placeService.getAll();
    }

    @GetMapping("/{id}")
    public PlaceDto getById(@PathVariable Long id) {
        return placeService.getById(id);
    }

    @PostMapping
    public PlaceDto create(@Valid @RequestBody PlaceDto dto) {
        return placeService.createPlace(dto);
    }

    @PutMapping("/{id}")
    public PlaceDto update(@PathVariable Long id, @Valid @RequestBody PlaceDto dto) {
        return placeService.updatePlace(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        placeService.delete(id);
    }
}

