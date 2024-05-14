package POM;

import POM.base.BasePage;
import locators.CartLocators;
import locators.HomePageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Cart extends BasePage {
    public Cart(WebDriver driver) {
        super(driver);
    }

    public String getItemCount() {
        return driver.findElement(By.className(CartLocators.CART_ITEM_COUNT)).getText();
    }

    public String getPriceCountDetails() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(CartLocators.PRICE_COUNT_DETAILS))).getText();
    }

    public void increaseFirstProductCount() {
        driver.findElement(By.xpath(CartLocators.INCREASE_COUNT_FIRST)).click();
    }

    public void decreaseFirstProductCount() {
        driver.findElement(By.xpath(CartLocators.DECREASE_COUNT_FIRST)).click();
    }

    public void increaseSecondProductCount() {
        driver.findElement(By.xpath(CartLocators.INCREASE_COUNT_SECOND)).click();
    }

    public void decreaseSecondProductCount() {
        driver.findElement(By.xpath(CartLocators.DECREASE_COUNT_SECOND)).click();
    }

    public String getPlaceOrderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CartLocators.PLACE_ORDER))).getText();
    }

    public void removeFirstProduct() {
        driver.findElement(By.xpath(CartLocators.REMOVE_FIRST)).click();
    }

    public void removeSecondProduct() {
        driver.findElement(By.xpath(CartLocators.REMOVE_SECOND)).click();
    }

    public String getEmptyCartMessage() {
        return driver.findElement(By.className(CartLocators.EMPTY_CART_MESSAGE)).getText();
    }

    public WebElement getLogoButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(CartLocators.LOGO_BUTTON)));
    }

    public HomePage goToHomePage() {
        getLogoButton().click();
        return new HomePage(driver);
    }

    public WebElement scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center', behavior: 'smooth'});", element);
        return element;
    }

    public HomePage navigateToHomePage() {
        getLogoButton().click();
        return new HomePage(driver);
    }
}
