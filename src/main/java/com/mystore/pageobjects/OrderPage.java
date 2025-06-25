package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	
	@FindBy(id = "total_price")
	WebElement valTotalPrice;
	
	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']")
	WebElement btnProceedToCheckOutCKPG;
	
	public void checkTotalOrderPg() throws Throwable {
		verifyPrice(total, valTotalPrice);
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
