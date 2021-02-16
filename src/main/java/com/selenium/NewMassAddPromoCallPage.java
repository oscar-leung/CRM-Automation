package com.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * example of MyAccountPage
 */

public class NewMassAddPromoCallPage extends pageObject{
    @FindBy(xpath = "//select[contains(@class,'ng-pristine ng-valid ng-touched')]")
    private WebElement productDropDown;

    @FindBy(tagName = "iframe")
    private WebElement myAccountiFrame;

    @FindBy(id = "chka00U0000006DoX4IAK")
    private WebElement QNASLCoPayCard;

    // Detailing Priority 
    @FindBy(xpath = "//input[@type='checkbox'][@name='Cholecap']")
    private WebElement cholecapCheckbox;

    @FindBy(xpath = "//input[@type='checkbox'][@name='Labrinone']")
    private WebElement labrinoneCheckbox;

    @FindBy(xpath = "//input[@type='checkbox'][@name='PROAIR']")
    private WebElement PROAIRCheckbox;

    @FindBy(xpath = "//input[@type='checkbox'][@name='QNASL']")
    private WebElement QNASLCheckbox;

    @FindBy(xpath = "//input[contains(@class,'quantity-input ng-pristine ng-valid ng-scope ng-valid-maxlength ng-touched')]")
    private WebElement inputCustomQuantity;
    

    public NewMassAddPromoCallPage(WebDriver driver) {
        super(driver);
    }
    
    public MyAccountPage clickProductDropDown(){
        switchToMyAccountIFrame();
        productDropDown.click();
        driver.switchTo().defaultContent(); // switches back default frame
        return new MyAccountPage(driver);
    }

    public void switchToMyAccountIFrame(){
        driver.switchTo().frame(myAccountiFrame);
    }

    public void clickOnCholecapCheckbox(){
        // ExpectedConditions.elementToBeClickable(cholecapCheckbox);
        cholecapCheckbox.click();
    }

    public void clickOnLabrinoneCheckbox(){
        //new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(labrinoneCheckbox));
        labrinoneCheckbox.click();
    }

    public void clickOnPROAIRCheckbox(){
        PROAIRCheckbox.click();
    }

    public void clickOnQNASLCheckbox(){
        QNASLCheckbox.click();
    }

    public NewMassAddPromoCallPage selectCoPayCard(){
        QNASLCoPayCard.click();
        return new NewMassAddPromoCallPage(driver);
    }

    public void changeQuantity(String num){
        inputCustomQuantity.sendKeys(num);
    }

	public boolean isInitialized() {
        return cholecapCheckbox.isDisplayed();
	}
}