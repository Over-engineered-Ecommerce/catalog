package com.overengineeredecommerce.application.repository;


import com.overengineeredecommerce.domain.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ProductRepository {
    List<Product> findAll();
    Product save(Product product);
    Optional<Product> findById(UUID id);
    Optional<Product> getProductByName(String name);
    void deleteById(UUID id);
}
