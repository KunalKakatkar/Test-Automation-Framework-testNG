package com.mystore.utility;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtility {
	
	 private static final String EXCEL_FILE_PATH = System.getProperty("user.dir")+"/TestData/MyStoreTestData.xlsx"; // Update path accordingly

	    public static Object[][] getTestData(String sheetName) {
	        Object[][] data;

	        try (FileInputStream fis = new FileInputStream(EXCEL_FILE_PATH);
	             Workbook workbook = new XSSFWorkbook(fis)) {

	            Sheet sheet = workbook.getSheet(sheetName);
	            if (sheet == null) {
	                throw new RuntimeException("Sheet " + sheetName + " not found in Excel file");
	            }

	            int rowCount = sheet.getPhysicalNumberOfRows();
	            int colCount = sheet.getRow(0).getLastCellNum();

	            data = new Object[rowCount - 1][colCount]; // Skip header

	            for (int i = 1; i < rowCount; i++) {
	                Row row = sheet.getRow(i);
	                for (int j = 0; j < colCount; j++) {
	                    Cell cell = row.getCell(j);
	                    data[i - 1][j] = getCellValue(cell);
	                }
	            }

	        } catch (IOException e) {
	            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
	        }

	        return data;
	    }

	    private static Object getCellValue(Cell cell) {
	        if (cell == null) return "";

	        switch (cell.getCellType()) {
	            case STRING:
	                return cell.getStringCellValue();
	            case NUMERIC:
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    return cell.getDateCellValue().toString();
	                } else {
	                    return String.valueOf(cell.getNumericCellValue());
	                }
	            case BOOLEAN:
	                return cell.getBooleanCellValue();
	            case FORMULA:
	                return cell.getCellFormula();
	            case BLANK:
	                return "";
	            default:
	                return cell.toString();
	        }
	    }
}
