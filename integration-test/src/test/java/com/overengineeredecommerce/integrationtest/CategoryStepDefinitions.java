package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.integrationtest.setup.cucumber.TestContext;
import com.overengineeredecommerce.transport.dto.CategoryRequestDto;
import com.overengineeredecommerce.transport.dto.CategoryResponseDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.springframework.http.HttpStatus;

import java.util.UUID;


public class CategoryStepDefinitions {


    private final TestContext testContext;

    public CategoryStepDefinitions(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("a valid request is made to retrieve all categories")
    public void aValidRequestToRetrieveAllCategories() {
        testContext.setResponse(RestAssured.get(StepDefinitions.getBaseUrl() + "/categories")
                .then());
    }


    @Given("a request is made to create a category called {string}")
    public void aRequestToCreateACategoryCalled(String categoryName) {
        ValidatableResponse response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new CategoryRequestDto(categoryName))
                .post(StepDefinitions.getBaseUrl() + "/category")
                .then();

        testContext.setResponse(response);
    }

    @And("the response should contain a category {string}")
    public void theResponseShouldContainTheCategory(String category) {
        testContext.getResponse().body("find { it.name == '" + category + "' }.name", Matchers.equalTo(category));
    }


    @Given("the category {string} exists")
    public void the_category_exists(String categoryName) {
        ValidatableResponse response = RestAssured
                .given()
                .queryParam("name", categoryName)
                .get(StepDefinitions.getBaseUrl() + "/categories/search").then();

        testContext.setResponse(response);
    }

    @Given("the category {string} does not exist")
    public void theCategoryDoesNotExist(String categoryName) {
        ValidatableResponse response = RestAssured
                .given()
                .queryParam("name", categoryName)
                .get(StepDefinitions.getBaseUrl() + "/categories/search").then();
        response.statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Given("the category with id {string} does not exist")
    public void the_category_with_id_does_not_exist(String id) {
        ValidatableResponse response = RestAssured
                .given()
                .queryParam("id", id)
                .get(StepDefinitions.getBaseUrl() + "/category").then();
        response.statusCode(HttpStatus.NOT_FOUND.value());
    }


    @When("a get request category by name called {string}")
    public void aGetRequestCategoryByNameCalled(String categoryName) {
        Response response = RestAssured
                .given()
                .queryParam("name", categoryName)
                .get(StepDefinitions.getBaseUrl() + "/categories/search");
        testContext.setResponse(response.then());
    }

    @When("a request to delete category with id {string}")
    public void aRequestToDeleteCategoryWithId(String id) {
        ValidatableResponse response = RestAssured
                .given()
                .queryParam("id", id)
                .delete(StepDefinitions.getBaseUrl() + "/category").then();
        testContext.setResponse(response);
    }

    @When("a request to delete the category")
    public void aRequestToDeleteTheCategory() {
        UUID id = testContext.getResponse().extract().as(CategoryResponseDto.class).id();

        ValidatableResponse response = RestAssured
                .given()
                .queryParam("id", id)
                .delete(StepDefinitions.getBaseUrl() + "/category").then();
        testContext.setResponse(response);
    }
}