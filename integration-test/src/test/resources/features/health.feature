Feature: publish fixture Change for singles match

  Scenario: Successfully check health
    Given a valid request to get health ""
    Then the response status should be 200

  Scenario: Successfully check health
    Given a valid request to get health "/liveness"
    Then the response status should be 200

  Scenario: Successfully check health
    Given a valid request to get health "/readiness"
    Then the response status should be 200