package com.mystore.dataprovider;

import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

import com.mystore.utility.CSVUtility;
import com.mystore.utility.ExcelUtility;
import com.mystore.utility.JSONUtility;

public class MyStoreDataProvider {
	
	@DataProvider(name = "excelData")
    public Object[][] provideDataExcel(Method method) {
        String sheetName = method.getName(); // test method name = sheet name
        return ExcelUtility.getTestData(sheetName);
    }
	
	@DataProvider(name = "csvData")
	public Object[][] provideDataCSV(Method method) {
	     String testName = method.getName();
	     String path = System.getProperty("user.dir") + "/TestData/paymentTest.csv";
	     return CSVUtility.getTestData(path);
	}
	
	@DataProvider(name = "jsonData")
    public Object[][] provideDataJSON() {
        return JSONUtility.getTestData();
    }

}
