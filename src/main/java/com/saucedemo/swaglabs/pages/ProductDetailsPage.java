package com.saucedemo.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {
    WebDriver driver;

    @FindBy(css = ".inventory_details_name")
    WebElement productName;

    @FindBy(css = ".inventory_details_price")
    WebElement productPrice;

    @FindBy(css = ".inventory_details_desc")
    WebElement productDescription;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public String getProductDescription() {
        return productDescription.getText();
    }
}
