package Testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class VerifyTestCasesPage extends BaseTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
    }

    @Test
    public void verifyTestCasesPage() {
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));

        driver.findElement(By.xpath("//a[contains(text(),'Test Cases')]")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("test_cases"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
