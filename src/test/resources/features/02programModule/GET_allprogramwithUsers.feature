@ProgramModule
Feature: Get All Program with users

  @tag1
  Scenario: User Login valid credential
    Given Admin creates request with valid credentials
    When Admin sends Https method  with valid endpoint
    Then Admin receives 200 OK with auto generated token

  @tag2
  Scenario: User able to retrieve all programs with valid Endpoint
    Given Admin creates GET Request for the LMS API
    When Admin sends "GET" HTTPS Request with endpoint "/allProgramsWithUsers"
    Then Admin receives 200 "OK" Status.

  @tag2
  Scenario: User able to retrieve all programs with invalid Endpoint
    Given Admin creates GET Request for the LMS API
    When Admin sends "GET" HTTPS Request with endpoint "/ThisIsInvalidEndpoint"
    Then Admin receives 404 "not found" Status.

  @tag2
  Scenario: User able to retrieve all programs with invalid Method
    Given Admin creates POST Request for the LMS API
    When Admin sends "POST" HTTPS Request with endpoint "/allProgramsWithUsers"
    Then Admin receives 405 "Not Allowed" Status.

  @tag2
  Scenario: User able to retrieve all programs without Authorization
    Given Admin creates GET Request without Authorization
    When Admin sends "GET" HTTPS Request with endpoint "/ThisIsInvalidEndpoint"
    Then Admin receives 401 "Unauthorized" Status.
