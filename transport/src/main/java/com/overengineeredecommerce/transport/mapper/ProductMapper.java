package com.overengineeredecommerce.transport.mapper;

import com.overengineeredecommerce.domain.entity.Product;
import com.overengineeredecommerce.transport.dto.ProductRequestDto;
import com.overengineeredecommerce.transport.dto.ProductResponseDto;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductRequestDto productRequestDto);
    ProductResponseDto fromProduct(Product product);
}