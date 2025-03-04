package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.integrationtest.setup.cucumber.TestContext;
import io.cucumber.java.en.Then;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.equalTo;

public class SharedStepDefinitions {

    private final TestContext testContext;

    public SharedStepDefinitions(TestContext testContext) {
        this.testContext = testContext;
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBeOK(int statusCode) {
        testContext.getResponse().statusCode(equalTo(statusCode));
    }

    @Then("the response status should be {string}")
    public void theResponseStatusShouldBeOK(String statusCode) {
        testContext.getResponse().statusCode(equalTo(HttpStatus.valueOf(statusCode).value()));
    }
}
