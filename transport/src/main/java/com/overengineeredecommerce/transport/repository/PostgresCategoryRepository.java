package com.overengineeredecommerce.transport.repository;

import com.overengineeredecommerce.domain.entity.Category;
import com.overengineeredecommerce.application.repository.CategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface PostgresCategoryRepository extends JpaRepository<Category, UUID> , CategoryRepository {
    Optional<Category> getCategoryByName(String name);
}
