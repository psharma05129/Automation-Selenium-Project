package Testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ContactUsForm extends BaseTest{
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
    }

    @Test
    public void contactUsTest() {
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));

        driver.findElement(By.xpath("//a[contains(text(),'Contact us')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Get In Touch')]")).isDisplayed());

        driver.findElement(By.name("name")).sendKeys("Test User");
        driver.findElement(By.name("email")).sendKeys("testuser@email.com");
        driver.findElement(By.name("subject")).sendKeys("Testing Contact Us");
        driver.findElement(By.id("message")).sendKeys("This is a test automation message");

        // File upload
        WebElement uploadFile = driver.findElement(By.name("upload_file"));
        uploadFile.sendKeys("C:\\Users\\ps967\\Downloads\\eyurg7y-y[.pdf"); 

        driver.findElement(By.name("submit")).click();
        driver.switchTo().alert().accept();

        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Success! Your details have been submitted successfully.')]")).isDisplayed());

        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
