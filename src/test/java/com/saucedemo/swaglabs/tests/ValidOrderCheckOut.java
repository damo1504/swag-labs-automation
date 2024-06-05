package com.saucedemo.swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.saucedemo.swaglabs.utils.JsonUtils;
import com.saucedemo.swaglabs.models.ValidOrderCheckOutData;

public class ValidOrderCheckOut extends BaseTest {

    @Test
    public void testValidOrderCheckOut() throws IOException {
        
    	logger.info("Starting ValidOrderCheckOut test...");
        ValidOrderCheckOutData validOrderCheckOutData = JsonUtils.readJsonFromFile("src/test/resources/validOrder.json", ValidOrderCheckOutData.class);

        String username = validOrderCheckOutData.getUsername();
        String password = validOrderCheckOutData.getPassword();
        String firstName = validOrderCheckOutData.getFirstName();
        String lastName = validOrderCheckOutData.getLastName();
        String postalCode = validOrderCheckOutData.getPostalCode();
        String expectedOrderConfirmationMessage = validOrderCheckOutData.getOrderConfirmationMessage();

        List<String> productNames = validOrderCheckOutData.getProducts().stream().map(ValidOrderCheckOutData.Product::getName).collect(Collectors.toList());
        Map<String, String> productPrices = validOrderCheckOutData.getProducts().stream()
                .collect(Collectors.toMap(ValidOrderCheckOutData.Product::getName, ValidOrderCheckOutData.Product::getPrice));

        loginPage.loginApplication(username, password);
        waitForTitle("Products");

        addProductsToCart(productNames);

        productsPage.openCart();
        waitForTitle("Your Cart");

        verifyCartContents(productNames, productPrices);
        cartPage.proceedToCheckout();

        waitForTitle("Checkout: Your Information");
        checkoutInformationPage.enterCheckoutInformation(firstName, lastName, postalCode);

        waitForTitle("Checkout: Overview");
        verifyCheckoutContents(productNames, productPrices);

        checkoutOverviewPage.finishCheckout();
        waitForTitle("Checkout: Complete!");

        verifyOrderConfirmation(expectedOrderConfirmationMessage);
        navigateBackToProducts();
        
        logger.info("ValidOrderCheckOut test case completed successfully.");
    }

    private void addProductsToCart(List<String> productNames) {
        for (String productName : productNames) {
            productsPage.addProductToCart(productName);
        }
    }

    private void verifyCartContents(List<String> productNames, Map<String, String> productPrices) {
        List<WebElement> cartProducts = cartPage.getCartItems();
        List<String> cartProductNames = cartProducts.stream()
                .map(cartProduct -> cartProduct.findElement(By.cssSelector(".inventory_item_name")).getText())
                .collect(Collectors.toList());

        Assert.assertEquals(cartProductNames.size(), productNames.size(),
                "Number of products in cart does not match the number of products added.");
        Assert.assertTrue(cartProductNames.containsAll(productNames),
                "Cart does not contain all the expected products.");

        for (String productName : productNames) {
            String cartProductPrice = cartPage.getCartItemByName(productName)
                    .findElement(By.cssSelector(".inventory_item_price")).getText();
            Assert.assertEquals(cartProductPrice, productPrices.get(productName),
                    "Price for product " + productName + " does not match in cart");
        }
    }

    private void verifyCheckoutContents(List<String> productNames, Map<String, String> productPrices) {
        List<WebElement> checkoutProducts = checkoutOverviewPage.getCheckoutItems();
        List<String> checkoutProductNames = checkoutProducts.stream()
                .map(checkoutProduct -> checkoutProduct.findElement(By.cssSelector(".inventory_item_name")).getText())
                .collect(Collectors.toList());

        Assert.assertEquals(checkoutProductNames.size(), productNames.size(),
                "Number of products in checkout does not match the number of products added.");
        Assert.assertTrue(checkoutProductNames.containsAll(productNames),
                "Checkout does not contain all the expected products.");

        double totalAmount = 0.0;
        for (String productName : productNames) {
            String checkoutProductPrice = checkoutOverviewPage.getCheckoutItemByName(productName)
                    .findElement(By.cssSelector(".inventory_item_price")).getText();
            Assert.assertEquals(checkoutProductPrice, productPrices.get(productName),
                    "Price for product " + productName + " does not match in checkout");
            totalAmount += Double.parseDouble(checkoutProductPrice.replace("$", ""));
        }

        double taxAmount = Double.parseDouble(checkoutOverviewPage.getTaxAmount());
        totalAmount += taxAmount;

        double displayedTotal = Double.parseDouble(checkoutOverviewPage.getTotalAmount());
        Assert.assertEquals(displayedTotal, totalAmount, 0.01, "Total amount does not match");
    }

    private void verifyOrderConfirmation(String expectedOrderConfirmationMessage) {
        String actualOrderConfirmationMessage = checkoutCompletePage.getOrderConfirmationMessage();
        Assert.assertEquals(actualOrderConfirmationMessage, expectedOrderConfirmationMessage);
    }

    private void navigateBackToProducts() {
        checkoutCompletePage.backToProducts();
        waitForTitle("Products");
    }
}
