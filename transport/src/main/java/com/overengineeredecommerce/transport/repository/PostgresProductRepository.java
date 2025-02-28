package com.overengineeredecommerce.transport.repository;

import com.overengineeredecommerce.application.repository.ProductRepository;
import com.overengineeredecommerce.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostgresProductRepository extends JpaRepository<Product, UUID>, ProductRepository {
    Optional<Product> getProductByName(String name);
}