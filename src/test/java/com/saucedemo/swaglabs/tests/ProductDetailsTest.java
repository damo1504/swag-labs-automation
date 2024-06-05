package com.saucedemo.swaglabs.tests;

import com.saucedemo.swaglabs.models.ProductDetails;
import com.saucedemo.swaglabs.utils.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ProductDetailsTest extends BaseTest {

    @Test
    public void testCheckProductDetails() throws IOException {
        
    	logger.info("Starting testCheckProductDetails...");
    	
        loginPage.loginApplication(properties.getProperty("username"), properties.getProperty("password"));
        waitForTitle("Products");
        
        ProductDetails productData = JsonUtils.readJsonFromFile("src/test/resources/productDetails.json", ProductDetails.class);
        
        String productName = productData.getProductName();
        productsPage.viewProductDetails(productName);
        
        Assert.assertEquals(productDetailsPage.getProductName(), productName);
        Assert.assertEquals(productDetailsPage.getProductPrice(), productData.getProductPrice());
        Assert.assertEquals(productDetailsPage.getProductDescription(), productData.getProductDescription());
        
        logger.info("testCheckProductDetails test case completed successfully.");
    }
}
