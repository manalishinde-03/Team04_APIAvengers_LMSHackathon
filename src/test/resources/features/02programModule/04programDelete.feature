@ProgramModuleAPIVerification
Feature: Delete Program Module

  #DELETE by Program ID Scenarios
  @TC01
  Scenario Outline: Delete program by ID with valid endpoint
    Given Admin creates DELETE request for "<TestCaseID>"
    When Admin sends DELETE request for <programIndex> with programID and valid endpoint for "<TestCaseID>"
    Then Response status code should be "<ExpectedStatusCode>"

    Examples: 
      | TestCaseID        | ExpectedStatusCode | programIndex |
      | DEL_PROGRAM_ID_01 |                200 |            0 |

  #@TC02
  #Scenario Outline: Delete program by invalid ID with valid endpoint
    #Given Admin creates DELETE request for "<TestCaseID>"
    #When Admin sends DELETE request for <programIndex> with invalid Program ID for "<TestCaseID>"
    #Then Response status code should be "<ExpectedStatusCode>"
#
    #Examples: 
      #| TestCaseID        | ExpectedStatusCode | programIndex |
      #| DEL_PROGRAM_ID_02 |                404 |            0 |
      #| DEL_PROGRAM_ID_03 |                401 |            0 |
