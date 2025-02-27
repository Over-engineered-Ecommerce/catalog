package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.integrationtest.setup.cucumber.TestContext;
import com.overengineeredecommerce.transport.dto.CategoryRequestDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.springframework.http.HttpStatus;


public class CategoryStepDefinitions {


    private final TestContext testContext;

    public CategoryStepDefinitions(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("a valid request to retrieve all categories")
    public void aValidRequestToRetrieveAllCategories() {
        testContext.setResponse(RestAssured.get(StepDefinitions.getBaseUrl() + "/categories")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON));
    }


    @Given("a request to create a category called {string}")
    public void aRequestToCreateACategoryCalled(String categoryName) {
        ValidatableResponse validatableResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new CategoryRequestDto(categoryName))
                .post(StepDefinitions.getBaseUrl() + "/category")
                .then();

        testContext.setResponse(validatableResponse);
    }

    @And("the response should contain a category {string}")
    public void theResponseShouldContainTheCategory(String category) {
        testContext.getResponse().body("find { it.name == '" + category + "' }.name", Matchers.equalTo(category));
    }


    @Given("a category informática exits")
    public void aCategoryInformaticaExits() {}

    @And("the response should contain {string}")
    public void theResponseShouldContain(String expectedContent) {
        testContext.getResponse().body(Matchers.containsString(expectedContent));
    }

    @When("a get request category by name called {string}")
    public void aGetRequestCategoryByNameCalled(String categoryName) {
        Response response = RestAssured
                .given()
                .queryParam("name", categoryName)
                .get(StepDefinitions.getBaseUrl() + "/category/byName");
        testContext.setResponse(response.then());
    }
}