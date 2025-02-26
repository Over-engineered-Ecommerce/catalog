package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.integrationtest.setup.Postgres;
import com.overengineeredecommerce.transport.Application;
import com.overengineeredecommerce.transport.dto.CategoryRequestDto;
import com.overengineeredecommerce.transport.dto.CategoryResponseDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;


@Slf4j
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryIT extends Postgres {

    @LocalServerPort
    private int port;

    private static String BASE_URL;


    @BeforeEach
    public void setUp() {

        BASE_URL = "http://localhost:" + port + "/catalog";
    }

    @Test
    public void getAllCategories() {
        RestAssured.get(BASE_URL + "/categories")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON) // Ensures the response is valid JSON
                .body("size()", equalTo(0));
    }

    @Test
    public void failToCreateCategory() {
        CategoryRequestDto invalidPayload = new CategoryRequestDto("in");


        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(invalidPayload)
                .post(BASE_URL + "/category")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body("name", equalTo("Please inform a name between 3 and 100 characters."));

    }

    @Test
    public void createCategory() {
        CategoryRequestDto payload = new CategoryRequestDto("Informática");

        CategoryResponseDto category = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post(BASE_URL + "/category")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("name", equalTo("Informática"))
                .body("id", notNullValue())
                .extract().
                as(CategoryResponseDto.class);


        log.info("category: " + category);


        RestAssured.get(BASE_URL + "/categories")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON) // Ensures the response is valid JSON
                .body("size()", equalTo(1))
                .body("find { it.name == 'Informática' }.name", Matchers.equalTo("Informática"))
                .body("find { it.name == 'Informática' }.id", equalTo(category.id().toString()));

        RestAssured.given()
                .queryParam("id", category.id())
                .get(BASE_URL + "/category")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                // Ensures the response is valid JSON
                .body("name", equalTo("Informática"))
                .body("id", equalTo(category.id().toString()));

        RestAssured.given()
                .queryParam("name", category.name())
                .get(BASE_URL + "/category/byName")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                // Ensures the response is valid JSON
                .body("name", equalTo("Informática"))
                .body("id", equalTo(category.id().toString()));
    }

}