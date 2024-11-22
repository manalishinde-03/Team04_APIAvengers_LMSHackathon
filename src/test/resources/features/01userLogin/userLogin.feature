@UserLogin
Feature: User Login Module

  @tag1
  Scenario: User Login valid credential
    Given Admin creates request with valid credentials
    When Admin calls Post Https method  with valid endpoint
    Then Admin receives 200 OK with auto generated token


  Scenario: User Login with invalid endpoint
    Given Admin creates request with valid credentials
    When Admin calls Post Https method  with invalid endpoint
    Then Admin receives 401 unauthorized


  Scenario: User Login with invalid credentials
    Given Admin creates request with invalid credentials
    When Admin calls Post Https method  with valid endpoint
    Then Admin receives 400 Bad request