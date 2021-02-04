package com.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Webpage Model - https://prnt.sc/snvzbs 
 * I've only inluded 'record_a_call' button here for Step 4 and recognized there's two areas of record_a_call buttons:
 * one at the top and one at the bottom ... (which I don't see) but recall from salesforce experience
 * There's alot meaningful objects to be created here from the fields and buttons and mousehovers in this current Salesforce UI
 */

public class AccountProfilePage extends pageObject{

    @FindBy(name = "record_a_call")
    private WebElement recordACallBtn;

    public AccountProfilePage(WebDriver driver) {
        super(driver);
    }
    
    public AccountProfilePage recordACall(){
        recordACallBtn.click();
        return new AccountProfilePage(driver);
    }

}