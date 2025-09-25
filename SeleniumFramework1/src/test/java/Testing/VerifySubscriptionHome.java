package Testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class VerifySubscriptionHome extends BaseTest{
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
    }

    @Test
    public void verifySubscriptionTest() {
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");

        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Subscription')]")).isDisplayed());

        driver.findElement(By.id("susbscribe_email")).sendKeys("testuser@email.com");
        driver.findElement(By.id("subscribe")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'You have been successfully subscribed!')]")).isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
