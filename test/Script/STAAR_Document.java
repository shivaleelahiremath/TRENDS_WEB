package Script;

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

public class STAAR_Document {
	private static Logger Log=Logger.getLogger("STAAR_Document");
	String[][] tabArray = null;
	Workbook workbk;
	Sheet sheet;
	int rowCount, colCount;
	String sheetPath = "test/Resources/Data/Assessment_INFO.xls";	
	WebDriver login;

	@BeforeClass
	public void setUp() throws InterruptedException{
		//opening Chrome browser..
				System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
				login = new ChromeDriver();				
				//Launching the application..
			//	login.get("http://ec2-184-73-167-15.compute-1.amazonaws.com/trendsReloaded");
				login.get("http://www.tangosoftware.com/trendsReloaded");
				System.out.println("Launched Trends web link..");
				Log.info("Trends Application launched successfully..");
	}
	
	@Test
	public void Login(){
		//Login functionality..
		Log.info("Testing STAAR documents for district BrownsvilleISD production..");
	    login.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_BrownsvilleISD");
	    login.findElement(By.id("password")).sendKeys("Shivu123");
	    login.findElement(By.xpath("html/body/form/button")).click();
	    Log.info("Login successfully..");
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
		VerifyDocumentScoreDetails(sht);
	//  VerifyProgramFunInfo(sht);		
	}
	
