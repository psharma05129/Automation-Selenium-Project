package Testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class VerifyAllProducts {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeMethod
    public void setUp() {
        // Disable Chrome popups/notifications
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void verifyAllProductsAndDetails() {
        
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));

        
        closeAdvertisementsIfPresent();
        WebElement productsLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Products')]")));
        productsLink.click();

        
        Assert.assertTrue(driver.getCurrentUrl().contains("products"));

        
        WebElement productsList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".features_items")));
        Assert.assertTrue(productsList.isDisplayed());

        
        WebElement viewProduct = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//a[contains(text(),'View Product')])[1]")));

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", viewProduct);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(viewProduct)).click();
        } catch (Exception e) {
            
            js.executeScript("arguments[0].click();", viewProduct);
        }

        
        Assert.assertTrue(driver.getCurrentUrl().contains("product_details"));

       
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2"))).isDisplayed());
        Assert.assertTrue(isElementVisible("//p[contains(text(),'Category')]"));
        Assert.assertTrue(isElementVisible("//span[contains(text(),'Rs.')]"));
        Assert.assertTrue(isElementVisible("//b[contains(text(),'Availability')]"));
        Assert.assertTrue(isElementVisible("//b[contains(text(),'Condition')]"));
        Assert.assertTrue(isElementVisible("//b[contains(text(),'Brand')]"));
    }

    // --- Helper methods ---
    private void closeAdvertisementsIfPresent() {
        try {
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            for (WebElement frame : iframes) {
                driver.switchTo().frame(frame);
                List<WebElement> closeButtons = driver.findElements(By.cssSelector("div[aria-label='Close ad']"));
                if (!closeButtons.isEmpty()) {
                    closeButtons.get(0).click();
                    break;
                }
                driver.switchTo().defaultContent();
            }
        } catch (Exception ignored) {
            driver.switchTo().defaultContent();
        }

        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (NoAlertPresentException ignored) {}
    }

    private boolean isElementVisible(String xpath) {
        try {
            return driver.findElement(By.xpath(xpath)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
