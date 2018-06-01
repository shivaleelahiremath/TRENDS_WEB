package TPRI_TJL;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TPRITJL_Function {
	 private static Logger debugLog=Logger.getLogger("STAAR_Asmt");
	 private static Logger reportsLog=Logger.getLogger("reportsLog");
     String sheetPath = "test/Resources/Data/Test_Document.xls";
	 WebDriver driver;	 
	 int rowCount, colCount;
	 
	 TPRI_Populate_Data populate = new TPRI_Populate_Data();
	 CompareData compareJava = new CompareData();  //create an object of type first

	@BeforeClass
	public void setUp() throws InterruptedException {
		//opening Chrome browser..
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		driver = new ChromeDriver();				
		//Launching the application..
		driver.get("http://trends.tangosoftware.com");
	//	login.get("http://devtools2.libertysol.com/TangoCentralTest");
		System.out.println("Launched Trends web link..");
	//	reportsLog.info("Trends Application launched successfully..");
	}	
	
	@Test
	public void Login() throws InterruptedException{
		//Login functionality..
		reportsLog.info("Testing STAAR documents for district BrownsvilleISD production..");
		driver.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_RiograndeCityCISD");
		driver.findElement(By.id("password")).sendKeys("Shivu123");
		driver.findElement(By.id("loginbutton")).click();
		reportsLog.info("Login successfully..");
		Thread.sleep(2000);
	}
	
	@Test
	public void getExcelData() throws Exception {
		Workbook workbk = Workbook.getWorkbook(new File(sheetPath));
		Sheet sht = workbk.getSheet("Sheet1");
		rowCount = sht.getRows(); 
		colCount = sht.getColumns();
//		System.out.println("erow: " + rowCount);
//		System.out.println("ecol: " + colCount);
		for(int i=26; i<sht.getRows(); i++){
			String Title = sht.getCell(0, i).getContents();
			String Grade = sht.getCell(1, i).getContents();
//			debugLog.error(i+"------ "+Title+" "+Grade+" --------");
			System.out.println(i+"------ "+Title+" "+Grade+" --------");
			populate.AssmentData(driver,Title,Grade);
			populate.StudentMainData(driver);
			populate.ScrTabData(driver);
			populate.PATabData(driver);
			populate.GKTabData(driver);
			populate.WRTabData(driver);
			populate.ReadTabData(driver);
			populate.CompTabData(driver);
			
			//Compare method..
			compareJava.compareTierData(populate.TierValues , populate.TierStudRec);
			compareJava.compareGroupData(populate.GroupValues, populate.GroupStudRec);
			compareJava.compareTargetStudent(populate.StudTotalTargetStud, populate.AsmtTotalTargetStud);
			compareJava.compareScrData(populate.StudentMainData, populate.ScrMainData);
			compareJava.comparePAData(populate.StudentMainData, populate.PAMainData);
			compareJava.compareGKData(populate.StudentMainData, populate.GKMainData);
			compareJava.compareWRData(populate.StudentMainData, populate.WRMainData);
			compareJava.compareReadData(populate.StudentMainData, populate.ReadMainData);
			compareJava.compareCompData(populate.StudentMainData, populate.CompMainData);
			debugLog.error("Completed.. "+Title+" "+Grade+" ------");
			System.out.println("Completed...."+Title+" "+Grade+" --------");

			Thread.sleep(1000);

			driver.findElement(By.id("menubutton"));
			Thread.sleep(1000);
			driver.findElement(By.id("ui-id-1"));
		}
	}

	
	
}
