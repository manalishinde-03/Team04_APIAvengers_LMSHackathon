@ProgramModuleAPIVerification
Feature: Create Program Module

  @CreateProgramWithExcel @TC1
  Scenario Outline: Validate API response for creating a program
    Given Admin creates POST request body for "<TestCaseID>"
    When Admin sends POST request to create a program for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID      | ExpectedStatusCode |
      | POST_PROGRAM_01 |                201 |
     | POST_PROGRAM_02 |                201 |
			|POST_PROGRAM_03 |                201 |
			| POST_PROGRAM_04 |                201 |
		| POST_PROGRAM_05 |                400 |
  
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
  
