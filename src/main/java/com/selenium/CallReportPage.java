package com.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Call Report Page - http://prnt.sc/snwkqv 
 * https://www.quora.com/How-do-I-validate-a-page-in-Selenium-WebDriver
 * I am using RecordTypeId as a mean to validate the page and as web element for me to access
 */

public class CallReportPage extends pageObject {

    @FindBy(id = "RecordTypeId")
    private WebElement RecordTypeId;

    public CallReportPage(WebDriver driver){
        super(driver);
        new Select(RecordTypeId);
    }
    public boolean isInitialized(){
        return RecordTypeId.isDisplayed();
    }
}