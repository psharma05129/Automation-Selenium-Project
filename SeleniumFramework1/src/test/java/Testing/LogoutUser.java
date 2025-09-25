package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutUser extends BaseTest{
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
    }

    @Test
    public void logoutTest() {
        
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));

        
        driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Login to your account')]")).isDisplayed());

        
        driver.findElement(By.name("email")).sendKeys(TestData.EMAIL);
        driver.findElement(By.name("password")).sendKeys(TestData.PASSWORD);
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        
        WebElement loggedInText = driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]"));
        Assert.assertTrue(loggedInText.isDisplayed());

        
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

        
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Login to your account')]")).isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
