package com.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Home Page model - https://prnt.sc/snwa3x 
 * I learned that there is not necessarily a one-to-one mapping between a complete HTML page and a page object. 
 * A page object should represent a meaningful contextual part of the page.
 */

public class HomePage extends pageObject {    

    @FindBy(className = "wt-My-Accounts")
    private WebElement myAccountsBtn;

    public HomePage(WebDriver driver){
        super(driver);
    }
    public HomePage navigateToMyAccounts(){
        myAccountsBtn.click();
        return new HomePage(driver);
    }
}