package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.domain.entity.Category;
import com.overengineeredecommerce.integrationtest.setup.cucumber.TestContext;
import com.overengineeredecommerce.transport.dto.ProductRequestDto;
import com.overengineeredecommerce.transport.dto.ProductResponseDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Map;

@Slf4j
public class productsStepDefinition {

    private final TestContext testContext;

    public productsStepDefinition(TestContext testContext) {
        this.testContext = testContext;
    }

    //GIVEN STEPS
    @Given("the product {string} does not exist")
    public void theProductDoesNotExist(String productName) {
        RestAssured
                .given()
                .queryParam("name", productName)
                .get(StepDefinitions.getBaseUrl() + "/products/search")
                .then()
                .statusCode(404);
    }


    //WHEN STEPS
    @When("a valid request is made to retrieve all products")
    public void aValidRequestIsMadeToRetrieveAllProducts() {
        testContext.setResponse(RestAssured
                .get(StepDefinitions.getBaseUrl() + "/products")
                .then());

    }

    @When("a request is made to create a product called {string} from the brand {string} with EAN {string}")
    public void aRequestIsMadeToCreateAProductCalled(String productName, String brand, String ean) {
        Map<String, String> details
                = Map.of("color", "White", "Device Size", "6,1", "Storage", "128GB", "version" , "12");
        HashSet<Category> categories = new HashSet<>();

        testContext.setResponse(RestAssured
                .given()
                .contentType("application/json")
                .body(new ProductRequestDto(productName, brand, ean, categories, details))
                .post(StepDefinitions.getBaseUrl() + "/product")
                .then());
    }

    //THEN STEPS
    @Then("the response should contain detail {string} details")
    public void theResponseShouldContainDetailDetails(String number) {
        ProductResponseDto productResponseDto = testContext.getResponse().extract().as(ProductResponseDto.class);
        Assertions.assertEquals(Integer.valueOf(number), productResponseDto.details().size());
    }
}
