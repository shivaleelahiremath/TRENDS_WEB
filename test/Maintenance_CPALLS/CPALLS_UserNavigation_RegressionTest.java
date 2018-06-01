package Maintenance_CPALLS;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

public class CPALLS_UserNavigation_RegressionTest {
	
	String sheetPath = "test/Resources/Data/RGCISD_Users.xls";
	WebDriver driver;
	int row, col;
	CPALLS_RegressionScenario regScenario= new CPALLS_RegressionScenario();
	
	@BeforeSuite
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://trends.tangosoftware.com");
	}
		
	@BeforeClass
	public void login(){
		driver.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_BrownsvilleISD");
		driver.findElement(By.id("password")).sendKeys("Shivu123");
		driver.findElement(By.id("loginbutton")).click();
	}
	
	@Test
	public void getExcel() throws BiffException, IOException, InterruptedException{
		Workbook wbook = Workbook.getWorkbook(new File(sheetPath));
		Sheet sht = wbook.getSheet(0);
		row = sht.getRows();
		col = sht.getColumns();
		for (int i=2;i<7; i++){
			String UserType = sht.getCell(0, i).getContents();
			String Email = sht.getCell(1, i).getContents();
			String studentID = sht.getCell(3, i).getContents();			
			System.out.println(i+"----------"+UserType +" "+Email+"-----------");
			
			regScenario.staffNavigation(driver, Email);
			regScenario.assessmentFilter(driver);
			regScenario.verify_GreenArrow_Click(driver, studentID);	
			regScenario.verify_HotLink_Fun(driver, studentID);
			regScenario.verify_FilterOptions(driver);
			regScenario.verify_HistorySubTabs(driver, studentID);

			System.out.println("Ends-----------------------------------------");
	        driver.findElement(By.id("menubutton")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//*[@id='ui-id-1']/a")).click();
		}
	}
	
	@AfterClass
	public void quit(){
		driver.quit();
	
	}
	

}
