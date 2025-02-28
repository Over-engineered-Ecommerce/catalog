package com.overengineeredecommerce.transport.mapper;


import com.overengineeredecommerce.domain.entity.Category;
import com.overengineeredecommerce.transport.dto.CategoryRequestDto;
import com.overengineeredecommerce.transport.dto.CategoryResponseDto;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto fromCategory(Category category);
}
