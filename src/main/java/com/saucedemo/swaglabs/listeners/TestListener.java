package com.saucedemo.swaglabs.listeners;

import com.aventstack.extentreports.Status;
import com.saucedemo.swaglabs.reporter.ExtentManager;
import com.saucedemo.swaglabs.reporter.ExtentTestManager;
import com.saucedemo.swaglabs.utils.ScreenshotUtil;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test Suite started!");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Suite is ending!");
		ExtentManager.getInstance().flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " started!");
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " passed!");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " failed!");
		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.FAIL, "Test failed: " + result.getThrowable());
        ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " skipped!");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " failed but within success percentage!");
	}
}
