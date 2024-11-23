#Author: Anusuya Selvaraj	
@GetAllClass
Feature: Get All Classses

Background: 

 Given Admin creates GET Request
 
  @GetAllRequest
  Scenario: Check if admin able to retrieve all classes  with valid Endpoint
  When Admin sends HTTPS Request with endpoint for getAllclass
  Then Admin receives 200 OK Status with response body for getAllclass                                                               
  
  
  @GetAllRequest
  Scenario: Check if admin able to retrieve all  classes with invalid Endpoint
  When Admin sends HTTPS Request with invalid endpoint for getAllclass
  Then Admin receives 404 status with error message Not Found for getAllclass
  
  @GetAllRequest
  Scenario: Check if admin able to retrieve all  classes with invalid method for getAllclass
  When Admin sends HTTPS Request with invalid method for getAllclass
  Then Admin receives 405 Method not allowed status visible for getAllclass
  
  @GetAllRequest
  Scenario: Check if admin able to retrieve all classes without Authorization
  When Admin sends HTTPS Request without authorization for getAllclass 
  Then Admin receives 401 status with error message Unauthorized for getAllclass 
  
  
  
  @GetclassrecordingsbyBatchId
  Scenario: Check if admin able to retrieve class recording  with valid Batchid
  When Admin sends HTTPS Request with endpoint for getclassRecordingsBatchID
  Then Admin receives 200 OK Status with response body for getclassRecordingsBatchID                                                            
   
  @GetclassrecordingsbyBatchId
 Scenario: Check if admin able to retrieve  class recording with invalid Batchid
  When Admin sends HTTPS Request with invalid Batchid for getclassRecordingsBatchID 
  Then Admin receives 404 Not Found Status with message visible for getclassRecordingsBatchID
  
  @GetclassrecordingsbyBatchId
 Scenario: Check if admin able to retrieve class recording with invalid Endpoint
  When Admin sends HTTPS Request with invalid endpoint for getclassRecordingsBatchID
  Then Admin receives 404 Not Found Status with message visible for getclassRecordingsBatchID
  
  @GetclassrecordingsbyBatchId 
 Scenario: Check if admin able to retrieve class recording with invalid method
  When Admin sends HTTPS Request with invalid method for getclassRecordingsBatchID
  Then Admin receives 405 Method not allowed status visible for getclassRecordingsBatchID
  
 @GetclassrecordingsbyBatchId
 Scenario: Check if admin able to retrieve class recording without Authorization
  When Admin sends HTTPS Request without authorization for getclassRecordingsBatchID
  Then Admin receives 401 status with error message Unauthorized for getclassRecordingsBatchID
  
  
  
  
@GetClassbyclassId
Scenario: Check if admin able to retrieve Class details  with valid classId
When Admin sends HTTPS Request with endpoint for GetClassbyclassId
Then Admin receives 200 OK Status with response body for GetClassbyclassId 
                                                       
@GetClassbyclassId
Scenario: Check if admin able to retrieve Class details with invalid classId
When Admin sends HTTPS Request with invalid classId for GetClassbyclassId 
Then Admin receives 404 Not Found Status with message visible for GetClassbyclassId 

@GetClassbyclassId
Scenario: Check if admin able to retrieve Class details with invalid Endpoint
When Admin sends HTTPS Request with invalid endpoint for GetClassbyclassId 
Then Admin receives 404 Not Found Status with message visible for GetClassbyclassId 

@GetClassbyclassId
Scenario: Check if admin able to retrieve Class details with invalid method
When Admin sends HTTPS Request with invalid method for GetClassbyclassId  
Then Admin receives 405 Method not allowed status visible for GetClassbyclassId 
@GetClassbyclassId
Scenario: Check if admin able to retrieve Class details without Authorization 
When Admin sends HTTPS Request without authorization for GetClassbyclassId  
Then Admin receives 401 status with error message Unauthorized for GetClassbyclassId 



  
 @GetClassbyClasstopic 
 Scenario: Check if admin able to retrieve all classes  with valid classtopic
 When Admin sends HTTPS Request with endpoint for GetClassbyClasstopic
 Then Admin receives 200 OK Status with response body for GetClassbyClasstopic                                                                
 
 @GetClassbyClasstopic
 Scenario: Check if admin able to retrieve all classes with invalid classtopic
 When Admin sends HTTPS Request with invalid classtopic for GetClassbyClasstopic 
