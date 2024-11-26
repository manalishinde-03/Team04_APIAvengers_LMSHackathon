@ClassModule

Feature:Get Request All Recordings

  Background:
    Given Admin creates the GET Request

  #AllRecordings
  @AllRecordings
  Scenario: Check if admin able to retrieve all class recordings
    When Admin sends HTTPS Request with the endpoint
    Then Admin receives 200 OK Status with response body

  Scenario: Check if admin able to retrieve all class recordings
  with invalid Endpoint
    When Admin sends HTTPS Request with invalid endpoint
    Then Admin receives 404 Not Found with message visible

  Scenario: Check if admin able to retrieve all class recordings
  with invalid Method
    When Admin sends HTTPS Request with invalid method for getallrecordings
    Then Admin receives 405 and Method not allowed status visible

  Scenario: Check if admin able to retrieve all class recordings
  without authorization
    When Admin sends HTTPS Request without authorization for getallrecordings
    Then Admin receives 401 status with error message Unauthorized

  @ClassRecordingsbyClassId

  Scenario: Check if admin able to retrieve class recordings with valid Class Id
    When Admin sends HTTPS Request with endpoint for getclassRecordingsClassID
    Then Admin receives 200 OK Status with response body

  Scenario: Check if admin able to retrieve all  classes with invalid Class Id
    When Admin sends HTTPS Request with invalid Classid for getclassRecordingsClassID
    Then Admin receives 404 Not Found with message visible

  Scenario: Check if admin able to retrieve all classes with invalid endpoint  for getclassRecordingsClassID
    When Admin sends HTTPS Request with invalid endpoint for getclassRecordingsClassID
    Then Admin receives 404 Not Found Status with message visible

  Scenario: Check if admin able to retrieve class recording with invalid method
    When Admin sends HTTPS Request with invalid method for getclassRecordingsClassID
    Then Admin receives 405 Method not allowed status visible for getclassRecordingsClassID

  Scenario: Check if admin able to retrieve all classes without Authorization
    When  Admin sends HTTPS Request without authorization for getclassRecordingsClassID
    Then  Admin receives 401 status with error message Unauthorized

  @UpcomingClassesforparticularStudentId
  Scenario: Check if admin able to retrieve upcoming classes with valid Student Id
    When Admin sends HTTPS Request for upcoming classes with valid Student Id
    Then Admin receives 200 OK Status with response body for upcoming classes with valid Student Id

  Scenario: Check if admin able to retrieve upcoming classes with invalid Student Id
    When Admin sends HTTPS Request with invalid Student Id for upcoming classes
    Then Admin receives 404 Not Found Status with message visible for upcoming classes with invalid Student Id

  Scenario: Check if admin able to retrieve upcoming classes with invalid endpoint
    When Admin sends HTTPS Request with invalid endpoint for upcoming classes
    Then Admin receives 404 Not Found Status with message visible for upcoming classes

  Scenario: Check if admin able to retrieve upcoming classes with invalid method
    When Admin sends HTTPS Request with invalid method for upcoming classes
    Then Admin receives 405 and Method not allowed status visible for upcoming classes

  Scenario: Check if admin able to retrieve all classes without Authorization
    When Admin sends HTTPS Request without authorization for upcoming classes
    Then Admin receives 401 status with error message Unauthorized for upcoming classes

















