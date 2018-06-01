package Script;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class SAccount_SANITY_TS_BISDTest03 {
	private static Logger debugLog=Logger.getLogger("debugLog");
	private static Logger reportsLog=Logger.getLogger("reportsLog");
	String[][] tabArray = null;
	int rowCount, colCount;
	String sheetPath = "test/Resources/Data/5th_March_documentlist.xls";	
	WebDriver login;

	@BeforeClass
	public void setUp() throws InterruptedException{
		//opening Chrome browser..
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		login = new ChromeDriver();				
		//Launching the application.
		//login.get("http://trends.tangosoftware.com");
    	login.get("http://tango-central-dev.elasticbeanstalk.com");
		System.out.println("Launched Trends web link..");
		reportsLog.info("Trends Application launched successfully..");
	}

	//Shivaleela@TX_BrownsvilleISD, Shivaleela@TX_ComalISD, TX_HarlingenCISD, TX_RioGrandeCityCISD
	@Test
	public void Login(){
		//Login functionality..
		reportsLog.info("Testing STAAR documents for district RioGrandeCityCISD production..");
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
		//System.out.println("erow: " + rowCount);
		//System.out.println("ecol: " + colCount);	
		for(int i= 122;i<sht.getRows(); i++){
			String DocTitle = sht.getCell(0, i).getContents();
			String AdminCode = sht.getCell(1, i).getContents();
			String AdminSeries = sht.getCell(2, i).getContents();
			String AdminMode = sht.getCell(3, i).getContents();
			String Language =  sht.getCell(4, i).getContents();
			String Target = sht.getCell(5, i).getContents();
			String Subject = sht.getCell(6, i).getContents();
			//	System.out.println("data - > "+DocTitle+"   "+AdminMode+"   "+AdminSeries);
			debugLog.error("------------"+DocTitle+"  "+AdminCode+"  "+AdminSeries+"  "+AdminMode+"  "+Language+"  "+Target+"  "+Subject+"-------------");
			Sanity_Check(DocTitle, AdminCode, AdminSeries, AdminMode, Language, Target, Subject);
			debugLog.error("Ends..."+DocTitle);
			debugLog.error("************************************************************************************");
		}		
	}

	public void Sanity_Check(String DocTitle, String AdminCode, String AdminSeries, String AdminMode, String Language, String Target, String Subject) throws InterruptedException{
		login.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//Selecting STAAR tab on assessment module..
		login.findElement(By.id("menubutton")).click();
//		Thread.sleep(1000);
		login.findElement(By.id("ui-id-9")).click();
//		Thread.sleep(3000);
		login.findElement(By.xpath("html/body/content/div/div[2]/div/div/div/div[1]/div/table/thead/tr/th[7]/filter/div/input")).click();
	    WebElement element = login.findElement(By.xpath("html/body/content/div/div[2]/div/div/div/div[1]/div/table/thead/tr/th[7]/filter/div/input"));
		element.sendKeys(DocTitle);
		element.sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		
		List<WebElement> Demo1 = login.findElements(By.id("STAARAssessments"));	            	
		WebElement DemoData = Demo1.get(2);	       
  //    System.out.println("assessment data: "+DemoData.getText());
		WebElement ACode = DemoData.findElement(By.xpath("tr/th[4]/filter/div"));
		WebElement ACode1= ACode.findElement(By.tagName("input"));
		ACode1.clear();
		ACode1.sendKeys(AdminCode);
		ACode1.sendKeys(Keys.TAB);
		Thread.sleep(2000);		
		
//		WebDriverWait wait = new WebDriverWait(login, 300 /*timeout in seconds*/);
//      if(wait.until(popup.isDisplayed()) == null)
		try{
		WebElement popup = login.findElement(By.className("ui-tooltip-content"));
		if(popup.isDisplayed())
        System.out.println("Admin code "+AdminCode+" didn't match");
		}catch (Exception e) {
	        System.out.println("Admin code "+AdminCode+" match");
			// TODO: handle exception
		}

		WebElement ASer = DemoData.findElement(By.xpath("tr/th[5]/filter/div"));
		WebElement ASer1= ASer.findElement(By.tagName("input"));
		ASer1.clear();
		ASer1.sendKeys(AdminSeries);
		ASer1.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		WebElement popup1 = login.findElement(By.className("ui-tooltip-content"));
		if(popup1.isDisplayed())
	    System.out.println("Admin Series "+AdminSeries+" didn't match");
				
		WebElement AMode = DemoData.findElement(By.xpath("tr/th[6]/filter/div"));
		WebElement AMode1= AMode.findElement(By.tagName("input"));
		AMode1.clear();
		AMode1.sendKeys(AdminMode);
		AMode1.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		WebElement popup2 = login.findElement(By.className("ui-tooltip-content"));
		if(popup2.isDisplayed())
	    System.out.println("Admin Mode "+AdminMode+" didn't match");
		
		WebElement Lang = DemoData.findElement(By.xpath("tr/th[8]/filter/div"));
		WebElement Lang1= Lang.findElement(By.tagName("input"));
		Lang1.clear();
		Lang1.sendKeys(Language);
		Lang1.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		WebElement popup3 = login.findElement(By.className("ui-tooltip-content"));
		if(popup3.isDisplayed())
	    System.out.println("Language "+Language+" didn't match");
		
		WebElement Tar = DemoData.findElement(By.xpath("tr/th[9]/filter/div"));
		WebElement Target1= Tar.findElement(By.tagName("input"));
		Target1.clear();
		Target1.sendKeys(Target);
		Target1.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		WebElement popup4 = login.findElement(By.className("ui-tooltip-content"));
		if(popup4.isDisplayed())
	    System.out.println("Target "+Target+" didn't match");
		
		WebElement Sub = DemoData.findElement(By.xpath("tr/th[11]/filter/div"));
		WebElement Sub1= Sub.findElement(By.tagName("input"));
		Sub1.clear();
		Sub1.sendKeys(Subject);
		Sub1.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		WebElement popup5 = login.findElement(By.className("ui-tooltip-content"));
		if(popup5.isDisplayed())
	    System.out.println("Subject "+Subject+" didn't match");
		
//		login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/main/right/sortnum")).click();
//		Thread.sleep(2000);

		//Selecting document..
		List mcanswers = login.findElements(By.className("number"));  
		//System.out.println("total documents: " +mcanswers.size());
		int SelectDocu = mcanswers.size()-2;	  
		String RecCount = login.findElement(By.className("reccount")).getText();			   
		if(RecCount.equalsIgnoreCase("0 Records")){
			reportsLog.info("Document not found: "+DocTitle);
			System.out.println("Document not found: "+DocTitle);
			//element.clear();
			//Thread.sleep(6000);
		}else{	    	 	    	         	
			List<WebElement> Assessment= login.findElements(By.className("go"));
			WebElement AssessmentClick=(WebElement) Assessment.get(SelectDocu); 
			AssessmentClick.click();
			System.out.println("Selected "+DocTitle+" document..");
			Thread.sleep(2000); 

			//SE Mastery tab..
			login.findElement(By.xpath("html/body/header/top/tabs/a[3]/tab")).click();
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
			login.findElement(By.xpath("html/body/header/top/tabs/a[4]/tab")).click();
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
			login.findElement(By.xpath("html/body/header/top/tabs/a[5]/tab")).click();
		//	System.out.println("selected Item analysis tab..");
			String ItemCount1 = login.findElement(By.className("reccount")).getText();
			int ItemTabCount = Integer.parseInt(ItemCount1.replace(" Records", ""));
	//		System.out.println("Item analysis tab question count:  "+ItemTabCount);
			
			//Remidation tab details... 
			Thread.sleep(2000);
			login.findElement(By.xpath("html/body/header/top/tabs/a[7]/tab")).click();
			List<WebElement> Demo2 = login.findElements(By.cssSelector(".top.titlehead.center"));	
			//System.out.println("total question header : " +Demo2.size());
			int RemeQsCount = Demo2.size();

			// Student tab..
			login.findElement(By.xpath("html/body/header/top/tabs/a[3]/tab")).click();
		//	System.out.println("selected Student tab..");
			//List<WebElement> Demo1 = login.findElements(By.className("ui-multiselect-optgroup-label"));
			//System.out.println(Demo1.size());
			//filter option..
			login.findElement(By.id("s2id_detailpicker")).click();
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
					debugLog.error("SE Mastery : Student Filter "+StudSECount+ " != SE Mastery tab "+SECount);
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
					debugLog.error("RC Mastery :Student Filter "+StudRCCount+" != RC Mastery tab "+RCCount);
				}
			}catch(Exception e){
				reportsLog.info("RC Mastery not available");
				login.findElement(By.id("select2-drop-mask")).click();
			}

			//Selecting Summary tab..
			Thread.sleep(3000);
			login.findElement(By.xpath("html/body/header/top/tabs/a[2]/tab")).click();
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
					debugLog.error("Question Count : Summary tab "+SumQsCount+" != Remediation tab "+RemeQsCount);
				}
			}else{
				debugLog.error("No data found on Remedation tab");
			}
			
			if(SumQsCount==ItemTabCount){
				reportsLog.info("question count is matching b/w Item analysis and summary tab for document: "+DocTitle);
			//	System.out.println("Expected is " +SumQsCount+ " and found is "+ItemTabCount);
			}else{
				debugLog.error("Question Count : Summary tab "+SumQsCount+" != Item Analysis tab "+ItemTabCount);
			}
		}
		System.out.println("*****************Completed***********************");
	}
}

