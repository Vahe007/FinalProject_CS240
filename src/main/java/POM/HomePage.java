package POM;

import POM.base.BasePage;
import locators.CartLocators;
import locators.HomePageLocators;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage closeBanner() {
        By.className(HomePageLocators.CLOSE_BUTTON);
        WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(HomePageLocators.CLOSE_BUTTON)));
        closeButton.click();
        return this;
    }

    public WebElement getLogoButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(HomePageLocators.LOGO_BUTTON)));
    }

    public WebElement getSearchField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(HomePageLocators.SEARCH)));
    }

    public void inputSearch(String product) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(HomePageLocators.SEARCH)));
        searchField.click();
        searchField.sendKeys(product);
        searchField.sendKeys(Keys.ENTER);
    }

    public SearchResultPage searchForProduct(String product) {
        inputSearch(product);
        return new SearchResultPage(driver);
    }

    public SearchResultPage searchForProductByButton(String product) {
        inputSearch(product);
        WebElement searchButton = driver.findElement(By.className(HomePageLocators.SEARCH_BUTTON));
        searchButton.click();
        return new SearchResultPage(driver);
    }

    public int getCartItemCount() {
        String itemCount = driver.findElement(By.className(HomePageLocators.CART_ITEM_COUNT)).getText();
        return Integer.parseInt(itemCount);
    }

    public WebElement getCartButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HomePageLocators.CART_LOGO)));
    }

    public Cart navigateToCart() {
        getLogoButton().click();
        return new Cart(driver);
    }

}
