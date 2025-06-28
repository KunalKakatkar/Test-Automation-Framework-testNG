package com.mystore.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AccountCreationPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.TestUtil;

public class AccountCreationTest extends BaseClass {
	
	IndexPage indexPage;
	LoginPage loginPage;
	AccountCreationPage accountCreationPage;
	HomePage homePage;
	TestUtil tu = new TestUtil();
	
	
	long randomNumber = tu.getRandomNumber();
	
	String mailId = "pulse.pass"+randomNumber+"@gmail.com";
	
	@BeforeMethod
	public void setup() {
		initialization();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void accountCreation() throws Throwable {
		indexPage = new IndexPage();
		// clickOnSignInButton method returns LoginPage, so we have created an loginPpage object & store it
		loginPage=indexPage.clickOnSignInButton(); 
		accountCreationPage=loginPage.createNewAccount(mailId);
		homePage=accountCreationPage.createNewAccount("mr", "Steve", "Smith", "testNG@1666", "23", "June", "1999"); //error dd has spaces
		homePage.verifyAccountCreationMsg();
	}
}
