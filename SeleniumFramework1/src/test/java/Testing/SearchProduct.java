package Testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SearchProduct extends BaseTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
    }

    @Test
    public void searchProductTest() {
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));

        driver.findElement(By.xpath("//a[contains(text(),'Products')]")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("products"));

        driver.findElement(By.id("search_product")).sendKeys("Tshirt");
        driver.findElement(By.id("submit_search")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Searched Products')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".features_items")).isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
