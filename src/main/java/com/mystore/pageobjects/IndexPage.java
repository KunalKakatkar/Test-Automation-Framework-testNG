package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class IndexPage extends BaseClass {
	
	//Pageobjects are initialized in base class
/*	public IndexPage(WebDriver driver) {
		super(driver);
	}
*/
	public IndexPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[contains(text(),'Sign in')]")
	WebElement btnSignIn;
	
	@FindBy(xpath = "//img[@alt='My Shop']")
	WebElement logoMyShop;
	
	@FindBy(id = "search_query_top")
	WebElement srchBx;
	 
	@FindBy(xpath ="//button[@name='submit_search']")
	WebElement btnSearch;
	
	
	public LoginPage clickOnSignInButton() throws Throwable {
		clickOn(btnSignIn);
		return new LoginPage();
	}
	
	public boolean validateLogo() throws Throwable {
		boolean b =isDisplayed(logoMyShop);
		return b;	
	}
	
	public String getMyShopTitle() throws Throwable {
		String myShopHomeTitle = driver.getTitle();
		return myShopHomeTitle;
	}
	
	public SearchResultPage searchMethod(String productName) throws Throwable {
		enterText(srchBx, productName);
		clickOn(btnSearch);
		return new SearchResultPage();
	}
	
	
}
