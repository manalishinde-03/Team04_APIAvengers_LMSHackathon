#Author:Anusuya Selvaraj	

Feature: Create Class API 
  
 # Background: 
 # Given Admin receives 200 OK with auto generated token

  @CreateClass
  Scenario Outline: Check if admin is able to create class with all details and validating positive and negative scenarios
  
    Given Admin creates Post Request for the LMS with below "<TestCase>" for classCreation
    When  Admin calls POST request with endpoint for classCreation
    Then  Admin receive "<Exp_StatusCode>" created status for classCreation
    
  Examples: 
 
 |TestCase     |Exp_StatusCode| 
 |POST-CLASS-01|201|
 |POST-CLASS-02|201|
 |POST-CLASS-03|400|
 |POST-CLASS-04|400|
 |POST-CLASS-05|400|
 |POST-CLASS-06|400|
 |POST-CLASS-07|404|
 |POST-CLASS-08|404|
 |POST-CLASS-09|401|
 

  