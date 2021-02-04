package com.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Login Page - https://prnt.sc/snwdgj 
 * https://www.pluralsight.com/guides/getting-started-with-page-object-pattern-for-your-selenium-tests 
 * Page Objects for Login Page 
 */

public class LoginPage extends pageObject {
    // instance variables 
    @FindBy(id = "username")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "Login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver){
        super(driver); 
    }

    public LoginPage login(){
        loginButton.click();
        return new LoginPage(driver); 
    }

    public boolean isInitialized(){
        return userName.isDisplayed();
    }

    public void enterUserName(String userName){
        this.userName.clear();
        this.userName.sendKeys(userName);
    }

    public void enterPassword(String password){
        this.password.clear();
        this.password.sendKeys(password);
    }
}