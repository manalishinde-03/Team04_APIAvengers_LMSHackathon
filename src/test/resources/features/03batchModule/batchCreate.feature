Feature: Admin Batch Module

Scenario: User Login Scenario
    Given Admin creates request with valid credentials  
  When Admin calls Post Https method  with valid endpoint
  Then Admin receives 200 OK with auto generated token

@test
Scenario Outline: Check if admin able to create a Batch with valid endpoint and request body

Given Admin creates POST request body for the Testcases "<TestCase>"
When  Admin sends HTTPS Request with endpoint
Then  Admin receives respective status codes "<Exp_StatusCode>" for the TestCases 
 
 Examples: 
 
 |TestCase|Exp_StatusCode| 
 |POST-BATCH-01| 404|
 |POST-BATCH-02|401|
 |POST-BATCH-03|400|
 |POST-BATCH-04|400|
 
 