package Script;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.collect.ObjectArrays;
import com.google.common.primitives.Ints;

public class Verify_STAAR_SAccount_DocScoreDetails_TS {
	private static Logger debugLog=Logger.getLogger("STAAR_Asmt");
	private static Logger reportsLog=Logger.getLogger("reportsLog");
	String[][] tabArray = null;
	int rowCount, colCount;
	String sheetPath = "test/Resources/Data/Latest_STARR_Document.xls";	
	WebDriver login;
	
	@BeforeClass
	public void setUp() throws InterruptedException{
		//opening Chrome browser..
				System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
				login = new ChromeDriver();				
		        //Launching the application..33
			//	login.get("http://ec2-184-73-167-15.compute-1.amazonaws.com/trendsReloaded");
				login.get("http://trends.tangosoftware.com");
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
		for(int i=0;i<1; i++){
			String DocTitle = sht.getCell(0, i).getContents();
			VerifyDocumentScoreDetails(DocTitle);
		}		
	}

	public void VerifyDocumentScoreDetails(String DocTitle) throws InterruptedException{			
	     //Selecting STAAR tab on assessment module..
	      login.findElement(By.id("menubutton")).click();
	      Thread.sleep(2000);
		  login.findElement(By.id("ui-id-9")).click();
		  Thread.sleep(4000);	
		   login.findElement(By.xpath("html/body/content/div/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input")).click();
	       WebElement element = login.findElement(By.xpath("html/body/content/div/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input"));
		   element.sendKeys(DocTitle);  
	       element.sendKeys(Keys.ENTER);
		   Thread.sleep(2000);
		   
		   //Selecting document..
		   List mcanswers = login.findElements(By.className("number"));    
		// System.out.println("total documents: " +mcanswers.size());
		   int rowCount = mcanswers.size()-1;
		   int SelectDocu = mcanswers.size()-2;	  
		   String RecCount = login.findElement(By.className("reccount")).getText();			   
	       if(RecCount.equalsIgnoreCase("0 Records")){
	    	   reportsLog.info("Document not found: "+DocTitle);
	    	   System.out.println("Document not found: "+DocTitle);
	   // 	   element.clear();
	   // 	   Thread.sleep(5000);
	       }
	       else{	    	 	    	  
	       List<WebElement> AssessmentList= login.findElements(By.id("STAARAssessments"));	    	   
           WebElement AsmtData=AssessmentList.get(3);
          // System.out.println("assessment data: "+AsmtData.getText());
      	   System.out.println("***************************Assessment Tab Data***************************");	  	
	       //Accessing Accessing values from the Assessment tab
		   String DocumentTitle =AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td[4]")).getText();
		   System.out.println("Document Title is: " +DocumentTitle);
		   
		   String PassTallyRight1 =AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td[10]/right")).getText();
		   int PassTallyRight = Integer.parseInt(PassTallyRight1);
		   
		   String FailTallyRight1 =AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td[11]/right")).getText();
		   int FailTallyRight = Integer.parseInt(FailTallyRight1);

		   String TotalTakenStud1 = AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td[12]")).getText();
		   int TotalTakenStud = Integer.parseInt(TotalTakenStud1.replace(",", ""));
		   
		   String TotalNonTakenStud1 = AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td[13]")).getText();
		   int TotalNonTakenStud = Integer.parseInt(TotalNonTakenStud1);
		   
		   int TotalTargetStud = TotalTakenStud+TotalNonTakenStud;
	//	   System.out.println("Total Target students in Assessment tab: "+TotalTargetStud);
		   
		   // Get right side value on Assessment tab..   
		   String AsmtTabData[]={"AdvancePlus","Advance","SatisfactoryPlus","Satisfactory","SatisfactoryBubble","UnSatisfactoryBubble","UnSatisfactory"};
		   String AsmtTabValues[]=new String[AsmtTabData.length];	      
		      for(int l=0;l<AsmtTabData.length;l++){
		    	  AsmtTabValues[l] = AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td["+(l+14)+"]/right")).getText();				  
		      }
		// System.out.println("Assessment tab values - AdvancePlus: "+AsmtTabValues[0] +"Advance: "+AsmtTabValues[1] +"SatisfactoryPlus: "+AsmtTabValues[2] +"Satisfactory: "+AsmtTabValues[3] +"SatisfactoryBubble: "+AsmtTabValues[4] +"UnSatisfactoryBubble: "+AsmtTabValues[5] +"UnSatisfactory: "+AsmtTabValues[6]);
		
		   int TotalAdvanceStudents = Integer.parseInt(AsmtTabValues[0]) + Integer.parseInt(AsmtTabValues[1]);
		   
		   String Incomplete = AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td[21]")).getText();
	//	   System.out.println("Incomplete students count in Assessment tab: "+Incomplete);
		  
		   //Get left side percentage value on Assessment tab..
		    String AsmtTabPerc1[]={"PassPerc","FailPerc"};
		    Double AsmtTabPerValues1[]=new Double[AsmtTabPerc1.length];	      
		    for(int m=0; m <AsmtTabPerc1.length ;m++){
		    	  String AsmtPer = AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td["+(m+10)+"]/left")).getText();
		    	  AsmtTabPerValues1[m]=Double.parseDouble(AsmtPer);
		    }  	   
	   //  System.out.println("Pass and Fail perc on assessment tab--------"+AsmtTabPerValues1[0]+" "+AsmtTabPerValues1[1]);
		   
		   String AsmtTabPerc[]={"AdvancePlusPerc","AdvancedPerc","SatisfactoryPlusPerc","SatisfactoryPerc","SatisfactoryBubblePerc","UnSatisfactoryBubblePerc","UnSatisfactoryPerc"};
		   Double AsmtTabPerValues[]=new Double[AsmtTabPerc.length];	      
		      for(int m=0;m<AsmtTabData.length;m++){
		    	  String AsmtPer = AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td["+(m+14)+"]/left")).getText();
		    	  AsmtTabPerValues[m]=Double.parseDouble(AsmtPer);
		   }
		// System.out.println("Adv+, Adv ... perc on assessment tab--------"+AsmtTabPerValues[0]+" "+AsmtTabPerValues[1]+" "+AsmtTabPerValues[2]);
		   	
		   List<WebElement> Assessment= login.findElements(By.className("go"));
    	   WebElement AssessmentClick=(WebElement) Assessment.get(SelectDocu); 
    	   AssessmentClick.click();
    	   System.out.println("Selected "+DocTitle+" document..");
    	   Thread.sleep(2000); 
    	   
    	   //Get total target student count on student tab.
	        String StudTakenCount1 = login.findElement(By.className("reccount")).getText();
	        int StudTotalTargetStud = Integer.parseInt(StudTakenCount1.replace(" Records", ""));
            System.out.println("target student count on student tab:  "+StudTotalTargetStud);   	   
    	   
    	   //PF and gender filter functionality..
    	   List<WebElement> allElements = login.findElements(By.name("multiselect_filterpicker")); 
           System.out.println("Filter button selected and Elements present in Filter button: " +allElements.size());  
           
           int[] StudPF_StudCount = new int[allElements.size()];
           String[] StudPF_Tasks = new String[allElements.size()];
           for(int x = 0; x < allElements.size(); x++){
        	   login.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
        	   login.findElement(By.className("ui-multiselect-none")).click();        	   
        	   StudPF_Tasks[x] = login.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).getAttribute("title");  	   
               login.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).click();
   	           login.findElement(By.className("ui-multiselect-close")).click();
   	           Thread.sleep(2000);
   	           String FilterRowCount = login.findElement(By.className("reccount")).getText();
   	           StudPF_StudCount[x] = Integer.parseInt(FilterRowCount.replace(" Records", ""));
   	        // System.out.println("Selected "+StudPF_Tasks[x].trim()+" task and total records on Student tab are: "+StudPF_StudCount[x]);
           }
    	   login.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
    	   login.findElement(By.className("ui-multiselect-none")).click();        	   
	       login.findElement(By.className("ui-multiselect-close")).click();
    	   
