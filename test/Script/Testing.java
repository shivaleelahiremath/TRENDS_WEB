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


public class Testing {
	private static Logger Log=Logger.getLogger("Testing");
	String[][] tabArray = null;
	Workbook workbk;
	Sheet sheet;
	int rowCount, colCount;
	String sheetPath = "test/Resources/Data/Assessment_INFO.xls";	
	WebDriver login;
	CommonClass c= new CommonClass();

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
		for(int i= 0 ;i<sht.getRows(); i++){
			String DocTitle =sht.getCell(0, i).getContents();
			VerifyDocumentScoreDetails(DocTitle);
		}
	}
	
	@Test
	public void VerifyDocumentScoreDetails(String DocTitle) throws InterruptedException{		
				
	       //Selecting STAAR tab on assessment module..
		   login.findElement(By.xpath("html/body/header/bottom/toolbar/left/div/label[2]/span")).click();  

	    // String DocTitle = "Algebra I EOC 2012";
		   login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input")).click();
	       WebElement element = login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input"));
		   element.sendKeys(DocTitle);
	       element.sendKeys(Keys.ENTER);
		   Thread.sleep(4000);
		   
		   //Selecting document..
		   List mcanswers = login.findElements(By.className("number"));    
		  //System.out.println("total documents: " +mcanswers.size());
		   int rowCount = mcanswers.size()-1;
		   int SelectDocu = mcanswers.size()-2;	  
		   String RecCount = login.findElement(By.className("reccount")).getText();	
		   
	       if(RecCount.equalsIgnoreCase("0 Records")){
	    	   Log.info("Document not found: "+DocTitle);
	    	   System.out.println("Document not found: "+DocTitle);
	    	   element.clear();
	    	   Thread.sleep(4000);
	       }else{	    	 
	    	  
	    	List<WebElement> AssessmentList= login.findElements(By.id("MaintenanceAssessmentListGrid"));	    	   
        	WebElement AsmtData=AssessmentList.get(3);
            //System.out.println("assessment length: "+xyz.getText());
     	   System.out.println("***************************Assessment Tab Data***************************");
  	
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
		   		   
		   // Initial Grid		   
		   String AsmtTabData[]={"AdvancePlus","Advance","SatisfactoryPlus","Satisfactory","SatisfactoryBubble","UnSatisfactoryBubble","UnSatisfactory"};
		      String AsmtTabValues[]=new String[AsmtTabData.length];		      
		      for(int l=0;l<AsmtTabData.length;l++){
		    	  AsmtTabValues[l] = AsmtData.findElement(By.xpath("tr["+rowCount+"]/td["+(l+14)+"]/right")).getText();				  
		      }
		      System.out.println("Assessment tab values - AdvancePlus: "+AsmtTabValues[0] +"Advance: "+AsmtTabValues[1] +"SatisfactoryPlus: "+AsmtTabValues[2] +"Satisfactory: "+AsmtTabValues[3] +"SatisfactoryBubble: "+AsmtTabValues[4] +"UnSatisfactoryBubble: "+AsmtTabValues[5] +"UnSatisfactory: "+AsmtTabValues[6]);
		
		   int TotalAdvanceStudents = c.sumOfString(AsmtTabValues[0], AsmtTabValues[1]);
		   System.out.println("Total Advance students in Assessment tab: "+TotalAdvanceStudents);
   
		   String Incomplete =AsmtData.findElement(By.xpath("tr["+rowCount+"]/td[21]")).getText();
		   System.out.println("UnSatisfactory Bubble students count in Assessment tab: "+Incomplete);

		   List<WebElement> Assessment= login.findElements(By.className("go"));
    	   WebElement AssessmentClick=(WebElement) Assessment.get(SelectDocu); 
    	   AssessmentClick.click();
    	   System.out.println("Selected "+DocTitle+" document..");
    	   Thread.sleep(4000); 	
     	   
    	   //Summary Tab Starts    	   
    	   System.out.println("***************************Summary Tab***************************");
    	   
    	  login.findElement(By.xpath("html/body/header/top/tabs/a[1]/tab")).click();
		  System.out.println("Summary tab Clicked"); 
   		  
		  //Accessing Accessing values from the passing criteria table 
		  List<WebElement> table= login.findElements(By.className("boxes"));
	      WebElement table_element1=table.get(1);
	      
		  WebElement table_element=table.get(0);
		  String SumTotalTargetStud1=table_element.findElement(By.xpath("tbody/tr[3]/td[2]")).getText();
		  int SumTotalTargetStud = Integer.parseInt(SumTotalTargetStud1);
	      System.out.println("Total Target students in Summary tab: "+SumTotalTargetStud);
	      		
	      String KentroOuterScore[]={"SumTotalAdvanceTally1","SumPassTally1","SumFailTally"};
	      int KentroOuterScoreValues[]=new int[KentroOuterScore.length];
	      for(int s=0;s<KentroOuterScore.length;s++){
	    	  String ss=table_element1.findElement(By.xpath("tbody/tr[2]/td["+(s+1)+"]")).getText();
	    	  KentroOuterScoreValues[s]=Integer.parseInt(ss);
	      }
	      
	      int PassTally = c.sumOfString(KentroOuterScoreValues[0], KentroOuterScoreValues[1]);
		   System.out.println("Total Advance students in Assessment tab: "+PassTally);
	      
	  //  System.out.println(KentroOuterScoreValues[0]+"  "+KentroOuterScoreValues[1]+"  "+KentroOuterScoreValues[2]);
	      String SumTakenStudent1 = table_element1.findElement(By.xpath("tbody/tr[2]/td[4]/u")).getText();
	      int SumTakenStudent = Integer.parseInt(SumTakenStudent1);
	      System.out.println("Total taken students count in summary tab:" +SumTakenStudent);
	      
	      int SumNotTakenStudent = SumTotalTargetStud - SumTakenStudent;
	      System.out.println("Total not taken students count in summary tab: " +SumNotTakenStudent);
	  
	      String InKentroScore[]={"SumAdvancePlus","SumAdvance","SumSatisfactoryPlus","SumSatisfactory","SumSatisfactoryBubble","SumUnSatisfactoryBubble","SumUnSatisfactory"};
	      int InKentroScoreValues[]=new int[InKentroScore.length];
	      
	      for(int s=0;s<InKentroScore.length;s++){
	    	  String ss=table_element1.findElement(By.xpath("tbody/tr[4]/td["+(s+1)+"]")).getText();
	    	  InKentroScoreValues[s]=Integer.parseInt(ss);
	      }
	     // system.out.println(InKentroScoreValues[0]+""+InKentroScoreValues[1]+""+InKentroScoreValues[2]+""+InKentroScoreValues[3]+""+InKentroScoreValues[4]+""+InKentroScoreValues[5]+""+InKentroScoreValues[6]);
	      
         // Not done
	      String SumPassPerc1=table_element1.findElement(By.xpath("tbody/tr[6]/td[2]")).getText();	
		  float  round= Float.parseFloat(SumPassPerc1.replace("%", ""));
	     //System.out.println("Summary tab Pass Percentage: " +SumPassPerc.replace("%", ""));
		  float SumPassPerc = Round(round,1);
		  System.out.println("Rounded pass percent: " +SumPassPerc);

		  //Modified changes/		  
		  String TotalTargetStdCount = c.intVerify(SumTotalTargetStud, TotalTargetStud);  
		  Log.info("Total Target student count is matching for Document: "+DocTitle+" and Status is: "+TotalTargetStdCount);
		  
		  String TotalTakenStudent = c.intVerify(SumTakenStudent, TotalTakenStud);  
		  Log.info("Total Taken student count is matching for Document: "+DocTitle+" and Status is: "+TotalTakenStudent);
		  
		  String TotalNotTakenStudent = c.intVerify(SumNotTakenStudent, TotalNonTakenStud);  
		  Log.info("Total not taken student count is matching for Document: "+DocTitle+"and Status is: "+TotalNotTakenStudent);
		  
		  String TotalAdvanceStudent = c.intVerify(KentroOuterScoreValues[0], TotalAdvanceStudents);  
		  Log.info("Total Advance Student count is matching for Document: "+DocTitle+" and Status is: "+TotalAdvanceStudent);
		 
		  String PassTallyCount = c.intVerify(PassTally, PassTallyRight);  
		  Log.info("PassTally student count is matching for Document: "+DocTitle+" and Status is : "+PassTallyCount);
		 
		  String FailTallyCount = c.intstringVerify(KentroOuterScoreValues[2], FailTallyRight);  
		  Log.info("FailTally student count is matching for Document: "+DocTitle+" and Status is : "+FailTallyCount);
		  		  
		  String AdvancePlusStdCount = c.intstringVerify(InKentroScoreValues[0], AsmtTabValues[0]);  
		  Log.info("AdvancePlus count is matching for Document: "+DocTitle+" and Status is : "+AdvancePlusStdCount);
		 
		  String AdvanceStdCount = c.intstringVerify(InKentroScoreValues[1], AsmtTabValues[1]);  
		  Log.info("Advance student count is matching for Document: "+DocTitle+" and Status is: "+AdvanceStdCount);
		 
		  String SatisfactoryPlusStudentCount = c.intstringVerify(InKentroScoreValues[2], AsmtTabValues[2]);  
		  Log.info("SatisfactoryPlus student count is matching for Document: "+DocTitle+" and Status is: "+SatisfactoryPlusStudentCount);
		 
		  String SatisfactoryStudentCount = c.intstringVerify(InKentroScoreValues[3], AsmtTabValues[3]);  
		  Log.info("Satisfactory student count is matching for Document: "+DocTitle+" and Status is: "+SatisfactoryStudentCount);
		
		  String SatisfactoryBubbleStudentCount = c.intstringVerify(InKentroScoreValues[4], AsmtTabValues[4]);  
		  Log.info("SatisfactoryBubble student count is matching for Document: "+DocTitle+" and Status is :"+SatisfactoryBubbleStudentCount);

		  String UnSatisfactoryBubbleStudentCount = c.intstringVerify(InKentroScoreValues[5], AsmtTabValues[5]);  
		  Log.info("UnSatisfactoryBubble student count is matching for Document: "+DocTitle+" and Status is :"+UnSatisfactoryBubbleStudentCount);

		  String UnSatisfactoryStudentCount = c.intstringVerify(InKentroScoreValues[6], AsmtTabValues[6]);  
		  Log.info("UnSatisfactory student count is matching for Document: "+DocTitle+" and Status is : "+UnSatisfactoryStudentCount);
		  
          System.out.println("Successfully verified all the values for Document: "+DocTitle);
          Log.info("Successfully verified all the values for Document: "+DocTitle);
	      login.findElement(By.id("menubutton")).click();
		  login.findElement(By.id("ui-id-1")).click();
		  Thread.sleep(2000);	 
	       } 	       
	}
		
	public static float Round(float num, int Rpl) {
		  float p = (float)Math.pow(10,Rpl);
		  num = num * p;
		  float tmp = Math.round(num);
		  return (float)tmp/p;
		  }

}

/*	      for(int task=0; task < SumPF_Tasks.length; task++){
String SumTask = SumPF_Tasks[task].trim();
int SumCount = SumPF_StudCount[task];
for(int task1=0; task1<StudPF_Tasks.length; task1++){	
	  String stdTask =StudPF_Tasks[task1].trim();
      if(SumTask.equalsIgnoreCase(stdTask)){
      	//System.out.println("enter if stmt..");
      	int StudCount = SumPF_StudCount[task1];
      	if(StudCount == SumCount){
      		System.out.println("count is matching for task: "+SumTask+" and "+stdTask+ "");
      		System.out.println("Expected is "+ SumCount+" and found is: "+StudCount);
      	}else{
             System.out.println("count  is not missing Expected is "+StudCount+" and found is: "+SumCount);  
          }
      }else{
         System.out.println("task is not matching..Expected is:"+SumTask+" and found is: "+stdTask.trim());
      }
}  
}	*/    

