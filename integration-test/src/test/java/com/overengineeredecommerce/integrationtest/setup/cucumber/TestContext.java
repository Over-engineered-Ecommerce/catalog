package com.overengineeredecommerce.integrationtest.setup.cucumber;

import io.restassured.response.ValidatableResponse;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("cucumber-glue")
public class TestContext {
    private ValidatableResponse response;
}