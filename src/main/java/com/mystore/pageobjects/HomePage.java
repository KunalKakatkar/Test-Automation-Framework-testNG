/**
 * 
 */
package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class HomePage extends BaseClass {

/*	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
*/ 
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[contains(text(),'My personal information')]")
	WebElement pageMyPersonagInfo;

	@FindBy(xpath = "//span[contains(text(),'Add my first address')]")
	WebElement pageAddMyFirstAddress;
	
	@FindBy(xpath = "//a[@class='account']//span")
	WebElement valueUser;
	
	@FindBy(xpath = "//p[@class='alert alert-success']")
	WebElement msgAccountCreation;
	
	@FindBy(id = "search_query_top")
	WebElement srchBx;
	 
	@FindBy(xpath ="//button[@name='submit_search']")
	WebElement btnSearch;
	
	public String searchProductName;
	
	public String verifyUser() {
		return getVisibleText(valueUser); 
	}
	
	public boolean checkMyPersonalPageVisibility() {
		return isDisplayed(pageMyPersonagInfo);
	}
	
	public void verifyAccountCreationMsg() {
	 verifyText(msgAccountCreation,"Your account has been created.");	
	}
	
	public SearchResultPage searchMethod(String productName) throws Throwable {
		searchProductName=productName;
		enterText(srchBx, searchProductName);
		clickOn(btnSearch);
		return new SearchResultPage();
	}
	
	public String getSearchProductName() {
		return searchProductName;
	}
	
	public FirstAddressPage firstAddress() {
		clickOn(pageAddMyFirstAddress);
		return new FirstAddressPage();
	}
	
	
}