    	   // Selecting question count...
		   List<WebElement> Student= login.findElements(By.className("greenarrow"));
    	   WebElement StudentClick=(WebElement) Student.get(0); 
    	   StudentClick.click();
    	   Thread.sleep(2000); 
    	   //Selecting total questions... 	   
    	   String QuestionCount = login.findElement(By.className("reccount")).getText();
	       int DocumentTotalQuestions = Integer.parseInt(QuestionCount.replace(" Records", ""));
    	   
    	  //Summary Tab Starts    	   
    	   System.out.println("***************************Summary Tab***************************");
	      login.findElement(By.xpath("html/body/header/top/tabs/a[2]/tab")).click();
		  System.out.println("Summary tab Clicked"); 
   
		  //Accessing Accessing values from the passing criteria table  
		  List<WebElement> table= login.findElements(By.className("boxes"));		  
		  WebElement table_element=table.get(0);
	      WebElement table_element1=table.get(1);
		  String SumTotalTargetStud1=table_element.findElement(By.xpath("tbody/tr[3]/td[2]")).getText();
		  int SumTotalTargetStud = Integer.parseInt(SumTotalTargetStud1);
	  //  System.out.println("Total Target students in Summary tab: "+SumTotalTargetStud);
		  
		  String SumItems1 = table_element.findElement(By.xpath("tbody/tr[2]/td[2]")).getText();
		  int SumQsCount = Integer.parseInt(SumItems1);

