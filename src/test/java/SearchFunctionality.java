import POM.SearchResultPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchFunctionality extends BaseTest {

    /**
     * Method to verify the presence and functionality of the search field.
     * Verifies if the search field is displayed and clickable.
     */
    @Test
    public void verifyPresence() {
        WebElement searchField = homePage.getSearchField();
        Assert.assertTrue(searchField.isDisplayed());
        searchField.click();
        Assert.assertTrue(searchField.isSelected());
    }

    /**
     * Method to perform an unsuccessful search operation.
     * Verifies if a proper message is displayed when no results are found for the search query.
     */
    @Test
    public void unsuccessfulSearch() {
        SearchResultPage searchPage = homePage.searchForProduct("aaaaaaaaaaaaaaaaaccccccccccccc");
        Assert.assertTrue(searchPage.noResultFound().isDisplayed(), "Results were found");
        Assert.assertEquals(searchPage.noResultFound().getText(), AssertionMessages.NO_RESULT_FOUND);
    }


    /**
     * Method to perform a search by clicking the search button.
     * Verifies if the search results match the expected criteria after searching by clicking the button.
     */
    @Test
    public void searchByButtonClick() {
        String searchValue = "iPhone 15";
        SearchResultPage searchResult = homePage.searchForProductByButton(searchValue);
        Assert.assertTrue(searchResult.getFilterHeader().isDisplayed());
        Assert.assertTrue(searchResult.getFirstProductTitle().contains(searchValue));
    }

    /**
     * Method to perform a successful search operation.
     * Verifies if the search results match the expected criteria after a successful search.
     */
    @Test
    public void successfulSearch() {
        SearchResultPage searchResult = homePage.searchForProduct("iPhone 15");
        Assert.assertTrue(searchResult.getFilterHeader().isDisplayed());
        Assert.assertTrue(searchResult.getFilterHeader().isDisplayed());
        Assert.assertTrue(searchResult.getFirstProductTitle().contains("iPhone 15"));
    }

}