Then Admin receives 404 Not Found Status with message visible for GetClassbyClasstopic 

  @GetClassbyClasstopic
 Scenario: Check if admin able to retrieve all  classes with invalid Endpoint
 When Admin sends HTTPS Request with invalid endpoint for GetClassbyClasstopic 
Then Admin receives 404 Not Found Status with message visible for GetClassbyClasstopic

 @GetClassbyClasstopic
 Scenario: Check if admin able to retrieve all classes with invalid method
 When Admin sends HTTPS Request with invalid method for GetClassbyClasstopic  
 Then Admin receives 405 Method not allowed status visible for GetClassbyClasstopic 
 
  @GetClassbyClasstopic
 Scenario: Check if admin able to retrieve all classes without Authorization
  When Admin sends HTTPS Request without authorization for GetClassbyClasstopic  
  Then Admin receives 401 status with error message Unauthorized for GetClassbyClasstopic
   
   
   
  
 @GetallClassesbyBatchId
Scenario: Check if admin able to retrieve all Classes  with valid Batchid
 When Admin sends HTTPS Request with endpoint for GetallClassesbyBatchId
 Then Admin receives 200 OK Status with response body for GetallClassesbyBatchId 
 @GetallClassesbyBatchId
Scenario: Check if admin able to retrieve  all Classes with invalid Batchid
When Admin sends HTTPS Request with invalid Batchid for GetallClassesbyBatchId 
Then Admin receives 404 Not Found Status with message visible for GetallClassesbyBatchId 
 @GetallClassesbyBatchId1
Scenario: Check if admin able to retrieve all Classes with invalid Endpoint
When Admin sends HTTPS Request with invalid endpoint for GetallClassesbyBatchId 
Then Admin receives 404 Not Found Status with message visible for GetallClassesbyBatchId

 @GetallClassesbyBatchId
Scenario: Check if admin able to retrieve all Classes with invalid method
When Admin sends HTTPS Request with invalid method for GetallClassesbyBatchId  
 Then Admin receives 405 Method not allowed status visible for GetallClassesbyBatchId 
 @GetallClassesbyBatchId
Scenario: Check if admin able to retrieve  all Classes without Authorization
   When Admin sends HTTPS Request without authorization for GetallClassesbyBatchId  
  Then Admin receives 401 status with error message Unauthorized for GetallClassesbyBatchId
   
  
  
 @GetallClassesbyStaffId
Scenario: Check if admin able to retrieve all Classes with valid StaffId
 When Admin sends HTTPS Request with endpoint for GetallClassesbyStaffId
 Then Admin receives 200 OK Status with response body for GetallClassesbyStaffId 
@GetallClassesbyStaffId
Scenario: Check if admin able to retrieve  all Classes with invalid StaffId
When Admin sends HTTPS Request with invalid StaffId for GetallClassesbyStaffId 
Then Admin receives 404 Not Found Status with message visible for GetallClassesbyStaffId 
@GetallClassesbyStaffId
Scenario: Check if admin able to retrieve all Classes with invalid Endpoint
When Admin sends HTTPS Request with invalid endpoint for GetallClassesbyStaffId 
Then Admin receives 404 Not Found Status with message visible for GetallClassesbyStaffId
@GetallClassesbyStaffId
Scenario: Check if admin able to retrieve all Classes with invalid method
When Admin sends HTTPS Request with invalid method for GetallClassesbyStaffId  
 Then Admin receives 405 Method not allowed status visible for GetallClassesbyStaffId 
@GetallClassesbyStaffId
Scenario: Check if admin able to retrieve  all Classes without Authorization
   When Admin sends HTTPS Request without authorization for GetallClassesbyStaffId  
  Then Admin receives 401 status with error message Unauthorized for GetallClassesbyStaffId
  
  