package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.integrationtest.setup.Postgres;
import com.overengineeredecommerce.transport.Application;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.hamcrest.core.IsEqual.equalTo;


@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthIT extends Postgres {

    @LocalServerPort
    private int port;

    private static String BASE_URL;

    @BeforeEach
    public void setUp() {
        BASE_URL = "http://localhost:" + port + "/catalog/actuator/health";
    }

    @Test
    public void testHealth() {
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON) // Ensures the response is valid JSON
                .body("status", equalTo("UP"));
    }

    @Test
    public void testLiveness() {
        RestAssured.get(BASE_URL + "/liveness")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON) // Ensures the response is valid JSON
                .body("status", equalTo("UP"));
    }

    @Test
    public void testReadiness() {

        RestAssured.get(BASE_URL + "/readiness")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON) // Ensures the response is valid JSON
                .body("status", equalTo("UP"));
    }
}