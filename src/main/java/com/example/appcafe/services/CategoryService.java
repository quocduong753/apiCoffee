package com.example.appcafe.services;

import com.example.appcafe.models.Category;
import com.example.appcafe.repository.CategoryRepository;
import com.example.appcafe.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final DrinkRepository drinkRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại đồ uống"));

        if (drinkRepository.existsByCategory(category)) {
            throw new RuntimeException("Không thể xoá: Đã có đồ uống thuộc loại này.");
        }

        categoryRepository.delete(category);
    }

}