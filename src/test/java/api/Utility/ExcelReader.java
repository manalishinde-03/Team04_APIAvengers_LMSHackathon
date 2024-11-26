package api.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import api.Pojo.BatchRequestBodyPojo;
//import api.Pojo.BatchRequestPojo;
import api.Pojo.BatchRequestPojo;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import api.Pojo.CreateProgramRequestPojo;
import api.Pojo.GetAllClassPojo;
import api.Pojo.LogInOutRequestPojo;
import api.Pojo.classCrequestPojo;
import api.Pojo.createClassRequestBodyPojo;

public class ExcelReader extends CommonUtils{
	
	
	public static String getauthorization;
	public static String getendpoint;
	public static String getmethod;
	public static String endpoint;
	public static String authorization;
	public static String baseUrlFromDataFile;
	
    public static List<CreateProgramRequestPojo> readTestData(String filePath, String sheetName) throws Exception {

        List<CreateProgramRequestPojo> programDataList = new ArrayList<>();
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // to Skip header row
            Row row = sheet.getRow(i);

            CreateProgramRequestPojo programData = new CreateProgramRequestPojo();
		
            programData.setTestCaseId(getCellValueAsString(row.getCell(0)));
            programData.setProgramName(getCellValueAsString(row.getCell(1)));
            programData.setProgramStatus(getCellValueAsString(row.getCell(2)));
            programData.setProgramDescription(getCellValueAsString(row.getCell(3)));
            programData.setExpectedStatusCode(getCellValueAsString(row.getCell(4)));

            programData.setEndPoint(getCellValueAsString(row.getCell(5)));
            // Added by Neha
            programData.setAction(getCellValueAsString(row.getCell(6)));
            programData.setMethod(getCellValueAsString(row.getCell(7)));

            programDataList.add(programData);
            
            //System.out.println("Loaded Row: " + row.getCell(0).getStringCellValue());
        }

