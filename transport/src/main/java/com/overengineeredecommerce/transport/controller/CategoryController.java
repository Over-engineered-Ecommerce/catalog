package com.overengineeredecommerce.transport.controller;

import com.overengineeredecommerce.application.service.CategoryService;
import com.overengineeredecommerce.domain.entity.Category;
import com.overengineeredecommerce.transport.HttpResponse;
import com.overengineeredecommerce.transport.dto.CategoryRequestDto;
import com.overengineeredecommerce.transport.dto.CategoryResponseDto;
import com.overengineeredecommerce.transport.mapper.CategoryMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

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
    public ResponseEntity<Stream<CategoryResponseDto>> getCategories() {
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories.stream().map(CategoryMapper.INSTANCE::fromCategory));
    }

    @Operation(
            summary = "Retrieves a category by its ID",
            description = "Fetches a specific category using its unique identifier",
            tags = {"Category"}
    )
    @GetMapping("/category")
    public ResponseEntity<HttpResponse> getCategoryById(@RequestParam UUID id) {
        Category categoryResponse = categoryService.getCategoryById(id);
        CategoryResponseDto categoryResponseDto = CategoryMapper.INSTANCE.fromCategory(categoryResponse);
        return ResponseEntity.ok(categoryResponseDto);
    }

    @Operation(
            summary = "Retrieves a category by its name",
            description = "Fetches a specific category using its name. The search is case-sensitive.",
            tags = {"Category"}
    )
    @GetMapping("/categories/search")
    public ResponseEntity<HttpResponse> getCategoryByName(@RequestParam(name = "name") String name) {
        Category categoryResponse = categoryService.getCategoryByName(name);
        CategoryResponseDto categoryResponseDto = CategoryMapper.INSTANCE.fromCategory(categoryResponse);
        return ResponseEntity.ok(categoryResponseDto);
    }


    @Operation(
            summary = "Deletes a category by its ID",
            description = "Removes a specific category from the system using its unique identifier",
            tags = {"Category"}
    )
    @DeleteMapping("/category")
    public ResponseEntity<HttpResponse> deleteCategoryById(@RequestParam(name = "id") UUID id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Create a new category",
            description = "Creates a new category in the system with the provided details",
            tags = {"Category"}
    )
    @PostMapping("/category")
    public ResponseEntity<HttpResponse> createCategory(@RequestBody @Valid CategoryRequestDto request) {
        Category category = CategoryMapper.INSTANCE.toCategory(request);
        Category categoryResponse = categoryService.createCategory(category);

        URI location = URI.create("/category?id=" + categoryResponse.getCategoryId());
        return ResponseEntity.created(location)
                .body(CategoryMapper.INSTANCE.fromCategory(categoryResponse));
    }



}
