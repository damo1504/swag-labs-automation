package com.saucedemo.swaglabs.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
	private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
	private static ExtentReports extent = ExtentManager.getInstance();

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get(Thread.currentThread().hashCode());
	}

	public static synchronized void endTest() {
		extent.removeTest(getTest());
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put(Thread.currentThread().hashCode(), test);
		return test;
	}
}
