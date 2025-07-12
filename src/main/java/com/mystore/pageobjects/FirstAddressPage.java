package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class FirstAddressPage extends BaseClass {

	public FirstAddressPage(WebDriver driver) {
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(id="company")
	WebElement txtCompany;
	
	@FindBy(id="address1")
	WebElement textAddress1;
	
	@FindBy(id="address2")
	WebElement textAddress2;
	
	@FindBy(id="city")
	WebElement txtCity;
	
	@FindBy(id="id_state")
	WebElement drpdwnState;
	
	@FindBy(id="postcode")
	WebElement txtPostCode;
	
	@FindBy(id="phone")
	WebElement txtHomePhone;
	
	@FindBy(id="phone_mobile")
	WebElement txtMobilePhone;
	
	@FindBy(id="other")
	WebElement txtAdditionalInfo;
	
	@FindBy(id="alias")
	WebElement txtAias;
	
	@FindBy(id="submitAddress")
	WebElement btnSave;
	
	public FirstAddressPage firstAddressDetails(String company, String addressLn1, String addressLn2, String city, String state, String postalCode, String homePhoneNumber, String mobileNumber, String additionalInfo, String addressAlias) throws Exception {
		enterText(txtCompany, company);
		enterText(textAddress1,addressLn1);
		enterText(textAddress2,addressLn2);
		enterText(txtCity,city);
		selectValueFromDropDown(drpdwnState,state);
		enterText(txtPostCode,postalCode);
		enterText(txtHomePhone,homePhoneNumber);
		enterText(txtMobilePhone,mobileNumber);
		enterText(txtAdditionalInfo,additionalInfo);
		textBoxClear(txtAias);
		enterText(txtAias,addressAlias);
		clickOn(btnSave);
		return this;
	}
}
