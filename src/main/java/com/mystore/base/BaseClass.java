package com.mystore.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import com.mystore.utility.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebElement element;
	public static Properties prop;
	public static WebDriver driver;
	public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtil.EXPLICITLY_WAIT));
	
	//reading config file
	@BeforeTest
	public void loadConfig() {
		try {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Configuration\\config.properties");
	//	System.out.println("driver " + driver);
			prop.load(fis);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//launch URL
	public static void initialization() {
		
		String browserName = prop.getProperty("browser");
		try {
			if(browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
		} catch (Exception e) {
			System.out.println("in exception of initialization");
			//e.printStackTrace();
		}
		
		//driver = new ChromeDriver();
		//System.setProperty("Webdriver.Chrome.Driver", "D:\\Automation\\Selenium-PracticeProject\\Drivers\\chromedriver.exe");
		maximizeWindow();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICITLY_WAIT));
		
		driver.get(prop.getProperty("url"));
	//	driver.get("http://www.automationpractice.pl/index.php");
		
	}
	
	//constructor for initilizing pagefactory objects
/*	public BaseClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
*/	
	//maximize window
	public static void maximizeWindow() {
		driver.manage().window().maximize();
	}
	
	//Click on element by locator
	public void clickOn(By locator) {
		
		boolean flag = false;
		try{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", locator);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			element.click();
			
			flag = true;
			
		} catch (Exception e) {
				throw e;
		} finally {
			if(flag) {
				System.out.println("Click action performed on locator - "+ locator);
			} else {
				System.err.println("unable to perform click action on locator - "+ locator);
			}
		}

	}
	
	
	//click on element by element	
	public void clickOn(WebElement element) {
		
			boolean flag = false;
			try{
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			WebElement elementWait = wait.until(ExpectedConditions.visibilityOf(element));
			elementWait.click();
			
			flag = true;
			
		} catch (Exception e) {
				throw e;
		} finally {
			if(flag) {
				System.out.println("Click action performed on element - "+ element);
			} else {
				System.err.println("unable to perform click action on element - "+ element);
			}

		}
	}
	
	//click on checkbox
		public void clickOnCheckbox(WebElement element) {
			
				boolean flag = false;
				try{
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				WebElement elementWait = wait.until(ExpectedConditions.visibilityOf(element));
				elementWait.click();
				
				flag = true;
				
			} catch (Exception e) {
					throw e;
			} finally {
				if(flag) {
					System.out.println("Click action performed on element - "+ element);
				} else {
					System.err.println("unable to perform click action on element - "+ element);
				}

			}
		}
	
		//check is displayed
	public boolean isDisplayed(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		boolean b = element.isDisplayed();
			if(b) {
				System.out.println(" Element is displayed ");
			} else {
				System.err.println("Element is Not displayed ");	
			}
			return b;
	}
		
		//SendKeys method
	public void enterText(WebElement element, String textToBeEntered) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
			tempEle.sendKeys(textToBeEntered);
		} catch (Exception e) {
			System.err.println("Unable to enter text at element "+element);
			throw e;	
		}
	}
	
	//clear text from textbox
	public void textBoxClear(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
			tempEle.clear();
			} catch (Exception e) {
				System.err.println("Unable to clear text at element "+element);
				throw e;	
			}
	}
	
	//to get text
	public String getVisibleText(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		String txt = element.getText();
		return txt;
	}
	
	//to verify landing [age
	public String verifyPage (WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
		return tempEle.getText();
	}
	
	//to click on radio button
	public void clickOnRadioBtn(WebElement element) {
		try{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			WebElement tempEle=wait.until(ExpectedConditions.visibilityOf(element));
			tempEle.click();
				
		} catch (Exception e) {
			System.err.println("unable to click on radio button - " + element);
			throw e;
		}
	}
	
	//to select value from drop down
	public void selectValueFromDropDown(WebElement element, String text) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Select select = new Select(element);
		try {
			select.selectByVisibleText(text);
		} catch (Exception e) {
			System.err.println("unable to select value - "+ text + " from dropdown - "+ element);
			throw e;
		}
		
	}
	
	//js click
	public void jsClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		WebElement tempEle=wait.until(ExpectedConditions.visibilityOf(element));
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", tempEle);
		} catch (Exception e) {
			System.err.println("unable to JS click on element - "+ tempEle);
		}
	}
	
	//Check cart value
	
	public double checkCartTotalATCPg(String prodPrice, WebElement valueQuantity, WebElement valueSubTotal, WebElement valueTotalShipping, WebElement valueTotal) {
		String productPrice= prodPrice.replace("$", "").trim();
		String quantity = valueQuantity.getText().trim();
		String subTotal = valueSubTotal.getText().trim();
		subTotal=subTotal.replace("$", "");
		String totalShipping = valueTotalShipping.getText().trim();
		totalShipping=totalShipping.replace("$", "");
		String actualTotal = valueTotal.getText().trim();
		actualTotal=actualTotal.replace("$","");
		double expectedTotal = ((Double.parseDouble(quantity))*(Double.parseDouble(productPrice)))+(Double.parseDouble(totalShipping));
		Assert.assertEquals(actualTotal, expectedTotal);
		return (Double.parseDouble(actualTotal));
	}
		
	//check price 
	public void verifyPrice(double price, WebElement element) {
		String actualTotal = element.getText().trim();
		actualTotal=actualTotal.replace("$", "");
		Assert.assertEquals(Double.parseDouble(actualTotal), price);
		
	}
	
	//
	public void verifyText(WebElement element, String msg) {
		String expectedMsg = msg;
		String actualMsg="";
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
			actualMsg = tempEle.getText();
			Assert.assertEquals(actualMsg, expectedMsg);
		} catch (Exception e) {
			System.err.println("Acutal message - \"" + actualMsg + "\" is not equal to expected message - \""+ expectedMsg +"\".");
			throw e;
		}
	}
}
