package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterUserExistingEmail extends BaseTest{
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
    }

    @Test
    public void registerWithExistingEmailTest() {
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));

        driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'New User Signup!')]")).isDisplayed());

        driver.findElement(By.name("name")).sendKeys("TestUser");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(TestData.EMAIL);
        driver.findElement(By.xpath("//button[contains(text(),'Signup')]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Email Address already exist!')]")).isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
