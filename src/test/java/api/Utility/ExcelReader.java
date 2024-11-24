package api.Utility;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import api.Pojo.CreateProgramRequestPojo;
import api.Pojo.LogInOutRequestPojo;

public class ExcelReader {
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
}
