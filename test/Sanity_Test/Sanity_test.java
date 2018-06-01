package Sanity_Test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Sanity_test {
	
	WebDriver driver;
	Sanity_Scenarios sanityTest = new Sanity_Scenarios();
	
	@BeforeTest
	public void setUp() throws InterruptedException{
		//opening Chrome browser..
		System.setProperty("webdriver.chrome.driver", "/Users/shivaleelah/Downloads/chromedriver");
		driver = new ChromeDriver();				
		//Launching the application..
//		driver.get("http://trends.tangosoftware.com");
//		System.out.println("Launched Trends web link..");
//		reportsLog.info("Trends Application launched successfully..");
	}
		

	//Shivaleela@TX_BrownsvilleISD, Shivaleela@TX_ComalISD, TX_HarlingenCISD, TX_RioGrandeCityCISD
	@Test
	public void Login(){
		//Login functionality..
		driver.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_Tangosoftware");
		driver.findElement(By.id("password")).sendKeys("Shivu123");
		driver.findElement(By.id("loginbutton")).click();
//		reportsLog.info("Login successfully..");
	}
	
	@Test
	public void sanityScenarios() throws InterruptedException{

		sanityTest.kentro_Tab(driver);
    	sanityTest.staar_Tab(driver);
		sanityTest.telpas_Tab(driver);
		sanityTest.lion_Tab(driver);
		sanityTest.kReady_Tab(driver); 
		sanityTest.tpri_tjl_Tab(driver);
		sanityTest.cpalls_Tab(driver); 
		sanityTest.stateAccountability_Tab(driver);	
		
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException{
		
		driver.findElement(By.id("gearbutton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("signoutbutton")).click();
		
	}


}
