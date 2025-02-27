package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.integrationtest.setup.cucumber.TestContext;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.springframework.http.HttpStatus;

public class Health {

    private final TestContext testContext;

    public Health(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("a valid request to get health {string}")
    public void aValidRequestToGetHealth(String suffix) {

        ValidatableResponse response = RestAssured.get(StepDefinitions.getBaseUrl() + "/actuator/health" +  suffix)
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON);
        testContext.setResponse(response);
    }
}
