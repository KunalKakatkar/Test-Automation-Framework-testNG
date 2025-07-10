package com.mystore.utility;
import com.aventstack.extentreports.ExtentReports;


public class ExtentManager {
	private static ExtentReports extent;

    public static ExtentReports getInstance() {
    	if (extent == null) {
            extent = new ExtentReports();  // reporter will be attached in listener 
        }
        return extent;
    }
    
    public static void setInstance(ExtentReports ext) {
        extent = ext;
    }

}
