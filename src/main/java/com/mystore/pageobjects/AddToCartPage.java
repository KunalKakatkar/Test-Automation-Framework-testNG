package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class AddToCartPage extends BaseClass {

	
/*	public AddToCartPage(WebDriver driver) {
		super(driver);
	}
*/	
	
	public AddToCartPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "quantity_wanted")
	WebElement txtQuantity;
	
	@FindBy(id = "group_1")
	WebElement drpdwnSize;
	
	@FindBy(xpath = "//button[@name='Submit']")
	WebElement btnAddToCart;
	
	@FindBy(xpath = "availability_value")
	WebElement chkStock;
	
	@FindBy(xpath = "//div[@class='layer_cart_product col-xs-12 col-md-6']//h2")
	WebElement msgSuccessadded;
	
	@FindBy(id = "our_price_display")
	WebElement productPriceDisplayed;
	
	@FindBy(id="layer_cart_product_quantity")
	WebElement valueQuantity;
	
	@FindBy(id="layer_cart_product_price")
	WebElement valueSubTotal;
	
	@FindBy(xpath="//span[@class='ajax_cart_shipping_cost']")
	WebElement valueTotalShipping;
	
	@FindBy(id="//span[@class='ajax_block_cart_total']")
	WebElement valueTotal;
	
	@FindBy(xpath="//span[contains(normalize-space(.) ,'Proceed to checkout')]")
	WebElement btnProceedToCheckOut;
	
	
	public String productPrice="";
	private double Total=0.0;
	private double shippingCost=0.0;
	
	public void selectQuantity(String quantity) {
		textBoxClear(txtQuantity);
		enterText(txtQuantity, quantity);
	}
	
	public void selectSize(String size) {
		selectValueFromDropDown(drpdwnSize,size);
	}
	
	public String checkStock() {
		String stock = getVisibleText(chkStock);
		productPrice = getVisibleText(productPriceDisplayed);
		clickOn(btnAddToCart);
		return stock;
	}
	
	public String validateAddToCartSuccessMsg() throws Throwable {
		String msg = getVisibleText(msgSuccessadded);
		return msg;
	}
	
	public void checkCartValue() throws Throwable {
		this.Total = checkCartTotalATCPg(productPrice, valueQuantity,valueSubTotal,valueTotalShipping,valueTotal);
		String shipping=valueTotalShipping.getText().replaceAll("$", "");
		this.shippingCost=Double.parseDouble(shipping);
	}
	
	public OrderPage proceedToCheckOutOrderPage() throws Throwable {
		jsClick(btnProceedToCheckOut);
		return new OrderPage();
	}
	
	//getter to pass Total value
	public double getTotal() {
		return this.Total;
	}
	
	//getter to pass Total value
		public double getShippingCost() {
			return this.shippingCost;
		}
		
}
