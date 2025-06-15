package org.example.eatwarsaw.service;

import org.example.eatwarsaw.dto.CategoryDto;
import org.example.eatwarsaw.mapper.CategoryMapper;
import org.example.eatwarsaw.model.Category;
import org.example.eatwarsaw.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;
    private final ImageStorageService imageStorageService;

    public CategoryService(CategoryRepository repository, CategoryMapper mapper, ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = repository.findAll();
        return mapper.toDtoList(categories);
    }

    public CategoryDto createCategory(String name, String author, String description, MultipartFile image) {
        String imageUrl = imageStorageService.saveImage(image, "categories");
        Category category = new Category();
        category.setName(name);
        category.setAuthor(author);
        category.setImageUrl(imageUrl);
        category.setDescription(description);
        repository.save(category);
        return mapper.toDto(category);
    }
}