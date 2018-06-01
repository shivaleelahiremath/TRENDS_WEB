package Maintenance_STAAR_Document;

import java.io.File;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class STAAR_MaintDoc_SANITY_TEST {
	private static Logger debugLog=Logger.getLogger("debugLog");
	private static Logger reportsLog=Logger.getLogger("reportsLog");
	String[][] tabArray = null;
	int rowCount, colCount;
	String sheetPath = "test/Resources/Data/Assessment_INFO.xls";	
	WebDriver login;

	@BeforeClass
	public void setUp() throws InterruptedException{
		//opening Chrome browser..
	//	System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		System.setProperty("webdriver.chrome.driver", "/Users/shivaleelah/Downloads/chromedriver");
		login = new ChromeDriver();				
		//Launching the application..
		login.get("http://trends.tangosoftware.com");
		System.out.println("Launched Trends web link..");
		reportsLog.info("Trends Application launched successfully..");
	}

	//Shivaleela@TX_BrownsvilleISD, Shivaleela@TX_ComalISD, TX_HarlingenCISD, TX_RioGrandeCityCISD
	@Test
	public void Login(){
		//Login functionality..
		reportsLog.info("Testing STAAR documents for district RioGrandeCityCISD production..");
		login.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_Tangosoftware");
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
		//System.out.println("erow: " + rowCount);
		//System.out.println("ecol: " + colCount);	
		for(int i= 1 ;i<sht.getRows(); i++){
			String DocTitle = sht.getCell(0, i).getContents();
			VerifyProgramFunInfo(DocTitle);
		}		
	}

	public void VerifyProgramFunInfo(String DocTitle) throws InterruptedException{

		//Selecting STAAR tab on assessment module..
		login.findElement(By.xpath("html/body/header/bottom/toolbar/left/div/label[2]/span")).click();  
        Thread.sleep(5000);

        WebElement element = login.findElement(By.xpath(".//*[@id='Title']/div/input"));
		element.sendKeys(DocTitle);   
		element.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		//Selecting document....
		List mcanswers = login.findElements(By.className("number"));  
		System.out.println("total documents: " +mcanswers.size());
		int SelectDocu = mcanswers.size()-1;	  
		String RecCount = login.findElement(By.className("reccount")).getText();			   
		if(RecCount.equalsIgnoreCase("0 Records")){
			reportsLog.info("Document not found: "+DocTitle);
			System.out.println("Document not found: "+DocTitle);
			//element.clear();
			//Thread.sleep(6000);
		}else{	 
			Thread.sleep(5000);
		    login.findElement(By.xpath("html/body/content/div[1]/div/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img")).click();
			Thread.sleep(5000);
			

//			List<WebElement> Assessment= login.findElements(By.className("go"));
//			WebElement AssessmentClick=(WebElement) Assessment.get(1); 
//			AssessmentClick.click(); 
//			System.out.println("Selected "+DocTitle+" document..");
//			Thread.sleep(2000); 

			//SE Mastery tab..
			login.findElement(By.xpath("html/body/header/top/tabs/a[2]/tab")).click();
		//	System.out.println("selected SE Mastery tab..");
			//Get SE mastery tab records..
			String SECount1 = login.findElement(By.className("reccount")).getText();
			int SECount = Integer.parseInt(SECount1.replace(" Records", ""));
			if(SECount == 0){
				reportsLog.info("No data found on SE Mastery tab..");
			}
//		    else{
//				System.out.println("SE Mastery total records on SE Mastery tab:  "+SECount);  
//			}

			//RC Mastery tab...
			login.findElement(By.xpath("html/body/header/top/tabs/a[3]/tab")).click();
		//	System.out.println("selected RC Mastery tab..");
			//Get RC mastery tab records..
			String RCCount1 = login.findElement(By.className("reccount")).getText();
			int RCCount = Integer.parseInt(RCCount1.replace(" Records", ""));
			if(RCCount == 0){
				reportsLog.info("No data found on RC Mastery tab..");
			}
//			else{
//				System.out.println("RC Mastery total records on RC Mastery tab:  "+RCCount);  	
//			}
			
			//Item Analysis tab..
			login.findElement(By.xpath("html/body/header/top/tabs/a[4]/tab")).click();
		//	System.out.println("selected Item analysis tab..");
			String ItemCount1 = login.findElement(By.className("reccount")).getText();
			int ItemTabCount = Integer.parseInt(ItemCount1.replace(" Records", ""));
	//		System.out.println("Item analysis tab question count:  "+ItemTabCount);
			
			//Demography tab..
			Thread.sleep(2000);
			login.findElement(By.xpath("html/body/header/top/tabs/a[5]/tab")).click();
			
			//Remidation tab details... 
			Thread.sleep(2000);
			login.findElement(By.xpath("html/body/header/top/tabs/a[6]/tab")).click();
			List<WebElement> Demo2 = login.findElements(By.cssSelector(".top.titlehead.center"));	
			//System.out.println("total question header : " +Demo2.size());
			int RemeQsCount = Demo2.size();

			// Student tab..
			login.findElement(By.xpath("html/body/header/top/tabs/a[2]/tab")).click();
		//	System.out.println("selected Student tab..");
			//List<WebElement> Demo1 = login.findElements(By.className("ui-multiselect-optgroup-label"));
			//System.out.println(Demo1.size());
			//filter option..
			login.findElement(By.id("select2-detailpicker-container")).click();
			Thread.sleep(2000); 
			List<WebElement> Demo = login.findElements(By.cssSelector(".select2-results-dept-0.select2-result.select2-result-unselectable.select2-result-with-children"));	            	
			//WebElement data = Demo.get(0);	
			//System.out.println(data.getText());

			try{    
				WebElement SEData = Demo.get(2);
				List<WebElement> SE =SEData.findElements(By.tagName("li"));
				//login.findElement(By.id("select2-drop-mask")).click();
			//	System.out.println("SE size : "+SE.size());
				int StudSECount = SE.size();
				if(StudSECount==SECount){
					reportsLog.info("SE Mastery count is matching. ");
			//		System.out.println("Expected is " +StudSECount+ " and found is "+SECount);
				}else{
					debugLog.error("SE Mastery count is not matching with the student tab filter data for document: "+DocTitle );
					debugLog.error("Expected on student filter option is " +StudSECount+ " and found on SE Mastery tab is "+SECount);
				}
			}catch(Exception e){
				reportsLog.info("SE Mastery not available");
			}

			try{
				WebElement RCData = Demo.get(3);	
				List<WebElement> RC =RCData.findElements(By.tagName("li"));
				login.findElement(By.id("select2-drop-mask")).click();
			//	System.out.println("RC size : "+RC.size());
				int StudRCCount = RC.size();
				if(StudRCCount==RCCount){
					reportsLog.info("RC Mastery count is matching. ");
			//		System.out.println("Expected is " +StudRCCount+ " and found is "+RCCount);
				}else{
					debugLog.error("RC Mastery count is not matching with the student tab filter data for document: "+DocTitle);
					debugLog.error("Expected on student filter option is " +StudRCCount+ " and found on RC Mastery tab is "+RCCount);
				}
			}catch(Exception e){
				reportsLog.info("RC Mastery not available");
//				login.findElement(By.id("select2-drop-mask")).click();
			}

			//Selecting Summary tab..
			Thread.sleep(3000);
			login.findElement(By.xpath("html/body/header/top/tabs/a[1]/tab")).click();
		//	System.out.println("Selected summary tab..");
			//Accessing Accessing values from the passing criteria table  
			List<WebElement> table= login.findElements(By.className("boxes"));		  
			WebElement table_element=table.get(0);
			String SumItems1 = table_element.findElement(By.xpath("tbody/tr[2]/td[2]")).getText();
			int SumQsCount = Integer.parseInt(SumItems1);

			//Verifying question count between summary tab and Remediaton tab..
			if(RemeQsCount > 0){
				if(SumQsCount==RemeQsCount){
					reportsLog.info("Document Total questions are matching for document: "+DocTitle);
				//	System.out.println("Expected is " +SumQsCount+ " and found is "+RemeQsCount);
				}else{
					debugLog.error("Document Total questions are not matching for document: "+DocTitle);
					debugLog.error("Expected qs count on summary tab is " +SumQsCount+ " and found on Remediation tab is "+RemeQsCount);
				}
			}else{
				debugLog.error("No data found on Remedation tab for document: "+DocTitle);
			}
			
			if(SumQsCount==ItemTabCount){
				reportsLog.info("question count is matching b/w Item analysis and summary tab for document: "+DocTitle);
			//	System.out.println("Expected is " +SumQsCount+ " and found is "+ItemTabCount);
			}else{
				debugLog.error("question count is not matching b/w Item analysis and summary tab for document: "+DocTitle);
				debugLog.error("Expected qs count on summary tab is " +SumQsCount+ " and found on Item analysis tab is "+ItemTabCount);
			}
		}
		System.out.println("*****************tested***********************");
		
		login.findElement(By.id("menubutton")).click();
		Thread.sleep(2000);
		login.findElement(By.id("ui-id-1")).click();
		Thread.sleep(2000);
	}
}
