package com.overengineeredecommerce.transport.controller;

import com.overengineeredecommerce.application.service.ProductService;
import com.overengineeredecommerce.domain.entity.Product;
import com.overengineeredecommerce.transport.HttpResponse;
import com.overengineeredecommerce.transport.dto.ProductRequestDto;
import com.overengineeredecommerce.transport.dto.ProductResponseDto;
import com.overengineeredecommerce.transport.mapper.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Stream;

/**
 * Controller for product operations
 * <p> This class is responsible for handling the requests related to products
 * and returning the responses to the client.
 * </p>
 */
@RestController
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }


    @Operation(
            summary = "Retrieves a list of all available products",
            description = "Retrieves a list of all available products in the system",
            tags = {"Product"}
    )
    @GetMapping("/products")
    public ResponseEntity<Stream<ProductResponseDto>> getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products.stream().map(ProductMapper.INSTANCE::fromProduct));
    }

    @Operation(
        summary = "Create a new product",
        description = "Creates a new product in the system with the provided details",
        tags = {"Product"}
    )
    @PostMapping("/product")
    public ResponseEntity<HttpResponse> createProduct(@RequestBody @Valid ProductRequestDto request) {
        Product product = ProductMapper.INSTANCE.toProduct(request);
        Product response = productService.createProduct(product);

        URI location = URI.create("/category?id=" + response.getProductId());
        return ResponseEntity.created(location)
                .body(ProductMapper.INSTANCE.fromProduct(response));


    }
}
