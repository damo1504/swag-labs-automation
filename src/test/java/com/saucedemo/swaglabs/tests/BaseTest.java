package com.saucedemo.swaglabs.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import com.saucedemo.swaglabs.pages.*;

public class BaseTest {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Properties properties;
	protected LoginPage loginPage;
	protected ProductsPage productsPage;
	protected ProductDetailsPage productDetailsPage;
	protected CartPage cartPage;
	protected CheckoutInformationPage checkoutInformationPage;
	protected CheckoutOverviewPage checkoutOverviewPage;
	protected CheckoutCompletePage checkoutCompletePage;
	protected Logger logger;

	@BeforeMethod
	public void setUp(ITestContext context) throws IOException {
		System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.properties");
		logger = LogManager.getLogger(this.getClass());

		properties = new Properties();
		FileInputStream input = new FileInputStream("src/test/resources/config.properties");
		properties.load(input);

		String browser = properties.getProperty("browser", "chrome");
		driver = initializeDriver(browser);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(properties.getProperty("baseURL"));
		driver.manage().window().maximize();
		
		initializePageObjects();
		verifyPageTitle("Swag Labs");
		context.setAttribute("WebDriver", driver);
	}

	private WebDriver initializeDriver(String browser) {
		switch (browser.toLowerCase()) {
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--headless");
			firefoxOptions.addArguments("--disable-gpu");
			firefoxOptions.addArguments("--window-size=1920,1080");
			return new FirefoxDriver(firefoxOptions);
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--headless");
			edgeOptions.addArguments("--disable-gpu");
			edgeOptions.addArguments("--window-size=1920,1080");
			return new EdgeDriver(edgeOptions);
		case "chrome":
		default:
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--disable-gpu");
			chromeOptions.addArguments("--window-size=1920,1080");
			return new ChromeDriver(chromeOptions);
		}
	}

	private void initializePageObjects() {
		loginPage = new LoginPage(driver);
		productsPage = new ProductsPage(driver);
		productDetailsPage = new ProductDetailsPage(driver);
		cartPage = new CartPage(driver);
		checkoutInformationPage = new CheckoutInformationPage(driver);
		checkoutOverviewPage = new CheckoutOverviewPage(driver);
		checkoutCompletePage = new CheckoutCompletePage(driver);
	}

	protected void verifyPageTitle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "The page title is not correct");
	}

	public void waitForTitle(String title) {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".title"), title));
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (driver != null) {
			driver.quit();
		}
	}
}
