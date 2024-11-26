@UserLogout
Feature: User Logout Module

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
