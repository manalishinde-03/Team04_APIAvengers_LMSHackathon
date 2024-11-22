@ProgramModule
Feature: Get All Programs

  @tag1
  Scenario: User Login valid credential
    Given Admin creates request with valid credentials
    When Admin sends Https method  with valid endpoint
    Then Admin receives 200 OK with auto generated token

  @tag2
  Scenario: Admin able to retrieve all programs with valid Endpoint
    Given Admin creates GET Request for the LMS API
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 200 OK Status with response body.

  @tag2
  Scenario: Admin able to retrieve all programs with invalid Endpoint
    Given Admin creates GET Request for the LMS API
    When Admin sends HTTPS Request with invalidendpoint
    Then Admin receives 404 not found  Status with error message

  @tag2
  Scenario: Admin able to retrieve all programs with invalid Method
    Given Admin creates POST Request for the LMS API
    When Admin sends POST Request with endpoint
    Then Admin receives 405 Method Not Allowed

  @tag2
  Scenario: Admin able to retrieve all programs without Authorization
    Given Admin creates GET Request without Authorization
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 401 Status with response body as Unauthorized


