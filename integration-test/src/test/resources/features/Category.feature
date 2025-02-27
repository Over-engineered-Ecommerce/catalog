Feature: publish fixture Change for singles match

  Scenario: Successfully retrieve a category
    Given a valid request to retrieve all categories
    Then the response status should be 200 OK