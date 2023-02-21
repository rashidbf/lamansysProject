package tests.ebay;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ebay.HomePage;
import pages.ebay.ProductPage;
import tests.BaseTest;

public class EbayTest extends BaseTest {

    @Test
    @Parameters({"productToSearch"})
    public void enToEndEbayTest(String productToSearch){
       HomePage ebayPage = new HomePage(myDriver.getDriver());
       ebayPage.searchProduct(productToSearch);
       ProductPage productPage = ebayPage.openFirstProduct();
       productPage.getPriceOfProduct();
    }
}
