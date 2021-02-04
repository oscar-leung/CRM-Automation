package com.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * example of MyAccountPage
 * https://prnt.sc/snwihn 
 * https://na131.salesforce.com/servlet/servlet.Integration?lid=01rU0000000YP6A&ic=1&linkToken=VmpFPSxNakF5TUMwd05TMHlOMVF3TmpvME5Eb3hPQzQ1T0RsYSxGMlc0M0ota1FIbW5IczZZTWdwQmwtLFlXWmtNR0po
 */

public class MyAccountPage extends pageObject{
    @FindBy(xpath = "//a[text()='Adams, Bob']") // easily modify to any user
    private WebElement adamsBob_link; 

    /**
     * https://www.guru99.com/handling-iframes-selenium.html#2
     */
    @FindBy(tagName = "iframe")
    private WebElement myAccountiFrame;

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }
    
    public MyAccountPage clickOnAdamsBob(){
        switchToMyAccountIFrame();
        adamsBob_link.click();
        driver.switchTo().defaultContent(); // switches back default frame
        return new MyAccountPage(driver);
    }

    public void switchToMyAccountIFrame(){ // wasn't sure if this was necessary atm, b/c I was still learning the application, might take out it
        driver.switchTo().frame(myAccountiFrame);
    }
}