package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mystore.base.BaseClass;

public class PaymentPage extends BaseClass {

/*	public PaymentPage(WebDriver driver) {
		super(driver);
	}
*/
	public PaymentPage() {
		PageFactory.initElements(driver, this);
	}
		
	AddToCartPage ap=new AddToCartPage();
	double Total = ap.getTotal();
	double shipTotal = ap.getShippingCost();
	double cartTotal = ap.getCartSubTotal();
	
	@FindBy(id = "total_price")
	WebElement valueTotalCost;
	
	@FindBy(id = "total_product")
	WebElement valueSubTotal;
	
	@FindBy(id = "total_shipping")
	WebElement valueTotalShip;
	
	@FindBy(xpath = "//a[@class='bankwire']")
	WebElement btnBankWire;
	
	@FindBy(xpath = "//a[@class='cheque']")
	WebElement btnCheque;
	
	double subTotal;
	double totalShipping;
	double GrandTotal;

	
		public void verifyTotalCostPaymentPage() throws Throwable {
			subTotal=Double.parseDouble(jsGetText(valueSubTotal).replace("$", "").trim()); 
			totalShipping= Double.parseDouble(jsGetText(valueTotalShip).replace("$", "").trim());
			GrandTotal = Double.parseDouble(jsGetText(valueTotalCost).replace("$", "").trim());
			 double subTotalPlusShipping = subTotal+totalShipping;
			 Assert.assertEquals(subTotal, cartTotal);
			 Assert.assertEquals(totalShipping, shipTotal);
			 Assert.assertEquals(GrandTotal, subTotalPlusShipping);
			 Assert.assertEquals(GrandTotal, Total);
		}
	
		public OrderSummaryPage selectPaymentType(String paymentType) throws Throwable {
			String payType=paymentType.trim().replace(" ", "");
			try {
				if(payType.equalsIgnoreCase("paybybankwire")) {
					clickOn(btnBankWire);
				} else if (payType.equalsIgnoreCase("paybycheck")) {
					clickOn(btnCheque);
				}
			} catch (Exception e) {
				System.err.println("Please select correct payment mode");
			}
			
			return new OrderSummaryPage();
		}
		
		
}
