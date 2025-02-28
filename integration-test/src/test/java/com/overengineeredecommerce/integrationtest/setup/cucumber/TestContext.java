package com.overengineeredecommerce.integrationtest.setup.cucumber;

import io.restassured.response.ValidatableResponse;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * TestContext is a Spring component that holds the response from REST-assured
 * for use in Cucumber step definitions.
 */
@Data
@Component
@Scope("cucumber-glue")
public class TestContext {
    private ValidatableResponse response;
}