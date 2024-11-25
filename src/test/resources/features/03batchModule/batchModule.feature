Feature: Admin Batch Module



Scenario Outline: Check if admin able to create a Batch with endpoint and request body

Given Admin creates POST request body for the Testcases "<TestCase>"
When  Admin sends HTTPS Request with endpoint
Then  Admin receives respective status codes "<Exp_StatusCode>" for the TestCases 
 
 Examples: 
 
 |TestCase|Exp_StatusCode| 
 |POST-BATCH-01|404|
 |POST-BATCH-02|401|
 |POST-BATCH-03|400|
 |POST-BATCH-04|400|
 |POST-BATCH-05|201|
 |POST-BATCH-06|400|
 |POST-BATCH-07|404|
 |POST-BATCH-08|201| 
 
  

Scenario Outline: Check if admin able to retrieve all batches  
 
Given  Admin creates GET Request for the "<GetTestCases>"
When  Admin sends the GetAll HTTPS Request with endpoint 
Then  Admin receives status code with response body "<ExpStatusCode>"                                                             


 Examples: 
 |GetTestCases|ExpStatusCode|
 |GETALL_BATCH_01| 401 |
 |GETALL_BATCH_02| 404 |
 |GETALL_BATCH_03| 200 |
 |GETALL_BATCH_04| 200 |
 
 
 

 Scenario Outline: Check if admin able to retrieve batch by Batch ID
 
 Given  Admin creates GET Request for the GetBy BatchID "<GetByBATCHID_Cases>"
 When  Admin sends the HTTPS Request with GetBy BatchID endpoint 
 Then  Admin receives status code with response body GetBy BatchID "<BATCHID_ExpStatusCode>" 
 
 Examples:
 
 |GetByBATCHID_Cases|BATCHID_ExpStatusCode|
 |GET_BY_BATCHID_01|404|
 |GET_BY_BATCHID_02|401|
 |GET_BY_BATCHID_03|404|
 |GET_BY_BATCHID_04|200|
 |GET_BY_BATCHID_05|200|
 
 
 

Scenario Outline: Check if admin able to retrieve batch by batch Name
 
 Given  Admin creates GET Request for the GetBy BatchName "<GetByBATCHNAME_Cases>" Request
 When  Admin sends the HTTPS Request with GetBy BatchName endpoint 
 Then  Admin receives status code with response body GetBy BatchName "<BATCHNAME_ExpStatusCode>" 
 
 Examples:
 
 |GetByBATCHNAME_Cases|BATCHNAME_ExpStatusCode|
 |GET_BY_BATCHNAME_01|401|
 |GET_BY_BATCHNAME_02|404|
 |GET_BY_BATCHNAME_03|404|
 |GET_BY_BATCHNAME_04|200|
 |GET_BY_BATCHNAME_05|200|
 
  

 Scenario Outline: Check if admin able to retrieve batch by Program ID
 
 Given Admin creates GET Request for the GetBy ProgramID "<GetBYPGMID_Cases>" Request
 When  Admin sends the HTTPS Request with GetBy ProgramID endpoint 
 Then  Admin receives status code with response body GetBy ProgramID "<GetByPGMID_ExpStatusCode>" 
 
 Examples:
 |GetBYPGMID_Cases|GetByPGMID_ExpStatusCode|
 |GET_BY_PGMID_01|404|
 |GET_BY_PGMID_02|401|
 |GET_BY_PGMID_03|404|
 |GET_BY_PGMID_04|200|
 |GET_BY_PGMID_05|200|
 
 
 

Scenario Outline: Check if admin able to delete a Batch By Batch ID

Given Admin creates DELETE Request By BatchId "<DelByBATCHID_Cases>"
When  Admin sends HTTPS Request  with Del By BatchId Endpoint
Then  Admin receives status code with success message for DelByBatchID request "<DelByBATCHID_ExpCode>"
 
 Examples:
 |DelByBATCHID_Cases|DelByBATCHID_ExpCode|
 |DEL_BY_BATCHID_01|404|
 |DEL_BY_BATCHID_02|401|
 |DEL_BY_BATCHID_03|404|
 |DEL_BY_BATCHID_04|200|
 
 

Scenario Outline: Check if admin able to update a batch by BatchID

 Given Admin creates PUT Request By BatchId "<UPDATEByBATCHID_Cases>"
 When Admin sends HTTPS Request with Update By BatchId Endpoint
 Then Admin receives status code with response message for UpdateByBatchID request "<UPDATEByBATCHID_ExpCode>"
 
 Examples: 
 |UPDATEByBATCHID_Cases|UPDATEByBATCHID_ExpCode|
 |UPDATE_BY_BATCHID_01|404|
 |UPDATE_BY_BATCHID_02|401|
 |UPDATE_BY_BATCHID_03|200|
 |UPDATE_BY_BATCHID_04|400|
 |UPDATE_BY_BATCHID_05|400|
 |UPDATE_BY_BATCHID_06|400|
 |UPDATE_BY_BATCHID_07|200|
 |UPDATE_BY_BATCHID_08|200|
 
 
 
 

 
