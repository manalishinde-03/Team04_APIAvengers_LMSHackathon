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
     #| POST_PROGRAM_02 |                201 |
		# |POST_PROGRAM_03 |                201 |
			| POST_PROGRAM_04 |                201 |
			| POST_PROGRAM_05 |                400 |
  #
  #@TC2
  #Scenario Outline: Validate API response for creating a program with No Auth
  #Given Admin creates POST request body for "<TestCaseID>"
  #When Admin sends POST request with No Auth to create a program for "<TestCaseID>"
  #Then Response status code should be "<ExpectedStatusCode>"
  #
  #Examples:
  #| TestCaseID      | ExpectedStatusCode |
  #| POST_PROGRAM_04 |                401 |
  #
  #@TC3
  #Scenario Outline: Validate API response for creating a program with Invalid Endpoint
  #Given Admin creates POST request body for "<TestCaseID>"
  #When Admin sends POST request to an invalid endpoint for "<TestCaseID>"
  #Then Response status code should be "<ExpectedStatusCode>"
  #
  #Examples:
  #| TestCaseID      | ExpectedStatusCode |
  #| POST_PROGRAM_05 |                404 |
  #
  #@TC4
  #Scenario Outline: Validate API response for creating program with existing programName
  #Given Admin creates POST request body for "<TestCaseID>"
  #When Admin sends POST request for creating program with existing programName "<TestCaseID>"
  #Then Response status code should be "<ExpectedStatusCode>"
  #
  #Examples:
  #| TestCaseID      | ExpectedStatusCode |
  #| POST_PROGRAM_06 |                400 |
  #
  #@TC5
  #Scenario Outline: Validate API response for creating program with Invalid Method
  #Given Admin creates POST request body for "<TestCaseID>"
  #When Admin sends "<Method>" request to create program for "<TestCaseID>"
  #Then Response status code should be "<ExpectedStatusCode>"
  #
  #Examples:
  #| TestCaseID      | ExpectedStatusCode | Method |
  #| POST_PROGRAM_07 |                405 | GET    |
  #| POST_PROGRAM_07 |                405 | PUT    |
  #
  #@TC6
  #Scenario Outline: Validate API response for creating program with invalid request body
  #Given Admin creates POST request body for "<TestCaseID>"
  #When Admin sends POST request with invalid request body for "<TestCaseID>"
  #Then Response status code should be "<ExpectedStatusCode>"
  #
  #Examples:
  #| TestCaseID      | ExpectedStatusCode |
  #| POST_PROGRAM_08 |                400 |
  #| POST_PROGRAM_09 |                400 |
  #| POST_PROGRAM_10 |                400 |
  #
  #PUT by Program Name Scenarios
  #@TC7
  #Scenario Outline: Validate API response for updating program with valid request body
  #Given Admin creates PUT request body for "<TestCaseID>"
  #When Admin sends PUT request for <programIndex> with valid request body for "<TestCaseID>"
  #Then Response status code should be "<ExpectedStatusCode>"
  #
  #Examples:
  #| TestCaseID     | ExpectedStatusCode | programIndex |
  #| PUT_PROGRAM_01 |                200 |                0 |
  #| PUT_PROGRAM_02 |                200 |                1 |
  #| PUT_PROGRAM_03 |                200 |                0 |
  #| PUT_PROGRAM_04 |                400 |                0 |
  #| PUT_PROGRAM_06 |                400 |                0 |
  #| PUT_PROGRAM_07 |                400 |                0 |
  #
  #@TC8
  #Scenario Outline: Validate API response for updating program with invalid Program Name
  #Given Admin creates PUT request body for "<TestCaseID>"
  #When Admin sends PUT request with invalid Program Name for "<TestCaseID>"
  #Then Response status code should be "<ExpectedStatusCode>"
  #
  #Examples:
  #| TestCaseID     | ExpectedStatusCode |
  #| PUT_PROGRAM_05 |                404 |
  #
  #@TC9
  #Scenario Outline: Validate API response for updating program with No Auth
  #Given Admin creates PUT request body for "<TestCaseID>"
  #When Admin sends PUT request with No Auth for "<TestCaseID>"
  #Then Response status code should be "<ExpectedStatusCode>"
  #
  #Examples:
  #| TestCaseID     | ExpectedStatusCode |
  #| PUT_PROGRAM_05 |                401 |
  #
 # PUT by Program ID Scenarios
  #@TC10
  #Scenario Outline: Validate API response for updating program by ID
    #Given Admin creates PUT request body for "<TestCaseID>"
    #When Admin sends PUT request for <programIndex> by Program ID for "<TestCaseID>"
    #Then Response status code should be "<ExpectedStatusCode>"
#
    #Examples: 
      #| TestCaseID        | ExpectedStatusCode | programIndex |
      #| PUT_PROGRAM_ID_01 |                200 |            0 |
      #| PUT_PROGRAM_ID_02 |                200 |            1 |
      #| PUT_PROGRAM_ID_03 |                200 |            0 |
      #
      #
      #@TC11
  #Scenario Outline: Validate API response for updating program by ID with invalid body
    #Given Admin creates PUT request body for "<TestCaseID>"
    #When Admin sends PUT request for <programIndex> with invalid request body for "<TestCaseID>"
    #Then Response status code should be "<ExpectedStatusCode>"
#
    #Examples: 
      #| TestCaseID        | ExpectedStatusCode | programIndex |
      #| PUT_PROGRAM_ID_04 |                404 |            0 |
      #| PUT_PROGRAM_ID_05 |                404 |            0 |
      #| PUT_PROGRAM_ID_06 |                400 |            0 |
      #| PUT_PROGRAM_ID_07 |                400 |            0 |
      #| PUT_PROGRAM_ID_08 |                400 |            0 |
      #| PUT_PROGRAM_ID_09 |                401 |            0 |
     #
     #
     #
     #@TC12
  #Scenario Outline: Validate API response for updating program by ID with invalid Method
    #Given Admin creates PUT request body for "<TestCaseID>"
    #When Admin sends PUT request for <programIndex> with invalid method for "<TestCaseID>"
    #Then Response status code should be "<ExpectedStatusCode>"
#
    #Examples: 
      #| TestCaseID        | ExpectedStatusCode | programIndex |
     #| PUT_PROGRAM_ID_10 |                405 |            0 |
