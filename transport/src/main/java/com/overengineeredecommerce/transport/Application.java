package com.overengineeredecommerce.transport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main application class for the Overengineered E-commerce application.
 * <p>
 * This class is responsible for bootstrapping the Spring Boot application.
 * It scans for components, entities, and JPA repositories in the specified packages.
 * </p>
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.overengineeredecommerce"})
@EntityScan(basePackages = "com.overengineeredecommerce.domain.entity")
@EnableJpaRepositories(basePackages = "com.overengineeredecommerce.transport")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
