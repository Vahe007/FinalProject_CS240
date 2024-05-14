import POM.ProductDetailsPage;
import POM.SearchResultPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductListingTest extends BaseTest {

    @Test
    public void verifySortByPrice() {
        SearchResultPage searchPage = homePage.searchForProduct("iPhone 15");

        WebElement priceSort = searchPage.getLowToHighButton();
        priceSort.click();

        long firstPrice = searchPage.parseToNumber(searchPage.getFirstProductPrice());
        long secondPrice = searchPage.parseToNumber(searchPage.getSecondProductPrice());

        Assert.assertTrue(firstPrice < secondPrice, AssertionMessages.SEARCH_ERROR_MESSAGE);

    }

    @Test
    public void noResultFound() {
        SearchResultPage searchPage = homePage.searchForProduct("aaaaaaaaaaaaaaaaaccccccccccccc");
        Assert.assertTrue(searchPage.noResultFound().isDisplayed(), "Results were found");
        Assert.assertEquals(searchPage.noResultFound().getText(), AssertionMessages.NO_RESULT_FOUND);
    }

    @Test
    public void verifyProductImageClickable() {
        SearchResultPage searchPage = homePage.searchForProduct("iPhone");
        ProductDetailsPage productDetails = searchPage.goToProduct();
        String mainWindowHandle = driver.getWindowHandle();

        //changes the tab
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Assert.assertTrue(productDetails.getAddToCartButton().isDisplayed(), AssertionMessages.ADD_TO_CART_NOT_DISPLAYED);
        Assert.assertTrue(productDetails.getBuyNowButton().isDisplayed(), AssertionMessages.BUY_NOW_NOT_DISPLAYED);
    }
}
