package com.mystore.testcases;

import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.MyStoreDataProvider;
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
	
	long randomNumber;
	String mailId;
	
	@Test(dataProvider = "jsonData", dataProviderClass = MyStoreDataProvider.class)  // for JSON file
	public void accountCreation(Map<String, String> data) throws Throwable {
		logger.info("**** Starting accountCreation test ****");
		randomNumber = tu.getRandomNumber();
		mailId = "pulse.pass"+randomNumber+"@gmail.com";
		homePage=indexPage.clickOnSignInButton().createNewAccount(mailId).createNewAccount(data.get("gender"), data.get("name"), data.get("surName"), data.get("password"), data.get("day"), data.get("month"), data.get("year")).verifyAccountCreationMsg(); 
		logger.info("**** Completed verifyAddToCart test ****");
	}
	
/*	@DataProvider(name="accountCreationData")
		public Object[][] accountCreationData(){
			Object [][] data = {{"mr", "Steve", "Smith", "testNG@1666", "23", "June", "1999"}}; 
			return data;
		}
*/	
}
