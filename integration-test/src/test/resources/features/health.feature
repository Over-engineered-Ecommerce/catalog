Feature: publish fixture Change for singles match

  Scenario Outline: Successfully check health
    When the request is made to the "<path>" endpoint
    Then the response status should be <httpStatus>
    Examples:
      | path       | httpStatus |
      |            | 200        |
      | /liveness  | 200        |
      | /readiness | 200        |
