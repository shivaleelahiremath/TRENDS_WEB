package TPRI_TJL;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TPRITJL_UserLogin_RegressionTest {
	
	WebDriver driver;
	String sheetPath = "test/Resources/Data/RGCISD_Users.xls";
	int rowCount, colCount;
	TPRITJL_RegressionScenario FunTest= new TPRITJL_RegressionScenario();

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
		for(int i=6; i<12; i++){
			String userType = sht.getCell(0, i).getContents();
			String Email = sht.getCell(1, i).getContents();
			String studentid = sht.getCell(2, i).getContents();
			
			System.out.println("----------------"+userType+":"+ Email +"-------------------------");
			driver.findElement(By.id("loginEmail")).sendKeys(Email);
			driver.findElement(By.id("password")).sendKeys("Shivu123");
			driver.findElement(By.id("loginbutton")).click();
			Thread.sleep(3000);
			
			if(userType.equalsIgnoreCase("Counsellor user:")){
				FunTest.CounsellorLogin(driver);  
			}
				
	    	FunTest.assessmentFilter(driver);
	    	FunTest.studentTab(driver, studentid);
	    	FunTest.SCRTab(driver, studentid);
	    	FunTest.PATab(driver, studentid);
	    	FunTest.GKTab(driver, studentid);
	    	FunTest.WRTab(driver, studentid);
	    	FunTest.READTab(driver, studentid);
	        FunTest.COMPTab(driver, studentid);
	        FunTest.SummTab(driver);
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
