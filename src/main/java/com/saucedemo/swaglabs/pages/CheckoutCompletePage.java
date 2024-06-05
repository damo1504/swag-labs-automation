package com.saucedemo.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {
	WebDriver driver;

	@FindBy(css = ".complete-header")
	WebElement orderConfirmationMessage;

	@FindBy(id = "back-to-products")
	WebElement backToProductsButton;

	@FindBy(css = ".title")
	WebElement title;

	public CheckoutCompletePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getOrderConfirmationMessage() {
		return orderConfirmationMessage.getText();
	}

	public void backToProducts() {
		backToProductsButton.click();
	}

	public String getTitle() {
		return title.getText();
	}
}
