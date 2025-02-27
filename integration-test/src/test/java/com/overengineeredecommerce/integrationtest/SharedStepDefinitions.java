package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.integrationtest.setup.cucumber.TestContext;
import io.cucumber.java.en.Then;

import static org.hamcrest.Matchers.equalTo;

public class SharedStepDefinitions {

    private final TestContext testContext;

    public SharedStepDefinitions(TestContext testContext) {
        this.testContext = testContext;
    }

    @Then("the response status should be {int} OK")
    public void theResponseStatusShouldBeOK(int statusCode) {
        testContext.getResponse().statusCode(equalTo(statusCode));
    }
}
