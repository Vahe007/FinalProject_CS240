import POM.SearchResultPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SortingTest extends BaseTest {

    /**
     * verification of price differences of first 2 products
     */
    @Test
    public void verifySortByPrice() {
        SearchResultPage searchPage = homePage.searchForProduct("iPhone 15");

        WebElement priceSort = searchPage.getLowToHighButton();
        priceSort.click();

        long firstPrice = searchPage.parseToNumber(searchPage.getFirstProductPrice());
        long secondPrice = searchPage.parseToNumber(searchPage.getSecondProductPrice());

        Assert.assertTrue(firstPrice < secondPrice, AssertionMessages.SEARCH_ERROR_MESSAGE);

    }


    /**
     * Method to test sorting products by price from low to high.
     * Verifies if the products are correctly sorted in ascending order by price.
     */
    @Test
    public void priceLowToHigh() {
        SearchResultPage searchResult = homePage.searchForProduct("iPhone 15");
        searchResult = searchResult.clickLowToHigh();

        boolean filterResult = searchResult.parseToNumber(searchResult.getFirstProductPrice()) < searchResult.parseToNumber(searchResult.getSecondProductPrice());
        Assert.assertTrue(filterResult);

        List<WebElement> prices = searchResult.getAllProductPrices();

        Assert.assertTrue(searchResult.isSortedAscending(prices));
        Assert.assertFalse(searchResult.isSortedDescending(prices));
    }


    /**
     * Method to test sorting products by price from high to low.
     * Verifies if the products are correctly sorted in descending order by price.
     */
    @Test
    public void priceHighToLow() {
        SearchResultPage searchResult = homePage.searchForProduct("iPhone 15");
        searchResult = searchResult.clickHighToLow();


        List<WebElement> prices = searchResult.getAllProductPrices();

        Assert.assertTrue(searchResult.isSortedDescending(prices));
        Assert.assertFalse(searchResult.isSortedAscending(prices));
    }
}
