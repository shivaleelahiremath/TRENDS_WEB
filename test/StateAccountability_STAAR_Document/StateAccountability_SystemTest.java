package StateAccountability_STAAR_Document;

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

public class StateAccountability_SystemTest {
	public static Logger debugLog=Logger.getLogger("SA_STAAR_ASMT");
	public static Logger reportsLog=Logger.getLogger("reportsLog");
	
	WebDriver driver;
	String sheetPath = "test/Resources/Data/Assessment_INFO.xls";
	int rowCount, colCount;
	String[][] tabArray = null;
	AsmtTab_Data asmtTab = new AsmtTab_Data();
	StudTab_Data studTab = new StudTab_Data();
	DemoTab_Data demoTab = new DemoTab_Data();
	SumTab_Data sumTab = new SumTab_Data();
	Validate_AsmtSum asmtSumValidate = new Validate_AsmtSum();
	Validate_StudSum studSumValidate = new Validate_StudSum();
	Validate_SumDemo demoSumValidate = new Validate_SumDemo();
	
	@BeforeSuite
	public void setup(){
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
		
	//8147884428
	@Test
	public void getExcelData() throws Exception{
		Workbook wbook= Workbook.getWorkbook(new File(sheetPath));
		Sheet sht = wbook.getSheet(0);
		rowCount = sht.getRows();
		colCount = sht.getColumns();
		tabArray = new String[rowCount][colCount];
		for(int i=2; i<sht.getRows(); i++){
			String DocTitle = sht.getCell(1, i).getContents();
			String AdminCode = sht.getCell(2, i).getContents();
			String AdminSeries = sht.getCell(3, i).getContents();
			String AdminMode = sht.getCell(4, i).getContents();
			String Language =  sht.getCell(5, i).getContents();
			String Target = sht.getCell(6, i).getContents();
			String Subject = sht.getCell(7, i).getContents();
			debugLog.error(i+"------------"+DocTitle+"  "+AdminCode+"  "+AdminSeries+"  "+AdminMode+"  "+Language+"  "+Target+"  "+Subject+"-------------");
	    	System.out.println(i+"------------"+DocTitle+"  "+AdminCode+"  "+AdminSeries+"  "+AdminMode+"  "+Language+"  "+Target+"  "+Subject+"-------------");
		
	    	asmtTab.filter_AsmtTab(DocTitle, AdminCode, AdminSeries, AdminMode, Language, Target, Subject, driver);
	    	if(asmtTab.RecCount.equalsIgnoreCase("0 Records")){
	    		asmtTab.missingDocument(driver);
			}else{	
				System.out.println("Document found..");
				asmtTab.populate_AsmtTabData(driver);
				
				studTab.populate_studentTabData(driver);
				demoTab.populate_DemotabData(driver);
				sumTab.populate_SummaryTabData(driver);
				
				//comparing data between summary and assessment tab..
				asmtSumValidate.validatePassingCriteria(asmtTab.otherTabValue, sumTab.sumValues, asmtTab.otherTab);
				asmtSumValidate.validatePercentageCriteria(asmtTab.otherTabPerc, sumTab.sumPerc, asmtTab.otherTab);
				asmtSumValidate.validateTargetStudent(asmtTab.AsmttotalTargetstud, sumTab.SumTotalTargetStud);
				asmtSumValidate.validateTakenStudent(asmtTab.AsmttotalTaken, sumTab.SumTakenStudent);
				asmtSumValidate.validateNotTakenStudent(asmtTab.AsmtnotTaken, sumTab.SumNotTakenStudent);
				asmtSumValidate.validatePassFailCriteria(asmtTab.AsmtPassTally, sumTab.SumPassTally, asmtTab.AsmtFailTally, sumTab.SumFailTally);
				asmtSumValidate.validatePassFailPerce(asmtTab.AsmtPassPerc, sumTab.SumPassPerc, asmtTab.AsmtFailPerc, sumTab.SumFailPerc);
				
				//comparing data between student, assessment and summary tab tab..
				studSumValidate.validateTargetStudent1(studTab.StudTotalTargetStud, asmtTab.AsmttotalTargetstud);
				studSumValidate.validateFilterTaskData(studTab.StudPF_Tasks, studTab.StudPF_StudCount, sumTab.SumFilter_Task, sumTab.Sum_TaskCount);
							
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

			}
			System.out.println("Ends.......");
			debugLog.error("Ends........");
			debugLog.error("************************************************************************************");
			
	 		}		
	}

}
