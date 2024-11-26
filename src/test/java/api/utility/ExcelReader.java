package api.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import api.pojo.GetAllClassPojo;
import api.pojo.classCrequestPojo;
import api.pojo.createClassRequestBodyPojo;

public class ExcelReader extends CommonUtils{
	
	//Code to read Data from Excel for -CreateClass
	
	public static String getauthorization;
	public static String getendpoint;
	public static String getmethod;
	public static String endpoint;
	public static String authorization;
	public static String baseUrlFromDataFile;

	public static classCrequestPojo classPojoUtil(String Sheet, String TestCase) throws IOException {

		XSSFWorkbook wb;
		File file = new File(config.getString("DataFile"));
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
//					if (row.getCell(4) != null) {
//						createClass.setBatchId(Integer.parseInt(getCellValue(row.getCell(4))));
//					}

					createClass.setClassComments(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null);

					if (row.getCell(6) != null) {

						String classDate = row.getCell(6).getStringCellValue();
						System.out.println("Date class1: " + classDate);
						classDate = classDate.replaceAll("^\"|\"$", "");
						System.out.println("Date class2: " + classDate);

//		            String classDate1 = getCellValue(row.getCell(6));
//		            System.out.println("Date class1: "+classDate1);
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
		File file = new File(config.getString("DataFile"));																							
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
//				getall.setEndpoint(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
//				System.out.println("Endpoint: " + row.getCell(2));
//				getendpoint = getall.getEndpoint();
				
				
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
