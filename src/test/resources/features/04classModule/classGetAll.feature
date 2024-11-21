#Author: Anusuya Selvaraj	
@GetAllClass
Feature: Get All Classses


Background: Admin sets Authorization to Bearer Token. 


  
  @GetAllAuthRequired
  Scenario: Check if admin able to retrieve all classes  with valid Endpoint
  Given Admin creates GET Request 
  When Admin sends HTTPS Request with endpoint 
  Then Admin receives 200 OK Status with response body.                                                                
  
  Scenario: Check if admin able to retrieve all  classes with invalid Endpoint
  Given Admin creates GET Request 
  When Admin sends HTTPS Request with invalid endpoint 
  Then Admin receives 404 status with error message Not Found .
  
  Scenario: Check if admin able to retrieve all  classes with invalid method
  Given Admin creates GET Request 
  When Admin sends HTTPS Request with endpoint 
  Then Admin receives 405 Method not allowed status visible.
  
  Scenario: Check if admin able to retrieve all classes without Authorization
  Given Admin creates GET Request 
  When  Admin sends HTTPS Request with endpoint 
  Then  Admin receives 401 status with error message Unauthorized.
  
  