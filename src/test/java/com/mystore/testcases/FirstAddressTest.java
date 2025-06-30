package com.mystore.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.FirstAddressPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;

public class FirstAddressTest extends BaseClass{
	
	LoginPage loginPage;
	HomePage homePage;
	FirstAddressPage firstAddressPage;


	@Test
	public void loginTest() throws Throwable {
		logger.info("**** Starting loginTest test ****");
		firstAddressPage=indexPage.clickOnSignInButton().loginWithValidCreds(prop.getProperty("username"), prop.getProperty("password")).firstAddress()
				         .firstAddressDetails("Airtel","Wakad", "PCMC", "Pune","New York","12345","9999999999","8888888888","India","home-address");
		logger.info("**** Completed loginTest test ****");
	}
}