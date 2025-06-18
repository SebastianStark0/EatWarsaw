package org.example.eatwarsaw.controller;

import jakarta.validation.Valid;
import org.example.eatwarsaw.dto.CategoryDto;
import org.example.eatwarsaw.dto.create.CategoryCreateDto;
import org.example.eatwarsaw.mapper.CategoryMapper;
import org.example.eatwarsaw.model.Category;
import org.example.eatwarsaw.repository.CategoryRepository;
import org.example.eatwarsaw.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getAll() {
        return categoryService.getAllCategories();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CategoryDto> createCategory(
            @RequestPart("category") @Valid CategoryCreateDto categoryDto,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        return ResponseEntity.ok(categoryService.createCategory(categoryDto, image));
    }
}
