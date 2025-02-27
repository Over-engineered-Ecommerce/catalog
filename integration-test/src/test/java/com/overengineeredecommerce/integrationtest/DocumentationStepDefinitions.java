package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.integrationtest.setup.cucumber.TestContext;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class DocumentationStepDefinitions {

    private final TestContext testContext;

    public DocumentationStepDefinitions(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("a valid request to get documentation")
    public void aValidRequestToGetDocumentation() {
        testContext.setResponse(RestAssured.get(StepDefinitions.getBaseUrl() + "/v3/api-docs")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON));
    }
}