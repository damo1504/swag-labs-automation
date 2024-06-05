package com.saucedemo.swaglabs.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String dest = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
            File destination = new File(dest);
            FileUtils.copyFile(source, destination);
            return dest;
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
            return e.getMessage();
        }
    }
}
