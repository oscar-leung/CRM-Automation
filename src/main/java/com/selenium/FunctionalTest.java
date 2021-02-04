package com.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Functional Test
 * @see https://www.pluralsight.com/guides/getting-started-with-page-object-pattern-for-your-selenium-tests
 * This is the 'One Driver to Rule them All'(LOTR) idea 
 */
public class FunctionalTest 
{
    protected static WebDriver driver; // one driver to rule them all

    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/oscarleung/Selenium Drivers/chromedriver"); // ensure I am using right correct driver and it is updated
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // I think 10 seconds is good enough for most web elements searching for this assignment
    }

    public static void cleanUp() {
        driver.manage().deleteAllCookies(); // where the cookies go? :) 
    }

    public static void tearDown(){
        driver.close();
    }
}
