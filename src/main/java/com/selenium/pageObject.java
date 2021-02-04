package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
/**
 * https://stackoverflow.com/questions/18436102/selenium-findby-vs-driver-findelement
 * https://github.com/SeleniumHQ/selenium/wiki/PageFactory 
 * https://stackoverflow.com/questions/17470152/how-to-verify-web-element-presence-on-the-page-using-selenium-2 
 */
public class pageObject {
    protected WebDriver driver;

    public pageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); 
    }
    // probably not needed since I havent used it, but nice to have 
    public boolean isElementPresent(By element) { 
        try {
            driver.findElement(element);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
     }
}