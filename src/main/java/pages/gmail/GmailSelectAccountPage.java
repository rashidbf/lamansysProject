package pages.gmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class GmailSelectAccountPage extends BasePage {
    public GmailSelectAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//li[@class='JDAKTe eARute W7Aapd zpCp3 SmR8'][2]")
    private WebElement deleteAccount;

    @FindBy(xpath = "//div[@class='wLBAL']")
    private WebElement deleteSelectedAccount;

    @FindBy(xpath = "(//div[@role='button'])[3]")
    private WebElement acceptPopUpDeleteAccount;


    public void deleteAccount(){
        getEwait().until(ExpectedConditions.elementToBeClickable(deleteAccount));
        flash(deleteAccount);
        deleteAccount.click();
        getEwait().until(ExpectedConditions.elementToBeClickable(deleteSelectedAccount));
        flash(deleteSelectedAccount);
        deleteSelectedAccount.click();
        getEwait().until(ExpectedConditions.elementToBeClickable(acceptPopUpDeleteAccount));
        flash(acceptPopUpDeleteAccount);
        acceptPopUpDeleteAccount.click();
        new GmailLoginPage(getDriver());
    }
}
