package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.AddressPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.SearchResultPage;

public class OrderPageTest extends BaseClass{
	LoginPage loginPage;
	HomePage homePage;
	SearchResultPage searchResultPage;
	AddToCartPage addToCartPage;
	OrderPage orderPage;
	AddressPage addressPage;
	
	public String searchItem = "Printed Chiffon Dress";
	public String searchProductValue;
	public String expectedSuccessMsg = "Product successfully added to your shopping cart";
	public String actualSuccessMsg;
	

	@Test(groups = {"regression"})
	public void verifyOrderPage() throws Throwable {
		logger.info("**** Starting verifyOrderPage test ****");
		addToCartPage=indexPage.clickOnSignInButton().loginWithValidCreds(prop.getProperty("username"), prop.getProperty("password")).searchMethod(searchItem).verifySearchResultandClick(searchItem)
				.selectSize("L").selectQuantity("2").checkStock().addToCart();
		actualSuccessMsg=addToCartPage.validateAddToCartSuccessMsg();
		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg);
		addressPage =addToCartPage.checkCartValue().proceedToCheckOutOrderPage().checkTotalOrderPg().proceedToCheckOutAddressPage();
		logger.info("**** Completed verifyOrderPage test ****");
	}
}
