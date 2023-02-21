package pages.gmail;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class GmailLoginPage extends BasePage {

    @FindBy(id ="identifierId")
    private WebElement emailInputLocator;
    @FindBy(id ="identifierNext")
    private WebElement nextSpanLocator;

    @FindBy(xpath = "//input[@type='password']")
    private  WebElement passwordInputLocator;

    @FindBy(id = "passwordNext")
    private WebElement passwordNexDivLocator;

    @FindBy(xpath = "//div[@class='o6cuMc']")
    private WebElement loginErrorMessageWrongMailLocator;

    @FindBy(xpath = "//div[@class='OyEIQ uSvLId']")
    private WebElement loginErrorMessageWrongPasswordLocator;
    @FindBy(xpath = "//html")
    private WebElement htmlTag;


    public GmailLoginPage(WebDriver driver) {
        super(driver);
    }


    public void insertEmail(String email){
        getEwait().until(ExpectedConditions.elementToBeClickable(emailInputLocator));
        flash(emailInputLocator);
        emailInputLocator.clear();
        emailInputLocator.sendKeys(email);
    }

    public void clickNextButton(){
        getEwait().until(ExpectedConditions.elementToBeClickable(nextSpanLocator));
        flash(nextSpanLocator);
        nextSpanLocator.click();
    }

    public void insertPassword(String password){
        getEwait().until(ExpectedConditions.elementToBeClickable(passwordInputLocator));
        flash(passwordInputLocator);
        passwordInputLocator.clear();
        passwordInputLocator.click();
        passwordInputLocator.sendKeys(password);
    }

    public GmailInboxPage clickPasswordNextButton(){
        getEwait().until(ExpectedConditions.elementToBeClickable(passwordNexDivLocator));
        flash(passwordNexDivLocator);
        passwordNexDivLocator.click();
        return new GmailInboxPage(getDriver());
    }

    public String getTextLoginErrorMessageWrongMail(){
        getEwait().until(ExpectedConditions.elementToBeClickable(loginErrorMessageWrongMailLocator));
        flash(loginErrorMessageWrongMailLocator);
        return loginErrorMessageWrongMailLocator.getText();
    }

    public String getTextLoginErrorMessageWrongPassword(){
        getEwait().until(ExpectedConditions.elementToBeClickable(loginErrorMessageWrongPasswordLocator));
        flash(loginErrorMessageWrongPasswordLocator);
        return loginErrorMessageWrongPasswordLocator.getText();
    }
}
