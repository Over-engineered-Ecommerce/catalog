Feature: publish fixture Change for singles match

  Scenario Outline: Successfully check health
    When the get request is made to the "<path>" endpoint
    Then the response status should be <httpStatus>
    Examples:
      | path       | httpStatus |
      |            | 200        |
      | /liveness  | 200        |
      | /readiness | 200        |


  Scenario Outline: Successfully check health
    When the get request is made to the "<path>" endpoint
    Then the response status should be "<httpStatus>"
    Examples:
      | path       | httpStatus |
      |            | OK         |
      | /liveness  | OK        |
      | /readiness | OK        |

  Scenario: Successfully retrieve a category
    Given a valid request to get documentation
    Then the response status should be 200