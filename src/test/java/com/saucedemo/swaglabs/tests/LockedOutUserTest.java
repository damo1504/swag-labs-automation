package com.saucedemo.swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LockedOutUserTest extends BaseTest {

    @Test
    public void testLockOutUser() {
    	logger.info("Starting testLockOutUser...");
    	
    	loginPage.loginApplication(properties.getProperty("invalid_user"), properties.getProperty("password"));
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out.");
        logger.info("testLockOutUser test case completed successfully.");
    }
}
