Feature: Category

  Scenario: Successfully reach categories endpoint
    When a valid request to retrieve all categories
    Then the response status should be 200

  Scenario: Successfully create a category
    Given a request to create a category called "IT & Electronics"
    Then the response status should be 201

  Scenario: Fail to create a category that already exists
    Given a category "IT & Electronics" exits
    When a request to create a category called "IT & Electronics"
    Then the response status should be 409

  Scenario: Successfully reach categories endpoint
    Given a valid request to retrieve all categories
    Then the response status should be 200
    And the response should contain a category "IT & Electronics"

  Scenario: Fail to create category with short name
    Given a request to create a category called "in"
    Then the response status should be 400
    And the response should contain "Please inform a name between 3 and 100 characters"

  Scenario: Fail to create category with empty name
    Given a request to create a category called ""
    Then the response status should be 400

  Scenario: Successfully get category by name
    Given a category "IT & Electronics" exits
    When a get request category by name called "IT & Electronics"
    Then the response status should be 200
    And the response should contain "IT & Electronics"

  Scenario: Fail to get non-existent category by name
    When a get request category by name called "NonExistent"
    Then the response status should be 404
    And the response should contain "Category not found"

  Scenario: Fail to delete non-existent category
    When a request to delete category with id "123e4567-e89b-12d3-a456-426614174000"
    Then the response status should be 204

  Scenario: Successfully delete category
    Given a category "IT & Electronics" exits
    When a request to delete the category
    Then the response status should be 204