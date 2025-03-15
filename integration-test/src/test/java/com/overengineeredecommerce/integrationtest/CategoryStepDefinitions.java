package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.domain.entity.Category;
import com.overengineeredecommerce.integrationtest.setup.cucumber.TestContext;
import com.overengineeredecommerce.transport.dto.CategoryRequestDto;
import com.overengineeredecommerce.transport.dto.CategoryResponseDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;

import java.util.UUID;


@Slf4j
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

        try{
            testContext.setCategory(response.extract().as(Category.class));
        } catch (Exception e) {
            log.error("Expected Failed to parse response as Category: {}" , e.getMessage());
        }
        testContext.setResponse(response);
    }


    @Given("the category {string} exists")
    public void the_category_exists(String categoryName) {
        ValidatableResponse response = RestAssured
                .given()
                .queryParam("name", categoryName)
                .get(StepDefinitions.getBaseUrl() + "/categories/search").then();

        CategoryResponseDto responseDto = response.extract().as(CategoryResponseDto.class);

        Assertions.assertEquals(categoryName, responseDto.name());
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
        UUID id = testContext.getResponse().extract().as(CategoryResponseDto.class).categoryId();

        ValidatableResponse response = RestAssured
                .given()
                .queryParam("id", id)
                .delete(StepDefinitions.getBaseUrl() + "/category").then();
        testContext.setResponse(response);
    }


    @Given("a category with name {string} exists")
    public void aCategoryWithNameExists(String categoryName) {
        aRequestToCreateACategoryCalled(categoryName);
    }
}