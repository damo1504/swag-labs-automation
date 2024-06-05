package com.saucedemo.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInformationPage {
	WebDriver driver;

	@FindBy(id = "first-name")
	WebElement firstName;

	@FindBy(id = "last-name")
	WebElement lastName;

	@FindBy(id = "postal-code")
	WebElement postalCode;

	@FindBy(id = "continue")
	WebElement continueButton;

	@FindBy(css = ".title")
	WebElement title;

	public CheckoutInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterCheckoutInformation(String fName, String lName, String pCode) {
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		postalCode.sendKeys(pCode);
		continueButton.click();
	}

	public String getTitle() {
		return title.getText();
	}
}
