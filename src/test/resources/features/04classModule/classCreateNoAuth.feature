#Author: Anusuya Selvaraj	

@tag
 Feature: Class creation with no auth
  
  Background: Set No auth
  
  Scenario: Check if admin is able to create class without auth
  
  Given Admin creates POST Request for the LMS with request body
  When Admin calls "POST" request with endpoint
  Then Admin receives 401 unauthorized
  