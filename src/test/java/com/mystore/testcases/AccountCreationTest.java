package com.mystore.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AccountCreationPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.TestUtil;

public class AccountCreationTest extends BaseClass {
	
	LoginPage loginPage;
	AccountCreationPage accountCreationPage;
	HomePage homePage;
	TestUtil tu = new TestUtil();
	
	long randomNumber = tu.getRandomNumber();
	String mailId = "pulse.pass"+randomNumber+"@gmail.com";
	
	@Test(dataProvider="accountCreationData")
	public void accountCreation(String gender,String name, String surName, String password, String day, String month, String year ) throws Throwable {
		logger.info("**** Starting accountCreation test ****");
		homePage=indexPage.clickOnSignInButton().createNewAccount(mailId).createNewAccount(gender, name, surName, password, day, month, year).verifyAccountCreationMsg(); 
		logger.info("**** Completed verifyAddToCart test ****");
	}
	
	@DataProvider(name="accountCreationData")
		public Object[][] accountCreationData(){
			Object [][] data = {{"mr", "Steve", "Smith", "testNG@1666", "23", "June", "1999"}}; 
			return data;
		}
	
}
