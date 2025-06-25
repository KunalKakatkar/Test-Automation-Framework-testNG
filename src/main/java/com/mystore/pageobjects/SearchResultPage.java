package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class SearchResultPage extends BaseClass {

/*	public SearchResultPage(WebDriver driver) {
		super(driver);
	}
*/
	public SearchResultPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@itemprop='url' and (contains(text(),'Printed Chiffon Dress'))]")
	WebElement  dressPrintedChiffon;
	
	@FindBy(xpath = "//a[contains(text(),'Printed Chiffon Dress')]/parent::h5/following-sibling::div[2]/a/span")
	WebElement btnMorePrintedChiffon;
	
	public AddToCartPage clickOnMoreBtn() throws Throwable{
		clickOn(btnMorePrintedChiffon);
		return new AddToCartPage();
	}
	
	
	
	
	

}
