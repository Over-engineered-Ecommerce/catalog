package com.overengineeredecommerce.transport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.overengineeredecommerce.domain.entity")
@EnableJpaRepositories(basePackages = "com.overengineeredecommerce.transport")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
