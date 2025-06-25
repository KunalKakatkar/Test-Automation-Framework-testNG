package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class ShippingPage extends BaseClass {

/*	public ShippingPage(WebDriver driver) {
		super(driver);
	}
*/  
	public ShippingPage() {
		PageFactory.initElements(driver, this);
	}
	AddToCartPage ap=new AddToCartPage();
	double shippingTotal=ap.getShippingCost();
	
	@FindBy(xpath = "//div[@class='delivery_option_price']")
	WebElement valueShippingCost;
	
	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement chkboxTnC;
	
	@FindBy(xpath="//button[@type='submit']/span[contains(normalize-space(.),'Proceed to checkout')]")
	WebElement btnProceedPayment;
	
	public void verifyShippingCost() throws Throwable {
		verifyPrice(shippingTotal,valueShippingCost);
	}
	
	public PaymentPage clikonProceedToPaymentPage() throws Throwable {
		clickOnCheckbox(chkboxTnC);
		clickOn(btnProceedPayment);
		return new PaymentPage();
	}

}
