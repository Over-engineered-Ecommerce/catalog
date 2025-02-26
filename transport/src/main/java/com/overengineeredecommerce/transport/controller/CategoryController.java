package com.overengineeredecommerce.transport.controller;



import com.overengineeredecommerce.domain.entity.Category;
import com.overengineeredecommerce.transport.CategoryMapper;
import com.overengineeredecommerce.transport.dto.CategoryRequestDto;
import com.overengineeredecommerce.transport.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Get all categories")
    @GetMapping("/categories")
    public ResponseEntity<?> getCategories() {

        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }

    @Operation(summary = "Get category by Id")
    @GetMapping("/category")
    public ResponseEntity<?> getCategoryById(@RequestParam UUID id) {
        Category categoryResponse = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryResponse);
    }

    @Operation(summary = "Get category by Name")
    @GetMapping("/category/byName")
    public ResponseEntity<?> getCategoryByName(@RequestParam String name) {

        Category categoryResponse = categoryService.getCategoryByName(name);
        return ResponseEntity.ok(categoryResponse);
    }

    @Operation(summary = "Create a new category")
    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryRequestDto request) {

        Category category = CategoryMapper.INSTANCE.toCategory(request);
        Category categoryResponse = categoryService.createCategory(category);

        return ResponseEntity.ok(CategoryMapper.INSTANCE.fromCategory(categoryResponse));
    }
}
