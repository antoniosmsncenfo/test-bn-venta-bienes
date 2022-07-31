package com.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class TestBase {

    private static WebDriver driver;

    public void waitVisibilityOf(long seconds, WebElement webElement) {
        new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitInvisibilityOf(long seconds, WebElement webElement) {
        new WebDriverWait(driver, seconds).until(ExpectedConditions.invisibilityOf(webElement));
    }

    public void waitTextToBePresentInElement(long seconds, WebElement webElement, String text) {
        new WebDriverWait(driver, seconds).until(ExpectedConditions.textToBePresentInElement(webElement, text));
    }

    public void waitElementToBeClickable(long seconds, WebElement webElement) {
        new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void navigateToPage(String url) {
        driver.get(url);
    }

    private static WebDriver initChromeDriver(String appURL) {
        System.out.println("Launching google chrome with new profile..");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(appURL);
        return driver;
    }

    private static WebDriver initFirefoxDriver(String appURL) {
        System.out.println("Launching Firefox browser..");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.navigate().to(appURL);
        return driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    private void setDriver(String browserType, String appURL) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver(appURL);
                break;
            case "firefox":
                driver = initFirefoxDriver(appURL);
                break;
            default:
                System.out.println("browser : " + browserType + " is invalid, Launching Chrome as browser of choice..");
                driver = initChromeDriver(appURL);
        }
    }

    @BeforeClass    
    public void TestBase(ITestContext context) {
        String browserType = "chrome";
        String appURL = "https://www.bnventadebienes.com/Home/HomeFilter";
        try {
            setDriver(browserType, appURL);
            context.setAttribute("driver", getDriver());
        } catch (Exception e) {
            System.out.println("Error....." + e.getStackTrace());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}