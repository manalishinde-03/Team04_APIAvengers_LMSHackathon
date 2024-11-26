@UserLogout
Feature: User Logout Module

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
