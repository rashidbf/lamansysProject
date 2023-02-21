package pages.ebay;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.Set;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "gh-ac")
    private WebElement searchBarLocator;

    @FindBy(xpath = "(//div[@class='s-item__image-wrapper image-treatment'])[2]")
    private WebElement firstProduct;

    public void searchProduct(String product) {
        getEwait().until(ExpectedConditions.elementToBeClickable(searchBarLocator));
        flash(searchBarLocator);
        searchBarLocator.clear();
        searchBarLocator.sendKeys(product);
        searchBarLocator.submit();
    }

    public ProductPage openFirstProduct() {
        getEwait().until(ExpectedConditions.elementToBeClickable(firstProduct));
        flash(firstProduct);
        firstProduct.click();
        String mainTab = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equalsIgnoreCase(mainTab)) {
                driver.switchTo().window(window);
                break;
            }
        }
        return new ProductPage(getDriver());
    }
}