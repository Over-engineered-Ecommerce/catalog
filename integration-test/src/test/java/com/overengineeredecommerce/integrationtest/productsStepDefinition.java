package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.integrationtest.setup.cucumber.TestContext;
import com.overengineeredecommerce.transport.dto.ProductRequestDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class productsStepDefinition {

    private final TestContext testContext;

    public productsStepDefinition(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("a valid request is made to retrieve all products")
    public void aValidRequestIsMadeToRetrieveAllProducts() {
        testContext.setResponse(RestAssured
                .get(StepDefinitions.getBaseUrl() + "/products")
                .then());

    }

    @Given("the product {string} does not exist")
    public void theProductDoesNotExist(String productName) {
        RestAssured
                .given()
                .queryParam("name", productName)
                .get(StepDefinitions.getBaseUrl() + "/products/search")
                .then()
                .statusCode(404);
    }

    @When("a request is made to create a product called {string} from the brand {string} with EAN {string}")
    public void aRequestIsMadeToCreateAProductCalled(String productName, String brand, String ean) {
        testContext.setResponse(RestAssured
                .given()
                .contentType("application/json")
                .body(new ProductRequestDto(productName, brand, ean))
                .post(StepDefinitions.getBaseUrl() + "/product")
                .then());
    }
}
