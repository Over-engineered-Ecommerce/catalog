package com.overengineeredecommerce.application.service;

import com.overengineeredecommerce.application.exception.NotFound;
import com.overengineeredecommerce.application.exception.UniqueInsertConstraint;
import com.overengineeredecommerce.application.repository.CategoryRepository;
import com.overengineeredecommerce.domain.entity.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public static final String CATEGORY_NOT_FOUND = "Category not found";

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category) {
        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new UniqueInsertConstraint("Category with name '" + category.getName() + "' already exists");
        }
    }

    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFound(CATEGORY_NOT_FOUND));
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name)
                .orElseThrow(() -> new NotFound(CATEGORY_NOT_FOUND));
    }

    public void deleteCategoryById(UUID id) {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, CATEGORY_NOT_FOUND);
        }
    }
}