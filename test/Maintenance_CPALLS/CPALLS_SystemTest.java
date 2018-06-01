package Maintenance_CPALLS;


import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CPALLS_SystemTest {
	public static Logger debugLog=Logger.getLogger("CPALLS_WEB");
	public static Logger reportsLog=Logger.getLogger("reportsLog");
	String sheetPath = "test/Resources/Data/CPALLS_Assessments.xls";
	int rowCount, colCount;
	
	AsmtTab_Data AsmtTab = new AsmtTab_Data();	
	Populate_StudTab_Data StudTabData = new Populate_StudTab_Data();
	Populate_PATab_Data PATabData = new Populate_PATab_Data();
	Populate_MathScienceTab_Data MathScienceTabData = new Populate_MathScienceTab_Data();
	Validate_AsmtStud validate_asmtstud = new Validate_AsmtStud();
	Validate_AsmtPA validate_asmtPA = new Validate_AsmtPA();
	Validate_AsmtMath validate_asmtMath = new Validate_AsmtMath();
	Validate_AsmtScience validate_asmtScience = new Validate_AsmtScience();
	Validate_Stud_PA_Math_ScienceTab_Data validate_Stud_PA_Math_Science = new Validate_Stud_PA_Math_ScienceTab_Data();
	WebDriver driver; 
	
	@BeforeSuite
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "/Users/shivaleelah/Downloads/chromedriver");
		driver = new ChromeDriver();
//		driver.manage().window().maximize();
		//open url..
		driver.get("http://trends.tangosoftware.com");
	}
	
	@BeforeClass
	public void login(){
		//sending login parameters..
		driver.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_LosFresnosCISD");
		driver.findElement(By.id("password")).sendKeys("Shivu123");
		driver.findElement(By.id("loginbutton")).click();
	}
	
	@Test
	public void getExcelData() throws InterruptedException, BiffException, IOException{
		
		Workbook wbook= Workbook.getWorkbook(new File(sheetPath));;
		Sheet sht = wbook.getSheet(0);
		rowCount = sht.getRows();
		colCount = sht.getColumns();
		for(int i=1; i<sht.getRows(); i++){
			String DocTitle = sht.getCell(1, i).getContents();
			System.out.println(i+"----------"+DocTitle+"-----------");
			debugLog.error(i+"----------"+DocTitle+"-----------");
		
			//Filtering and populating MAP and NMA data on Assessment tab..
			AsmtTab.filter_Asmttab(DocTitle ,driver);	
			AsmtTab.populate_AsmtTabData(driver);
		
			//Populating MAP and NMA data from Student, PA, Math and Science tab..
			StudTabData.populate_StudData(driver);
			PATabData.populate_PAData(driver);
			MathScienceTabData.populate_MathData(driver);
			MathScienceTabData.populate_ScienceData(driver);	
		
			//Verifying MAP and NMA data between Assessment and student tab..
			validate_asmtstud.verify_TargetStudCount(AsmtTab.totalTaken, StudTabData.Stud_targetcount);
			validate_asmtstud.verify_PhonoAwareness(AsmtTab.readPhonoAwareData, StudTabData.readStudPhonoAwareData);
			validate_asmtstud.verify_RapidLetterNaming(AsmtTab.readRapidLetterData, StudTabData.readStudRapidLetterData);
			validate_asmtstud.verify_RapidVocabNaming(AsmtTab.readRapidVocabNamingData, StudTabData.readStudRapidVocabNamingData);
			validate_asmtstud.verify_MathOverall(AsmtTab.readMathmaticsData, StudTabData.readStudMathmaticsData);
			validate_asmtstud.vereify_ScienceOverall(AsmtTab.readScienceData, StudTabData.readStudScienceData);

			//Verifying MAP and NMA data between Assessment and PA tab..
			validate_asmtPA.verify_PhonoAwareness(AsmtTab.readPhonoAwareData, PATabData.readPAPhonoAwareData);
			validate_asmtPA.verify_RapidLetterNaming(AsmtTab.readRapidLetterData, PATabData.readPARapidLetterData);
			validate_asmtPA.verify_RapidVocabNaming(AsmtTab.readRapidVocabNamingData, PATabData.readPARapidVocabNamingData);

			//Verifying MAP and NMA data between assessment and Math tab..
			validate_asmtMath.verify_MathOverall(AsmtTab.readMathmaticsData, MathScienceTabData.readMathTabData);
			validate_asmtScience.vereify_ScienceOverall(AsmtTab.readScienceData, MathScienceTabData.readScienceTabData);

			//Verifying MAP and NMA data between student, PA, Math and Science tabs..
			validate_Stud_PA_Math_Science.verify_PhonoAwareness(StudTabData.readStudPhonoAwareData, PATabData.readPAPhonoAwareData);
			validate_Stud_PA_Math_Science.verify_RapidLetterNaming(StudTabData.readStudRapidLetterData, PATabData.readPARapidLetterData);
			validate_Stud_PA_Math_Science.verify_RapidVocabNaming(StudTabData.readStudRapidVocabNamingData, PATabData.readPARapidVocabNamingData);
			validate_Stud_PA_Math_Science.verify_MathOverall(StudTabData.readStudMathmaticsData, MathScienceTabData.readMathTabData );
			validate_Stud_PA_Math_Science.vereify_ScienceOverall(StudTabData.readStudScienceData, MathScienceTabData.readScienceTabData );
			
			System.out.println("Ends.......");
			debugLog.error("Ends........");
			debugLog.error("*******************************************************************************************");
			driver.findElement(By.id("menubutton")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("ui-id-1")).click();
			Thread.sleep(2000);
		}
	}
	
//	@AfterSuite
//	public void tearDown(){
//		driver.quit();
//	}
	
}
