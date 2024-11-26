@ClassModuleAPI
Feature: DELETE REQUEST(class by Class Id)

Background:
  Given Admin creates DELETE Request
  
  @DeleteClassbyClassID
  Scenario: Check if admin able to delete a class
  When Admin sends HTTPS Request with endpoint for Delete Class by Class Id 
  Then Admin receives 200 Status OK with response body for Delete Class by Class Id 
  
  Scenario: Check if admin able to delete a class with invalid endpoint for Delete Class by Class Id
  When Admin sends HTTPS Request with invalid endpoint for Delete Class by Class Id 
  Then Admin receives 404 Not Found Status with error message for Delete Class by Class Id 
  
  Scenario: Check if admin able to retrieve  all Classes with invalid Class ID for Delete Class by Class Id 
  When Admin sends HTTPS Request with invalid Class Id for Delete Class by Class Id 
  Then Admin receives 404 Not Found Status with message visible for Delete Class by Class Id 
  
  Scenario: Check if admin able to delete a class without authorization
  When Admin sends HTTPS Request without authorization for Delete Class by Class Id 
  Then Admin receives 401 Unauthorized Status for Delete Class by Class Id 
  
  