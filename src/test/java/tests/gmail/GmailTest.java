package tests.gmail;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.gmail.GmailInboxPage;
import pages.gmail.GmailLoginPage;
import pages.gmail.GmailSelectAccountPage;
import tests.BaseTest;


public class GmailTest extends BaseTest {

    @Test(priority = 1)
    @Parameters({"email","password"})
    public void loginGmailSuccess(String email, String password){
        GmailLoginPage gmailPage = new GmailLoginPage(myDriver.getDriver());
        gmailPage.insertEmail(email);
        gmailPage.clickNextButton();
        gmailPage.insertPassword(password);
        GmailInboxPage gmailInboxPage = gmailPage.clickPasswordNextButton();
        gmailInboxPage.clickProfileIcon();
        String emailProfile = gmailInboxPage.getTextFromProfileIcon();
        Assert.assertEquals(emailProfile,email,"The mail profile expected is %s" + email);
        GmailSelectAccountPage gmailSelectAccountPage = gmailInboxPage.clickSignOutButton();
        gmailSelectAccountPage.deleteAccount();

    }
    @Test(priority = 2)
    @Parameters({"wrongEmail","email","loginErrorMessageWrongEmail"})
    public void loginGmailFailWrongEmail(String wrongEmail, String email, String loginErrorMessageWrongEmail){
        GmailLoginPage gmailPage = new GmailLoginPage(myDriver.getDriver());
        gmailPage.insertEmail(wrongEmail);
        gmailPage.clickNextButton();
        String errorMessage = gmailPage.getTextLoginErrorMessageWrongMail();
        Assert.assertEquals(errorMessage,loginErrorMessageWrongEmail,"The mail expected is %s" + email);
    }

    @Test(priority = 2)
    @Parameters({"email", "wrongPassword","loginErrorMessageWrongPassword"})
    public void loginGmailFailWrongPassword(String email,String wrongPassword, String loginErrorMessageWrongPassword){
        GmailLoginPage gmailPage = new GmailLoginPage(myDriver.getDriver());
        gmailPage.insertEmail(email);
        gmailPage.clickNextButton();
        gmailPage.insertPassword(wrongPassword);
        gmailPage.clickPasswordNextButton();
        String errorMessage = gmailPage.getTextLoginErrorMessageWrongPassword();
        Assert.assertEquals(errorMessage,loginErrorMessageWrongPassword,"The password is wrong");
    }
}
