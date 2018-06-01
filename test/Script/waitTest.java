package Script;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class waitTest {
    
	WebDriver driver;
	@Test
    public void ImplicitWait() throws InterruptedException
    {
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		driver = new ChromeDriver();
        driver.get("http://trends.tangosoftware.com");
        System.out.println("Launched Trends web link..");
		driver.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_BrownsvilleISDTest03");
		driver.findElement(By.id("password")).sendKeys("Shivu123");
		driver.findElement(By.id("loginbutton")).click();
		
//      driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		driver.findElement(By.id("menubutton")).click();

       WebDriverWait wait = new WebDriverWait(driver,10); 
	   wait.until(ExpectedConditions.elementToBeClickable(By.id("ui-id-10"))).click();
//	   driver.findElement(By.id("ui-id-9")).click();
    }
}






