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

    public CategoryService(CategoryRepository repository, CategoryMapper mapper, ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
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
        repository.save(category);
        return mapper.toDto(category);
    }
    public CategoryDto updateCategory(Long id, CategoryCreateDto dto, MultipartFile image) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        String imageUrl = (image == null || image.isEmpty())
                ? category.getImageUrl()  // зберігаємо старе, якщо нового нема
                : imageStorageService.saveImage(image, "categories");

        fillCategoryFromDto(category, dto, imageUrl);
        repository.save(category);
        return mapper.toDto(category);
    }


    private void fillCategoryFromDto(Category category, CategoryCreateDto dto, String imageUrl) {
        category.setName(dto.getName());
        category.setAuthor(dto.getAuthor());
        category.setDescription(dto.getDescription());
        category.setImageUrl(imageUrl);
    }

}