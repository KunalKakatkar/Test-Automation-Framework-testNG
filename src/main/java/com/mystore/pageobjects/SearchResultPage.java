package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mystore.base.BaseClass;

public class SearchResultPage extends BaseClass {

/*	public SearchResultPage(WebDriver driver) {
		super(driver);
	}
*/
	public SearchResultPage(WebDriver driver) {
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(xpath = "//a[contains(normalize-space(.),'Printed Chiffon Dress') and @itemprop='url']")
	WebElement  valueSearchResult;
	
	@FindBy(id = "group_1")
	WebElement drpdwnSize;
	
	@FindBy(id = "quantity_wanted")
	WebElement txtQuantity;
	
	@FindBy(id = "our_price_display")
	WebElement productPriceDisplayed;
	
	@FindBy(id = "availability_value")
	WebElement chkStock;
	
	@FindBy(xpath = "//button[@name='Submit']")
	WebElement btnAddtoCart;
	
	public static String productPrice;
	public static String productQuantity;
	
	
	public SearchResultPage verifySearchResultandClick(String searchValue) {
		verifyText(valueSearchResult, searchValue);
		clickOn(valueSearchResult);
		//System.out.println("returning product price - "+productPrice);
		return this;
	}
	
	public SearchResultPage selectSize(String size) {
		selectValueFromDropDown(drpdwnSize,size);
		this.productPrice = getVisibleText(productPriceDisplayed);
		return this;
	}
	
	public SearchResultPage selectQuantity(String quantity) {
		this.productQuantity=quantity;
		textBoxClear(txtQuantity);
		enterText(txtQuantity, productQuantity);
		return this;
	}
	
	public SearchResultPage checkStock() {
		String stock = getVisibleText(chkStock);
		Assert.assertEquals(stock, "In stock");
		return this;
	}	
	
	public AddToCartPage addToCart() {
		clickOn(btnAddtoCart);
		return new AddToCartPage(getDriver());
	}
	
	public String getProductPrice() {
		 return this.productPrice;
	}
	
	public String getProductQuantity() {
		return this.productQuantity;
	}


	
	
	
	

}