	      String KentroOuterScore[]={"SumTotalAdvanceTally1","SumPassTally1","SumFailTally"};
	      int KentroOuterScoreValues[]=new int[KentroOuterScore.length];
	      for(int s=0;s<KentroOuterScore.length;s++){
	    	  String ss=table_element1.findElement(By.xpath("tbody/tr[2]/td["+(s+1)+"]")).getText();
	    	  KentroOuterScoreValues[s]=Integer.parseInt(ss);
	      }	      
	//    System.out.println(KentroOuterScoreValues[0]+"  "+KentroOuterScoreValues[1]+"  "+KentroOuterScoreValues[2]);
	      
	      int SumPassTally = KentroOuterScoreValues[0] + KentroOuterScoreValues[1];
	//    System.out.println("Total Advance students in Assessment tab: "+SumPassTally);
	      	      	  
	      String SumTakenStudent1 = table_element1.findElement(By.xpath("tbody/tr[2]/td[4]/u")).getText();
	      int SumTakenStudent = Integer.parseInt(SumTakenStudent1);
	 //   System.out.println("Total taken students count in summary tab:" +SumTakenStudent);
	      
	      int SumNotTakenStudent = SumTotalTargetStud - SumTakenStudent;
	 //   System.out.println("Total not taken students count in summary tab: " +SumNotTakenStudent);
	      
	      String InKentroScore[]={"SumAdvancePlus","SumAdvance","SumSatisfactoryPlus","SumSatisfactory","SumSatisfactoryBubble","SumUnSatisfactoryBubble","SumUnSatisfactory"};
	      String InKentroScoreValues[]=new String[InKentroScore.length];
	      for(int s=0;s<InKentroScore.length;s++){
	    	  InKentroScoreValues[s] =table_element1.findElement(By.xpath("tbody/tr[4]/td["+(s+1)+"]")).getText();
	      }
	    // System.out.println(InKentroScoreValues[0]+""+InKentroScoreValues[1]+""+InKentroScoreValues[2]+""+InKentroScoreValues[3]+""+InKentroScoreValues[4]+""+InKentroScoreValues[5]+""+InKentroScoreValues[6]);
     		  
