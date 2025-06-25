package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;

public class HomePageTest extends BaseClass{

	HomePage homePage; 
	IndexPage indexPage;
	LoginPage loginPage;
	
	@BeforeMethod
	public void setup() {
		initialization();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void myPersonalInfoPageTest() throws Throwable {
		indexPage = new IndexPage();
		// clickOnSignInButton method returns LoginPage, so we have created an loginPpage object & store it
		loginPage=indexPage.clickOnSignInButton(); 
		// loginWithValidCreds method returns HomePage, so we have created an HomePage object & store it
		homePage=loginPage.loginWithValidCreds(prop.getProperty("username"), prop.getProperty("password"));
		boolean result = homePage.checkMyPersonalPageVisibility();
		Assert.assertTrue(result);
	}
}
