package StateAccountability_STAAR_Document;

import java.awt.Label;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SA_ItemAnalysis {
	private static Logger debugLog=Logger.getLogger("STAAR_Asmt");
	private static Logger reportsLog=Logger.getLogger("reportsLog");
	String[][] tabArray = null;
	int rowCount, colCount;
	String sheetPath = "test/Resources/Data/5th_March_documentlist.xls";	
	WebDriver login;
    int SelectDocu;
    String StudDocTitle;
	String[][] rowValue ;


	@BeforeClass
	public void setUp() throws InterruptedException{
		//opening Chrome browser..
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		login = new ChromeDriver();				
		//Launching the application..
//		login.get("http://trends.tangosoftware.com");
		login.get("http://tango-central-dev.elasticbeanstalk.com");
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
		for(int i=1;i<sht.getRows(); i++){
			String DocTitle = sht.getCell(0, i).getContents();
			String AdminCode = sht.getCell(1, i).getContents();
			String AdminSeries = sht.getCell(2, i).getContents();
			String AdminMode = sht.getCell(3, i).getContents();
			String Language =  sht.getCell(4, i).getContents();
			String Target = sht.getCell(5, i).getContents();
			String Subject = sht.getCell(6, i).getContents();
			DocumentVerify(DocTitle, AdminCode, AdminSeries, AdminMode, Language, Target, Subject);
		}		
	}

	public void DocumentVerify(String DocTitle, String AdminCode, String AdminSeries, String AdminMode, String Language, String Target, String Subject) throws InterruptedException, RowsExceededException, WriteException{
		//Selecting STAAR tab on assessment module..
		login.findElement(By.id("menubutton")).click();
		Thread.sleep(2000);

		login.findElement(By.id("ui-id-9")).click();
		Thread.sleep(3000);	

		login.findElement(By.xpath("//*[@id='STAARAssessments']/tr/th[7]/filter/div/input")).click();
		WebElement element = login.findElement(By.xpath("//*[@id='STAARAssessments']/tr/th[7]/filter/div/input"));
		element.sendKeys(DocTitle);  
		element.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		List<WebElement> Demo = login.findElements(By.id("STAARAssessments"));	            	
		WebElement DemoData = Demo.get(2);	       
	  //System.out.println("assessment data: "+DemoData.getText());
		
		WebElement ACode = DemoData.findElement(By.xpath("tr/th[4]/filter/div"));
		WebElement ACode1= ACode.findElement(By.tagName("input"));
		ACode1.clear();
		ACode1.sendKeys(AdminCode);
		ACode1.sendKeys(Keys.ENTER);
		Thread.sleep(3000);				
		
		WebElement ASer = DemoData.findElement(By.xpath("tr/th[5]/filter/div"));
		WebElement ASer1= ASer.findElement(By.tagName("input"));
		ASer1.clear();
		ASer1.sendKeys(AdminSeries);
		ASer1.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
				
		WebElement AMode = DemoData.findElement(By.xpath("tr/th[6]/filter/div"));
		WebElement AMode1= AMode.findElement(By.tagName("input"));
		AMode1.clear();
		AMode1.sendKeys(AdminMode);
		AMode1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		WebElement Lang = DemoData.findElement(By.xpath("tr/th[8]/filter/div"));
		WebElement Lang1= Lang.findElement(By.tagName("input"));
		Lang1.clear();
		Thread.sleep(2000);

		Lang1.sendKeys(Language);
		Lang1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		WebElement Tar = DemoData.findElement(By.xpath("tr/th[9]/filter/div"));
		WebElement Target1= Tar.findElement(By.tagName("input"));
		Target1.clear();
		Target1.sendKeys(Target);
		Thread.sleep(2000);
		Target1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		WebElement Sub = DemoData.findElement(By.xpath("tr/th[11]/filter/div"));
		WebElement Sub1= Sub.findElement(By.tagName("input"));
		Sub1.clear();
		Thread.sleep(2000);

		Sub1.sendKeys(Subject);
		Thread.sleep(1000);
		Sub1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

//		login.findElement(By.xpath("//*[@id='STAARAssessments']/tr/th[7]/main/left")).click();
//		Thread.sleep(3000);

		//Selecting document..
		List<WebElement> sel = login.findElements(By.id("STAARAssessments"));	            	
		WebElement sel1 = sel.get(5);
		List mcanswers = sel1.findElements(By.className("number"));    
	//  System.out.println("total documents: " +mcanswers.size());
	    rowCount = mcanswers.size()-1;
		SelectDocu = mcanswers.size()-1;	  
		String RecCount = login.findElement(By.className("reccount")).getText();		   
		if(RecCount.equalsIgnoreCase("0 Records")){
			reportsLog.info("Document not found: "+DocTitle);
			System.out.println("Document not found: "+DocTitle);
			login.findElement(By.id("menubutton")).click();
			Thread.sleep(2000);
			login.findElement(By.id("ui-id-1")).click();
			Thread.sleep(2000);
			//element.clear();
			//Thread.sleep(5000);
		}
		else{	    	 	    	  
			List<WebElement> AssessmentList= login.findElements(By.id("STAARAssessments"));	    	   
			WebElement AsmtData=AssessmentList.get(3);
			// System.out.println("assessment data: "+AsmtData.getText());
			System.out.println("***************************Assessment Tab Data***************************");	  	
			//Accessing Accessing values from the Assessment tab
			String DocumentTitle =AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td[7]")).getText();
			System.out.println("Document Title is: " +DocumentTitle);
			Thread.sleep(2000);

			//Selecting the document..
			List<WebElement> Assessment= login.findElements(By.className("go"));
			WebElement AssessmentClick=(WebElement) Assessment.get(SelectDocu); 
			AssessmentClick.click();
			System.out.println("Selected "+DocTitle+" document..");
			Thread.sleep(3000);
			
			StudDocTitle = login.findElement(By.xpath("/html/body/header/bottom/leftbar/breadcrumbs")).getText();
			System.out.println(StudDocTitle);

		    //Clicking on Item Analysis tab..
			login.findElement(By.xpath("html/body/header/top/tabs/a[5]/tab")).click();
			System.out.println("selected item analyis tab..");
			
			Thread.sleep(2000);

			login.findElement(By.xpath("//*[@id='QuestionId']/left")).click();
			Thread.sleep(2000);
			
			//Selecting total questions... 	   
			String QuestionCount = login.findElement(By.className("reccount")).getText();
			int DocumentTotalQuestions = Integer.parseInt(QuestionCount.replace(" Records", ""));
			System.out.println("total questions : "+DocumentTotalQuestions);
			
			String DataSet[]={"QuestionId","ContentSE","ProcessSE"};
			int ROWS = DocumentTotalQuestions;
			int COLS = DataSet.length;
			String[][] rowValue = new String[ROWS][COLS];
			for(int i=0;i<DocumentTotalQuestions;i++){
				for(int j=0;j<DataSet.length;j++){
					 rowValue[i][j] = login.findElement(By.xpath("//*[@id='AccountabilityAssessmentItemAnalysis']/tr["+(i+1)+"]/td["+(j+3)+"]")).getText();
					 System.out.println((i+1)+"---->"+rowValue[i][j]);
				}						
			}		

			
/*			String[] QsID = new String[DocumentTotalQuestions];
			String[] SE = new String[DocumentTotalQuestions];
			for(int i=0;i<DocumentTotalQuestions;i++){
				QsID[i] = login.findElement(By.xpath("//*[@id='AccountabilityAssessmentItemAnalysis']/tr["+(i+1)+"]/td[3]")).getText();
				SE[i] = login.findElement(By.xpath("//*[@id='AccountabilityAssessmentItemAnalysis']/tr["+(i+1)+"]/td[4]")).getText();
				System.out.println("question ID --> "+QsID[i]+"  SE data --> "+SE[i] );
			}
*/			

		try{
			WritableWorkbook wb = Workbook.createWorkbook(new File("/Users/shivaleelah/Desktop/SA_Document_Data/"+StudDocTitle+".xls"));
			WritableSheet ws = wb.createSheet("data",1);{
				 for (rowCount =0;rowCount<DocumentTotalQuestions; rowCount++){
				    	for (colCount =0; colCount<3; colCount++){
				    		System.out.println(rowValue[rowCount][colCount]);
					    	 jxl.write.Label l = new jxl.write.Label(colCount, rowCount, rowValue[rowCount][colCount]);
				    //         jxl.write.Label label = new jxl.write.Label(0,1,se);
			                 ws.addCell(l);
			              //   wb.write();
				    	}
				 }
			}
			wb.write();
			wb.close();
		}catch (Exception e) {
			// TODO: handle exception
		      e.printStackTrace();
		}
		}
		
		System.out.println("*******************************Completed**************************************");
	}
	
}