		  String SumTabPerc[]={"SumAdvancePlusPerc","SumAdvancedPerc","SumSatisfactoryPlusPerc","SumSatisfactoryPerc","SumSatisfactoryBubblePerc","SumUnSatisfactoryBubblePerc","SumUnSatisfactoryPerc"};		   
		  Double SumTabPerValues[]=new Double[SumTabPerc.length];	   
		      for(int n=0;n<SumTabPerc.length;n++){
		    	String s  = table_element1.findElement(By.xpath("tbody/tr[5]/td["+(n+1)+"]")).getText();
		    	SumTabPerValues[n]=Double.parseDouble(s);
		      }
		//System.out.println("Advance+, Advance.. percentage on summary tab-----"+SumTabPerValues[0]+" "+SumTabPerValues[1]+" "+SumTabPerValues[2]);
		 			  
		  String SumTabOverallPerc[]={"SumAdvancePerc","SumPassPerc","SumFailPerc"};
		  Float SumTabOverallValues[]=new Float[SumTabOverallPerc.length];	      
		      for(int x=0;x<SumTabOverallPerc.length;x++){
		    	  String percValue = table_element1.findElement(By.xpath("tbody/tr[6]/td["+(x+1)+"]")).getText();
		    	  SumTabOverallValues[x]=Float.parseFloat(percValue.replace("%", ""));
		      }
    	//System.out.println("Advance, Pass and Fail percentage on summary tab-----"+SumTabOverallValues[0]+" "+SumTabOverallValues[1]+" "+SumTabOverallValues[2]);		 	
		  Double SumPassPerc = (double) (SumTabOverallValues[0] + SumTabOverallValues[1]);
			
		  //Accessing values from the passing criteria table  
 		  List<WebElement> Tasks_table = login.findElements(By.className("tableswithbars"));		  
 		  WebElement table_element2=Tasks_table.get(1);
		  List<WebElement> tr_collection=table_element2.findElements(By.cssSelector(".tableswithbars>tbody>tr"));
	   //   System.out.println("NUMBER OF ROWS IN PROGRAM FUNDING TABLE = "+tr_collection.size());	        
	      
	      //Program Funding tasks and student count on summary tab..	 
	      String[] SumPF_Tasks= new String[tr_collection.size()];
	      int[] SumPF_StudCount = new int[tr_collection.size()];
	      for(int row = 0;row<tr_collection.size();row++){
	      SumPF_Tasks[row] =table_element2.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[1]/bar/text")).getText();
	      String Taken_Stud = table_element2.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[11]")).getText();
	      String No_Data = table_element2.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[12]")).getText();
	      SumPF_StudCount[row] = (Integer.parseInt(Taken_Stud) + Integer.parseInt(No_Data));
	 //   System.out.println("Program Funding task and student count on summary tab: "+SumPF_Tasks[row]+"--"+SumPF_StudCount[row]);
	      }

	      WebElement table_element3 =Tasks_table.get(0);
		  List<WebElement> tr_collection1=table_element3.findElements(By.cssSelector(".tableswithbars>tbody>tr"));
	 //   System.out.println("NUMBER OF ROWS IN GENDER AND ETHNICITY TABLE = "+tr_collection1.size());	  
	      
	      //Gender and gender count on summary tab..	 
	      String[] SumGender= new String[tr_collection1.size()];
	      int[] SumGender_StudCount = new int[tr_collection1.size()];
	      try{
	      for(int row = 2; row < 4;row++){
	      SumGender[row] =table_element3.findElement(By.xpath("tbody/tr["+row+"]/td[1]/bar/text")).getText();
	      String Taken_Stud = table_element3.findElement(By.xpath("tbody/tr["+row+"]/td[11]")).getText();
	      String No_Data = table_element3.findElement(By.xpath("tbody/tr["+row+"]/td[12]")).getText();
	      SumGender_StudCount[row] = (Integer.parseInt(Taken_Stud) + Integer.parseInt(No_Data));
	     // System.out.println(SumGender[row]+" student count on summary tab: "+SumGender_StudCount[row]);
	      }	 
	      }catch (Exception e){
             System.out.println("Gender not found");
	      }

