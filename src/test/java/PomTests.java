import POM.ProductDetailsPage;
import POM.SearchResultPage;
import locators.SearchResultPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PomTests extends BaseTest {
    @Test
    public void testAll() {
//        in some cases the banner is appearing, in some it's not, when its appearing
//        the method invocation below should be uncommented
//        homePage.closeBanner();


        SearchResultPage searchPage = homePage.searchForProduct("iPhone 15");
        searchPage.clickHighToLow();
        searchPage.filterAvailableProducts();
        String filterText = searchPage.findElement(By.xpath(SearchResultPageLocators.AVAILABILITY_FILTER)).getText();
        Assert.assertEquals(filterText, AssertionMessages.FILTER_TEXT);

        Assert.assertTrue(searchPage.getLowToHighButton().getText().contains(AssertionMessages.LOW_TO_HIGH), AssertionMessages.LOW_TO_HIGH);

        ProductDetailsPage productDetailsPage = searchPage.goToProduct();

        productDetailsPage.clickHeaderLogo();
    }
}
