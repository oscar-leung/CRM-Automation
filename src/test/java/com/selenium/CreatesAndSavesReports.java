package com.selenium;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Oscar Leung
 * @version 1.0 An automation script that uses Page-Object which creates and
 *          saves a Call Report. Objective - to demostrate automation code and
 *          to have a working script I have it set up where I can easily press
 *          Run Test or Debug Test(to system print)
 */
public class CreatesAndSavesReports extends FunctionalTest {

    /**
     * Step 1 Log-in to application from a Google Chrome browser This step was
     * fairly simple since I had prior experience on my own scripts of trying to
     * automate job applications on LinkedIn
     */
    @Test
    public void signingIn() {
        FunctionalTest.setUp(); // set up the driver properties and created the driver object
        driver.get("https://login.salesforce.com/"); // open the web browser 
        LoginPage logInPage = new LoginPage(driver); // page object is necessary for every login page 
        assertTrue(logInPage.isInitialized()); // ensures page is there
        logInPage.enterUserName("bb67@bb2.com"); // allows easily modification
        logInPage.enterPassword("bugb1234");
        logInPage.login();
    }

    /**
     * Steps 2 - 5 
     * Navigate to "My Accounts" using tab near top of home page. 
     * Select "Adams, Bob" from the My Accounts list. 
     * Near the top of the Account page is a "Record A Call" button. 
     * Select this button. 
     * Validate the Call Report page is displayed.
     * 
     * There's alot to expand on here with all these page objects, but for now I have it execute these simple steps
     */
    @Test
    public void validateCallReport() {
        signingIn(); // calling the previous test case to move on 
        HomePage homePage = new HomePage(driver); // created homepage object to interact with 
        homePage.navigateToMyAccounts();
        MyAccountPage myAccountPage = new MyAccountPage(driver); // ditto
        myAccountPage.clickOnAdamsBob(); // might be too specific for a method but xpath is flexible and dynamic here cause I can just change the xpath
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver); //ditto 
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // Added wait time to find the element
        accountProfilePage.recordACall(); // clicks record a call button
        CallReportPage callReportPage = new CallReportPage(driver);
        callReportPage.isInitialized(); // page validation
    }

    /**
     * Step 6 
     * On Call Report page, select "Mass Add Promo Call" from the Record Type drop down list.
     * 
     * I think one line of code for this specific step is fine and adding to page objects isn't worth the time unless needed
     */
    @Test
    public void selectMassAddPromoCall() {
        validateCallReport();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Fixed the missing element by adding a wait time
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("RecordTypeId"))); 
        new Select(driver.findElement(By.id("RecordTypeId"))).selectByIndex(19); // where 19 is 'Mass Add Promo Call'
    }


    /**
     * Step 7 On Call Report page, the script should select Cholecap and Labrinone
     * in Detail Priority section. I prefer to have specific methods that checks the
     * boxes instead of entering a name for a product.
     * 
     * ~ 51 seconds
     *
     */
    @Test
    public void selectCholecapAndLabrinone() {
        selectMassAddPromoCall();
        NewMassAddPromoCallPage newMassAddPromoCallPage = new NewMassAddPromoCallPage(driver);  
        // newMassAddPromoCallPage.isInitialized(); // checks page is there
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // fixed clicking checkbox due to elements not fully loaded, especially those with slow internet
        newMassAddPromoCallPage.clickOnCholecapCheckbox(); 
        newMassAddPromoCallPage.clickOnLabrinoneCheckbox();
    }

    /**
     * Step 8 Under Call Discussions section, make sure a subsection appeared for
     * both Cholecap and Labrinone. Also make sure the "Product" fields are set to
     * the respective product (one should be set to Cholecap, the other should be
     * set to Labrinone).
     * 
     * https://stackoverflow.com/questions/11934966/how-to-get-selected-option-using-selenium-webdriver-with-java/11938304#11938304
     * I had trouble understanding this step. I used getting the first option selected method to verify the right products
     */
    @Test
    public void verifyCholecapAndLabrinone() {
        selectCholecapAndLabrinone();
        Select selectCholecap = new Select(driver.findElement(By.xpath("//body[contains(@class,'sfdcBody brandQuaternaryBgr')]//tr//tr[1]//td[2]//div[1]//div[1]//div[1]//div[2]//span[1]//div[1]//span[1]//select[1]")));
        WebElement optionCholecap = selectCholecap.getFirstSelectedOption();
        String defaultItemCholecap = optionCholecap.getText();
        System.out.println(defaultItemCholecap); // https://prnt.sc/snsn2n - verified using debug console

        Select selectLabrinone = new Select(driver.findElement(By.xpath("//body[contains(@class,'sfdcBody brandQuaternaryBgr')]//tr//tr//tr[2]//td[2]//div[1]//div[1]//div[1]//div[2]//span[1]//div[1]//span[1]//select[1]")));
        WebElement optionLabrinone = selectLabrinone.getFirstSelectedOption();
        String defaultItemLabrinone = optionLabrinone.getText();
        System.out.println(defaultItemLabrinone); // DITTO
    }

    /**
     * Step 9
     * On the same Call Discussion section, make sure each section appears in order the product was selected. 
     * If Labrinone was selected first, then Labrinone should show up first (from top down) under Call Discussion section.
     * 
     * Presets - Product Selected [Cholecap,Labrinone | Lab Detail Group 1,PROAIR,QNASL]
     * I'm not sure if this works, but basically I am comparing two xpaths(the ordered number and the products name)
     * I have selected the products prior and then compare the order of it with xpath with the contains text [1,2,3,4]
     * 
     */
    @Test
    public void selectedInOrder(){
        verifyCholecapAndLabrinone();
        // List <WebElement> lst = new ArrayList<WebElement>();
        driver.switchTo().activeElement().findElement(By.xpath("//input[@type='checkbox'][@name='PROAIR']")).click();
        driver.switchTo().activeElement().findElement(By.xpath("//input[@type='checkbox'][@name='QNASL']")).click();

        WebElement firstNum = driver.findElement(By.xpath("//td[contains(@class,'transparent ng-binding')][contains(text(),'1')]"));
        WebElement firstSelected = driver.findElement(By.xpath("//body[contains(@class,'sfdcBody brandQuaternaryBgr')]//tr//tr//tr//tr[2]//td[3]"));
        String firstText = firstNum.getText();
        String firstSelectedText = firstSelected.getText();
        System.out.println("First Selected Product: " + firstText + " " + firstSelectedText);

        WebElement secondNum = driver.findElement(By.xpath("//td[contains(@class,'transparent ng-binding')][contains(text(),'2')]"));
        WebElement secondSelected = driver.findElement(By.xpath("//veev-field[contains(@class,'ng-scope ng-isolate-scope')]//tr[3]//td[3]"));
        String secondText = secondNum.getText();
        String secondSelectedText = secondSelected.getText();
        System.out.println("Second Selected Product: " + secondText + " " + secondSelectedText);

        WebElement thirdNum = driver.findElement(By.xpath("//td[contains(@class,'transparent ng-binding')][contains(text(),'3')]"));
        WebElement thirdSelected = driver.findElement(By.xpath("//veev-field[contains(@class,'ng-scope ng-isolate-scope')]//tr[4]//td[3]"));
        String thirdText = thirdNum.getText();
        String thirdSelectedText = thirdSelected.getText();
        System.out.println("Third Selected Product: " + thirdText + " " + thirdSelectedText);

        WebElement forthNum = driver.findElement(By.xpath("//td[contains(@class,'transparent ng-binding')][contains(text(),'4')]"));
        WebElement forthSelected = driver.findElement(By.xpath("//tr[5]//td[3]"));
        String forthText = forthNum.getText();
        String forthSelectedText = forthSelected.getText();
        System.out.println("Forth Selected Product: " + forthText + " " + forthSelectedText);

        // lst.addAll(driver.switchTo().activeElement().findElements(By.className("edetail-span ng-binding")));
        // for(WebElement webElement : lst){
        //     System.out.println(webElement.getText());
        // }
    }
    /**
     * Step 10
     * In Samples and Promotional Items section, select "QNASL Co-Pay Card", and change quantity to 2.
     * 
     */
        @Test
        public void selectCoPayCard(){
            selectedInOrder();
            NewMassAddPromoCallPage newMassAddPromoCallPage = new NewMassAddPromoCallPage(driver);
            newMassAddPromoCallPage.selectCoPayCard(); // Opportunity to by dynamic here w/ alot of Samples And Promotional Items
            //driver.findElement(By.xpath("//input[@type='text'][@name='Medical_Event_vod__c']")).sendKeys("keysToSend");
            // I am aware this is bad idea to use abs xpath
            driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/span[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/span[7]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/div[1]/div[1]/span[1]/span[1]/span[1]/span[1]/span[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[5]/tr[2]/td[3]/span[1]/span[1]/input[1]")).clear();
            driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/span[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/span[7]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/div[1]/div[1]/span[1]/span[1]/span[1]/span[1]/span[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[5]/tr[2]/td[3]/span[1]/span[1]/input[1]")).sendKeys("2");
        }

    /**
     * Step 11 
     * Call report should be saved using "Saved" button with a check for successful submission. 
     * As final step, the script should logout (found on drop down menu in upper right).
     */
    @Test
    public void saveCallReport(){
        selectCoPayCard();
        driver.findElement(By.xpath("//body[contains(@class,'sfdcBody brandQuaternaryBgr')]/div[@id='contentWrapper']/div[contains(@class,'bodyDiv brdPalette brandPrimaryBrd')]/table[@id='bodyTable']/tbody/tr/td[@id='bodyCell']/span[@id='j_id0:j_id1']/div[@id='veeva-app']/div[contains(@class,'ng-scope')]/div[contains(@class,'veeva-pl-edit ng-scope')]/form[contains(@name,'edit_form')]/div[contains(@class,'bPageBlock brandSecondaryBrd bEditBlock secondaryPalette veeva-pl-page-spacing AccountTab')]/div[contains(@class,'pbBottomButtons')]/table/tbody/tr/td[@id='bottomButtonRow']/span[1]/input[1]")).click();
        driver.findElement(By.id("userNavButton")).click();
        driver.findElement(By.linkText("Logout")).click();
        FunctionalTest.cleanUp();
        FunctionalTest.tearDown();
    }
}
