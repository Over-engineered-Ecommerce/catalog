package com.overengineeredecommerce.integrationtest;

import com.overengineeredecommerce.integrationtest.setup.Postgres;
import com.overengineeredecommerce.transport.Application;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(classes = Application.class,  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SwaggerIT extends Postgres {

    private String BASE_URL;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        BASE_URL = "http://localhost:" + port + "/catalog/v3/api-docs";
    }

    @Test
    public void givenWeHaveApiDocumentationThenReturnOK() {
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}