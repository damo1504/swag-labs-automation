package com.saucedemo.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutOverviewPage {
	WebDriver driver;

	@FindBy(css = ".cart_item")
	List<WebElement> checkoutItems;

	@FindBy(css = ".summary_tax_label")
	WebElement taxLabel;

	@FindBy(css = ".summary_total_label")
	WebElement totalLabel;

	@FindBy(id = "finish")
	WebElement finishButton;

	@FindBy(css = ".title")
	WebElement title;

	public CheckoutOverviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<WebElement> getCheckoutItems() {
		return checkoutItems;
	}

	public WebElement getCheckoutItemByName(String productName) {
		return checkoutItems.stream().filter(checkoutItem -> checkoutItem
				.findElement(By.cssSelector(".inventory_item_name")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
	}

	public String getTaxAmount() {
		return taxLabel.getText().replace("Tax: $", "");
	}

	public String getTotalAmount() {
		return totalLabel.getText().replace("Total: $", "");
	}

	public void finishCheckout() {
		finishButton.click();
	}

	public String getTitle() {
		return title.getText();
	}
}
