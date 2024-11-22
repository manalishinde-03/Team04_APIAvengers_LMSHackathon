
@GetById
Feature: Get Classes by Id

Background: Admin sets Authorization to Bearer Token

  
  @GetById
  Scenario: Check if admin able to retrieve class recording  with valid Class Id
  
  Given Admin creates GET Request 
  When Admin sends HTTPS Request with endpoint 
  Then Admin receives 200 OK Status with response body                                                                
  
  Scenario: Check if admin able to retrieve all  classes with invalid Class Id
  Given Admin creates GET Request 
  When Admin sends HTTPS Request with endpoint 
  Then Admin receives 404 Not found with message visible
  
  Scenario: Check if admin able to retrieve all  classes with invalid endpoint
  Given Admin creates GET Request 
  When Admin sends HTTPS Request with invalid endpoint 
  Then Admin receives 404 Not Found Status with message visible
  
  Scenario: Check if admin able to retrieve class recording with invalid method
  Given Admin creates GET Request 
  When Admin sends HTTPS Request with endpoint 
  Then Admin receives 405 and Method not allowed status visible
  
  Scenario: Check if admin able to retrieve all classes without Authorization
  Given Admin creates GET Request 
  When  Admin sends HTTPS Request with endpoint 
  Then  Admin receives 401 status with error message Unauthorized
  
  
  
  