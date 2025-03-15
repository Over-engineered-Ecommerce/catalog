Feature: Category

  Scenario: Add a new category into database
    When a category is created with name "IT & Electronics"
    Then the result should be a category with name "IT & Electronics" and generatedId
