Feature: Admin Batch Module

Background: 

 Given Admin sets "<Valid>" Authorization to Bearer Token


@test
Scenario Outline: Check if admin able to create a Batch with valid endpoint and request body

Given Admin creates POST request body for the Testcases "<TestCase>"
When  Admin sends HTTPS Request with endpoint
Then  Admin receives respective status codes "<Exp_StatusCode>" for the TestCases 
 
 Examples: 
 
 |TestCase|Exp_StatusCode| 
 |POST-BATCH-01|404|
 |POST-BATCH-02|401|
 |POST-BATCH-03|400|
 |POST-BATCH-04|400|
 |POST-BATCH-05|400|
 |POST-BATCH-06|400|
 |POST-BATCH-07|404|
 |POST-BATCH-08|201|
 
 
 
@test
Scenario Outline: Check if admin able to retrieve all batches  
 
Given  Admin creates GET Request for the "<GetTestCases>"
When  Admin sends the GetAll HTTPS Request with endpoint 
Then  Admin receives status code with response body "<ExpStatusCode>"                                                             


 Examples: 
 |GetTestCases|ExpStatusCode|
 |GETALL_BATCH_01| 401 |
 |GETALL_BATCH_02| 400 |
 
 
 
 
 
 
 
 
 
