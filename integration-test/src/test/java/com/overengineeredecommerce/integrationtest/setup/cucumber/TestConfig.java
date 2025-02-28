package com.overengineeredecommerce.integrationtest.setup.cucumber;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for integration tests.
 * Scans the specified package for Spring components.
 */
@Configuration
@ComponentScan("com.overengineeredecommerce.integrationtest")
public class TestConfig { }
