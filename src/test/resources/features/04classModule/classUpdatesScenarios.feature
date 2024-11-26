@UpdateNewClass
Feature: Put Classes by class Recording path

Background: Admin sets Authorization to Bearer Token

  
  @UpdateNewClass
  Scenario: Check if admin able to update a class with valid classID 
  and mandatory fields in request body 
  Given Admin creates PUT Request with valid class Id and all the fields
  When Admin sends Https Request with endpoint 
  Then Admin receives 200 OK Status with updated value in response body                                                                
  
  Scenario: Check if admin able to update a Class Recording 
  with valid classID  in request body
  Given Admin creates PUT Request with valid classId
  When Admin sends Https Request with endpoint 
  Then Admin receives 200 OK Status with updated value in response body.                                         
  
  
  Scenario: Check if admin able to update a class with valid classID 
  and mandatory fields in request body without authorization
  Given Admin creates PUT Request with valid classID and data
  When Admin sends Https Request with endpoint
  Then Admin receives 401 Unauthorized
  
  Scenario: Check if admin able to update a class with invalid classID 
  and mandatory fields in request body
  Given Admin creates PUT Request with invalid classID and valid data
  When Admin sends Https Request with endpoint
  Then Admin receives 404 Not Found Status with message and boolean success details
  
  Scenario: Check if admin able to update a Class with invalid data
  Given Admin creates PUT  Request with invalid data
  When Admin sends Https Request with endpoint 
  Then Admin receives 400 Bad Request Status with message and boolean success details
    
    
  Scenario: Check if admin able to update a Class with invalid endpoint
  Given Admin creates PUT  Request with valid Class Id
  When Admin sends Https Request with endpoint 
  Then Admin receives 404 Not Found
  
  Scenario: Check if admin able to update a Class with a deleted batchID  field  in the request body with other mandatory fields
  Given Admin creates PUT Request with delelted batch Id
  When  Admin sends Https Request with endpoint 
  Then  Admin receives 400 Bad Request Status with message and boolean success details
  
  
  
  
  