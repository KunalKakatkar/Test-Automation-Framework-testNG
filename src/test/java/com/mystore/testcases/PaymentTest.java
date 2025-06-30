package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.AddressPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.OrderConfirmationPage;
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.OrderSummaryPage;
import com.mystore.pageobjects.PaymentPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.pageobjects.ShippingPage;

public class PaymentTest extends BaseClass {
	
	LoginPage loginPage;
	HomePage homePage;
	SearchResultPage searchResultPage;
	AddToCartPage addToCartPage;
	OrderPage orderPage;
	AddressPage addressPage;
	ShippingPage shippingPage;
	PaymentPage paymentPage;
	OrderSummaryPage orderSummaryPage;
	OrderConfirmationPage orderConfirmationPage;
	
	public String searchItem = "Printed Chiffon Dress";
	public String searchProductValue;
	public String expectedSuccessMsg = "Product successfully added to your shopping cart";
	public String actualSuccessMsg;

	@Test
	public void paymentTest() throws Throwable {
		logger.info("**** starting test - paymentTest ****");
		addToCartPage=indexPage.clickOnSignInButton().loginWithValidCreds(prop.getProperty("username"), prop.getProperty("password")).searchMethod(searchItem).verifySearchResultandClick(searchItem)
								.selectSize("L").selectQuantity("2").selectQuantity("2").checkStock().addToCart();
		actualSuccessMsg=addToCartPage.validateAddToCartSuccessMsg();
		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg);
		orderConfirmationPage=addToCartPage.checkCartValue().proceedToCheckOutOrderPage().checkTotalOrderPg().proceedToCheckOutAddressPage().selectAddress("home-address", "deliver to home address")
							  .clickOnProceedShipPage().verifyShippingCost().clikonProceedToPaymentPage().verifyTotalCostPaymentPage().selectPaymentType("Pay by check")
							  .clickOnConfirmOrder().verifyOrderSuccessMsg();
		logger.info("**** Completed test - paymentTest ****");
	}
}
