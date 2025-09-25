package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginUserIncorrect extends BaseTest{
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
    }

    @Test
    public void loginWithIncorrectCredentials() {
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));

        driver.findElement(By.linkText("Signup / Login")).click();
        Assert.assertTrue(driver.getPageSource().contains("Login to your account"));

        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("wrongemail@test.com");
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("wrongpassword");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        Assert.assertTrue(driver.getPageSource().contains("Your email or password is incorrect!"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
