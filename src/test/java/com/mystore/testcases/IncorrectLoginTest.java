package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.mystore.base.BaseClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;

public class IncorrectLoginTest extends BaseClass {
	
	LoginPage loginPage;
	HomePage homePage;
	
	//data
	String expectedErrorMsg="Authentication failed.";
	String actualErrorMsg="";
	

	//providing data with dataProvider
	@Test(dataProvider="incorrectLoginData",description = "Verify if the proper error message is shown for the user when they enter invalid credentials")
	public void incorrectloginTest(String username, String password) throws Throwable {
		logger.info("**** Starting incorrectloginTest test ****");
		actualErrorMsg=indexPage.clickOnSignInButton().loginWithInvalidCreds(username, password).getErrorLoginMSG();
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
		logger.info("**** Completed incorrectloginTest test ****");
	}
	
	//DataProvider method with name "incorrectLoginData"
	@DataProvider(name="incorrectLoginData")
	public Object[][] dataProviderIncorrectLogin() {
		Object [][] loginData ={ {"abc8989@gmail.com","pass8989"}, {"qwe3344@gmail.com", "pass3344"}, {"nmkl1289@gmail.com", "pass1289"}, {"zrft6123@gmail.com","pass6123"} };
		return loginData;
	}
	
}
