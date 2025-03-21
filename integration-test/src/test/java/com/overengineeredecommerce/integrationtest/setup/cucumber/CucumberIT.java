package com.overengineeredecommerce.integrationtest.setup.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * This class is used to run Cucumber integration tests.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.overengineeredecommerce.integrationtest",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class CucumberIT { }