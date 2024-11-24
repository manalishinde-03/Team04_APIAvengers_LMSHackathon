@ProgramModuleAPIVerification
Feature: Create Program Module

  #
  #@TC1
  #Scenario: Create Program with valid endpoint
  #Given Admin creates POST Request for the LMS with request body
  #When Admin sends POST HTTPS Request and request Body with endpoint
  #Then Admin receives 201 Created Status with response body
  #
  #@TC2
  #Scenario: Create Program with only mandatory details and valid endpoint
  #Given Admin creates POST Request with only mandatory fields
  #When Admin sends HTTPS Request and  request Body with only mandatory fields
  #Then Admin receives 201 Created Status with response body
  #
  #@TC3
  #Scenario: Create Program with No Authorization
  #Given Admin creates POST Request with No Authorization
  #When Admin sends POST HTTPS Request without Authorization token
  #Then Admin receives 401 Unauthorized Status code
  @CreateProgramWithExcel @TC1
  Scenario Outline: Validate API response for creating a program
    Given Admin creates POST request body for "<TestCaseID>"
    When Admin sends POST request to create a program for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID      | ExpectedStatusCode |
      | POST_PROGRAM_01 |                201 |
      | POST_PROGRAM_02 |                201 |
      | POST_PROGRAM_03 |                400 |

  @TC2
  Scenario Outline: Validate API response for creating a program with No Auth
    Given Admin creates POST request body for "<TestCaseID>"
    When Admin sends POST request with No Auth to create a program for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID      | ExpectedStatusCode |
      | POST_PROGRAM_04 |                401 |

  @TC3
  Scenario Outline: Validate API response for creating a program with Invalid Endpoint
    Given Admin creates POST request body for "<TestCaseID>"
    When Admin sends POST request to an invalid endpoint for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID      | ExpectedStatusCode |
      | POST_PROGRAM_05 |                404 |

  @TC4
  Scenario Outline: Validate API response for creating program with existing programName
    Given Admin creates POST request body for "<TestCaseID>"
    When Admin sends POST request for creating program with existing programName "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID      | ExpectedStatusCode |
      | POST_PROGRAM_06 |                400 |

  @TC5
  Scenario Outline: Validate API response for creating program with Invalid Method
    Given Admin creates POST request body for "<TestCaseID>"
    When Admin sends "<Method>" request to create program for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID      | ExpectedStatusCode | Method |
      | POST_PROGRAM_07 |                405 | GET    |
      | POST_PROGRAM_07 |                405 | PUT    |
      
      @TC6
  Scenario Outline: Validate API response for creating program with invalid request body
    Given Admin creates POST request body for "<TestCaseID>"
    When Admin sends POST request with invalid request body for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID      | ExpectedStatusCode |
      | POST_PROGRAM_08 |                400 |
      | POST_PROGRAM_09 |                400 |
      | POST_PROGRAM_10 |                400 |
      
      @TC7
  Scenario Outline: Validate API response for updating program with valid request body
    Given Admin creates PUT request body for "<TestCaseID>"
    When Admin sends PUT request with valid request body for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID      | ExpectedStatusCode |
      | PUT_PROGRAM_01|                200 |
