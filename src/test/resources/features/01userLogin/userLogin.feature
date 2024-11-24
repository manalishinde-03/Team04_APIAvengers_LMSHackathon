@UserLogin
Feature: User Login Module

  @TC01
  Scenario Outline: User Login valid credential
    Given Admin creates POST login request body for "<TestCaseID>"
    When Admin sends POST request to login for "<TestCaseID>"
    Then Admin receives "<ExpectedStatusCode>" "OK" Status.

    Examples:
      | TestCaseID      | ExpectedStatusCode |
      | POST_LOGIN-01   | 200                |
      | POST_LOGIN-02   | 401                |
      | POST_LOGIN-03   | 400                |
