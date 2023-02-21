package tests;

import driver.MyDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import pages.gmail.GmailLoginPage;

public class BaseTest {
    protected static MyDriver myDriver;

    protected GmailLoginPage gmailPage;

    @BeforeSuite(alwaysRun =true)
    @Parameters({"browser","url"})
    public void beforeSuite(String browser, String url){
        myDriver = new MyDriver(browser);
        myDriver.getDriver().get(url);
        myDriver.getDriver().manage().window().maximize();
        gmailPage = new GmailLoginPage(myDriver.getDriver());
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(){
        gmailPage.dispose();
    }
}

