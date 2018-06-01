package Script;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Latest_STAAR_Main_SystemTest_TS {
	private static Logger debugLog=Logger.getLogger("STAAR_Asmt");
	private static Logger reportsLog=Logger.getLogger("reportsLog");
	String[][] tabArray = null;
	int rowCount, colCount;
	String sheetPath = "test/Resources/Data/5th_March_documentlist.xls";	
    WebDriver login;

	@BeforeClass
	public void setUp() throws InterruptedException {
		//opening Chrome browser..
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		login = new ChromeDriver();				
		//Launching the application..
		//login.get("http://ec2-184-73-167-15.compute-1.amazonaws.com/trendsReloaded");
		login.get("http://trends.tangosoftware.com");
 //   	login.get("http://tango-central-dev.elasticbeanstalk.com");
		System.out.println("Launched Trends web link..");
		reportsLog.info("Trends Application launched successfully..");
	}	

	//Shivaleela@TX_BrownsvilleISD, Shivaleela@TX_ComalISD, TX_HarlingenCISD, TX_RioGrandeCityCISD
	@Test
	public void Login(){
		//Login functionality..
		reportsLog.info("Testing STAAR documents for district BrownsvilleISD production..");
		login.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_BrownsvilleISDTest03");
		login.findElement(By.id("password")).sendKeys("Shivu123");
		login.findElement(By.id("loginbutton")).click();
		reportsLog.info("Login successfully..");
	}

	@Test
	public void getExcelData() throws Exception {
		Workbook workbk = Workbook.getWorkbook(new File(sheetPath));
		Sheet sht = workbk.getSheet("Sheet1");
		rowCount = sht.getRows(); 
		colCount = sht.getColumns();
		tabArray = new String[rowCount][colCount];
		System.out.println("erow: " + rowCount);
		System.out.println("ecol: " + colCount);
		for(int i=1; i<sht.getRows(); i++){
			String AdminCode = sht.getCell(0, i).getContents();
			String AdminSeries = sht.getCell(1, i).getContents();
			String AdminMode = sht.getCell(2, i).getContents();
			String DocTitle = sht.getCell(3, i).getContents();
			String Language =  sht.getCell(4, i).getContents();
			String Target = sht.getCell(5, i).getContents();
			String Subject = sht.getCell(6, i).getContents();
			debugLog.error(i+"------------"+DocTitle+"  "+AdminCode+"  "+AdminSeries+"  "+AdminMode+"  "+Language+"  "+Target+"  "+Subject+"-------------");
			
	//		System.out.println("data - > "+DocTitle+"   "+AdminMode+"   "+AdminSeries);			
			//Modified code
			Latest_STAAR_Main_DATA obj = new Latest_STAAR_Main_DATA();
			obj.DocumentVerify(DocTitle, AdminCode, AdminSeries, AdminMode, Language, Target, Subject, login);
	//		obj.DemoGraphySummaryVerify(login);
			
			debugLog.error("Ends..."+DocTitle);
			debugLog.error("************************************************************************************");
			
//			STAAR_SAccontData_TS newObj = new STAAR_SAccontData_TS();
//			newObj.DocumentVerify(DocTitle, AdminMode, AdminSeries, login);			
//		    newObj.StudentFilterFun(login);
//		    newObj.DemographyTabScore(DocTitle, AdminMode, AdminSeries, login);
//		    newObj.Summary_Asmt_Score(login);
//		    newObj.SummaryDemoScore(login);
//		    newObj.VerifyDocumentScoreDetails(DocTitle, AdminMode, AdminSeries);
		//	VerifyDocumentScoreDetails(DocTitle, AdminMode, AdminSeries);		
		}
	}
	
//	@Test
//	public void logout(){
//		login.findElement(By.id("gearbutton")).click();		
//	}
	
//	@AfterClass
	//	public void tearDown() throws InterruptedException{
	//		login.findElement(By.id("gearbutton")).click();
	//		Thread.sleep(2000);
	//		login.findElement(By.id("signoutbutton")).click();
	//		login.quit();
	//	}
	

}
