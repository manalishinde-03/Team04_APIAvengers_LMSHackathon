Feature: Delete Batch Module

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
 