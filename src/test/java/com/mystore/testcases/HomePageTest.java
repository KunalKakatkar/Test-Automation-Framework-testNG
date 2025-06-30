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
	LoginPage loginPage;
	
	@Test
	public void myPersonalInfoPageTest() throws Throwable {
		logger.info("**** Starting myPersonalInfoPageTest test ****");
		boolean result=indexPage.clickOnSignInButton().loginWithValidCreds(prop.getProperty("username"), prop.getProperty("password")).checkMyPersonalPageVisibility();
		Assert.assertTrue(result);
		logger.info("**** Completed myPersonalInfoPageTest test ****");
	}
}
