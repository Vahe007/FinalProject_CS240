import POM.Cart;
import POM.HomePage;
import POM.ProductDetailsPage;
import POM.SearchResultPage;
import locators.CartLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;

public class CartTest extends BaseTest {

    /**
     * Test to verify adding a product to the cart.
     */
    @Test
    public void addToCartTest() {
        String mainWindowHandle = driver.getWindowHandle();
        HomePage homePage = new HomePage(driver);
        SearchResultPage searchResultPage = homePage.searchForProduct("iphone 15");
        ProductDetailsPage productDetailsPage = searchResultPage.goToProduct();


        //switching the main tab, since opens in a new tab
        Iterator var4 = this.driver.getWindowHandles().iterator();

        while(var4.hasNext()) {
            String windowHandle = (String)var4.next();
            if (!windowHandle.equals(mainWindowHandle)) {
                this.driver.switchTo().window(windowHandle);
                break;
            }
        }
        Cart cart = productDetailsPage.addToCart();

        Assert.assertTrue(cart.getPriceCountDetails().contains("(4 items)"), "Cart item count is not as expected after adding the first product");

        // Verify navigating back to homepage
        cart.navigateToHomePage();
        homePage.closeBanner();
        Assert.assertEquals(homePage.getCartItemCount(), 1, "Cart item count is not as expected after navigating back to homepage");
    }

    /**
     * Test to verify adding another product to the cart.
     */
    @Test(dependsOnMethods = {"addToCartTest"})
    public void addAnotherProductToCartTest() {
        SearchResultPage searchResultPage = homePage.searchForProduct("iphone 12");
        ProductDetailsPage productDetailsPage = searchResultPage.goToProduct();

        //switching the main tab, since opens in a new tab
        String mainWindowHandle = driver.getWindowHandle();
        Iterator var4 = this.driver.getWindowHandles().iterator();
        while(var4.hasNext()) {
            String windowHandle = (String)var4.next();
            if (!windowHandle.equals(mainWindowHandle)) {
                this.driver.switchTo().window(windowHandle);
                break;
            }
        }


        Cart cart = productDetailsPage.addToCart();

        Assert.assertTrue(cart.getPlaceOrderText().contains(AssertionMessages.PLACE_ORDER), "Place order text not found");
        Assert.assertTrue(cart.getPriceCountDetails().contains(AssertionMessages.PRICE_COUNT_DETAILS), "Price count details not as expected");

        Assert.assertTrue(cart.getPriceCountDetails().contains("(5 items)"), "Cart item count is not as expected after adding the first product");

        // Verify navigating back to homepage
        cart.navigateToHomePage();
        Assert.assertEquals(homePage.getCartItemCount(), 2, "Cart item count is not as expected after navigating back to homepage");
    }

    /**
     * Test to verify removing a product from the cart.
     */
    @Test(dependsOnMethods = {"addAnotherProductToCartTest"})
    public void removeProductFromCartTest() {
        Cart cart = homePage.navigateToCart();

        // Remove the first product
        cart.removeFirstProduct();
        Assert.assertEquals(cart.getEmptyCartMessage(), AssertionMessages.EMPTY_CART_MESSAGE, "Empty cart message not as expected after removing the product");

        // Verify cart item count after removing one item
        Assert.assertEquals(cart.getPriceCountDetails().contains("(4 items)"), 1, "Cart item count is not as expected after removing one product");
    }

    /**
     * Test to verify changing product quantity in the cart.
     */
    @Test(dependsOnMethods = {"addAnotherProductToCartTest"})
    public void changeProductQuantityInCartTest() {
        Cart cart = homePage.navigateToCart();

        // Increase first product count
        cart.increaseFirstProductCount();
        Assert.assertTrue(cart.getPriceCountDetails().contains("(5 items)"), "Product count is not as expected after increasing");

        // Decrease second product count
        cart.decreaseSecondProductCount();
        Assert.assertTrue(cart.getPriceCountDetails().contains("(4 items)"), "Product count is not as expected after decreasing");
    }
}

