package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;
import com.mystore.utility.TestUtil;

public class OrderConfirmationPage extends BaseClass {

/*	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
	}
*/	
	public OrderConfirmationPage() {
		PageFactory.initElements(driver, this);
	}

	//getting success message from test util class
	TestUtil tu = new TestUtil();
	String successMsg = tu.getSuccessMsg();
	
	@FindBy(xpath = "//p[@class='alert alert-success']")
	WebElement msgConfirmOrder;
	
	public void verifyOrderSuccessMsg() throws Throwable {
		verifyText(msgConfirmOrder,successMsg);
	}
	
	

}
