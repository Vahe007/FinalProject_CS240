import POM.HomePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class BaseTest {
    public static final String WEBDRIVER = "webdriver.chrome.driver";
    public static final String DRIVER_PATH = "src/main/resources/chromedriver";
    public static final String baseURL = "https://www.flipkart.com/";
    public static final String PATH = "screenshots/";
    public static final String EXTENSION = ".png";

    protected HomePage homePage;
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;



    @BeforeMethod
    public void beforeClass() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        driver.manage().window().maximize();
//        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.get(baseURL);
        homePage = new HomePage(driver);
    }


    @AfterMethod
    public void screenshotAndTerminate(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            if (driver != null) {
                TakesScreenshot screenshotMaker = (TakesScreenshot) driver;
                Path screenShotBytes = screenshotMaker.getScreenshotAs(OutputType.FILE).toPath();
                try {
                    Files.createDirectory(Paths.get(PATH));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Path destination = Paths.get(PATH + result.getStartMillis() + "-screenshot" + EXTENSION);
                try {
                    Files.move(screenShotBytes, destination);
                    System.out.println("Screenshot saved: " + destination);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }


    public WebDriver getDriver() {
        return driver;
    }
}