	      //Ethnicity tasks and student count on summary tab..
	      String[] SumEthnicity= new String[tr_collection1.size()+1];	
	      int[] SumEthn_StudCount = new int[tr_collection1.size()+1];
	      int max=tr_collection1.size()+1;
	      for(int row = 5; row < max;row++){	    	  
	      SumEthnicity[row] =table_element3.findElement(By.xpath("tbody/tr["+row+"]/td[1]/bar/text")).getText();
	      String Taken_Stud = table_element3.findElement(By.xpath("tbody/tr["+row+"]/td[11]")).getText();
	      String No_Data = table_element3.findElement(By.xpath("tbody/tr["+row+"]/td[12]")).getText();
	      SumEthn_StudCount[row] = Integer.parseInt(Taken_Stud) + Integer.parseInt(No_Data);
	  //  System.out.println(SumEthnicity[row]+" student count on summary tab: "+SumEthn_StudCount[row]);
	      }

	      String[] SumGender_Ethn_Task = ObjectArrays.concat(SumGender, SumEthnicity, String.class);	            
	      //removes all nulls 
	      ArrayList<String> al = new ArrayList<String>(); 
	      for(int i=0; i<SumGender_Ethn_Task.length; i++){ 
	      if(SumGender_Ethn_Task[i]!=null) 
	      al.add(SumGender_Ethn_Task[i]); 
	      }
	      SumGender_Ethn_Task = (String[])al.toArray(new String[al.size()]); 
	     
	      int[] Gender_Ethn_TaskCount = ArrayUtils.addAll(SumGender_StudCount, SumEthn_StudCount);
	      List<Integer> list = new ArrayList<Integer>(); 
	      for(int i=0; i<Gender_Ethn_TaskCount.length; i++){ 
	      if(Gender_Ethn_TaskCount[i] !=0)
	    	  list.add(Gender_Ethn_TaskCount[i]);	
	      } 
	      Gender_Ethn_TaskCount = Ints.toArray(list);
	      	      
