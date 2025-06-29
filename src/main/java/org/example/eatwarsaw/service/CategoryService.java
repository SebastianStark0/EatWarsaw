package org.example.eatwarsaw.service;

import org.example.eatwarsaw.dto.CategoryDto;
import org.example.eatwarsaw.dto.create.CategoryCreateDto;
import org.example.eatwarsaw.exception.ResourceNotFoundException;
import org.example.eatwarsaw.mapper.CategoryMapper;
import org.example.eatwarsaw.model.Category;
import org.example.eatwarsaw.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;
    private final ImageStorageService imageStorageService;
    private final UserService userService;

    public CategoryService(CategoryRepository repository, CategoryMapper mapper, UserService userService, ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
        this.userService = userService;
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = repository.findAll();
        return mapper.toDtoList(categories);
    }

    public CategoryDto createCategory(CategoryCreateDto dto, MultipartFile image) {
        String imageUrl = (image == null || image.isEmpty())
                ? "0.jpg"
                : imageStorageService.saveImage(image, "categories");

        Category category = new Category();
        fillCategoryFromDto(category, dto, imageUrl);
        category.setAuthor(userService.getCurrentUser());

        repository.save(category);
        return mapper.toDto(category);
    }

    public CategoryDto updateCategory(Long id, CategoryCreateDto dto, MultipartFile image) {
        Category category = findById(id);
        String imageUrl = (image == null || image.isEmpty())
                ? category.getImageUrl()
                : imageStorageService.saveImage(image, "categories");
        fillCategoryFromDto(category, dto, imageUrl);
        repository.save(category);
        return mapper.toDto(category);
    }

    private void fillCategoryFromDto(Category category, CategoryCreateDto dto, String imageUrl) {
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setImageUrl(imageUrl);
    }

    public Category findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }
}