        workbook.close();
        return programDataList;
    }

    // Added by Neha, To handle and create Pojo for LogInOut
    public static HashMap<String, LogInOutRequestPojo> readTestDataLoginLogout(String filePath, String sheetName) throws Exception {
        HashMap<String, LogInOutRequestPojo> testDataMap = new HashMap<>();
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // to Skip header row
            Row row = sheet.getRow(i);

            LogInOutRequestPojo logInOutRequestPojo = new LogInOutRequestPojo();
            logInOutRequestPojo.setTestCaseId(getCellValueAsString(row.getCell(0)));
            logInOutRequestPojo.setBaseUri(getCellValueAsString(row.getCell(1)));
            logInOutRequestPojo.setEndPoint(getCellValueAsString(row.getCell(2)));
            logInOutRequestPojo.setExpectedCode(getCellValueAsString(row.getCell(3)));
            logInOutRequestPojo.setUserLoginEmailId(getCellValueAsString(row.getCell(4)));
            logInOutRequestPojo.setPassword(getCellValueAsString(row.getCell(5)));
            logInOutRequestPojo.setAction(getCellValueAsString(row.getCell(6)));
            testDataMap.put(logInOutRequestPojo.getTestCaseId(), logInOutRequestPojo);
            System.out.println("Loaded Row: " + row.getCell(0).getStringCellValue());
        }
        workbook.close();
        file.close();
        return testDataMap;
    }

    //  cell value as a String
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); // Handling the dates
                }
                return String.valueOf((int) cell.getNumericCellValue()); // Convert numeric to int and then to String
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
    
    
public  BatchRequestPojo batchPojoUtil(String Sheet, String TestCase) throws IOException {
		
		XSSFWorkbook wb;		
		File file = new File(config.getString("Excel_Path"));																							
		FileInputStream fis = new FileInputStream(file); 
		wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(Sheet);		
		BatchRequestPojo batchreq= new BatchRequestPojo();
		BatchRequestBodyPojo createBatch= new BatchRequestBodyPojo();	
		
		//System.out.println("TestCase Name:"+TestCase);
		for (Row row : sheet) {

			Cell firstCell = row.getCell(0);
			//System.out.println("First Call:"+firstCell);
			if(null != firstCell && firstCell.getStringCellValue()!="" && firstCell.getCellType() == CellType.STRING
					&& firstCell.getStringCellValue().equals(TestCase) ) {
				
				 System.out.println("Row :"+firstCell.getStringCellValue());
				 
				 
				if (TestCase.startsWith("POST") || TestCase.startsWith("UPDATE") )
				{	
					
					Cell BatchDescription=row.getCell(3);
					if (BatchDescription != null && BatchDescription.getCellType() == CellType.STRING) {
						
			            createBatch.setBatchDescription(BatchDescription.getStringCellValue());
			        } else if(BatchDescription != null && TestCase.startsWith("UPDATE"))
					{
						createBatch.setBatchDescription(BatchDescription.getStringCellValue());
					}else {
			            createBatch.setBatchDescription(null); 
			        }						
					
					
					Cell prgname= row.getCell(7);
					if(prgname != null && prgname.getStringCellValue().equalsIgnoreCase("Valid") && TestCase.equalsIgnoreCase("POST-BATCH-05"))
					{
						createBatch.setProgramName(programNameArrList.get(0));
					}else if(prgname != null && prgname.getStringCellValue().equalsIgnoreCase("Valid") && TestCase.equalsIgnoreCase("POST-BATCH-08"))
					{
						createBatch.setProgramName(programNameArrList.get(1));
					}else if(prgname != null && TestCase.startsWith("UPDATE"))
					{
						createBatch.setProgramName(prgname.getStringCellValue());
					}else {						
						createBatch.setProgramName(null);
						
					}
					
					
					Cell BatchStatus = row.getCell(5);
			        if (BatchStatus != null && BatchStatus.getCellType() == CellType.STRING) {
			            createBatch.setBatchStatus(BatchStatus.getStringCellValue());
			        } else {
			            createBatch.setBatchStatus(null); 
			        }
					
					
					if (row.getCell(4) != null) {
			            String NoOfClasses = row.getCell(4).getStringCellValue();		            
			            createBatch.setBatchNoOfClasses(NoOfClasses != null && !NoOfClasses.isEmpty() ? Integer.parseInt(NoOfClasses.trim()) : null);
			        }         
										
					}
					
					batchreq.setEndpoint(row.getCell(1).getStringCellValue());
					
					Cell paramet=row.getCell(9);
					 if (paramet != null){			
						 batchreq.setParam(paramet.getStringCellValue());
			            
			        }else {
			        	batchreq.setParam(null);
			        }
					
					 
					Cell batchid=row.getCell(10);	
					if(batchid !=null && batchid.getStringCellValue().length()>0) {
					batchreq.setBatchId(Integer.parseInt(batchid.getStringCellValue()));
					}else {
						batchreq.setBatchId(null);
					}
						
					batchreq.setAuthorizarion(row.getCell(8).getStringCellValue());
					
					
	                  Cell Batchname = row.getCell(2);
					
					if (Batchname != null && Batchname.getCellType() == CellType.STRING) {
			            createBatch.setBatchName(Batchname.getStringCellValue());
			        } else if(Batchname == null && (TestCase.equalsIgnoreCase("UPDATE_BY_BATCHID_03") || TestCase.equalsIgnoreCase("UPDATE_BY_BATCHID_08"))) {
			        	createBatch.setBatchName(batchNameArrList.get(0));
			        }else if(Batchname != null && TestCase.startsWith("UPDATE"))
					{
						createBatch.setBatchName(Batchname.getStringCellValue());
					}
			        else {
			            createBatch.setBatchName(null); 
			        }
					

			        Cell prgID=row.getCell(6);
					if (prgID != null && prgID.getStringCellValue().equalsIgnoreCase("Valid") && TestCase.equalsIgnoreCase("POST-BATCH-05")){
						createBatch.setProgramId(programIDArrList.get(0));  
			        }else if (prgID != null && prgID.getStringCellValue().equalsIgnoreCase("Valid") && TestCase.equalsIgnoreCase("POST-BATCH-08")) {
			        	createBatch.setProgramId(programIDArrList.get(1));
			        }
					else if (prgID != null){						   
				            createBatch.setProgramId(Integer.parseInt(prgID.getStringCellValue()));
				            
				        }else if(prgID == null && (TestCase.equalsIgnoreCase("UPDATE_BY_BATCHID_03") || TestCase.equalsIgnoreCase("UPDATE_BY_BATCHID_08"))) {
				        	createBatch.setProgramId(programIDArrList.get(0));
				        }
				        else {
				        	createBatch.setProgramId(null);
				        }
					
					batchreq.setCreatebatch(createBatch); 
					
				}	
			
			}		
		
		wb.close();			
		
		return batchreq;
		
	}

