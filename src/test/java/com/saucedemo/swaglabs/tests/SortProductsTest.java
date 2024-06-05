package com.saucedemo.swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class SortProductsTest extends BaseTest {

    @Test
    public void testSortProductsByPriceLowToHigh() {
    	logger.info("Starting testSortProductsByPriceLowToHigh...");
    	
    	
        loginPage.loginApplication(properties.getProperty("username"), properties.getProperty("password"));
        waitForTitle("Products");
        
        productsPage.sortProducts("Price (low to high)");
        List<Double> productPrices = productsPage.getProductPrices().stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .collect(Collectors.toList());
        
        for (int i = 0; i < productPrices.size() - 1; i++) {
            Assert.assertTrue(productPrices.get(i) <= productPrices.get(i + 1), "Products are not sorted by price correctly.");
        }
        
        logger.info("testSortProductsByPriceLowToHigh test case completed successfully.");
    }
}
