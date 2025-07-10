package com.mystore.listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.mystore.base.BaseClass;
import com.mystore.utility.ExtentManager;

public class ExtentReportListener implements ITestListener, ISuiteListener {
	    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	    @Override
	    public void onStart(ISuite suite) {
	    	
	    	String suiteName = suite.getName(); // dynamically get suite name
	    	String browserName = suite.getXmlSuite().getParameter("browserName"); // gets browser name from suite
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        String reportPath = System.getProperty("user.dir") + "/test-output/Reports/ExtentReport_" + timestamp + ".html";

	        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
	        spark.config().setDocumentTitle(BaseClass.prop.getProperty("documentTitle")); 
	        spark.config().setReportName(suiteName); //  dynamic name

	        ExtentReports extent = new ExtentReports();
	        extent.attachReporter(spark);
	        extent.setSystemInfo("Host", BaseClass.prop.getProperty("host"));
	        extent.setSystemInfo("Environment", BaseClass.prop.getProperty("environment"));
	        extent.setSystemInfo("User", BaseClass.prop.getProperty("user",System.getProperty("user.name")));
	        if (browserName == null) {
	            browserName = "Not Provided";
	        }
	        extent.setSystemInfo("Browser", browserName);
	        System.out.println(browserName);
	        ExtentManager.setInstance(extent); // assign to utility class
	    	
	    }

	    @Override
	    public void onFinish(ISuite suite) {
	    	ExtentReports extent = ExtentManager.getInstance();
	    	ExtentManager.getInstance().flush();
	    }

	    @Override
	    public void onTestStart(ITestResult result) {
	    	ExtentReports extent = ExtentManager.getInstance();
	    	 ExtentTest test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
	    	    extentTest.set(test);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        extentTest.get().log(Status.PASS, "Test Passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        extentTest.get().log(Status.FAIL, "Test Failed");
	        extentTest.get().log(Status.FAIL, result.getThrowable());

	        Object currentClass = result.getInstance();
	        WebDriver driver = ((com.mystore.base.BaseClass) currentClass).getDriver();

	   /*     String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
	        try {
	            extentTest.get().addScreenCaptureFromPath(screenshotPath);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	   */ }

	//    @Override
	    public void onTestSkipped(ITestResult result) {
	        extentTest.get().log(Status.SKIP, "Test Skipped");
	    }

}
