package Main_MasterFilter_Test;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Maintenance_STAAR_Document.AsmtTab_Data;

public class Main_Filter_Test {
	
	public static Logger debugLog=Logger.getLogger("Main_STAAR_ASMT");
	public static Logger reportsLog=Logger.getLogger("reportsLog");

	WebDriver driver;
	
	String sheetPath = "test/Resources/Data/FrenscoTest01.xls";
	int rowCount, colCount;
	AsmtTab_Data asmtTab = new AsmtTab_Data();
	Populate_Stud_MasterFilterData studMasterFilData = new Populate_Stud_MasterFilterData();
	Populate_Sum_MasterFilterData sumMasterFilData = new Populate_Sum_MasterFilterData();
	Populate_Demo_MasterFilterData demoMasterFilData = new Populate_Demo_MasterFilterData();
	Validate_StudSumm_MasterFilter_Data validate_StudSumm_MasterFilter = new Validate_StudSumm_MasterFilter_Data();
	Validate_DemoSumm_MsterFilter_Data validate_DemoSumm_MasterFilter = new Validate_DemoSumm_MsterFilter_Data();
	
	Populate_Summ_MasterFilter sumMaster = new Populate_Summ_MasterFilter();
	Hash_Map hashMap= new Hash_Map();
	
	@BeforeSuite
	public void setup() {
		//setting property to open the chrome browser..
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		driver = new ChromeDriver();
		//to open url
		driver.get("http://trends.tangosoftware.com");
//		driver.get("http://tangocentralweb-dev.elasticbeanstalk.com");
	}

	@BeforeTest
	public void login() {
		//sending login parameters..
		driver.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_LosFresnosCISDTest01");
	    driver.findElement(By.id("password")).sendKeys("Shivu123"); 
		driver.findElement(By.id("loginbutton")).click();	
	}

	@Test
	public void getExcelData() throws Exception {
		Workbook wbook= Workbook.getWorkbook(new File(sheetPath));
		Sheet sht = wbook.getSheet(0);
		rowCount = sht.getRows();
		colCount = sht.getColumns();
		for(int i=1; i<2; i++) {
			String DocTitle = sht.getCell(1, i).getContents();
			String AdminCode = sht.getCell(2, i).getContents();
			String AdminSeries = sht.getCell(3, i).getContents();
			String AdminMode = sht.getCell(4, i).getContents();
			String Language =  sht.getCell(5, i).getContents();
			String Target = sht.getCell(6, i).getContents();
			String Subject = sht.getCell(7, i).getContents();
			debugLog.error(i+"------------"+DocTitle+"  "+AdminCode+"  "+AdminSeries+"  "+AdminMode+"  "+Language+"  "+Target+"  "+Subject+"-------------");
			System.out.println(i+"------------"+DocTitle+"  "+AdminCode+"  "+AdminSeries+"  "+AdminMode+"  "+Language+"  "+Target+"  "+Subject+"-------------");
			asmtTab.Modified_filter_AsmtTab(DocTitle, AdminCode, AdminSeries, AdminMode, Language, Target, Subject, driver);
//			asmtTab.filter_AsmtTab(DocTitle, AdminCode, AdminSeries, AdminMode, Language, Target, Subject, driver);
			if(asmtTab.RecCount.equalsIgnoreCase("0 Records")) {
				asmtTab.missingDocument(driver);
			}else {
				System.out.println("Document found..");
				//Changed code.. populating master filter data on demography tab..
				studMasterFilData.populate_Stud_MasterFilterData(driver);
				demoMasterFilData.populate_Demo_MasterFilterData(driver);
				
//				hashMap.populate_Demo_MasterFilterData(driver);
//				sumMasterFilData.populate_Sum_MasterFilterData(driver);
				sumMaster.populate_Data(driver);
 
//				hashMap.print(hashMap.mapTaken, hashMap.mapNotTaken, hashMap.mapRowValue);
//				Validating student target count between student and summary tab..
//				validate_StudSumm_MasterFilter.validate_StudSumm_MasterFilter(studMasterFilData.StudFilterTargetCount, sumMasterFilData.SumFilterStud_TargetCount, sumMasterFilData.Sum_TaskCount, studMasterFilData.StudFilterTasks);
				validate_DemoSumm_MasterFilter.validate_DemoSumm_MasterFilter(sumMaster.SumMasterFilterTasks, demoMasterFilData.DemoFilterTasks, sumMaster.SumOption_Tasks, demoMasterFilData.Demography_Task, sumMaster.SumOption_Taken, demoMasterFilData.Demo_Taken);
//				validate_DemoSumm_MasterFilter.validate_DemoSumm_MasterFilter();
			}
			
			System.out.println("Ends.......");
			debugLog.error("Ends........");
			debugLog.error("************************************************************************************");

			driver.findElement(By.id("menubutton")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("ui-id-1")).click();
			Thread.sleep(2000);			
		}		
	}
	
//	@AfterSuite
//	public void teardown() {
//		driver.quit();
//	}

}
