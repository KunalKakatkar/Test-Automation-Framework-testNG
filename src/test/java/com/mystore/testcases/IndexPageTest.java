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
	

	@BeforeMethod
	public void setup() {
		initialization();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void verifyLogo() throws Throwable {
		indexPage = new IndexPage();
		boolean result = indexPage.validateLogo();
		Assert.assertTrue(result);
	}
	
	@Test
	public void verifyTitle() throws Throwable {
		String actualTitle = indexPage.getMyShopTitle();
		Assert.assertEquals(actualTitle, expectedIndexPageTitle);
	
	}

}
