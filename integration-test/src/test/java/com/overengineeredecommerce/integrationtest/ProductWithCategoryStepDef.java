package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.domain.entity.Category;
import com.overengineeredecommerce.integrationtest.setup.cucumber.TestContext;
import com.overengineeredecommerce.transport.dto.ProductRequestDto;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import java.util.HashSet;
import java.util.Map;

public class ProductWithCategoryStepDef {

    private final TestContext testContext;

    public ProductWithCategoryStepDef(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("a request is made to create a product with category")
    public void aRequestIsMadeToCreateAProductWithCategory() {

        Category category = testContext.getCategory();

        Map<String, String> details
                = Map.of("color", "White", "Device Size", "6,1", "Storage", "128GB", "version" , "12");
        HashSet<Category> categories = new HashSet<>();

        categories.add(category);

        testContext.setResponse(RestAssured
                .given()
                .contentType("application/json")
                .body(new ProductRequestDto("Iphone 11", "Iphone", "3423145365", categories, details))
                .post(StepDefinitions.getBaseUrl() + "/product")
                .then());
    }
}
