package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.integrationtest.setup.cucumber.TestContext;
import com.overengineeredecommerce.transport.dto.ProductResponseDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

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

    @Then("the response should contain detail key {string} value {string}")
    public void theResponseShouldContainDetailKeyValue(String key, String value) {

        ProductResponseDto productResponseDto = testContext.getResponse().extract().as(ProductResponseDto.class);
        Assertions.assertNotNull(productResponseDto.details().get(key));
        Assertions.assertEquals(value, productResponseDto.details().get(key));
    }

    @Then("the response should contain {string}")
    public void theResponseShouldContain(String expectedContent) {
        testContext.getResponse().body(Matchers.containsString(expectedContent));
    }

    @And("the response should be a list of size {int}")
    public void theResponseShouldBeAEmptyList(int size) {
        ArrayList<?> list = (ArrayList<?>) testContext.getResponse().extract().as(List.class);
        Assertions.assertEquals(size, list.size());
    }

}
