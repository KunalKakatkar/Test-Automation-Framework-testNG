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
	
	public String verifyUser() {
		return getVisibleText(valueUser); 
	}
	
	public boolean checkMyPersonalPageVisibility() {
		return isDisplayed(pageMyPersonagInfo);
	}
	
	
}


