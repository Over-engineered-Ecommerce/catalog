package com.overengineeredecommerce.application.service;

import com.overengineeredecommerce.application.exception.UniqueInsertConstraint;
import com.overengineeredecommerce.application.repository.ProductRepository;
import com.overengineeredecommerce.domain.entity.Product;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static final String PRODUCT_NOT_FOUND = "Product not found";

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueInsertConstraint("Product with name '" + product.getName() + "' already exists");
        }
        catch (Exception e) {
            throw new UniqueInsertConstraint("Error creating product");
        }
    }

}