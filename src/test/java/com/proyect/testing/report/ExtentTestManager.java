package com.proyect.testing.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
  public static ExtentReports extent =
            ExtentManager.getInstance();

    private static final ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    public static void startTest(String testName) {

        test.set(
            extent.createTest(testName)
        );
    }

    public static ExtentTest getTest() {

        return test.get();
    }
}
