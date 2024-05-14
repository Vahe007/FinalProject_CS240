import POM.ProductDetailsPage;
import POM.SearchResultPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductDetails extends BaseTest {

    @Test
    public void verifyProductDetails() {
        String mainWindowHandle = driver.getWindowHandle();

        SearchResultPage searchPage = homePage.searchForProduct("iPhone 15");
        ProductDetailsPage productDetailsPage = searchPage.goToProduct();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String productTitle = productDetailsPage.getProductTitle();
        WebElement addToCartButton = productDetailsPage.getAddToCartButton();
        WebElement buyNowButton = productDetailsPage.getBuyNowButton();

        Assert.assertTrue(productTitle.contains(AssertionMessages.PRODUCT_TITLE), AssertionMessages.PRODUCT_TITLE);
        Assert.assertTrue(addToCartButton.getText().contains(AssertionMessages.CART_TEXT), AssertionMessages.CART_TEXT);
        Assert.assertTrue(buyNowButton.getText().contains(AssertionMessages.BUY_NOW), AssertionMessages.BUY_NOW);
    }
}
