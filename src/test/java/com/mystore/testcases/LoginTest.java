package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.TestUtil;

public class LoginTest extends BaseClass {

	//page objects
	TestUtil tu= new TestUtil();
	LoginPage loginPage;
	HomePage homePage;
	
	//data
	String expectedProfileName=tu.getProfileName();
	String actualProfileName="";
	

	@Test(groups = {"sanity","regression"})
	public void loginTest() throws Throwable {
		logger.info("**** Starting Login Test ****");
		actualProfileName=indexPage.clickOnSignInButton().loginWithValidCreds(prop.getProperty("username"), prop.getProperty("password")).verifyUser();
		Assert.assertEquals(actualProfileName, expectedProfileName);
		logger.info("**** Completed Login Test ****");
	}
}
