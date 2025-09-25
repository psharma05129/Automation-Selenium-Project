package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegisterUser extends BaseTest{
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
    }

    @Test
    public void registerUserTest() {
        
        handlePopup();

        
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));

        
        driver.findElement(By.linkText("Signup / Login")).click();

        
        Assert.assertTrue(driver.getPageSource().contains("New User Signup!"));

        
        driver.findElement(By.name("name")).sendKeys("Priya");
        
              
        driver.findElement(By.xpath("//input[@data-qa='signup-email']"))
              .sendKeys(TestData.EMAIL);

        
        driver.findElement(By.xpath("//button[text()='Signup']")).click();

        
        Assert.assertTrue(driver.getPageSource().contains("Enter Account Information"));

        
        driver.findElement(By.id("id_gender2")).click();
        driver.findElement(By.id("password")).sendKeys(TestData.PASSWORD);
        driver.findElement(By.id("days")).sendKeys("10");
        driver.findElement(By.id("months")).sendKeys("May");
        driver.findElement(By.id("years")).sendKeys("1995");
        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("optin")).click();
        driver.findElement(By.id("first_name")).sendKeys("Priya");
        driver.findElement(By.id("last_name")).sendKeys("Sharma");
        driver.findElement(By.id("address1")).sendKeys("New Delhi, India");
        driver.findElement(By.id("country")).sendKeys("India");
        driver.findElement(By.id("state")).sendKeys("Delhi");
        driver.findElement(By.id("city")).sendKeys("Delhi");
        driver.findElement(By.id("zipcode")).sendKeys("110001");
        driver.findElement(By.id("mobile_number")).sendKeys("9876543210");

        
        driver.findElement(By.xpath("//button[text()='Create Account']")).click();

        
        Assert.assertTrue(driver.getPageSource().contains("Account Created!"));

        
        driver.findElement(By.linkText("Continue")).click();

        
        Assert.assertTrue(driver.getPageSource().contains("Logged in as"));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    /**
     * Handles advertisement or overlay popups if they appear on the page.
     */
    public void handlePopup() {
        try {
            // Example: Close modal if displayed
            WebElement popupCloseBtn = driver.findElement(By.cssSelector(".adsbygoogle + .close"));
            if (popupCloseBtn.isDisplayed()) {
                popupCloseBtn.click();
            }
        } catch (Exception e) {
            // If popup not found, just continue
        }
    }
}


