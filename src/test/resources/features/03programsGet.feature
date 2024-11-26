@ProgramModule
Feature: Get All Programs

  @tag2
  Scenario Outline: User able to retrieve all programs with valid Endpoint
    Given Admin creates GET Program request for the LMS API for "<TestCaseID>"
    When Admin sends GET request with endpoint for "<TestCaseID>"
    Then Admin receives "<ExpectedStatusCode>" status.

    Examples:
      | TestCaseID                | ExpectedStatusCode |
      | GET_ALL_PROGRAM_TC11      | 200                |
      | GET_ALL_PROGRAM_12        | 404                |
      | GET_ALL_PROGRAM_13        | 405                |
      | GET_ALL_PROGRAM_14        | 401                |
      | GET_ID_PROGRAM_15         | 200                |
      | GET_ID_PROGRAM_16         | 404                |
      | GET_ID_PROGRAM_17         | 404                |
      | GET_ID_PROGRAM_18         | 401                |
      | GET_ID_PROGRAM_19         | 404                |
      | GET_ALLUSERS_PROGRAM_20   | 200                |
      | GET_ALLUSERS_PROGRAM_21   | 404                |
      | GET_ALLUSERS_PROGRAM_22   | 405                |
      | GET_ALLUSERS_PROGRAM_23   | 401                |
