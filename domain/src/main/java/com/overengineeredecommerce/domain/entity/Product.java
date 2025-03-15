package com.overengineeredecommerce.domain.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "PRODUCT")
public final class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID productId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "brand", nullable = false, unique = false)
    private String brand;

    @Column(name = "ean", nullable = false, unique = true)
    private String ean;

    @ManyToMany(fetch = FetchType.EAGER ,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "product_category",  // Join table name
            joinColumns = @JoinColumn(name = "product_id"),  // Foreign key to Product
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> details;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.setCreatedAt(now);
        this.setUpdatedAt(now);
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }

    public Product() {
    }

    public Product(UUID productId, String name, String brand, String ean, Set<Category> categories, Map<String, String> details, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.ean = ean;
        if (categories != null && !categories.isEmpty()) {
            this.categories = categories;
        }
        this.details = details;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        if(categories == null) {
            this.categories = new HashSet<>();
        }
        this.categories = categories;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}