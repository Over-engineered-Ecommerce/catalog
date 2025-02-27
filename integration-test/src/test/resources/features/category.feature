Feature: Category

  Scenario: Successfully reach categories endpoint
    When a valid request to retrieve all categories
    Then the response status should be 200

  Scenario: Successfully create a category
    Given a request to create a category called "Informática"
    Then the response status should be 201

  Scenario: Successfully refuse to create a category that already exists
    Given a category informática exits
    When a request to create a category called "Informática"
    Then the response status should be 409

  Scenario: Successfully reach categories endpoint
    Given a valid request to retrieve all categories
    Then the response status should be 200
    And the response should contain a category "Informática"

  Scenario: fail to create category with short name
    Given a request to create a category called "in"
    Then the response status should be 400
    And the response should contain "Please inform a name between 3 and 100 characters"

  Scenario: Successfully get category by name
    Given a category informática exits
    When a get request category by name called "Informática"
    Then the response status should be 200
    And the response should contain "Informática"