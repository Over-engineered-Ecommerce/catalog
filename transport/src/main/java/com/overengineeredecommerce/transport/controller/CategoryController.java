package com.overengineeredecommerce.transport.controller;


import com.overengineeredecommerce.domain.entity.Category;
import com.overengineeredecommerce.transport.CategoryMapper;
import com.overengineeredecommerce.transport.dto.CategoryRequestDto;
import com.overengineeredecommerce.transport.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * Controller for category operations
 * <p> This class is responsible for handling the requests related to categories
 * and returning the responses to the client.
 * </p>
 */
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Retrieves a list of all available categories",
            description = "Retrieves a list of all available categories in the system",
            tags = {"Category"}
    )
    @GetMapping("/categories")
    public ResponseEntity<?> getCategories() {

        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }

    @Operation(
            summary = "Retrieves a category by its ID",
            description = "Fetches a specific category using its unique identifier",
            tags = {"Category"}
    )
    @GetMapping("/category")
    public ResponseEntity<?> getCategoryById(@RequestParam UUID id) {
        Category categoryResponse = categoryService.getCategoryById(id);
        return ResponseEntity.ok(CategoryMapper.INSTANCE.fromCategory(categoryResponse));
    }

    @Operation(
            summary = "Deletes a category by its ID",
            description = "Removes a specific category from the system using its unique identifier",
            tags = {"Category"}
    )
    @DeleteMapping("/category")
    public ResponseEntity<?> deleteCategoryById(@RequestParam(name = "id") UUID id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Retrieves a category by its name",
            description = "Fetches a specific category using its name. The search is case-sensitive.",
            tags = {"Category"}
    )
    @GetMapping("/categories/search")
    public ResponseEntity<?> getCategoryByName(@RequestParam(name = "name") String name) {
        Category categoryResponse = categoryService.getCategoryByName(name);
        return ResponseEntity.ok(CategoryMapper.INSTANCE.fromCategory(categoryResponse));
    }

    @Operation(
            summary = "Create a new category",
            description = "Creates a new category in the system with the provided details",
            tags = {"Category"}
    )
    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryRequestDto request) {
        Category category = CategoryMapper.INSTANCE.toCategory(request);
        Category categoryResponse = categoryService.createCategory(category);

        URI location = URI.create("/category?id=" + categoryResponse.getId());
        return ResponseEntity.created(location)
                .body(CategoryMapper.INSTANCE.fromCategory(categoryResponse));
    }



}
