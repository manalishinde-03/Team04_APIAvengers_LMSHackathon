@UserLogout
Feature: User Logout Module
#
#  @TC01
#  Scenario Outline: User Login valid credential
#    Given Admin creates POST login request body for "<TestCaseID>"
#    When Admin sends POST request to login for "<TestCaseID>"
#    Then Admin receives "<ExpectedStatusCode>" "OK" Status.
#
#    Examples:
#      | TestCaseID      | ExpectedStatusCode |
#      | POST_LOGIN-01   | 200                |

  @TC02
  Scenario Outline: User Logout valid credential
    Given Admin creates GET logout request body for "<TestCaseID>"
    When Admin sends POST request to logout for "<TestCaseID>"
    Then Admin receives "<ExpectedStatusCode>" "OK" Status.

    Examples:
      | TestCaseID      | ExpectedStatusCode |
      | GET_LOGOUT-04   | 200                |
      | GET_LOGOUT-05   | 404                |
      | GET_LOGOUT-06   | 401                |

#  @tag1
#  Scenario: User Login valid credential
#    Given Admin creates request with valid credentials
#    When Admin calls Post Https method  with valid endpoint
#    Then Admin receives 200 OK with auto generated token
#
#
#  @tag1
#  Scenario: User able to logout
#    Given Admin creates request
#    When Admin calls Get Https method with valid endpoint
#    Then Admin receives 200 ok  and response with Logout successful
#
#
#  @tag1
#  Scenario: User able to logout  with invalid endpoint
#    Given Admin creates request
#    When Admin calls Get Https method with invalid-endpoint
#    Then Admin receives 404 Not found
#
#
#  @tag1
#  Scenario: User able to logout with No Auth
#    Given Admin creates request without Auth
#    When Admin calls Get Https method with valid endpoint
#    Then Admin receives 401  unauthorized