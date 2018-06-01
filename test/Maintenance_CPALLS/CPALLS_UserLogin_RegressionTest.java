package Maintenance_CPALLS;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class CPALLS_UserLogin_RegressionTest {
	
	WebDriver driver;
	String sheetPath = "test/Resources/Data/RGCISD_Users.xls";
	int rowCount, colCount;
	CPALLS_RegressionScenario regScenario= new CPALLS_RegressionScenario();

	@BeforeSuite
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://trends.tangosoftware.com");
	}
	
	@Test
	public void getData() throws Exception{
		Workbook wbook = Workbook.getWorkbook(new File(sheetPath));
		Sheet sht = wbook.getSheet(0);
		rowCount = sht.getRows();
		colCount = sht.getColumns();
		for(int i=8; i<14; i++){
			String userType = sht.getCell(0, i).getContents();
			String Email = sht.getCell(1, i).getContents();
			String studentId = sht.getCell(3, i).getContents();
			
			System.out.println("----------------"+userType+":"+ Email +"-------------------------");
			driver.findElement(By.id("loginEmail")).sendKeys(Email);
			driver.findElement(By.id("password")).sendKeys("Shivu123");
			driver.findElement(By.id("loginbutton")).click();
			Thread.sleep(3000);
			
			if(userType.equalsIgnoreCase("Counsellor user:")){
				regScenario.CounsellorLogin(driver);  
			}		
			regScenario.assessmentFilter(driver);
			regScenario.verify_GreenArrow_Click(driver, studentId);	
			regScenario.verify_HotLink_Fun(driver, studentId);
			regScenario.verify_FilterOptions(driver);
			regScenario.verify_HistorySubTabs(driver, studentId);
	        System.out.println("Ends-----------------------------------------");
	        driver.findElement(By.id("menubutton")).click();
			Thread.sleep(1000);
	        driver.findElement(By.xpath("//*[@id='ui-id-1']/a")).click();
	        logout();
		}		
	}
	
	public void logout() throws InterruptedException{
		driver.findElement(By.id("gearbutton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("signoutbutton")).click();
	}


}
