package POM;

import POM.base.BasePage;
import locators.ProductDetailsPageLocators;
import locators.SearchResultPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends BasePage {
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getAddToCartButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductDetailsPageLocators.ADD_TO_CART_BUTTON)));
        WebElement addToCartButton = driver.findElement(By.xpath(ProductDetailsPageLocators.ADD_TO_CART_BUTTON));
        return addToCartButton;
    }

    public String getProductTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ProductDetailsPageLocators.PRODUCT_TITLE)));
        WebElement title = driver.findElement(By.className(ProductDetailsPageLocators.PRODUCT_TITLE));
        return title.getText();
    }

    public WebElement getBuyNowButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductDetailsPageLocators.BUY_NOW_BUTTON)));
        WebElement buyNowButton = driver.findElement(By.xpath(ProductDetailsPageLocators.BUY_NOW_BUTTON));
        return buyNowButton;
    }

    public HomePage clickHeaderLogo() {
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ProductDetailsPageLocators.HEADER_LOGO)));
        logo.click();
        return new HomePage(driver);
    }

    public Cart addToCart() {
        getAddToCartButton().click();
        return new Cart(driver);
    }

}
