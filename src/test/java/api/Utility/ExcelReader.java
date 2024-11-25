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

public class ExcelReader extends CommonUtils{
	
	
	
	public  BatchRequestPojo batchPojoUtil(String Sheet, String TestCase) throws IOException {
		
		XSSFWorkbook wb;		
		File file = new File(config.getString("DataFile"));																							
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
			        } else if(Batchname == null && TestCase.equalsIgnoreCase("UPDATE_BY_BATCHID_03"	)) {
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
				            
				        }else if(prgID == null && TestCase.equalsIgnoreCase("UPDATE_BY_BATCHID_03")) {
				        	createBatch.setProgramId(programIDArrList.get(0));
				        }
				        else {
				        	createBatch.setProgramId(null);
				        }
					
					batchreq.setCreatebatch(createBatch); 
					
				}	
			
			}		
		
		wb.close();			
		System.out.println("batchreq ::"+batchreq.toString());
		return batchreq;
		
	}
	
	
	

}
