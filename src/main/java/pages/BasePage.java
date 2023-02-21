package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait ewait;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        ewait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.driver = driver;
    }
    protected WebDriver getDriver(){
        return driver;
    }
    protected WebDriverWait getEwait(){
        return ewait;
    }

    public void dispose(){
        if(driver!=null){
            driver.quit();
        }
    }

    public static void jsFlash(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String backgroundColor = element.getCssValue("backgroundColor");
        String borderBk = element.getCssValue("border");
        String color = "orange";
        String border = "2px solid red";
        for (int i = 0 ; i<5;i++){
            changeColor(color,border, element,js);
        }
        changeColor(backgroundColor,borderBk,element,js);
    }
    public static void changeColor(String color, String border, WebElement element, JavascriptExecutor js){
        js.executeScript("arguments[0].style.backgroundColor = '"+color+"'", element);
        js.executeScript("arguments[0].style.border = '"+border+"'", element);
    }

    public void flash(WebElement element){
        jsFlash(element,driver);
    }
}