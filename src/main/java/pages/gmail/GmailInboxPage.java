package pages.gmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class GmailInboxPage extends BasePage {
    public GmailInboxPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='gb_b gb_nd gb_qg gb_r gb_Ff']")
    private WebElement profileIconLocator;

    @FindBy(xpath = "//*[@id=\"gb\"]/div[2]/div[3]/div[3]/iframe")
    private WebElement iframe;

    @FindBy(xpath = "//div[@class='Wdz6e ZnExKf']")
    private WebElement nameInsideProfileIconLocator;

     @FindBy(xpath = "//div[@class='T6SHIc']")
     private WebElement signOutLocator;

    public void clickProfileIcon() {
        getEwait().until(ExpectedConditions.elementToBeClickable(profileIconLocator));
        flash(profileIconLocator);
        profileIconLocator.click();
    }
     public String getTextFromProfileIcon(){
        getEwait().until(ExpectedConditions.elementToBeClickable(iframe));
        driver.switchTo().frame(iframe);
        flash(nameInsideProfileIconLocator);
        String nameProfile = nameInsideProfileIconLocator.getText();
        driver.switchTo().defaultContent();
        return nameProfile;
    }

    public GmailSelectAccountPage clickSignOutButton() {
        getEwait().until(ExpectedConditions.elementToBeClickable(iframe));
        driver.switchTo().frame(iframe);
        getEwait().until(ExpectedConditions.elementToBeClickable(signOutLocator));
        flash(signOutLocator);
        signOutLocator.click();
        return new GmailSelectAccountPage(getDriver());
    }
}
