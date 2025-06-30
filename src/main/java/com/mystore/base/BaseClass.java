package com.mystore.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.mystore.pageobjects.IndexPage;
import com.mystore.utility.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebElement element;
	public static Properties prop;
	//public static WebDriver driver;
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<> ();
	public WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TestUtil.EXPLICITLY_WAIT));
	public Logger logger=LogManager.getLogger(this.getClass());
	protected IndexPage indexPage;
	
	public static WebDriver getDriver() {
		//Get driver from thread local map
		return driver.get();
	}
	
	//reading config file
	@BeforeTest
	public void loadConfig() {
		logger.info("Reading config properties");
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
		logger.info("Completed reading config properties");
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setup() {
		initialization();
		indexPage = new IndexPage(getDriver());
	}
	
	@AfterMethod
	public void tearDown() {
		 if (getDriver() != null) {
		        getDriver().quit();
		    }
	}
	
	//launch URL
	public void initialization() {
		logger.info("Initializing browser");
		
		String browserName = prop.getProperty("browser");
		try {
			if(browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				//driver = new ChromeDriver();
				//Set Browser to ThreadLocalMap
				driver.set(new ChromeDriver());
			} else if (browserName.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				//driver = new EdgeDriver();
				//Set Browser to ThreadLocalMap
				driver.set(new EdgeDriver());
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				//driver = new FirefoxDriver();
				//Set Browser to ThreadLocalMap
				driver.set(new FirefoxDriver());
			}
		} catch (Exception e) {
			throw e;
			
		}
		
		logger.info("Initialized browser with name - " +browserName);
		
		//driver = new ChromeDriver();
		//System.setProperty("Webdriver.Chrome.Driver", "D:\\Automation\\Selenium-PracticeProject\\Drivers\\chromedriver.exe");
		maximizeWindow();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
	//	getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICITLY_WAIT));
		getDriver().get(prop.getProperty("url"));
		
		logger.info("Initializing completed");
	}
	
	//constructor for initilizing pagefactory objects
/*	public BaseClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
*/	
	//maximize window
	public void maximizeWindow() {   //removed static due to logger
		
		getDriver().manage().window().maximize();
		logger.info("window maximized");
	}
	
	//Click on element by locator
	public void clickOn(By locator) {
		logger.info("Trying to click on element by locator - " +locator);
		boolean flag = false;
		try{
	//		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", locator);
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
		logger.info("Trying to click on element - " +element);
			boolean flag = false;
			try{
		//		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
			WebElement elementWait = wait.until(ExpectedConditions.visibilityOf(element));
			elementWait.click();
			logger.info("Click on element - "+element);
			
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
			logger.info("Trying to JS click on checkbox - " +element);
			try {
			  ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
				WebElement elementWait = wait.until(ExpectedConditions.visibilityOf(element));
				JavascriptExecutor js = (JavascriptExecutor) getDriver();
				js.executeScript("arguments[0].click();", element);
				logger.info("JS Click on checkbox - "+element);
				
			} catch (Exception e) {
					throw e;
			}
		}
	
		//check is displayed
	public boolean isDisplayed(WebElement element) {
	//	((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		logger.info("Checking if element is displayed - " +element);
		boolean b = element.isDisplayed();
			if(b) {
				logger.info(" Element is displayed ");
			} else {
				System.err.println("Element is Not displayed ");	
			}
			return b;
	}
		
		//SendKeys method
	public void enterText(WebElement element, String textToBeEntered) {
		logger.info("trying to sendkeys, with text - "+textToBeEntered+ " on element - " +element);
		try {
	//		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
			WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
			tempEle.sendKeys(textToBeEntered);
			logger.info("successfully performed sendkeys, with text - "+textToBeEntered+ " on element - " +element);
		} catch (Exception e) {
			System.err.println("Unable to enter text at element "+element);
			throw e;	
		}
	}
	
	//clear text from textbox
	public void textBoxClear(WebElement element) {
		try {
		//	((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
			logger.info("trying to clear textbox - " + element);
			WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
			tempEle.clear();
			logger.info("successfully cleared textbox - " + element);
			} catch (Exception e) {
				System.err.println("Unable to clear text at element "+element);
				throw e;	
			}
	}
	
	//to get text
	public String getVisibleText(WebElement element) {
	//	((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		logger.info("trying to getText on element - " + element);
		WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
		String txt = tempEle.getText();
		logger.info("returning getText on element - " + element);
		return txt;
	}
	
	//to verify landing page
	public String verifyPage (WebElement element) {
	//	((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		logger.info("trying to verifyPage - " + element);
		WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
		logger.info("returning page value of Page - " + element);
		return tempEle.getText();
	}
	
	//to click on radio button
	public void clickOnRadioBtn(WebElement element) {
		logger.info("Trying to click on Radio button - " +element);
		try{
	//		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
			WebElement tempEle=wait.until(ExpectedConditions.visibilityOf(element));
			tempEle.click();
			logger.info("Successfully clicked on Radio button - " +element);
				
		} catch (Exception e) {
			System.err.println("unable to click on radio button - " + element);
			throw e;
		}
	}
	
	//to select value from drop down
	public void selectValueFromDropDown(WebElement element, String text) {
	//	((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		logger.info("Trying to select value - " +text+ " from drop down -" +element);
		element.click();
		logger.info(" Clicked on dropdown - "+element);
		text=text.trim();
		Select select = new Select(element);
/*		try {
			select.selectByContainsVisibleText(text);
		} catch (Exception e) {
			System.err.println("unable to select value - "+ text + " from dropdown - "+ element);
			throw e;
		}
*/
		List<WebElement> options = select.getOptions();
		for(WebElement option: options) {
			String clearText = option.getText().replace("\u00A0", "").trim();
			if(clearText.equals(text))
			{
				option.click();
				logger.info("selected value " +text+ "from drop down - "+element);
				break;
			}
		}
	}
	
	//js click
	public void jsClick(WebElement element) {
		//((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		logger.info("Trying to JS click on element - " +element);
		WebElement tempEle=wait.until(ExpectedConditions.visibilityOf(element));
		try {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click();", tempEle);
		logger.info("Successfully performed JS click on element - " +element);
		} catch (Exception e) {
			System.err.println("unable to JS click on element - "+ tempEle);
		}
	}
	
	//js getText
	public String jsGetText(WebElement element) {
		logger.info("Trying to get text using JS on element - " +element);
		WebElement tempEle=wait.until(ExpectedConditions.visibilityOf(element));
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		String text = (String) js.executeScript("return arguments[0].innerText;", element);
		logger.info("Returning text using JS on element - " +element);
		return text;
	}
	

	//verify text
	public void verifyText(WebElement element, String msg) {
		logger.info("Verifying text on element - " +element);
		String expectedMsg = msg;
		String actualMsg="";
		try {
		//	((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
			WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
			actualMsg = tempEle.getText().trim();
			Assert.assertEquals(actualMsg, expectedMsg);
			logger.info("Successfully verifed text on element - " +element);
		} catch (Exception e) {
			System.err.println("Acutal message - \"" + actualMsg + "\" is not equal to expected message - \""+ expectedMsg +"\".");
			throw e;
		}
	}
}
