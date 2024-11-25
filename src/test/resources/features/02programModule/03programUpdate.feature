@ProgramModuleAPIVerification
Feature: Update Program Module

  #PUT by Program Name Scenarios
  @TC7
  Scenario Outline: Validate API response for updating program with valid request body
  Given Admin creates PUT request body for "<TestCaseID>"
  When Admin sends PUT request for <programIndex> with valid request body for "<TestCaseID>"
  Then Response status code should be "<ExpectedStatusCode>"
  
  Examples:
  | TestCaseID     | ExpectedStatusCode | programIndex |
  | PUT_PROGRAM_01 |                200 |                0 |
  | PUT_PROGRAM_02 |                200 |                1 |
  | PUT_PROGRAM_03 |                200 |                0 |
  | PUT_PROGRAM_04 |                400 |                0 |
  | PUT_PROGRAM_06 |                400 |                0 |
  | PUT_PROGRAM_07 |                400 |                0 |
  
  @TC8
  Scenario Outline: Validate API response for updating program with invalid Program Name
  Given Admin creates PUT request body for "<TestCaseID>"
  When Admin sends PUT request with invalid Program Name for "<TestCaseID>"
  Then Response status code should be "<ExpectedStatusCode>"
  
  Examples:
  | TestCaseID     | ExpectedStatusCode |
  | PUT_PROGRAM_05 |                404 |
  
  @TC9
  Scenario Outline: Validate API response for updating program with No Auth
  Given Admin creates PUT request body for "<TestCaseID>"
  When Admin sends PUT request with No Auth for "<TestCaseID>"
  Then Response status code should be "<ExpectedStatusCode>"
  
  Examples:
  | TestCaseID     | ExpectedStatusCode |
  | PUT_PROGRAM_05 |                401 |
  
 # PUT by Program ID Scenarios
  @TC10
  Scenario Outline: Validate API response for updating program by ID
    Given Admin creates PUT request body for "<TestCaseID>"
    When Admin sends PUT request for <programIndex> by Program ID for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID        | ExpectedStatusCode | programIndex |
      | PUT_PROGRAM_ID_01 |                200 |            0 |
      | PUT_PROGRAM_ID_02 |                200 |            1 |
      | PUT_PROGRAM_ID_03 |                200 |            0 |
      
      
      @TC11
  Scenario Outline: Validate API response for updating program by ID with invalid body
    Given Admin creates PUT request body for "<TestCaseID>"
    When Admin sends PUT request for <programIndex> with invalid request body for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID        | ExpectedStatusCode | programIndex |
      | PUT_PROGRAM_ID_04 |                404 |            0 |
      | PUT_PROGRAM_ID_05 |                404 |            0 |
      | PUT_PROGRAM_ID_06 |                400 |            0 |
      | PUT_PROGRAM_ID_07 |                400 |            0 |
      | PUT_PROGRAM_ID_08 |                400 |            0 |
      | PUT_PROGRAM_ID_09 |                401 |            0 |
     
     
     @TC12
  Scenario Outline: Validate API response for updating program by ID with invalid Method
    Given Admin creates PUT request body for "<TestCaseID>"
    When Admin sends PUT request for <programIndex> with invalid method for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID        | ExpectedStatusCode | programIndex |
     | PUT_PROGRAM_ID_10 |                405 |            0 |
