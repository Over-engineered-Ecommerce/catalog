package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.integrationtest.setup.cucumber.TestContext;
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

}