	public void VerifyDocumentScoreDetails(Sheet sheet) throws InterruptedException{		
		
		for(int i= 0 ;i<sheet.getRows(); i++)
		{
	       //Selecting STAAR tab on assessment module..
		   login.findElement(By.xpath("html/body/header/bottom/toolbar/left/div/label[2]/span")).click();  
           String DocTitle =sheet.getCell(0, i).getContents();

	    // String DocTitle = "Algebra I EOC 2012";
		   login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input")).click();
	       WebElement element = login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input"));
		   element.sendKeys(DocTitle);
	       element.sendKeys(Keys.ENTER);
		   Thread.sleep(4000);
		   
		   //Selecting document..
		   List mcanswers = login.findElements(By.className("number"));    
		 //  System.out.println("total documents: " +mcanswers.size());
		   int rowCount = mcanswers.size()-1;
		   int SelectDocu = mcanswers.size()-2;	  
		   String RecCount = login.findElement(By.className("reccount")).getText();	
		   
	       if(RecCount.equalsIgnoreCase("0 Records")){
	    	   Log.info("Document not found: "+DocTitle);
	    	   System.out.println("Document not found: "+DocTitle);
	    	   element.clear();
	    	   Thread.sleep(6000);
	       }else{	    	 
	    	  
	    	List<WebElement> AssessmentList= login.findElements(By.id("MaintenanceAssessmentListGrid"));	    	   
        	WebElement AsmtData=AssessmentList.get(3);
            //System.out.println("assessment length: "+xyz.getText());
    	 	  	
	       //Accessing Accessing values from the Assessment tab
		   String DocumentTitle =AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[4]")).getText();
		   System.out.println("Document Title is: " +DocumentTitle);
		   
		   String PassTallyRight1 =AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[10]/right")).getText();
//		   String ListSatisfactory =login.findElement(By.cssSelector(".PassTally>right")).getText();
		   int PassTallyRight = Integer.parseInt(PassTallyRight1);
		   System.out.println("Pass Tally right: " +PassTallyRight);
		   
		   String FailTallyRight =AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[11]/right")).getText();
		   System.out.println("Fail Tally right: " +FailTallyRight);
		   
		   String TotalTakenStud1 = AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[12]")).getText();
		   int TotalTakenStud = Integer.parseInt(TotalTakenStud1.replace(",", ""));
		   System.out.println("Total Taken students: "+TotalTakenStud);
		   
		   String TotalNonTakenStud1 = AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[13]")).getText();
		   int TotalNonTakenStud = Integer.parseInt(TotalNonTakenStud1);
		   System.out.println("Total Not Taken Students: " +TotalNonTakenStud); 
		   
		   int TotalTargetStud = TotalTakenStud+TotalNonTakenStud;
		   System.out.println("Total Target students in Assessment tab: "+TotalTargetStud);
		   
		   String AdvancePlus = AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[14]/right")).getText();
		   System.out.println("Advance Plus students count in Assessment tab: "+AdvancePlus);
		   
		   String Advance = AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[15]/right")).getText();
		   System.out.println("Advance students count in Assessment tab: "+Advance);
		   
		   int TotalAdvanceStudents = Integer.parseInt(AdvancePlus)+Integer.parseInt(Advance);
		   System.out.println("Total Advance students in Assessment tab: "+TotalAdvanceStudents);
		   
		   String SatisfactoryPlus= AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[16]/right")).getText();
		   System.out.println("Satisfactory Plus students count in Assessment tab: "+SatisfactoryPlus);
		   
		   String Satisfactory = AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[17]/right")).getText();
		   System.out.println("Satisfactory students count in Assessment tab: "+Satisfactory);
		   
		   String SatisfacrotyBubble=AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[18]/right")).getText();
		   System.out.println("UnSatisfactory Bubble students count in Assessment tab: "+SatisfacrotyBubble);
		   
		   String UnSatisfacrotyBubble=AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[19]/right")).getText();
		   System.out.println("UnSatisfactory Bubble students count in Assessment tab: "+UnSatisfacrotyBubble);
		   
		   String UnSatisfacroty=AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[20]/right")).getText();
		   System.out.println("UnSatisfactory Bubble students count in Assessment tab: "+UnSatisfacroty);
		   
		   String Incomplete =AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[21]")).getText();
		   System.out.println("UnSatisfactory Bubble students count in Assessment tab: "+Incomplete);

		   //Get left side percentage criteria..
		   String PassTallyLeft1 =AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[10]/left")).getText();
		 //String PassTallyLeft = PassTallyLeft1.concat("%");
		   float PassTallyLeft = Float.parseFloat(PassTallyLeft1);
		   System.out.println("Pass Tally Percentage: " +PassTallyLeft);
		   
		   String FailTallyLeft =AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[11]/left")).getText();
		   System.out.println("Fail Tally percentage: " +FailTallyLeft);	   
		   	
		   List<WebElement> Assessment= login.findElements(By.className("go"));
    	   WebElement AssessmentClick=(WebElement) Assessment.get(SelectDocu); 
    	   AssessmentClick.click();
    	   System.out.println("Selected "+DocTitle+" document..");
    	   Thread.sleep(4000); 	
    	   
	      login.findElement(By.xpath("html/body/header/top/tabs/a[1]/tab")).click();
		  System.out.println("Summary tab Clicked"); 
   
		  List<WebElement> table= login.findElements(By.className("boxes"));
		  
		  //Accessing Accessing values from the passing criteria table  
		  WebElement table_element=table.get(0);
		  String SumTotalTargetStud1=table_element.findElement(By.xpath("tbody/tr[3]/td[2]")).getText();
		  int SumTotalTargetStud = Integer.parseInt(SumTotalTargetStud1);
	      System.out.println("Total Target students in Summary tab: "+SumTotalTargetStud);
	      
	      WebElement table_element1=table.get(1);
		  		 		  
	      String SumTotalAdvanceTally1 =table_element1.findElement(By.xpath("tbody/tr[2]/td[1]")).getText();
	      int SumTotalAdvanceTally = Integer.parseInt(SumTotalAdvanceTally1);
		  System.out.println("Summary tab Advance Pass Tally: "+SumTotalAdvanceTally);
		  
	      String SumPassTally1 =table_element1.findElement(By.xpath("tbody/tr[2]/td[2]")).getText();
	      int SumPassTally = SumTotalAdvanceTally + Integer.parseInt(SumPassTally1);
		  System.out.println("Summary tab Pass Tally: "+SumPassTally);
		  
		  String SumFailTally =table_element1.findElement(By.xpath("tbody/tr[2]/td[3]")).getText();
		  System.out.println("Summary tab Fail Tally: "+SumFailTally);	
		  	      
	      String SumTakenStudent1 = table_element1.findElement(By.xpath("tbody/tr[2]/td[4]/u")).getText();
	      int SumTakenStudent = Integer.parseInt(SumTakenStudent1);
	      System.out.println("Total taken students count in summary tab:" +SumTakenStudent);
	      
	      int SumNotTakenStudent = SumTotalTargetStud - SumTakenStudent;
	      System.out.println("Total not taken students count in summary tab: " +SumNotTakenStudent);
	      
	      String SumAdvancePlus=table_element1.findElement(By.xpath("tbody/tr[4]/td[1]")).getText();
	      System.out.println("Advance Plus students count in summary tab "+SumAdvancePlus);
	      
	      String SumAdvance=table_element1.findElement(By.xpath("tbody/tr[4]/td[2]")).getText();
	      System.out.println("Advance students count in summary tab "+SumAdvance);
	      
	      String SumSatisfactoryPlus = table_element1.findElement(By.xpath("tbody/tr[4]/td[3]")).getText();
	      System.out.println("Satisfactory Plus students count in Summary tab: "+SumSatisfactoryPlus);
	     
	      String SumSatisfactory = table_element1.findElement(By.xpath("tbody/tr[4]/td[4]")).getText();
	      System.out.println("Satisfactory students count in Summary tab: "+SumSatisfactory);
	      
	      String SumSatisfactoryBubble = table_element1.findElement(By.xpath("tbody/tr[4]/td[5]")).getText();
	      System.out.println("Satisfactory students count in Summary tab: "+SumSatisfactoryBubble);
	      
	      String SumUnSatisfactoryBubble = table_element1.findElement(By.xpath("tbody/tr[4]/td[6]")).getText();
	      System.out.println("UnSatisfactory Bubble students count in Summary tab: "+SumUnSatisfactoryBubble);
	      
	      String SumUnSatisfactory = table_element1.findElement(By.xpath("tbody/tr[4]/td[7]")).getText();
	      System.out.println("UnSatisfactory Bubble students count in Summary tab: "+SumUnSatisfactory);
	      
		  String SumPassPerc1=table_element1.findElement(By.xpath("tbody/tr[6]/td[2]")).getText();	
		  float  round= Float.parseFloat(SumPassPerc1.replace("%", ""));
	    //System.out.println("Summary tab Pass Percentage: " +SumPassPerc.replace("%", ""));
		  float SumPassPerc = Round(round,1);
		  System.out.println("Rounded pass percent: " +SumPassPerc);
		 		  
		  if(SumTotalTargetStud ==TotalTargetStud){
			  Log.info("Total target student count is matching for Document: "+DocTitle);
		  }else{
			  Log.error("Total target student count is not matching for Document: "+DocTitle);
			  Log.error("Expected is " +SumTotalTargetStud+ " and found is "+TotalTargetStud);
		  }
		  if(SumTakenStudent == TotalTakenStud){
			  Log.info("Total taken student count is matching for document: "+DocTitle);
		  }else{
			  Log.error("Total taken student count is not matching for document: "+DocTitle);
			  Log.error("Expected is " +SumTakenStudent+ " and found is "+TotalTakenStud);
		  }
		  if(SumNotTakenStudent == TotalNonTakenStud){
			  Log.info("Total not taken student count is matching for document: "+DocTitle);
		  }else{
			  Log.error("Total not taken student count is not matching for document: "+DocTitle);
			  Log.error("Expected is " +SumNotTakenStudent+ " and found is "+TotalNonTakenStud);
		  }
		  if(SumTotalAdvanceTally== TotalAdvanceStudents){
			  Log.info("Total Advance tally count is matching for Document: "+DocTitle);
		  }else{
			  Log.error("Total Advance tally count is not matching for Document: "+DocTitle);
			  Log.error("Expected is " +SumTotalAdvanceTally+ " and found is "+TotalAdvanceStudents);
		  }
		  if(SumPassTally == PassTallyRight){
			  Log.info("Pass Tally count is matching for Document: "+DocTitle);
		  }else{
			  Log.error("Pass Tally count is not matching for Document: "+DocTitle);
			  Log.error("Expected is " +SumPassTally+ " and found is "+PassTallyRight);
		  }
		  if(SumFailTally.equals(FailTallyRight)){
			  Log.info("Fail Tally count is matching for Document: "+DocTitle);
		  }else{
			  Log.error("Fail Tally count is not matching for Document: "+DocTitle);
			  Log.error("Expected is " +SumFailTally+ " and found is "+FailTallyRight);
		  }
		  if(SumAdvancePlus.equals(AdvancePlus)){
			  Log.info("Advance Plus Students count is matching for Document: "+DocTitle);
		  }else{
			  Log.error("Advance Plus Students count is not matching for Document: "+DocTitle);
			  Log.error("Expected is " +SumAdvancePlus+ " and found is "+AdvancePlus);
		  }
		  if(SumAdvance.equals(Advance)){
			  Log.info("Advance Students count is matching for Document: "+DocTitle);
		  }else{
			  Log.error("Advance Students count is not matching for Document: "+DocTitle);
			  Log.error("Expected is " +SumAdvance+ " and found is "+Advance);
		  }
		  if(SumSatisfactoryPlus.equals(SatisfactoryPlus)){
			  Log.info("Satisfactory Plus Students count is matching for Document: "+DocTitle);
		  }else{
			  Log.error("Satisfactory Plus Students count is not matching for Document: "+DocTitle);
			  Log.error("Expected is " +SumSatisfactoryPlus+ " and found is "+SatisfactoryPlus);
		  }
		  if(SumSatisfactory.equals(Satisfactory)){
			  Log.info("Satisfactory Students count is matching for Document: "+DocTitle);
		  }else{
			  Log.error("Satisfactory Students count is not matching for Document: "+DocTitle);
			  Log.error("Expected is " +SumSatisfactory+ " and found is "+Satisfactory);
		  }
		  if(SumSatisfactoryBubble.equals(SatisfacrotyBubble)){
			  Log.info("Satisfactory Bubble Students count is matching for Document: "+DocTitle);
		  }else{
			  Log.error("Satisfactory Bubble Students count is not matching for Document: "+DocTitle);	
			  Log.error("Expected is " +SumSatisfactoryBubble+ " and found is "+SatisfacrotyBubble);
		  }
		  if(SumUnSatisfactoryBubble.equals(UnSatisfacrotyBubble)){
			  Log.info("UnSatisfactory Bubble Students count is matching for Document: "+DocTitle);
		  }else{
			  Log.error("UnSatisfactory Bubble Students count is not matching for Document: "+DocTitle);
			  Log.error("Expected is " +SumUnSatisfactoryBubble+ " and found is "+UnSatisfacrotyBubble);
		  }
		  if(SumUnSatisfactory.equals(UnSatisfacroty)){
			  Log.info("UnSatisfactory Students count is matching for Document: "+DocTitle);
		  }else{
			  Log.error("UnSatisfactory Students count is not matching for Document: "+DocTitle);
			  Log.error("Expected is " +SumUnSatisfactory+ " and found is "+UnSatisfacroty);
		  }
		  		  
          System.out.println("Successfully verified all the values for Document: "+DocTitle);
          Log.info("Successfully verified all the values for Document: "+DocTitle);
	      login.findElement(By.id("menubutton")).click();
		  login.findElement(By.id("ui-id-1")).click();
		  Thread.sleep(2000);	 
	       }
	    } 	       
	}
		
/*	@Test
	public void VerifyProgramFunInfo(Sheet sheet) throws InterruptedException{
	
	       //Selecting STAAR tab on assessment module..
		   login.findElement(By.xpath("html/body/header/bottom/toolbar/left/div/label[2]/span")).click();  
           String DocTitle =sheet.getCell(0, i).getContents();

	    // String DocTitle = "Algebra I EOC 2012";
		   login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input")).click();
	       WebElement element = login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input"));
		   element.sendKeys(DocTitle);
	       element.sendKeys(Keys.ENTER);
		   Thread.sleep(4000);
		   
		   //Selecting document..
		   List mcanswers = login.findElements(By.className("number"));    
	 //    System.out.println("total documents: " +mcanswers.size());
		   int rowCount = mcanswers.size()-1;
		   int SelectDocu = mcanswers.size()-2;	  
		   String RecCount = login.findElement(By.className("reccount")).getText();	
		   
	       if(RecCount.equalsIgnoreCase("0 Records")){
	    	   Log.info("Document not found: "+DocTitle);
	    	   System.out.println("Document not found: "+DocTitle);
	    	   element.clear();
	    	   Thread.sleep(6000);
	       }else{	    	 	    	  
	    	List<WebElement> AssessmentList= login.findElements(By.id("MaintenanceAssessmentListGrid"));	    	   
        	WebElement AsmtData=AssessmentList.get(3);
        	
        	List<WebElement> Assessment= login.findElements(By.className("go"));
    	   WebElement AssessmentClick=(WebElement) Assessment.get(SelectDocu); 
    	   AssessmentClick.click();
    	   System.out.println("Selected "+DocTitle+" document..");
    	   Thread.sleep(2000); 	
    	   
    	   login.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
    	   System.out.println("Filter option selected..");
    	   
//    	    login.findElement(By.id("ui-multiselect-filterpicker-option-8")).click();
//    	    login.findElement(By.xpath("html/body/header/top")).click();
//    	    String MaleCount = login.findElement(By.className("reccount")).getText();
//    	    System.out.println("Total male count: "+MaleCount);
    	   
	  	  String ProgramFunding[]={"Title 1 A","Migrant","Limited English Proficient","Bilingual Education","English as a Second Language","Gifted and Talented","Special Education","At Risk","Economically Disadvantaged","Career and Technology","GI9"};
	  	  
	  	  for(int x=0;x<test.size();x++){
	  		  
	  		  if(ProgramFunding[x].contains(test.get(x).getText())){
	  		  System.out.println("Filter Option is present: "+ProgramFunding[x]);
	  		  test.get(0).click();
		  	  login.findElement(By.tagName("toolbar")).click();
		   	  String ProgramFundingCount = login.findElement(By.className("reccount")).getText();
   	          System.out.println( ProgramFunding[x]+" count: "+ProgramFundingCount);	  		    
	  		  }
	  		  else{
	  			  System.out.println("Option Not Available"+ProgramFunding[x]);
	  		  }	  		  
	  	  }
	       }
		}

	}*/
	public static float Round(float num, int Rpl) {
		  float p = (float)Math.pow(10,Rpl);
		  num = num * p;
		  float tmp = Math.round(num);
		  return (float)tmp/p;
		  }

}
