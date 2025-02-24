package com.overengineeredecommerce.transport.service;


import com.overengineeredecommerce.domain.entity.Category;
import com.overengineeredecommerce.transport.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(UUID id) {
        return categoryRepository.getReferenceById(id);
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name).orElse(null);
    }
}
