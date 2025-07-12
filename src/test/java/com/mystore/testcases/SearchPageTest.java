package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.SearchResultPage;

public class SearchPageTest extends BaseClass {

	LoginPage loginPage;
	HomePage homePage;
	SearchResultPage searchResultPage;
	AddToCartPage addToCartPage;
	
	public String searchItem = "Printed Chiffon Dress";

	@Test(groups = {"sanity","regression"})
	public void searchProducts() throws Throwable {
		logger.info("**** Starting searchProducts test ****");
		addToCartPage=indexPage.clickOnSignInButton().loginWithValidCreds(prop.getProperty("username"), prop.getProperty("password")).searchMethod(searchItem).verifySearchResultandClick(searchItem)
					  .selectSize("L").selectQuantity("2").checkStock().addToCart();
		logger.info("**** Completed searchProducts test ****");
	}
	
}
