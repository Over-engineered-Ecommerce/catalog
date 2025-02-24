package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.domain.entity.Category;
import com.overengineeredecommerce.integrationtest.setup.Postgres;
import com.overengineeredecommerce.transport.Application;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;


@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryIT extends Postgres {

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
    public void createCategory() {
        Category payload = new Category("Informática");

        ExtractableResponse<Response> response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post(BASE_URL + "/category")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("name", equalTo("Informática"))
                .body("id", notNullValue())
                .extract();

        Category category = response.as(Category.class);


        RestAssured.get(BASE_URL + "/categories")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON) // Ensures the response is valid JSON
                .body("size()", equalTo(1))
                .body("find { it.name == 'Informática' }.name", Matchers.equalTo("Informática"))
                .body("find { it.name == 'Informática' }.id", equalTo(category.getId().toString()));

        RestAssured.given()
                .queryParam("id", category.getId())
                .get(BASE_URL + "/category")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                // Ensures the response is valid JSON
                .body("name", equalTo("Informática"))
                .body("id", equalTo(category.getId().toString()));

        RestAssured.given()
                .queryParam("name", category.getName())
                .get(BASE_URL + "/category/byName")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                // Ensures the response is valid JSON
                .body("name", equalTo("Informática"))
                .body("id", equalTo(category.getId().toString()));
    }

}