	      String[] SumFilter_Task = ObjectArrays.concat(SumPF_Tasks, SumGender_Ethn_Task, String.class);	
	      int[] Sum_TaskCount = ArrayUtils.addAll(SumPF_StudCount, Gender_Ethn_TaskCount);
//	      System.out.println("All Filter task and task count on summary tab----->"+Arrays.toString(SumFilter_Task)+ "----" +Arrays.toString(Sum_TaskCount));
	       	         
	     
		//Verifying data between the summary tab and Assessment tab..
		  if(SumTotalTargetStud ==TotalTargetStud){
			  reportsLog.info("Total target student count is matching for Document: "+DocTitle);
		  }else{
			  debugLog.error("Total target student count is not matching b/w summary and assessment tab for Document: "+DocTitle);
			  debugLog.error("Expected is " +SumTotalTargetStud+ " and found is "+TotalTargetStud);
		  }
		  if(StudTotalTargetStud ==TotalTargetStud){
			  reportsLog.info("Total target student count is matching for Document: "+DocTitle);
			  System.out.println("Expected is " +StudTotalTargetStud+ " and found is "+TotalTargetStud);

		  }else{
			  debugLog.error("Total target student count is not matching b/w student and assessment tab for Document: "+DocTitle);
			  debugLog.error("Expected is " +StudTotalTargetStud+ " and found is "+TotalTargetStud);
		  }
		  if(SumTakenStudent == TotalTakenStud){
			  reportsLog.info("Total taken student count is matching for document: "+DocTitle);
		  }else{
			  debugLog.error("Total taken student count is not matching b/w summary and assessment tab for document: "+DocTitle);
			  debugLog.error("Expected is " +SumTakenStudent+ " and found is "+TotalTakenStud);
		  }
		  if(SumNotTakenStudent == TotalNonTakenStud){
			  reportsLog.info("Total not taken student count is matching for document: "+DocTitle);
		  }else{
			  debugLog.error("Total not taken student count is not matching b/w summary and assessment tab for document: "+DocTitle);
			  debugLog.error("Expected is " +SumNotTakenStudent+ " and found is "+TotalNonTakenStud);
		  }
		  if(SumQsCount==DocumentTotalQuestions){
			  reportsLog.info("Document Total questions are matching for document: "+DocTitle);
		  }else{
			  debugLog.error("Document Total questions are not matching b/w summary and Item Analysis tab for document: "+DocTitle);
			  debugLog.error("Expected is " +SumQsCount+ " and found is "+DocumentTotalQuestions);
		  }
		  if(KentroOuterScoreValues[0]== TotalAdvanceStudents){
			  reportsLog.info("Total Advance tally count is matching for Document: "+DocTitle);
		  }else{
			  debugLog.error("Total Advance tally count is not matching for Document: "+DocTitle);
			  debugLog.error("Expected is " +KentroOuterScoreValues[0]+ " and found is "+TotalAdvanceStudents);
		  }
		  if(SumPassTally == PassTallyRight){
			  reportsLog.info("Pass Tally count is matching for Document: "+DocTitle);
		  }else{
			  debugLog.error("Pass Tally count is not matching b/w summary and assessment tab  for Document: "+DocTitle);
			  debugLog.error("Expected is " +SumPassTally+ " and found is "+PassTallyRight);
		  }
		  if(KentroOuterScoreValues[2] == FailTallyRight){
			  reportsLog.info("Fail Tally count is matching for Document: "+DocTitle);
		  }else{
			  debugLog.error("Fail Tally count is not matching for Document: "+DocTitle);
			  debugLog.error("Expected is " +KentroOuterScoreValues[2]+ " and found is "+FailTallyRight);
		  }
		  if(InKentroScoreValues[0].equals(AsmtTabValues[0])){
			  reportsLog.info("Advance Plus Students count is matching for Document: "+DocTitle);
		  }else{
			  debugLog.error("Advance Plus Students count is not matching b/w summary and assessment tab for Document: "+DocTitle);
			  debugLog.error("Expected is " +InKentroScoreValues[0]+ " and found is "+AsmtTabValues[0]);
		  }
		  if(InKentroScoreValues[1].equals(AsmtTabValues[1])){
			  reportsLog.info("Advance Students count is matching for Document: "+DocTitle);
		  }else{
			  debugLog.error("Advance Students count is not matching b/w summary and assessment tab for Document: "+DocTitle);
			  debugLog.error("Expected is " +InKentroScoreValues[1]+ " and found is "+AsmtTabValues[1]);
		  }
		  if(InKentroScoreValues[2].equals(AsmtTabValues[2])){
			  reportsLog.info("Satisfactory Plus Students count is matching for Document: "+DocTitle);
		  }else{
			  debugLog.error("Satisfactory Plus Students count is not matchingb/w summary and assessment tab for Document: "+DocTitle);
			  debugLog.error("Expected is " +InKentroScoreValues[2]+ " and found is "+AsmtTabValues[2]);
		  }
		  if(InKentroScoreValues[3].equals(AsmtTabValues[3])){
			  reportsLog.info("Satisfactory Students count is matching for Document: "+DocTitle);
		  }else{
			  debugLog.error("Satisfactory Students count is not matching b/w summary and assessment tab for Document: "+DocTitle);
			  debugLog.error("Expected is " +InKentroScoreValues[3]+ " and found is "+AsmtTabValues[3]);
		  }
		  if(InKentroScoreValues[4].equals(AsmtTabValues[4])){
			  reportsLog.info("Satisfactory Bubble Students count is matching for Document: "+DocTitle);
		  }else{
			  debugLog.error("Satisfactory Bubble Students count is not matching b/w summary and assessment tab for Document: "+DocTitle);	
			  debugLog.error("Expected is " +InKentroScoreValues[4]+ " and found is "+AsmtTabValues[4]);
		  }
		  if(InKentroScoreValues[5].equals(AsmtTabValues[5])){
			  reportsLog.info("UnSatisfactory Bubble Students count is matching for Document: "+DocTitle);
		  }else{
			  debugLog.error("UnSatisfactory Bubble Students count is not matching b/w summary and assessment tab for Document: "+DocTitle);
			  debugLog.error("Expected is " +InKentroScoreValues[5]+ " and found is "+AsmtTabValues[5]);
		  }
		  if(InKentroScoreValues[6].equals(AsmtTabValues[6])){
			  reportsLog.info("UnSatisfactory Students count is matching for Document: "+DocTitle);
		  }else{
			  debugLog.error("UnSatisfactory Students count is not matching b/w summary and assessment tab for Document: "+DocTitle);
			  debugLog.error("Expected is " +InKentroScoreValues[6]+ " and found is "+AsmtTabValues[6]);
		  }
		  		  
