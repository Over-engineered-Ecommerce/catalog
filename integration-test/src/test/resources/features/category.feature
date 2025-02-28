Feature: Category

  Scenario: Successfully reach categories endpoint
    When a valid request is made to retrieve all categories
    Then the response status should be 200


  Scenario: Successfully create category
    Given the category "IT & Electronics" does not exist
    When a request is made to create a category called "IT & Electronics"
    Then the response status should be 201

  Scenario: Fail to create category with same name
    Given the category "IT & Electronics" exists
    When a request is made to create a category called "IT & Electronics"
    Then the response status should be 409

  Scenario: Successfully reach categories endpoint
    Given a valid request is made to retrieve all categories
    Then the response status should be 200
    And the response should contain a category "IT & Electronics"

  Scenario: Fail to create category with short name
    Given a request is made to create a category called "in"
    Then the response status should be 400
    And the response should contain "Please inform a name between 3 and 100 characters"

  Scenario: Fail to create category with empty name
    Given the category "" does not exist
    When a request is made to create a category called ""
    Then the response status should be 400

  Scenario: Successfully get category by name
    Given the category "IT & Electronics" exists
    When a get request category by name called "IT & Electronics"
    Then the response status should be 200
    And the response should contain "IT & Electronics"

  Scenario: Fail to get non-existent category by name
    When a get request category by name called "NonExistent"
    Then the response status should be 404
    And the response should contain "Category not found"

  Scenario: Fail to delete non-existent category
    Given the category with id "123e4567-e89b-12d3-a456-426614174000" does not exist
    When a request to delete category with id "123e4567-e89b-12d3-a456-426614174000"
    Then the response status should be 204

  Scenario: Successfully delete category
    Given the category "IT & Electronics" exists
    When a request to delete the category
    Then the response status should be 204


  Scenario: Successfully create category
    Given the category "IT & Electronics" does not exist
    When a request is made to create a category called "IT & Electronics"
    Then the response status should be 201


  Scenario: Successfully create category
    Given the category "Skincare & Cosmetics" does not exist
    When a request is made to create a category called "Skincare & Cosmetics"
    Then the response status should be 201

  Scenario: Successfully create category
    Given the category "Kids’ Toys & Games" does not exist
    When a request is made to create a category called "Kids’ Toys & Games"
    Then the response status should be 201

  Scenario: Successfully reach categories endpoint
    Given a valid request is made to retrieve all categories
    Then the response status should be 200
    And the response should contain a category "IT & Electronics"
    And the response should contain a category "Skincare & Cosmetics"
    And the response should contain a category "Kids’ Toys & Games"