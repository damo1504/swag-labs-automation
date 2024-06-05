package com.saucedemo.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    WebDriver driver;

    @FindBy(css = ".cart_item")
    List<WebElement> cartItems;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(css = ".title")
    WebElement title;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public WebElement getCartItemByName(String productName) {
        return cartItems.stream().filter(cartItem -> cartItem.findElement(By.cssSelector(".inventory_item_name"))
                .getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }

    public String getTitle() {
        return title.getText();
    }

    public void removeProductFromCart(String productName) {
        WebElement cartItem = getCartItemByName(productName);
        cartItem.findElement(By.cssSelector("[id*='remove']")).click();
    }
}
