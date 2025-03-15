package com.overengineeredecommerce.transport.mapper;

import com.overengineeredecommerce.domain.entity.Category;
import com.overengineeredecommerce.transport.dto.CategoryRequestDto;
import com.overengineeredecommerce.transport.dto.CategoryResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class CategoryMapperTest {

    @Test
    void toCategory() {
        Category category = new Category(UUID.randomUUID(), "Technology");
        CategoryResponseDto dto = CategoryMapper.INSTANCE.fromCategory(category);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(category.getCategoryId(), dto.categoryId());
        Assertions.assertEquals(category.getName(), dto.name());
    }

    @Test
    void fromCategory() {
        CategoryRequestDto dto = new CategoryRequestDto("Technology");
        Category category = CategoryMapper.INSTANCE.toCategory(dto);
        Assertions.assertNotNull(category);
        Assertions.assertEquals(dto.getName(), category.getName());

    }
}