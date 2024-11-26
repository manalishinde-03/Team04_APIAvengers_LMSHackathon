#Author: Anusuya S

Feature: Get All Classes

@GetAllClass
Scenario Outline: Check if admin able to retrieve all classes for positive and negative scenarios  
 
Given  Admin creates GET Request for the "<GetTestCases>" for classmodule
When  Admin sends the GetAll HTTPS Request with endpoint for classmodule
Then  Admin receives status code with response body "<ExpStatusCode>" for classmodule                                                           


 Examples: 
 |GetTestCases|ExpStatusCode|
 |GETALL_CLASS_01| 200 |
 |GETALL_CLASS_02| 404 |
 |GETALL_CLASS_03| 405 |
 |GETALL_CLASS_04| 401 |
 |GETALL_CLASS_05| 200 |
 |GETALL_CLASS_06| 404 |
 |GETALL_CLASS_07| 404 |
 |GETALL_CLASS_08| 405 |
 |GETALL_CLASS_09| 401 |
 |GETALL_CLASS_10| 200 |
 |GETALL_CLASS_11| 404 |
 |GETALL_CLASS_12| 404 |
 |GETALL_CLASS_13| 405 |
 |GETALL_CLASS_14| 401 |
 |GETALL_CLASS_15| 200 |
 |GETALL_CLASS_16| 404 |
 |GETALL_CLASS_17| 404 |
 |GETALL_CLASS_18| 405 |
 |GETALL_CLASS_19| 401 |
 |GETALL_CLASS_20| 200 |
 |GETALL_CLASS_21| 404 |
 |GETALL_CLASS_22| 404 |
 |GETALL_CLASS_23| 405 |
 |GETALL_CLASS_24| 401 |
 |GETALL_CLASS_25| 200 |
 |GETALL_CLASS_26| 404 |
 |GETALL_CLASS_27| 404 |
 |GETALL_CLASS_28| 405 |
 |GETALL_CLASS_29| 401 |
 
 