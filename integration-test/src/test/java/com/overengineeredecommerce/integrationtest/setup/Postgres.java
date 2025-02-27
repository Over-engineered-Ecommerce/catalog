package com.overengineeredecommerce.integrationtest.setup;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@Testcontainers
@ExtendWith(SpringExtension.class)
public abstract class Postgres {

    private static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER;


    static {
        POSTGRESQL_CONTAINER = new PostgreSQLContainer<>("postgres:16-alpine")
                .withDatabaseName("catalog_db")
                .withUsername("test")
                .withPassword("test");

        POSTGRESQL_CONTAINER.start();
    }



    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {

        log.info("Postgres container: {}", POSTGRESQL_CONTAINER.getJdbcUrl());

        registry.add("spring.datasource.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRESQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRESQL_CONTAINER::getPassword);
    }
}