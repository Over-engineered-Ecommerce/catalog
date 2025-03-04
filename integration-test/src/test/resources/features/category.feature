Feature: Category

  Scenario: Successfully reach categories endpoint
    When a valid request is made to retrieve all categories
    Then the response status should be 200

  Scenario Outline: Create categories
    Given the category "<categoryName>" does not exist
    When a request is made to create a category called "<categoryName>"
    Then the response status should be <statusCode>
    Given a valid request is made to retrieve all categories
    Then the response status should be 200
    And the response should contain a category "<categoryName>"
    Examples:
      | categoryName         |  | statusCode |
      | IT & Electronics     |  | 201        |
      | Skincare & Cosmetics |  | 201        |
      | Kids` Toys & Games   |  | 201        |


  Scenario Outline: Fail to create category with invalid name
    Given the category "<categoryName>" does not exist
    When a request is made to create a category called "<categoryName>"
    Then the response status should be 400
    And the response should contain "<errorMessage>"
    Examples:
      | categoryName | errorMessage                                           |
      | in           | Please inform a name between 3 and 100 characters      |
      |              | Please inform a name between 3 and 100 characters     |


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
