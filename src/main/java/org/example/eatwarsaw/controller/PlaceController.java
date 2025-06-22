package org.example.eatwarsaw.controller;

import jakarta.validation.Valid;
import org.example.eatwarsaw.dto.PlaceDto;
import org.example.eatwarsaw.dto.create.PlaceCreateDto;
import org.example.eatwarsaw.service.PlaceService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PlaceDto> createPlace(
            @RequestPart("place") @Valid PlaceCreateDto placeDto,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {

        PlaceDto created = placeService.createPlace(placeDto, imageFile);
        return ResponseEntity.ok(created);
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

