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
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.SearchResultPage;

public class AddToCartTest extends BaseClass {
	IndexPage indexPage;
	LoginPage loginPage;
	HomePage homePage;
	SearchResultPage searchResultPage;
	AddToCartPage addToCartPage;
	OrderPage orderPage;
	
	public String searchProductValue;
	public String expectedSuccessMsg = "Product successfully added to your shopping cart";
	public String actualSuccessMsg;
	
	@BeforeMethod
	public void setup() {
		initialization();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void verifyAddToCart() throws Throwable {
		indexPage = new IndexPage();
		// clickOnSignInButton method returns LoginPage, so we have created an loginPpage object & store it
		loginPage=indexPage.clickOnSignInButton(); 
		// loginWithValidCreds method returns HomePage, so we have created an HomePage object & store it
		homePage=loginPage.loginWithValidCreds(prop.getProperty("username"), prop.getProperty("password"));
		searchResultPage=homePage.searchMethod("Printed Chiffon Dress");
		this.searchProductValue = homePage.getSearchProductName();
		searchResultPage.verifySearchResultandClick(searchProductValue);
		searchResultPage.selectSize("L");
		searchResultPage.selectQuantity("2");
		searchResultPage.checkStock();
		addToCartPage=searchResultPage.addToCart();
		System.out.println("50");
		actualSuccessMsg=addToCartPage.validateAddToCartSuccessMsg();
		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg);
		addToCartPage.checkCartValue();
		System.out.println("56");
		orderPage=addToCartPage.proceedToCheckOutOrderPage();
		System.out.println("57");
	}

}
