package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mystore.base.BaseClass;

public class AccountCreationPage  extends BaseClass {

/*	public AccountCreationPage(WebDriver driver) {
		super(driver);
	}
*/
	public AccountCreationPage() {
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//h1[contains(text(),'Create an account')]")
	WebElement chkAccountCreationPage;
	
	@FindBy(xpath = "//label[contains(normalize-space(.),'Mr.')]")
	WebElement radioMR;
	
	@FindBy(xpath = "//label[contains(normalize-space(.),'Mrs.')]")
	WebElement radioMRS;
	
	@FindBy(id="customer_firstname")
	WebElement txtCreateFirstName;
	
	@FindBy(id="customer_lastname")
	WebElement txtCreateLastName;
	
	@FindBy(id="email")
	WebElement txtCreateEmaiID;
	
	@FindBy(id="passwd")
	WebElement txtCreatePswd;
	
	@FindBy(id="days")
	WebElement drpdwnDays;
	
	@FindBy(id="months")
	WebElement drpdwnMonths;
	
	@FindBy(id="years")
	WebElement drpdwnYears;
	
	@FindBy(id = "submitAccount")
	WebElement btnSubmitAccount;
	
		
	public HomePage createNewAccount(String gender, String firstName, String lastName, String password, String day, String month, String year) {
		verifyText(chkAccountCreationPage, "CREATE AN ACCOUNT");
		if(gender.equalsIgnoreCase("mr")) {
			clickOnRadioBtn(radioMR);
		} else if (gender.equalsIgnoreCase("mrs")) {
			clickOnRadioBtn(radioMRS);
		}
		enterText(txtCreateFirstName,firstName);
		enterText(txtCreateLastName,lastName);
		enterText(txtCreatePswd,password);
		selectValueFromDropDown(drpdwnDays,day);
		selectValueFromDropDown(drpdwnMonths,month);
		selectValueFromDropDown(drpdwnYears,year);
		clickOn(btnSubmitAccount);
		return new HomePage();
	}
	
}
