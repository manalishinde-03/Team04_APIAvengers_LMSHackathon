
@ProgramModuleAPIVerification
Feature: Create Program Module

  @TC1
  Scenario: Create Program with valid endpoint
    Given Admin creates POST Request for the LMS with request body
    When Admin sends POST HTTPS Request and request Body with endpoint
    Then Admin receives 201 Created Status with response body

  @TC2
  Scenario: Create Program with only mandatory details and valid endpoint
     Given Admin creates POST Request with only mandatory fields
    When Admin sends HTTPS Request and  request Body with only mandatory fields
    Then Admin receives 201 Created Status with response body
    
    @TC3
  Scenario: Create Program with No Authorization
     Given Admin creates POST Request with No Authorization
    When Admin sends POST HTTPS Request without Authorization token
    Then Admin receives 401 Unauthorized Status code

@CreateProgramWithExcel 
 
 
 @TC4
 Scenario Outline: Validate API response for creating a program
    Given Admin creates POST request body for "<TestCaseID>"
    When Admin sends POST request to create a program for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples:
      | TestCaseID | ExpectedStatusCode |
      | TC01      | 201                |
      | TC02      | 400                |
 