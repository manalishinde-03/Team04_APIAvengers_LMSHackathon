#Author:Anusuya Selvaraj	
@CreateClass
Feature: Create Class API 
  
  Background: Set Auth to bearer token (admin token)

  @CreateClassAuthRequired
  Scenario: Check if admin is able to create class with all valid details
    Given Admin creates POST Request for the LMS with request body
    When Admin calls "POST " request with endpoint
    Then Admin receive 201 created status 

  Scenario: Check if admin is able to create class with only mandatory
  Given Admin creates POST Request for the LMS with mandatory data
  When Admin calls "POST " request with endpoint
  Then Admin receive 201 created status 
  
  Scenario: Check if admin is able to create class with only additional field values
  Given Admin creates POST Request for the LMS with only addditional field values
  When Admin calls "POST " request with endpoint
  Then Admin receive 400 bad request
  
  Scenario: Check if admin is able to create class with invalid data
  Given Admin creates POST Request for the LMS with invalid data  
  When  Admin calls "POST " request with endpoint
  Then  Admin receive 400 with error message
  
  
  Scenario: Check if admin is able to create class with existing class topic
  Given Admin creates POST request for the LMS with existing class topic
  When Admin calls "POST " request with endpoint
  Then Admin receive 400 with error message
  
  
  
  Scenario: Check if admin is able to create class without request body
  Given Admin create POST request for the LMS without request body
  When Admin calls "POST " request with endpoint
  Then Admin receive 400 / 415 status code
  
  
  Scenario: Check if admin is able to create class with invalid endpoint
  
  Given Admin create POST request for the LMS with valid request
  When Admin calls "POST " request with invalid endpoint
  Then Admin receive 404 Not found
  
  
  Scenario: Check if admin is able to create class with invalid baseURL
  
  Given Admin create POST request for the LMS with invalid baseURL
  When Admin calls "POST " request with endpoint
  Then Admin receive 404 Not found
  
 