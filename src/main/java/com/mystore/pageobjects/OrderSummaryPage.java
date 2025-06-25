package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class OrderSummaryPage extends BaseClass{

/*	public OrderSummaryPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
*/	
	public OrderSummaryPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[contains(normalize-space(.),'I confirm my order')]")
	WebElement btnConfirmOrder;
	
	public OrderConfirmationPage clickOnConfirmOrder() throws Throwable {
		clickOn(btnConfirmOrder);
		return new OrderConfirmationPage();
	}

	
	
	
	
	
	

}
