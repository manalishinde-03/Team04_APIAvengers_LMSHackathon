#Author: Anusuya Selvaraj	
@GetAllClass
Feature: Get All Classses
 
  @GetAllListRequest
  Scenario Outline:  Check if admin able to retrieve all classes 
  Given  Admin creates GET Request for the "<GetTestCases>" getall classes
  When   Admin sends HTTPS Request with endpoint for getAllclass
  Then  Admin receives statuscode with response body for getAllclass "<ExpStatusCode>"                                                                
  
  
  Examples: 
 |GetTestCases|ExpStatusCode|
 |GETALL_CLASS_01| 200 |
 |GETALL_CLASS_02| 404 |
 |GETALL_CLASS_03| 405 |
 |GETALL_CLASS_04| 401 |
  
  
  @GetclassrecordingsbyBatchId
  Scenario Outline: Check if admin able to retrieve all class recordings by BatchId
  Given  Admin creates GET Request for the "<GetTestCases>"class recordings by BatchId
  When Admin sends HTTPS Request with endpoint for getAll class recordings by BatchId
  Then Admin receives statuscode with response body for getAll class recordings by BatchId "<ExpStatusCode>"    
  
   Examples: 
 |GetTestCases|ExpStatusCode|
 |GETALL_CLASS_05| 200 |
 |GETALL_CLASS_06| 404 |
 |GETALL_CLASS_07| 404 |
 |GETALL_CLASS_08| 405 |
 |GETALL_CLASS_09| 401 |
 
 
  @GetClassbyclassId
  Scenario Outline: Check if admin able to retrieve classes byclassId
    Given  Admin creates GET Request for the "<GetTestCases>" by classId
  When   Admin sends HTTPS Request with endpoint for getAllclass by classId
  Then Admin receives statuscode with response body for getAll class by classId "<ExpStatusCode>"   
  
    Examples: 
 |GetTestCases|ExpStatusCode|
  |GETALL_CLASS_10| 200 |
 |GETALL_CLASS_11| 404 |
 |GETALL_CLASS_12| 404 |
 |GETALL_CLASS_13| 405 |
 |GETALL_CLASS_14| 401 |
 
 
  @GetClassbyClasstopic 
  Scenario Outline: Check if admin able to retrieve all classes byClasstopic
    Given  Admin creates GET Request for the "<GetTestCases>" by Classtopic
  When Admin sends HTTPS Request with endpoint for getAllclass by Classtopic
  Then Admin receives statuscode with response body for getAllclass by Classtopic "<ExpStatusCode>" 
  
    Examples: 
 |GetTestCases|ExpStatusCode|
 |GETALL_CLASS_15| 200 |
 |GETALL_CLASS_16| 404 |
 |GETALL_CLASS_17| 404 |
 |GETALL_CLASS_18| 405 |
 |GETALL_CLASS_19| 401 |
 
   @GetallClassesbyBatchId
  Scenario Outline: Check if admin able to retrieve Classes byBatchId
    Given  Admin creates GET Request for the "<GetTestCases>" Classes byBatchId
  When Admin sends HTTPS Request with endpoint for getClasses byBatchIdD
  Then Admin receives statuscode with response body for getClasses byBatchId "<ExpStatusCode>"                                                         
   
     Examples: 
 |GetTestCases|ExpStatusCode|
    |GETALL_CLASS_20| 200 |
 |GETALL_CLASS_21| 404 |
 |GETALL_CLASS_22| 404 |
 |GETALL_CLASS_23| 405 |
 |GETALL_CLASS_24| 401 |
 
   @GetallClassesbyStaffId
 Scenario Outline: Check if admin able to retrieve Classes byStaffId
   Given  Admin creates GET Request for the "<GetTestCases>"byStaffId
  When Admin sends HTTPS Request with enpoint for getClasses byStaffId
  Then Admin receives statuscode with response body for getClasses byStaffId
  
    Examples: 
 |GetTestCases|ExpStatusCode|
  |GETALL_CLASS_25| 200 |
 |GETALL_CLASS_26| 404 |
 |GETALL_CLASS_27| 404 |
 |GETALL_CLASS_28| 405 |
 |GETALL_CLASS_29| 401 |
 
 
 
  
  
  
  
  



  
  
  
