package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class AddressPage extends BaseClass {

/*	public AddressPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
*/ 
	public AddressPage(WebDriver driver) {
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(id = "id_address_delivery")
	WebElement drpdwnAddress;
	
	@FindBy(xpath = "//textarea[@name='message']")
	WebElement txtCmtBx;
	
	@FindBy(xpath="//button[@name='processAddress']/span")
	WebElement btnProToChkOutShipPage;
	
	public AddressPage selectAddress(String address, String comments)  throws Throwable {
		selectValueFromDropDown(drpdwnAddress, address);
		enterText(txtCmtBx,comments);
		return this;
	}
	
	public ShippingPage clickOnProceedShipPage()  throws Throwable {
		clickOn(btnProToChkOutShipPage);
		return new ShippingPage(getDriver());
	}
}
