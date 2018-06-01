package Maintenance_CPALLS;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class PerformanceTest {

	WebDriver browser;
	@Test(invocationCount=2,threadPoolSize = 2)

	public void performanceTest() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		browser = new ChromeDriver();
		browser.get("http://trends.tangosoftware.com");
	    for (int second = 0;; second++) {
	        if (second >= 60) AssertJUnit.fail("timeout");
	        try { 
	        	if (isElementPresent(By.className("title"))) 
	          break; 
	        	} catch (Exception e) {}
	      }
	   
	   browser.findElement(By.id("loginEmail")).sendKeys("shivaleela@TX_BrownsvilleISD");
	   Thread.sleep(1000);
	   browser.findElement(By.id("password")).sendKeys("Shivu123");
	   browser.findElement(By.id("loginbutton")).click();
	   Thread.sleep(3000);
		try{
			String userName = browser.findElement(By.xpath("html/body/header/top/logininfo/text()")).getText().trim();
			System.out.println(userName);
		    AssertJUnit.assertEquals(browser.findElement(By.xpath("/html/body/header/top/logininfo/text()")).getText().trim(),"Shivaleela Hiremath");
		   }catch(Exception e){}
		   finally{
		    System.out.printf("%n[END] Thread Id : %s",Thread.currentThread().getId());
		    browser.quit();
		   }
	}   
	   
	private boolean isElementPresent(By by) {
	      try {
	        browser.findElement(by);
	        return true;
	      }catch (NoSuchElementException e) {
	        return false;
	      }
    }
}
