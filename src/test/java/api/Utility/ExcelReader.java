package api.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import api.Pojo.BatchRequestBodyPojo;
import api.Pojo.BatchRequestPojo;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import api.Pojo.CreateProgramRequestPojo;
import api.Pojo.LogInOutRequestPojo;

public class ExcelReader extends CommonUtils{
    public static List<CreateProgramRequestPojo> readTestData(String filePath, String sheetName) throws Exception {

        List<CreateProgramRequestPojo> programDataList = new ArrayList<>();
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);

        System.out.println(">>>>>>>>>>>>>>> in Excel reader to read sheet");
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

        System.out.println(">>>>>>>>>>>>>>> in Excel reader to read sheet");
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
}
