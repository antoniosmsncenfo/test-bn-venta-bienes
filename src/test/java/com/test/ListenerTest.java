package com.test;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

@Listeners
public class ListenerTest implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("La prueba: " + result.getName() + " ha iniciado");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
// TODO Auto-generated method stub
        System.out.println("Success of test cases and its details are : " + result.getName());
        // attach screenshots to report
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        saveScreenShot(driver);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        System.out.println(result.getMethod().getMethodName() + " failed!");
        // attach screenshots to report
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        saveScreenShot(driver);
    }

    @Attachment
    public byte[] saveScreenShot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
// TODO Auto-generated method stub
        System.out.println("Skip of test cases and its details are : " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
// TODO Auto-generated method stub
        System.out.println("Failure of test cases and its details are : " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
// TODO Auto-generated method stub
    }

    @Override
    public void onFinish(ITestContext context) {
// TODO Auto-generated method stub
    }
}