package com.mystore.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;
import com.mystore.utility.TestUtil;

public class IndexPageTest extends BaseClass {
	
	//BaseClass base = new BaseClass();
	TestUtil tl = new TestUtil();
	String expectedIndexPageTitle = tl.getIndexPageTitle();
	IndexPage indexPage ;
	

	@Test
	public void verifyLogo() throws Throwable {
		logger.info("**** Starting verifyLogo test ****");
		indexPage = new IndexPage(getDriver());
		boolean result = indexPage.validateLogo();
		Assert.assertTrue(result);
		logger.info("**** Completed verifyLogo test ****");
	}
	
	@Test
	public void verifyTitle() throws Throwable {
		logger.info("**** Starting verifyTitle test ****");
		String actualTitle = indexPage.getMyShopTitle();
		Assert.assertEquals(actualTitle, expectedIndexPageTitle);
		logger.info("**** Completed verifyTitle test ****");
	
	}

}
