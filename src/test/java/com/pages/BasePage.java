package com.pages;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickImagesLink() {
        // It should have a logic to click on images link
        // And it should navigate to google images page
    }

    @Step("Get page Title")
    public String getPageTitle() {
        String title = driver.getTitle();
        return title;
    }

    @Step("Verify base page Title {0}")
    public boolean verifyBasePageTitle(String title) {
        return getPageTitle().contains(title);
    }
}
