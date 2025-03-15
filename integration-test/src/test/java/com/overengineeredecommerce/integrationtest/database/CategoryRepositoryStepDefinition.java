package com.overengineeredecommerce.integrationtest.database;

import com.overengineeredecommerce.application.repository.CategoryRepository;
import com.overengineeredecommerce.domain.entity.Category;
import com.overengineeredecommerce.integrationtest.setup.cucumber.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class CategoryRepositoryStepDefinition {

    CategoryRepository categoryRepository;

    private final TestContext testContext;

    CategoryRepositoryStepDefinition(CategoryRepository categoryRepository, TestContext testContext) {
        this.categoryRepository = categoryRepository;
        this.testContext = testContext;
    }

    @When("a category is created with name {string}")
    public void aCategoryIsCreatedWithName(String categoryName) {
        Category savedCategory = categoryRepository.save(new Category(null, categoryName));
        testContext.setCategory(savedCategory);
    }

    @Then("the result should be a category with name {string} and generatedId")
    public void theResultShouldBeACategoryWithNameAndGeneratedId(String categoryName) {

        Category category = testContext.getCategory();
        Assertions.assertNotNull(category);
        Assertions.assertEquals(categoryName, category.getName());
        Assertions.assertNotNull(category.getCategoryId());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNotNull(category.getUpdatedAt());
    }
}
