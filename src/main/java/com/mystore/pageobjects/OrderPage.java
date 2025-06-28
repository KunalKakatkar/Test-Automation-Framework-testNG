package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mystore.base.BaseClass;

public class OrderPage extends BaseClass {
	
/*	public OrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
*/	
	
	public OrderPage() {
		PageFactory.initElements(driver, this);
	}
	
	//object to get total value
	AddToCartPage acp = new AddToCartPage();
	double total =acp.getTotal();
	double shippingCost = acp.getShippingCost();
	double subTotal = acp.getCartSubTotal();
	
	@FindBy(id = "total_product")
	WebElement valTotalPrice;
	
	@FindBy(id = "total_shipping")
	WebElement valTotalShippingPrice;
	
	@FindBy(id = "total_price")
	WebElement valGrandTotal;
	
	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']")
	WebElement btnProceedToCheckOutCKPG;
	
	public void checkTotalOrderPg() throws Throwable {
		Double totalPrice = Double.parseDouble(getVisibleText(valTotalPrice).replace("$",""));
		Double totalShipping = Double.parseDouble(getVisibleText(valTotalShippingPrice).replace("$","").trim());
		Double grandTotal = Double.parseDouble(getVisibleText(valGrandTotal).replace("$","").trim());
		double totalCartValue = totalPrice+totalShipping;
		System.out.println(subTotal +"ln46");
		Assert.assertEquals(totalPrice,subTotal);
		Assert.assertEquals(totalShipping,shippingCost);
		Assert.assertEquals(grandTotal, total);
		Assert.assertEquals(grandTotal,totalCartValue);
	}
	
	//proceeds directly to address page if user is already logged in
	public AddressPage proceedToCheckOutAddressPage() throws Throwable {
		clickOn(btnProceedToCheckOutCKPG);
		return new AddressPage();
	}
	
	//proceeds to login page if user has not logged in 
	public LoginPage proceedToCheckOutLoginPage() throws Throwable {
		clickOn(btnProceedToCheckOutCKPG);
		return new LoginPage();
	}
		

}
