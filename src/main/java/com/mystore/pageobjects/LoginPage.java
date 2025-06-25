package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class LoginPage extends BaseClass {

	//pageobjects are initialized in base class
/*	public LoginPage(WebDriver driver) {
		super(driver);
	}
*/	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "email")
	WebElement txtUsername;
	
	@FindBy(id = "passwd")
	WebElement txtPassword;
	
	@FindBy(id = "email_create")
	WebElement txtEmailCreate;
	
	@FindBy(id = "SubmitLogin")
	WebElement btnLogin;
	
	@FindBy(id = "SubmitCreate")
	WebElement btnSubmitCreate;
	
	
	@FindBy(xpath = "//div[@class='alert alert-danger' and not (@id='create_account_error') ]//li")
	WebElement msgErrorLogin;
	
	//proceeds to home page
	public HomePage loginWithValidCreds(String username, String password) throws Throwable {
		enterText(txtUsername,username);
		enterText(txtPassword,password);
		clickOn(btnLogin);
		return new HomePage();	
	}
	
	//proceeds to addesss page
	public AddressPage loginWithValidCredsThenAddress(String username, String password) throws Throwable {
		enterText(txtUsername,username);
		enterText(txtPassword,password);
		clickOn(btnLogin);
		return new AddressPage();	
	}
	
	public LoginPage loginWithInvalidCreds(String username, String password) throws Throwable {
		enterText(txtUsername,username);
		enterText(txtPassword,password);
		clickOn(btnLogin);
		return new LoginPage();
	}
	
	public String getErrorLoginMSG() {
		return getVisibleText(msgErrorLogin); 
	}
	
	public AccountCreationPage createNewAccount(String email) throws Throwable {
		enterText(txtEmailCreate, email);
		clickOn(btnSubmitCreate);
		return new AccountCreationPage();
	}
	
}
