package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	
	@FindBy(id = "total_price")
	WebElement valueTotalCost;
	
	@FindBy(xpath = "//a[@class='bankwire']")
	WebElement btnBankWire;
	
	@FindBy(xpath = "//a[@class='cheque']")
	WebElement btnCheque;
	

	
		public void verifyTotalCostPaymentPage() throws Throwable {
			verifyPrice(Total,valueTotalCost);
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
