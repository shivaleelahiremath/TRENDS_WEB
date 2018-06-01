package Script;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Kento_Main_SystemTest {
	public static Logger debugLog = Logger.getLogger("KENTRO_ASMT");
	public static Logger reportsLog = Logger.getLogger("reportsLog");
	WebDriver driver;
	String sheetPath = "test/Resources/Data/5th_March_documentlist.xls";
	int rowCount, colCount;
	String[][] tabArray = null;
	Kentro_AsmtTab_Data asmtTab = new Kentro_AsmtTab_Data();
	Kentro_StudTab_Data studTab = new Kentro_StudTab_Data();
	Kentro_SumTab_Data sumTab = new Kentro_SumTab_Data();
	Kentro_DemoTab_Data demoTab = new Kentro_DemoTab_Data();
	Kento_Validate_AsmtSum asmtSumValidate = new Kento_Validate_AsmtSum();
	Kentro_Validation_StudSum studSumValidate = new Kentro_Validation_StudSum();
	Kentro_Validate_SumDemo demoSumValidate = new Kentro_Validate_SumDemo();
	
	@BeforeSuite
	public void setUp(){
		//setting property to open the chrome browser..
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		driver = new ChromeDriver();
		//to open url
		driver.get("http://trends.tangosoftware.com");		
	}
	
	@BeforeTest
	public void login(){
		//sending login parameters..
		driver.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_RioGrandeCityCISDTest08");
		driver.findElement(By.id("password")).sendKeys("Shivu123");
		driver.findElement(By.id("loginbutton")).click();		
	}
	
	@Test
	public void getExcelData() throws Exception{
		Workbook wbook= Workbook.getWorkbook(new File(sheetPath));
		Sheet sht = wbook.getSheet(0);
		rowCount = sht.getRows();
		colCount = sht.getColumns();
		tabArray = new String[rowCount][colCount];
		for(int i=2; i<sht.getRows(); i++){
			String DocTitle = sht.getCell(0, i).getContents();
			String AsmtCode = sht.getCell(1, i).getContents();			
			System.out.println(i+"----------"+DocTitle+"  "+AsmtCode+"-----------");
			debugLog.error(i+"----------"+DocTitle+"  "+AsmtCode+"-----------");
            asmtTab.filter_AsmtTab(DocTitle,AsmtCode,driver);
			asmtTab.populate_AsmtTabData(driver);
			studTab.populate_studentTabData(driver);
			demoTab.populate_DemotabData(driver);
			sumTab.populate_SummaryTabData(driver);	
			
			//comparing data between summary and assessment tab..
			asmtSumValidate.validateKentroPassingCriteria(asmtTab.otherTabValue, sumTab.sumValues, asmtTab.otherTab);
			asmtSumValidate.validateKentroPercentageCriteria(asmtTab.otherTabPerc, sumTab.sumPerc, asmtTab.otherTab);
			asmtSumValidate.validateTargetStudent(asmtTab.AsmttotalTargetstud, sumTab.SumTotalTargetStud);
			asmtSumValidate.validateTakenStudent(asmtTab.AsmttotalTaken, sumTab.SumTakenStudent);
			asmtSumValidate.validateNotTakenStudent(asmtTab.AsmtnotTaken, sumTab.SumNotTakenStudent);
			asmtSumValidate.validatePassFailCriteria(asmtTab.AsmtPassTally, sumTab.SumPassTally, asmtTab.AsmtFailTally, sumTab.SumFailTally);
			asmtSumValidate.validatePassFailPerce(asmtTab.AsmtPassPerc, sumTab.SumPassPerc, asmtTab.AsmtFailPerc, sumTab.SumFailPerc);
		
			//comparing data between student, assessment and summary tab tab..
			studSumValidate.validateTargetStudent1(studTab.StudTargetStudent, asmtTab.AsmttotalTargetstud);
//			studSumValidate.validateFilterTaskData(studTab.StudPF_Tasks, studTab.StudPF_StudCount, sumTab.SumFilter_Task, sumTab.Sum_TaskCount);
		
			//comparing data between summary and demographic tab..
			demoSumValidate.validateProgramFundingValue(demoTab.DemographyPF_Task, demoTab.rowValue1, sumTab.SumPF_Tasks, sumTab.SumPF_val);
			demoSumValidate.validateProgramFundingTakenStud(demoTab.DemographyPF_Task, demoTab.DemoPF_Taken, sumTab.SumPF_Tasks, sumTab.SumPF_Taken);
			demoSumValidate.validateProgramFundingNotTakenStud(demoTab.DemographyPF_Task, demoTab.DemoPF_NotTaken, sumTab.SumPF_Tasks, sumTab.Sum_NotTaken);
	
			demoSumValidate.validateGender(demoTab.GenderOption, demoTab.GenderRowValue1, sumTab.SumGender, sumTab.SumGender_val);
			demoSumValidate.validateTakenGender(demoTab.GenderOption, demoTab.DemoGen_Taken, sumTab.SumGender, sumTab.SumGen_Taken);
			demoSumValidate.validateNotTakenGender(demoTab.GenderOption, demoTab.DemoGen_NotTaken, sumTab.SumGender, sumTab.SumGen_NotTaken);

			demoSumValidate.validateEthnicity(demoTab.EthnicityOption, demoTab.EthnicityRowValue1, sumTab.SumEthnicity, sumTab.SumEthnicity_val);
			demoSumValidate.validateEthnicityTakenStud(demoTab.EthnicityOption, demoTab.DemoEth_Taken, sumTab.SumEthnicity, sumTab.SumEthTaken);
			demoSumValidate.validateEthnicityNotTakenStud(demoTab.EthnicityOption, demoTab.DemoEth_NotTaken, sumTab.SumEthnicity, sumTab.SumEthNotTaken);
			System.out.println("Ends........");
			debugLog.error("Ends.........");
			
			driver.findElement(By.id("menubutton")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("ui-id-1")).click();
			Thread.sleep(2000);
		}
		
	}

}
