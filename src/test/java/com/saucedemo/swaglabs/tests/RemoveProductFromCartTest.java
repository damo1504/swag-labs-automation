package com.saucedemo.swaglabs.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class RemoveProductFromCartTest extends BaseTest {

    @Test
    public void testRemoveProductFromCart() {
        
    	logger.info("Starting testRemoveProductFromCart...");
    	
        loginPage.loginApplication(properties.getProperty("username"), properties.getProperty("password"));
        waitForTitle("Products");

        String productName = "Sauce Labs Backpack";
        productsPage.addProductToCart(productName);
        productsPage.openCart();
        waitForTitle("Your Cart");

        cartPage.removeProductFromCart(productName);
        List<String> cartProductNames = cartPage.getCartItems().stream()
                .map(cartItem -> cartItem.findElement(By.cssSelector(".inventory_item_name")).getText())
                .collect(Collectors.toList());
        
        Assert.assertFalse(cartProductNames.contains(productName), "The product was not removed from the cart.");
        
        logger.info("testRemoveProductFromCart test case completed successfully.");
    }
}
