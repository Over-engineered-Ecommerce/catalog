package com.overengineeredecommerce.transport.repository;

import com.overengineeredecommerce.transport.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> getCategoryByName(String name);
}
