package Maintenance_CPALLS;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

public class waitTest {

	WebDriver driver;

	@BeforeSuite
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://trends.tangosoftware.com");
	}
		
	@BeforeClass
	public void login(){
		driver.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_ARLINGTONISD");
		driver.findElement(By.id("password")).sendKeys("Shivu123");
		driver.findElement(By.id("loginbutton")).click();
	}

	@Test
	public void waitClick() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

		for(int i=2;i<7;i++){
			driver.findElement(By.xpath(".//*[@id='radio']/label["+i+"]/span")).click();
		}		
		
		for(int i=1 ;i<7;i++ ){
	        driver.findElement(By.xpath("html/body/header/top/tabs/a["+i+"]/tab")).click();
		}
		
		WebDriverWait wait = new WebDriverWait(driver,10); 
		for(int i=1; i<6; i++){
			driver.findElement(By.id("menubutton")).click();
//	        driver.findElement(By.xpath("//*[@id='ui-id-"+i+"']/a")).click();
//			WebElement click1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-"+i+"']/a")));
//			click1.click();
	        Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-"+i+"']/a"))).click();
		}
		driver.findElement(By.id("menubutton")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='ui-id-1']/a")).click();
		driver.findElement(By.xpath(".//*[@id='radio']/label[6]/span")).click();
        Thread.sleep(1000);

		driver.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img")).click();
		
	    for(int i=1 ;i<6;i++ ){
	        driver.findElement(By.xpath("html/body/header/top/tabs/a["+i+"]/tab")).click();
		}
	    
//	    WebElement element = (new WebDriverWait(driver, 10))
//	    .until(ExpectedConditions.presenceOfElementLocated(By.id(ÒelementÓ)));
	    
	}
	
//	@AfterClass
//	public void quit(){
//		driver.quit();
//	}
//	
}
