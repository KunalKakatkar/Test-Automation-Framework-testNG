package com.mystore.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;	
import java.util.Properties;
import java.util.function.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import com.mystore.pageobjects.IndexPage;
import com.mystore.utility.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebElement element;
	public static Properties prop;
	// public static WebDriver driver;
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<>();
	public WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TestUtil.EXPLICITLY_WAIT));
	public Logger logger = LogManager.getLogger(this.getClass());
	private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
	protected IndexPage indexPage;
	String testName;

	public static WebDriver getDriver() {
		// Get driver from thread local map
		return driverLocal.get();
	}

	// reading config file when data (like report name, host etc) from config file
	// is need in extent report
	static {
		try {
			try {
				prop = new Properties();
				FileInputStream fis = new FileInputStream(
						System.getProperty("user.dir") + "\\Configuration\\config.properties");
				prop.load(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				throw new RuntimeException("Failed to load config.properties file");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// reading config file when no data from config file is need in extent report
	/*
	 * @BeforeSuite(alwaysRun = true, groups = {"sanity","regression"}) public void
	 * loadConfig() { logger.info("Reading config properties"); try { prop = new
	 * Properties(); FileInputStream fis = new
	 * FileInputStream(System.getProperty("user.dir")+
	 * "\\Configuration\\config.properties"); System.out.println("driver " +
	 * driver); prop.load(fis); } catch(FileNotFoundException e) {
	 * e.printStackTrace(); }catch (IOException e) { throw new
	 * RuntimeException("Failed to load config.properties file"); }
	 * logger.info("Completed reading config properties"); }
	 */

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browserName", "isHeadless", "isLambdaTest" })
	public void setup(String browser, boolean isHeadless, boolean isLambdaTest, Method method) {
		testName = method.getName(); // dynamic test name in LambdaTest
		initialization(browser, isHeadless, isLambdaTest);
		indexPage = new IndexPage(getDriver());
	}

	@AfterMethod(alwaysRun = true, groups = { "sanity", "regression" })
	public void tearDown() {
		WebDriver localDriver = getDriver();
		if (localDriver != null) {
			localDriver.quit(); // Close the browser
			driver.remove(); // Remove the driver from ThreadLocal	
			driverLocal.remove();
			logger.info("Browser closed and driver removed");
		} else {
			logger.warn("Driver was null in tearDown()");
		}
	}

	// launch URL
	public void initialization(String browser, boolean isHeadless, boolean isLambdaTest) {
		logger.info("Initializing browser");
		String driverPath = System.getProperty("user.dir");
		if(isLambdaTest) {
			MutableCapabilities capabilities = new MutableCapabilities();
            capabilities.setCapability("browserName", browser);
            capabilities.setCapability("browserVersion", "138");
            HashMap<String, Object> ltOptions = new HashMap<>();
            ltOptions.put("username", "kunalkakatkar16");
            ltOptions.put("accessKey", "LT_xY41eL8RWAWdcPIFFwAFO09FUvq6zBPU2eP8BZb1ZwKWdHA");
            ltOptions.put("platformName", "Windows 10");
            ltOptions.put("resolution", "1920x1080");
            ltOptions.put("build", "Selenium 4");
            ltOptions.put("project", "My_Store");
            ltOptions.put("name", testName);
            ltOptions.put("w3c", true);
            capabilities.setCapability("LT:Options", ltOptions);
            
            WebDriver driver = null;
            try {
            	WebDriver remoteDriver = new RemoteWebDriver(new URL(HUB_URL), capabilities);
                driverLocal.set(remoteDriver);
                wait = new WebDriverWait(remoteDriver, Duration.ofSeconds(30));
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("Invalid LambdaTest URL");
            }
            	
		} else {
		try {
			 WebDriver localDriver = null;  //for lambda test
			if (browser.equalsIgnoreCase("chrome")) {
				ChromeOptions options = new ChromeOptions(); //added
				if (isHeadless) {
					logger.info("Initializing browser - Headless Chrome");
					options.addArguments("--headless=new");
					options.addArguments("--disable-gpu");
					options.addArguments("--window-size=1920,1080");
					driver.set(new ChromeDriver(options));
					wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30));
				} else {
					logger.info("Initializing browser - Chrome");
					//WebDriverManager.chromedriver().setup();
					// Set Browser to ThreadLocalMap
				//	driver.set(new ChromeDriver());
					System.setProperty("webdriver.chrome.driver",driverPath+"\\drivers\\chromedriver.exe");
					localDriver = new ChromeDriver(options); // added and commented above line for lambda test
				}
			} else if (browser.equalsIgnoreCase("edge")) {
				 EdgeOptions options = new EdgeOptions();
				if (isHeadless) {
					logger.info("Initializing browser - Headless Edge");
					options.addArguments("--headless=new");
					options.addArguments("--disable-gpu");
					options.addArguments("--window-size=1920,1080");
					driver.set(new EdgeDriver(options));
					wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30));
				} else {
					logger.info("Initializing browser - Edge");
					//WebDriverManager.edgedriver().setup();
					// Set Browser to ThreadLocalMap
					//driver.set(new EdgeDriver());
					// localDriver = new EdgeDriver(options); // added and commented above line for lambda test
			        System.setProperty("webdriver.edge.driver", driverPath + "\\drivers\\msedgedriver.exe");
			        localDriver = new EdgeDriver(options);
				}
			} else if (browser.equalsIgnoreCase("firefox")) {
				FirefoxOptions options = new FirefoxOptions();
				if (isHeadless) {
					logger.info("Initializing browser - Headless Firefox");
					options.addArguments("--headless");
					options.addArguments("--disable-gpu");
					options.addArguments("--window-size=1920,1080");
					driver.set(new FirefoxDriver(options));
					wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30));
				} else {
					logger.info("Initializing browser - Firefox");
					//WebDriverManager.firefoxdriver().setup();
					// Set Browser to ThreadLocalMap
					//driver.set(new FirefoxDriver());
					System.setProperty("webdriver.firefox.driver", driverPath+ "\\drivers\\geckodriver.exe");
					 localDriver = new FirefoxDriver(options);// added and commented above line for lambda test
				}
			}
			 	driverLocal.set(localDriver);
	            wait = new WebDriverWait(localDriver, Duration.ofSeconds(30));
		} catch (Exception e) {
			throw e;

		}
		}

		logger.info("Initialized browser with name - " + browser);
		maximizeWindow();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		// getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICITLY_WAIT));
		getDriver().get(prop.getProperty("url"));

		logger.info("Initializing completed");
	}

	// constructor for initilizing pagefactory objects
	/*
	 * public BaseClass(WebDriver driver) { this.driver = driver;
	 * PageFactory.initElements(driver, this); }
	 */
	// maximize window
	public void maximizeWindow() { // removed static due to logger

		getDriver().manage().window().maximize();
		logger.info("window maximized");
	}

	// Click on element by locator
	public void clickOn(By locator) {
		logger.info("Trying to click on element by locator - " + locator);
		boolean flag = false;
		try {
			// ((JavascriptExecutor)
			// getDriver()).executeScript("arguments[0].scrollIntoView(true);", locator);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			element.click();

			flag = true;

		} catch (Exception e) {
			throw e;
		} finally {
			if (flag) {
				System.out.println("Click action performed on locator - " + locator);
			} else {
				System.err.println("unable to perform click action on locator - " + locator);
			}
		}

	}

	// click on element by element
	public void clickOn(WebElement element) {
		logger.info("Trying to click on element - " + element);
		boolean flag = false;
		try {
			// ((JavascriptExecutor)
			// getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
			WebElement elementWait = wait.until(ExpectedConditions.visibilityOf(element));
			elementWait.click();
			logger.info("Click on element - " + element);

			flag = true;

		} catch (Exception e) {
			throw e;
		} finally {
			if (flag) {
				System.out.println("Click action performed on element - " + element);
			} else {
				System.err.println("unable to perform click action on element - " + element);
			}

		}
	}

	// click on checkbox
	public void clickOnCheckbox(WebElement element) {
		logger.info("Trying to JS click on checkbox - " + element);
		try {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
			WebElement elementWait = wait.until(ExpectedConditions.visibilityOf(element));
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].click();", element);
			logger.info("JS Click on checkbox - " + element);

		} catch (Exception e) {
			throw e;
		}
	}

	// check is displayed
	public boolean isDisplayed(WebElement element) {
		// ((JavascriptExecutor)
		// getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		logger.info("Checking if element is displayed - " + element);
		boolean b = element.isDisplayed();
		if (b) {
			logger.info(" Element is displayed ");
		} else {
			System.err.println("Element is Not displayed ");
		}
		return b;
	}

	// SendKeys method
	public void enterText(WebElement element, String textToBeEntered) {
		logger.info("trying to sendkeys, with text - " + textToBeEntered + " on element - " + element);
		try {
			// ((JavascriptExecutor)
			// getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
			WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
			tempEle.sendKeys(textToBeEntered);
			logger.info("successfully performed sendkeys, with text - " + textToBeEntered + " on element - " + element);
		} catch (Exception e) {
			System.err.println("Unable to enter text at element " + element);
			throw e;
		}
	}

	// clear text from textbox
	public void textBoxClear(WebElement element) throws Exception {
		try {
		
			logger.info("trying to clear textbox - " + element);
			WebElement tempEle = wait.until(ExpectedConditions.elementToBeClickable(element));
			tempEle.click();
			logger.info("Clicked on the textbox to be cleared - " + element);
			tempEle.clear();
			logger.info("successfully cleared textbox - " + element);
		} catch (Exception e) {
			System.err.println("Unable to clear text at element " + element);
			throw e;
		}
   	
	
	/*		WebDriverWait waits = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
			Function<WebDriver, Boolean> jsCondition = new Function<WebDriver, Boolean>() {
				
		    public Boolean apply(WebDriver driver ) {
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        return (Boolean) js.executeScript(
		            "var el = document.getElementById('quantity_wanted');" +
		            "return el && el.offsetParent !== null && getComputedStyle(el).visibility !== 'hidden';"
		        );
		    }
		    
		};
		waits.until(jsCondition);
	//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(300);
		((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
	*/
	}

	// to get text
	public String getVisibleText(WebElement element) {
		// ((JavascriptExecutor)
		// getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		logger.info("trying to getText on element - " + element);
		WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
		String txt = tempEle.getText();
		logger.info("returning getText on element - " + element);
		return txt;
	}

	// to verify landing page
	public String verifyPage(WebElement element) {
		// ((JavascriptExecutor)
		// getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		logger.info("trying to verifyPage - " + element);
		WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
		logger.info("returning page value of Page - " + element);
		return tempEle.getText();
	}

	// to click on radio button
	public void clickOnRadioBtn(WebElement element) {
		logger.info("Trying to click on Radio button - " + element);
		try {
			// ((JavascriptExecutor)
			// getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
			WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
			tempEle.click();
			logger.info("Successfully clicked on Radio button - " + element);

		} catch (Exception e) {
			System.err.println("unable to click on radio button - " + element);
			throw e;
		}
	}

	// to select value from drop down
	public void selectValueFromDropDown(WebElement element, String text) {
		// ((JavascriptExecutor)
		// getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		logger.info("Trying to select value - " + text + " from drop down -" + element);
		element.click();
		logger.info(" Clicked on dropdown - " + element);
		text = text.trim();
		Select select = new Select(element);
		/*
		 * try { select.selectByContainsVisibleText(text); } catch (Exception e) {
		 * System.err.println("unable to select value - "+ text + " from dropdown - "+
		 * element); throw e; }
		 */
		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			String clearText = option.getText().replace("\u00A0", "").trim();
			if (clearText.equals(text)) {
				option.click();
				logger.info("selected value " + text + "from drop down - " + element);
				break;
			}
		}
	}

	// js click
	public void jsClick(WebElement element) {
		// ((JavascriptExecutor)
		// getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		logger.info("Trying to JS click on element - " + element);
		WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
		try {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].click();", tempEle);
			logger.info("Successfully performed JS click on element - " + element);
		} catch (Exception e) {
			System.err.println("unable to JS click on element - " + tempEle);
		}
	}

	// js getText
	public String jsGetText(WebElement element) {
		logger.info("Trying to get text using JS on element - " + element);
		WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		String text = (String) js.executeScript("return arguments[0].innerText;", element);
		logger.info("Returning text using JS on element - " + element);
		return text;
	}

	// verify text
	public void verifyText(WebElement element, String msg) {
		logger.info("Verifying text on element - " + element);
		String expectedMsg = msg;
		String actualMsg = "";
		try {
			// ((JavascriptExecutor)
			// getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
			WebElement tempEle = wait.until(ExpectedConditions.visibilityOf(element));
			actualMsg = tempEle.getText().trim();
			Assert.assertEquals(actualMsg, expectedMsg);
			logger.info("Successfully verifed text on element - " + element);
		} catch (Exception e) {
			System.err.println("Acutal message - \"" + actualMsg + "\" is not equal to expected message - \""
					+ expectedMsg + "\".");
			throw e;
		}
	}
}
