@UserLogout
Feature: User Logout Module


  @tag1
  Scenario: User Login valid credential
    Given Admin creates request with valid credentials
    When Admin calls Post Https method  with valid endpoint
    Then Admin receives 200 OK with auto generated token


  @tag1
  Scenario: User able to logout
    Given Admin creates request
    When Admin calls Get Https method with valid endpoint
    Then Admin receives 200 ok  and response with Logout successful


  @tag1
  Scenario: User able to logout  with invalid endpoint
    Given Admin creates request
    When Admin calls Get Https method with invalid-endpoint
    Then Admin receives 404 Not found


  @tag1
  Scenario: User able to logout with No Auth
    Given Admin creates request without Auth
    When Admin calls Get Https method with valid endpoint
    Then Admin receives 401  unauthorized