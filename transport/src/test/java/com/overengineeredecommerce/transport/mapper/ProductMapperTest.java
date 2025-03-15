package com.overengineeredecommerce.transport.mapper;

import com.overengineeredecommerce.domain.entity.Category;
import com.overengineeredecommerce.domain.entity.Product;
import com.overengineeredecommerce.transport.dto.ProductRequestDto;
import com.overengineeredecommerce.transport.dto.ProductResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

class ProductMapperTest {

    @Test
    void toProduct() {
        HashSet<Category> categories = new HashSet<>();
        categories.add(new Category(UUID.randomUUID(), "Technology"));
        HashMap<String , String > details = new HashMap<>();
        details.put("color", "black");


        Product product  = new Product(UUID.randomUUID(), "Phone", "Xiaomi", "7898567779018", categories, details);
        ProductResponseDto dto = ProductMapper.INSTANCE.fromProduct(product);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(product.getProductId(), dto.productId());
        Assertions.assertEquals(product.getName(), dto.name());
        Assertions.assertEquals(product.getBrand(), dto.brand());
        Assertions.assertEquals(product.getEan(), dto.ean());
        Assertions.assertEquals(product.getCategories(), dto.categories());
        Assertions.assertEquals(product.getDetails(), dto.details());

    }

    @Test
    void toProductNullCategory() {
        HashSet<Category> categories = null;
        HashMap<String , String > details = new HashMap<>();
        details.put("color", "black");

        Product product  = new Product(UUID.randomUUID(), "Phone", "Xiaomi", "7898567779018", categories, details);
        ProductResponseDto dto = ProductMapper.INSTANCE.fromProduct(product);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(product.getProductId(), dto.productId());
        Assertions.assertEquals(product.getName(), dto.name());
        Assertions.assertEquals(product.getBrand(), dto.brand());
        Assertions.assertEquals(product.getEan(), dto.ean());
        Assertions.assertEquals(product.getCategories(), dto.categories());
        Assertions.assertEquals(product.getDetails(), dto.details());

    }

    @Test
    void fromProduct() {

        HashSet<Category> categories = new HashSet<>();
        categories.add(new Category(UUID.randomUUID(), "Technology"));
        HashMap<String , String > details = new HashMap<>();
        details.put("color", "black");

        ProductRequestDto dto = new ProductRequestDto("Phone", "Xiaomi", "7898567779018", categories, details);
        Product product = ProductMapper.INSTANCE.toProduct(dto);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(dto.getName(), product.getName());
        Assertions.assertEquals(dto.getBrand(), product.getBrand());
        Assertions.assertEquals(dto.getEan(), product.getEan());
        Assertions.assertEquals(dto.getCategories(), product.getCategories());
        Assertions.assertEquals(dto.getDetails(), product.getDetails());
    }

}