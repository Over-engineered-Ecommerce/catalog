Feature: Category

  Scenario: Successfully reach products endpoint
    When a valid request is made to retrieve all products
    Then the response status should be 200
