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
	IndexPage indexPage;
	LoginPage loginPage;
	HomePage homePage;
	
	//data
	String expectedProfileName=tu.getProfileName();
	String actualProfileName="";
	
	
	@BeforeMethod
	public void setup() {
		initialization();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void loginTest() throws Throwable {
		indexPage = new IndexPage();
		// clickOnSignInButton method returns LoginPage, so we have created an loginPpage object & store it
		loginPage=indexPage.clickOnSignInButton(); 
		// loginWithValidCreds method returns HomePage, so we have created an HomePage object & store it
		homePage=loginPage.loginWithValidCreds(prop.getProperty("username"), prop.getProperty("password"));
		actualProfileName = homePage.verifyUser();
		Assert.assertEquals(actualProfileName, expectedProfileName);
	}
}