		  for (int temp=0;temp<AsmtTabPerValues.length;temp++){			  
			  double PercDiff=Math.abs(SumTabPerValues[temp] - AsmtTabPerValues[temp]);
			  if(PercDiff > 1.0){
				  debugLog.error(AsmtTabPerc[temp]+ " Percentage is not matching b/w summary and assessment tab for document: "+DocumentTitle+" and difference value is: "+PercDiff);
			  }else{
				  reportsLog.info(AsmtTabPerc[temp]+ " Percentage is matching for Document: "+DocumentTitle);
			  }
		  }
		 		 
		  double PassPerc = Math.abs(SumPassPerc - AsmtTabPerValues1[0] );
		  if(PassPerc> 1.0){
			  debugLog.error("Pass Percentage is not matching b/w summary and assessment tab for document: "+DocumentTitle+" and difference value is: "+PassPerc); 
		  }else{
			  reportsLog.info("Pass Percentage is matching for Document: "+DocumentTitle);
		  }
		  
		  double FailPerc = Math.abs(SumTabOverallValues[2] - AsmtTabPerValues1[1]);
		  if(FailPerc > 1.0){
			  debugLog.error("Fail Percentage is not matching b/w summary and assessment tab for document: "+DocumentTitle+" and difference value is: "+PassPerc); 	
		  }else{
			 reportsLog.info("Fail Percentage is matching for Document: "+DocumentTitle);
		  }
		  
	      //verifying Program Funding, Gender and Ethnicity task count... 
	      for(int task=0; task < StudPF_Tasks.length; task++){
	    	  String StudTask = StudPF_Tasks[task].trim();
	    	  int StudCount = StudPF_StudCount[task];
	    	  for(int task1=0; task1 < SumFilter_Task.length; task1++){	
	    		  String SumTask =SumFilter_Task[task1].trim();
		            if(StudTask.equalsIgnoreCase(SumTask)){
		            	int SumCount = Sum_TaskCount[task1];
		            	if(StudCount == SumCount){
		            		reportsLog.info("count is matching for task: "+StudTask+" and "+SumTask);
		            	}else{
			               debugLog.error(StudTask.trim()+ "--" +SumTask.trim()+" PF/Gender/Ethnicity task count is not matching for document: " +DocTitle+ " Expected value on Student tab is "+StudCount+" and found value on summary tab is: "+SumCount);
			               System.out.println("count is not matching for document: " +DocTitle+ "Expected value on Student tab is "+StudCount+" and found value on summary tab is: "+SumCount);
		            	}
		            }
	    	     }  
	      }		  		 		  		  
          System.out.println("Successfully verified all the values for Document: "+DocTitle);
          reportsLog.info("Successfully verified all the values for Document: "+DocTitle);
   	      System.out.println("************************************************************************************");
	      }   
	}	
	
	@AfterClass
	public void tearDown() throws InterruptedException{
		login.findElement(By.id("gearbutton")).click();
		Thread.sleep(2000);
		login.findElement(By.id("signoutbutton")).click();
		login.quit();
	}
}
