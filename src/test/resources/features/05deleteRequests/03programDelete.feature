@ProgramModuleAPIVerification
Feature: Delete Program Module

  @TC01
  Scenario Outline: Delete program by ID with valid endpoint
    Given Admin creates DELETE request for "<TestCaseID>"
    When Admin sends DELETE request for <programIndex> with programID and valid endpoint for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID        | ExpectedStatusCode | programIndex |
      | DEL_PROGRAM_ID_01 |                200 |            2 |

  @TC02
  Scenario Outline: Delete program by invalid ID with valid endpoint
    Given Admin creates DELETE request for "<TestCaseID>"
    When Admin sends DELETE request for <programIndex> with invalid Program ID for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID        | ExpectedStatusCode | programIndex |
      | DEL_PROGRAM_ID_02 |                404 |            2 |
      | DEL_PROGRAM_ID_03 |                401 |            3 |
     
  @TC01
  Scenario Outline: Delete program by Name with valid endpoint
    Given Admin creates DELETE request for "<TestCaseID>"
    When Admin sends DELETE request for <programIndex> with programName and valid endpoint for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID        | ExpectedStatusCode | programIndex |
      | DEL_PROGRAM_NAME_01 |                200 |            2 |

  @TC02
  Scenario Outline: Delete program by invalid Name with valid endpoint
    Given Admin creates DELETE request for "<TestCaseID>"
    When Admin sends DELETE request for <programIndex> with invalid Program Name for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID        | ExpectedStatusCode | programIndex |
      | DEL_PROGRAM_NAME_02 |                404 |            3 |
      | DEL_PROGRAM_NAME_03 |                401 |            2 |
