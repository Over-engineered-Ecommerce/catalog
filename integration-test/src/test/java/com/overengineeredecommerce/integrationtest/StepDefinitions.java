package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.integrationtest.setup.Postgres;
import com.overengineeredecommerce.transport.Application;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.hamcrest.core.IsEqual.equalTo;


@Slf4j
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StepDefinitions extends Postgres {

    @LocalServerPort
    private int port;

    private static String baseUrl;

    ValidatableResponse response;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/catalog";
    }

    @Given("a valid request to retrieve all categories")
    public void aValidRequestToRetrieveAllCategories() {
        ValidatableResponse response = RestAssured.get(baseUrl + "/categories")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON);
    }

    @When("the request is sent")
    public void theRequestIsSent() {
    }

    @Then("the response status should be {int} OK")
    public void theResponseStatusShouldBeOK(int StatusCode) {
        response.statusCode(equalTo(StatusCode));

    }
}