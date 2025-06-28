package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mystore.base.BaseClass;

public class AddToCartPage extends BaseClass {

	
/*	public AddToCartPage(WebDriver driver) {
		super(driver);
	}
*/	
	
	public AddToCartPage() {
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//button[@name='Submit']")
	WebElement btnAddToCart;
	
	@FindBy(xpath = "//div[@class='layer_cart_product col-xs-12 col-md-6']//h2")
	WebElement msgSuccessadded;
	
	
	@FindBy(id="layer_cart_product_quantity")
	WebElement valueQuantity;
	
	@FindBy(id="layer_cart_product_price")
	WebElement valueSubTotal;
	
	@FindBy(xpath="//strong[contains(normalize-space(.),'Total shipping')]/following-sibling::span")
	WebElement valueTotalShipping;
	
	@FindBy(xpath="//div[@class='layer_cart_row']/strong[normalize-space(text()) ='Total']/following-sibling::span")
	WebElement valueTotal;
	
	@FindBy(xpath="//span[contains(normalize-space(.) ,'Proceed to checkout')]")
	WebElement btnProceedToCheckOut;
	
	SearchResultPage sp = new SearchResultPage();
	String productPrice = sp.getProductPrice();
	
	private static double Total;
	private static double cartTotal;
	private static double shippingCost;
	private static double actualTotalPrice;
	private static double expectedTotalPrice;
	
	public String quantity;
	public String subTotal;
	public String totalShipping;
	public String total;
	public double subTotalDouble;
	
	
	public String validateAddToCartSuccessMsg() throws Throwable {
		String msg = jsGetText(msgSuccessadded);
		return msg.trim();
		}
	
	public void checkCartValue() throws Throwable {
		productPrice=productPrice.replace("$","").trim();
		quantity = jsGetText(valueQuantity).replace("$", "").trim();
		subTotal = jsGetText(valueSubTotal).replace("$", "").trim(); 
		totalShipping = jsGetText(valueTotalShipping).replace("$", "").trim();
		total = jsGetText(valueTotal).replace("$", "").trim();
		System.out.println("quantity - " + quantity+ ",subTotal - " +subTotal+ ",totalShipping - "+ totalShipping+", total - " +total);
		actualTotalPrice = Double.parseDouble(total);
		expectedTotalPrice = (Double.parseDouble(productPrice)*Double.parseDouble(quantity)) + Double.parseDouble(totalShipping);
		Assert.assertEquals(actualTotalPrice, expectedTotalPrice);
		System.out.println( "actualTotalPrice" +actualTotalPrice);
		System.out.println("expectedTotalPrice -" +expectedTotalPrice);
		this.shippingCost=Double.parseDouble(totalShipping);
		subTotalDouble=Double.parseDouble(subTotal);
		this.cartTotal=subTotalDouble;
		this.Total=actualTotalPrice;
	}
	
	public OrderPage proceedToCheckOutOrderPage() throws Throwable {
		jsClick(btnProceedToCheckOut);
		return new OrderPage();
	}
	
	//getter to pass Total value
	public double getTotal() {
		return this.Total;
	}
	
	public double getCartSubTotal() {
		return this.cartTotal;
	}
	
	//getter to pass Total value
		public double getShippingCost() {
			return this.shippingCost;
		}
		
}
