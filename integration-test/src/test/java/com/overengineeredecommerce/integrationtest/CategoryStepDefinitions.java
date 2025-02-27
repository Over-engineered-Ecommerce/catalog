package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.integrationtest.setup.cucumber.TestContext;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;


public class CategoryStepDefinitions {


    private final TestContext testContext;

    public CategoryStepDefinitions(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("a valid request to retrieve all categories")
    public void aValidRequestToRetrieveAllCategories() {
        testContext.setResponse(RestAssured.get(StepDefinitions.getBaseUrl() + "/categories")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON));
    }
}