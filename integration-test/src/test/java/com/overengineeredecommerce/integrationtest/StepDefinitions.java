package com.overengineeredecommerce.integrationtest;


import com.overengineeredecommerce.integrationtest.setup.Postgres;
import com.overengineeredecommerce.integrationtest.setup.cucumber.TestConfig;
import com.overengineeredecommerce.transport.Application;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@Slf4j
@CucumberContextConfiguration
@SpringBootTest(classes = {Application.class, TestConfig.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class StepDefinitions extends Postgres {

    @LocalServerPort
    @Getter
    private int port;

    @Getter
    private static String baseUrl;

    @Before
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/catalog";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


}
