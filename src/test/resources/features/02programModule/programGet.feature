
@GetProgramAPIVerification
Feature: GET Program Details

  @TC1
  Scenario: Get All Programs with valid endpoint
    Given Admin creates GET Request for the LMS API
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 200 OK Status with response body                                                       


