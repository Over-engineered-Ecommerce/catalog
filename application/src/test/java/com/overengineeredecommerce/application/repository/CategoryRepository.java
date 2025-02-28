package com.overengineeredecommerce.application.repository;


import com.overengineeredecommerce.domain.entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface CategoryRepository {
    List<Category> findAll();
    Category save(Category category);
    Optional<Category> findById(UUID id);
    Optional<Category> getCategoryByName(String name);
    void deleteById(UUID id);
}
