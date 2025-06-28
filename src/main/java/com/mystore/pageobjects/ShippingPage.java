package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
	
	@FindBy(xpath = "//p[@class='checkbox']/label")
	WebElement chkboxTnC;
	
	@FindBy(xpath="//button[@type='submit']/span[contains(normalize-space(.),'Proceed to checkout')]")
	WebElement btnProceedPayment;
	
	public void verifyShippingCost() throws Throwable {
		double actualShippingCost = Double.parseDouble(getVisibleText(valueShippingCost).replace("$", "").trim());
		Assert.assertEquals(actualShippingCost,shippingTotal);
		System.out.println("actualShippingCost" +actualShippingCost);
		System.out.println("shippingTotal" +shippingTotal);
	}
	
	public PaymentPage clikonProceedToPaymentPage() throws Throwable {
		clickOnCheckbox(chkboxTnC);
		clickOn(btnProceedPayment);
		return new PaymentPage();
	}

}
