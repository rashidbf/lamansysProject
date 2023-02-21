package pages.ebay;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@itemprop='price']")
    private WebElement priceOfProductLocator;

    @FindBy(xpath = "//div[@class='d-picture-minview__container']")
    private WebElement zoomLocator;

    public void getPriceOfProduct(){
        getEwait().until(ExpectedConditions.elementToBeClickable(priceOfProductLocator));
        flash(priceOfProductLocator);
        String priceOfProduct =  priceOfProductLocator.getText();
        System.out.println("The price of product is:  " + priceOfProduct);

    }
}
