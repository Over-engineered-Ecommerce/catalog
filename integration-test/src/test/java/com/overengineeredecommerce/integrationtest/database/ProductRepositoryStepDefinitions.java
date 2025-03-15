package com.overengineeredecommerce.integrationtest.database;

import com.overengineeredecommerce.application.repository.ProductRepository;
import com.overengineeredecommerce.domain.entity.Category;
import com.overengineeredecommerce.domain.entity.Product;
import com.overengineeredecommerce.integrationtest.setup.cucumber.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.HashSet;

public class ProductRepositoryStepDefinitions {

    ProductRepository productRepository;

    private final TestContext testContext;

    ProductRepositoryStepDefinitions(ProductRepository productRepository, TestContext testContext) {
        this.productRepository = productRepository;
        this.testContext = testContext;
    }

    @When("a Product is created with name {string} and brand {string} and EAN {string}")
    public void aProductIsCreatedWithNameAndBrandAndEAN(String name, String brand, String ean) {
        HashSet<Category> categories = new HashSet<>();

        HashMap<String, String> details = new HashMap<>();
        Product savedProduct = productRepository.save(new Product(null, name, brand, ean, categories, details));

        testContext.setProduct(savedProduct);
    }

    @When("a Product is created with name {string} and brand {string} and EAN {string} and null categories")
    public void aProductIsCreatedWithNameAndBrandAndEANAndNUllCategory(String name, String brand, String ean) {
        HashSet<Category> categories = null;

        HashMap<String, String> details = new HashMap<>();
        Product savedProduct = productRepository.save(new Product(null, name, brand, ean, categories, details));

        testContext.setProduct(savedProduct);
    }

    @Then("the result should be a Product with name {string} and brand {string} and EAN {string} and generatedId")
    public void theResultShouldBeAProductWithNameAndBrandAndEANAndGeneratedId(String name, String brand, String ean) {

        Product product = testContext.getProduct();
        Assertions.assertNotNull(product);
        Assertions.assertEquals(name, product.getName());
        Assertions.assertEquals(brand, product.getBrand());
        Assertions.assertEquals(ean, product.getEan());

        Assertions.assertNotNull(product.getProductId());
        Assertions.assertNotNull(product.getCreatedAt());
        Assertions.assertNotNull(product.getUpdatedAt());
    }

    @And("delete product with generatedId")
    public void deleteProductWithGeneratedId() {
        Product product = testContext.getProduct();
        productRepository.deleteById(product.getProductId());
    }
}