public static classCrequestPojo classPojoUtil(String Sheet, String TestCase) throws IOException {

	XSSFWorkbook wb;
	File file = new File(config.getString("Excel_Path"));
	FileInputStream fis = new FileInputStream(file);
	wb = new XSSFWorkbook(fis);
	XSSFSheet sheet = wb.getSheet(Sheet);
	classCrequestPojo classreq = new classCrequestPojo();
	createClassRequestBodyPojo createClass = new createClassRequestBodyPojo();
	// int BATCH_ID = 8570;

	for (Row row : sheet) {

		Cell firstCell = row.getCell(0);

		if (null != firstCell && firstCell.getStringCellValue() != "" && firstCell.getCellType() == CellType.STRING
				&& firstCell.getStringCellValue().equals(TestCase)) {

			System.out.println("Row :" + firstCell.getStringCellValue());

			if (TestCase.startsWith("POST"))

			{
				
				//code to pick  batchId value from previous batch create- data chaining
				Cell btchname= row.getCell(4);
				if(btchname == null && TestCase.equalsIgnoreCase("POST-CLASS-01"))
				{
					createClass.setBatchId(batchIDArrList.get(0));
				}else if(btchname == null && TestCase.equalsIgnoreCase("POST-CLASS-02"))
				{
					createClass.setBatchId(batchIDArrList.get(1));
				}else if(btchname != null) {						
					createClass.setBatchId(Integer.parseInt(getCellValue(row.getCell(4))));						
				}
				
				//code to pick batchId value from excelfile
//				if (row.getCell(4) != null) {
//					createClass.setBatchId(Integer.parseInt(getCellValue(row.getCell(4))));
//				}

				createClass.setClassComments(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null);

				if (row.getCell(6) != null) {

					String classDate = row.getCell(6).getStringCellValue();
					System.out.println("Date class1: " + classDate);
					classDate = classDate.replaceAll("^\"|\"$", "");
					System.out.println("Date class2: " + classDate);

//	            String classDate1 = getCellValue(row.getCell(6));
//	            System.out.println("Date class1: "+classDate1);
					createClass.setClassDate(classDate);

				}

				createClass
						.setClassDescription(row.getCell(7) != null ? row.getCell(7).getStringCellValue() : null);

				if (row.getCell(8) != null) {
					String classNo = getCellValue(row.getCell(8));
					System.out.println("Class No: " + classNo);
					createClass.setClassNo(
							classNo != null && !classNo.isEmpty() ? Integer.parseInt(classNo.trim()) : null);
				}

				createClass.setClassNotes(row.getCell(9) != null ? row.getCell(9).getStringCellValue() : null);
				createClass.setClassRecordingPath(
						row.getCell(10) != null ? row.getCell(10).getStringCellValue() : null);
				createClass.setClassStaffId(row.getCell(11) != null ? row.getCell(11).getStringCellValue() : null);
				createClass.setClassTopic(row.getCell(12) != null ? row.getCell(12).getStringCellValue() : null);

				// date1,date2
				if (row.getCell(13) != null) {
					String classScheduleListStr = row.getCell(13).getStringCellValue();
					List<String> list = Arrays.asList(classScheduleListStr.split(","));
					createClass.setClassScheduledDates(list);
				}

				classreq.setCreateClass(createClass);
				classreq.setAuthorization(row.getCell(15) != null ? row.getCell(15).getStringCellValue() : null);
				System.out.println("Authorization :" + row.getCell(15));
				authorization = classreq.getAuthorization();

				classreq.setEndpoint(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
				System.out.println("Endpoint" + row.getCell(2));
				endpoint = classreq.getEndpoint();
				
				baseUrlFromDataFile = row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null;
				System.out.println("BaseUrl" + baseUrlFromDataFile);
				
			}
		}
		wb.close();

	}
	return classreq;
}

public static String getCellValue(Cell cell) {
	if (cell == null) {
		return ""; // Return empty string for null cells
	}

	switch (cell.getCellType()) {
	case STRING:
		return cell.getStringCellValue();
	case NUMERIC:
		return String.valueOf((int) cell.getNumericCellValue());
	case BOOLEAN:
		return String.valueOf(cell.getBooleanCellValue());
	case BLANK:
		return "";
	default:
		return "Unsupported Type";
	}
}

// GetAllClass - data from Excel 

public static GetAllClassPojo getAllClassPojoData(String Sheet, String GetTestCases) throws IOException
{
	XSSFWorkbook wb;		
	File file = new File(config.getString("Excel_Path"));																							
	FileInputStream fis = new FileInputStream(file); 
	wb = new XSSFWorkbook(fis);
	XSSFSheet sheet = wb.getSheet(Sheet);	
	
	GetAllClassPojo getall = new GetAllClassPojo();
	
	for (Row row : sheet) {

		Cell firstCell = row.getCell(0);
		if (firstCell != null && firstCell.getCellType() == CellType.STRING
				&& firstCell.getStringCellValue().equals(GetTestCases)) {
			
			System.out.println("Row : " + firstCell.getStringCellValue());
		
			getall.setMethod(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
			System.out.println("Method: " + row.getCell(1));
			getmethod = getall.getMethod();
		
			//to grab batch id from previous class and get class created-chaining
			Cell endpoint = row.getCell(2);
			String endpointStr; 
			if(endpoint != null && GetTestCases.equalsIgnoreCase("GETALL_CLASS_05"))
			{
				endpointStr = row.getCell(2).getStringCellValue() + "/" + batchIDArrList.get(0);
				System.out.println("Endpoint: " + endpointStr);
				getall.setEndpoint(endpointStr);
		
			}else if(endpoint != null) {						
				getall.setEndpoint(row.getCell(2).getStringCellValue());					
				System.out.println("Endpoint: " + getall.getEndpoint());
			}
			getendpoint = getall.getEndpoint();
			
			//code to get enpoint value from excel
//			getall.setEndpoint(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
//			System.out.println("Endpoint: " + row.getCell(2));
//			getendpoint = getall.getEndpoint();
			
			
			if (row.getCell(14) != null) {
				String statusCode = getCellValue(row.getCell(14));
				System.out.println("StatusCode:" + statusCode);
				getall.setStatusCode(statusCode != null && !statusCode.isEmpty() ? Integer.parseInt(statusCode.trim()) : null);
				
				
			getall.setAuthorization(row.getCell(15) != null ? row.getCell(15).getStringCellValue() : null);
			System.out.println("Authorization: " + row.getCell(15));
			getauthorization = getall.getAuthorization();				
			
				
											
			}
			
		}
		wb.close();	
	}
	return getall;
}
}
