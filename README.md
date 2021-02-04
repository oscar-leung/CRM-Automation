# CRM-Automation
Java Selenium done in Page Objects Pattern

I've develop some automation code against the Salesforce CRM application to gain insight and a feel for what's involved with automation development for this application in a web browser.

The main goal here is to have a working script that tests the given steps. 

Automation Script Steps
Log-in to application from a web browser (e.g. Internet Explorer, Firefox, Chrome, etc.).
Navigate to "My Accounts" using tab near top of home page.
Select "Adams, Bob" from the My Accounts list.
Near the top of the Account page is a "Record A Call" button. Select this button.
Validate the Call Report page is displayed.
On Call Report page, select "Mass Add Promo Call" from the Record Type drop down list.
On Call Report page, the script should select Cholecap and Labrinone in Detail Priority section.
Under Call Discussions section, make sure a subsection appeared for both Cholecap and Labrinone. Also make sure the "Product" fields are set to the respective product (one should be set to Cholecap, the other should be set to Labrinone).
On the same Call Discussion section, make sure each section appears in order the product was selected. If Labrinone was selected first, then Labrinone should show up first (from top down) under Call Discussion section.
In Samples and Promotional Items section, select "QNASL Co-Pay Card", and change quantity to 2.
Call report should be saved using "Saved" button with a check for successful submission. As final step, the script should logout (found on drop down menu in upper right).


![](crm_automation.gif)
