package Script;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.collect.ObjectArrays;
import com.google.common.primitives.Ints;

public class Latest_STAAR_Main_DATA {
	
	String[][] tabArray = null;
    int rowCount, colCount;
    int SelectDocu;
	private static Logger debugLog=Logger.getLogger("STAAR_Asmt");
	private static Logger reportsLog=Logger.getLogger("reportsLog");
	

	public void DocumentVerify(String DocTitle, String AdminCode,String AdminSeries,String AdminMode,String Language, String Target, String Subject, WebDriver login) throws InterruptedException{

		//Selecting STAAR tab on assessment module..
		login.findElement(By.xpath("html/body/header/bottom/toolbar/left/div/label[2]/span")).click();  
		Thread.sleep(2000);

		List<WebElement> Demo1 = login.findElements(By.id("MaintenanceAssessmentListGrid"));	            	
		WebElement DemoData = Demo1.get(2);	       
	//	System.out.println("assessment data: "+DemoData.getText());

		WebElement ACode = DemoData.findElement(By.xpath("tr/th[4]/filter/div"));
		WebElement ACode1= ACode.findElement(By.tagName("input"));
		ACode1.clear();
		ACode1.sendKeys(AdminCode);
		ACode1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);				
		
		WebElement ASer = DemoData.findElement(By.xpath("tr/th[5]/filter/div"));
		WebElement ASer1= ASer.findElement(By.tagName("input"));
		ASer1.clear();
		ASer1.sendKeys(AdminSeries);
		ASer1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
				
		WebElement AMode = DemoData.findElement(By.xpath("tr/th[6]/filter/div"));
		WebElement AMode1= AMode.findElement(By.tagName("input"));
		AMode1.clear();
		AMode1.sendKeys(AdminMode);
		AMode1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[7]/filter/div/input")).click();
		WebElement element = login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[7]/filter/div/input"));
		element.sendKeys(DocTitle);   
		element.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		WebElement Lang = DemoData.findElement(By.xpath("tr/th[8]/filter/div"));
		WebElement Lang1= Lang.findElement(By.tagName("input"));
		Lang1.clear();
		Lang1.sendKeys(Language);
		Lang1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		WebElement Tar = DemoData.findElement(By.xpath("tr/th[9]/filter/div"));
		WebElement Target1= Tar.findElement(By.tagName("input"));
		Target1.clear();
		Target1.sendKeys(Target);
		Target1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		WebElement Sub = DemoData.findElement(By.xpath("tr/th[11]/filter/div"));
		WebElement Sub1= Sub.findElement(By.tagName("input"));
		Sub1.clear();
		Sub1.sendKeys(Subject);
		Sub1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		login.findElement(By.xpath("//*[@id='MaintenanceAssessmentListGrid']/tr/th[7]/main/left")).click();
		Thread.sleep(3000);

		//Selecting document..
		List<WebElement> sel = login.findElements(By.id("MaintenanceAssessmentListGrid"));	            	
		WebElement sel1 = sel.get(5);	   
		List mcanswers = sel1.findElements(By.className("number"));    
		System.out.println("total documents: " +mcanswers.size());
	    rowCount = mcanswers.size()-1;
	    System.out.println("row count -> "+rowCount);
		SelectDocu = mcanswers.size()-2;	  
		String RecCount = login.findElement(By.className("reccount")).getText();			   
		if(RecCount.equalsIgnoreCase("0 Records")){
//			reportsLog.info("Document not found: "+DocTitle);
//			System.out.println("Document not found: "+DocTitle);
//			login.findElement(By.id("menubutton")).click();
//			Thread.sleep(2000);
//			login.findElement(By.id("ui-id-1")).click();
//			Thread.sleep(2000);
			documentmis(DocTitle, login);
			//element.clear();
			//Thread.sleep(5000);
		}
		else{	
			AssessmentTabScoreDetails(DocTitle,login);
		}
	}
	
	public void documentmis(String DocTitle, WebDriver login) throws InterruptedException{
		System.out.println("Document not found: "+DocTitle);
		login.findElement(By.id("menubutton")).click();
		Thread.sleep(2000);
		login.findElement(By.id("ui-id-1")).click();
		Thread.sleep(3000);
	}
	
	public void AssessmentTabScoreDetails(String DocTitle, WebDriver login) throws InterruptedException{
		
		List<WebElement> AssessmentList= login.findElements(By.id("MaintenanceAssessmentListGrid"));	    	   
		WebElement AsmtData=AssessmentList.get(3);
	//	System.out.println("assessment data: "+AsmtData.getText());
		System.out.println("***************************Assessment Tab Data***************************");	  	
		//Accessing Accessing values from the Assessment tab
		
		String DocumentTitle =AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td[7]")).getText();
//		String DocumentTitle =AsmtData.findElement(By.id("Title")).getText();
		System.out.println("Document Title is: " +DocumentTitle);

		String PassTallyRight1 =AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td[13]/right")).getText();
		int PassTallyRight = Integer.parseInt(PassTallyRight1);

		String FailTallyRight1 =AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td[14]/right")).getText();
		int FailTallyRight = Integer.parseInt(FailTallyRight1);

		String TotalTakenStud1 = AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td[15]")).getText();
		int TotalTakenStud = Integer.parseInt(TotalTakenStud1.replace(",", ""));

		String TotalNonTakenStud1 = AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td[16]")).getText();
		int TotalNonTakenStud = Integer.parseInt(TotalNonTakenStud1);

		int TotalTargetStud = TotalTakenStud+TotalNonTakenStud;
		//System.out.println("Total Target students in Assessment tab: "+TotalTargetStud);

		// Get right side value on Assessment tab..   
		String AsmtTabData[]={"AdvancePlus","Advance","SatisfactoryPlus","Satisfactory","SatisfactoryBubble","UnSatisfactoryBubble","UnSatisfactory"};
		String AsmtTabValues[]=new String[AsmtTabData.length];	      
		for(int l=0;l<AsmtTabData.length;l++){
			AsmtTabValues[l] = AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td["+(l+17)+"]/right")).getText();				  
		}
		// System.out.println("Assessment tab values - AdvancePlus: "+AsmtTabValues[0] +"Advance: "+AsmtTabValues[1] +"SatisfactoryPlus: "+AsmtTabValues[2] +"Satisfactory: "+AsmtTabValues[3] +"SatisfactoryBubble: "+AsmtTabValues[4] +"UnSatisfactoryBubble: "+AsmtTabValues[5] +"UnSatisfactory: "+AsmtTabValues[6]);

		int TotalAdvanceStudents = Integer.parseInt(AsmtTabValues[0]) + Integer.parseInt(AsmtTabValues[1]);

		String Incomplete = AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td[24]")).getText();
		//	   System.out.println("Incomplete students count in Assessment tab: "+Incomplete);

		//Get left side percentage value on Assessment tab..
		String AsmtTabPerc1[]={"PassPerc","FailPerc"};
		Double AsmtTabPerValues1[]=new Double[AsmtTabPerc1.length];	      
		for(int m=0; m <AsmtTabPerc1.length ;m++){
			String AsmtPer = AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td["+(m+13)+"]/left")).getText();
			AsmtTabPerValues1[m]=Double.parseDouble(AsmtPer);
		}  	   
		//  System.out.println("Pass and Fail perc on assessment tab--------"+AsmtTabPerValues1[0]+" "+AsmtTabPerValues1[1]);

		String AsmtTabPerc[]={"AdvancePlusPerc","AdvancedPerc","SatisfactoryPlusPerc","SatisfactoryPerc","SatisfactoryBubblePerc","UnSatisfactoryBubblePerc","UnSatisfactoryPerc"};
		Double AsmtTabPerValues[]=new Double[AsmtTabPerc.length];	      
		for(int m=0;m<AsmtTabData.length;m++){
			String AsmtPer = AsmtData.findElement(By.xpath("tbody/tr["+rowCount+"]/td["+(m+17)+"]/left")).getText();
			AsmtTabPerValues[m]=Double.parseDouble(AsmtPer);
		}
		//	 System.out.println("Adv+, Adv ... perc on assessment tab--------"+AsmtTabPerValues[0]+" "+AsmtTabPerValues[1]+" "+AsmtTabPerValues[2]);

		List<WebElement> Assessment= login.findElements(By.className("go"));
		WebElement AssessmentClick=(WebElement) Assessment.get(SelectDocu); 
		AssessmentClick.click();
		System.out.println("Selected "+DocTitle+" document..");
		Thread.sleep(2000); 

		//Get total target student count on student tab.
		String StudTakenCount1 = login.findElement(By.className("reccount")).getText();
		int StudTotalTargetStud = Integer.parseInt(StudTakenCount1.replace(" Records", ""));
		//     System.out.println("target student count on student tab:  "+StudTotalTargetStud);   	   

		//PF and gender filter functionality..
		List<WebElement> allElements = login.findElements(By.name("multiselect_filterpicker")); 
		System.out.println("Filter button selected and Elements present in Filter button: " +allElements.size());  

		int[] StudPF_StudCount = new int[allElements.size()];
		String[] StudPF_Tasks = new String[allElements.size()];
/*
		for (int x =0;x<allElements.size();x++){
			StudPF_Tasks[x] = login.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).getAttribute("title"); 
			// System.out.println("task: "+StudPF_Tasks[x].trim());
		}
		int index=0;
		for(int a=0; a<StudPF_Tasks.length; a++){
			String value = StudPF_Tasks[a].trim();
			//  System.out.println(value);
			if(value.equals("All Failed")){
				index=a;
				//	System.out.println("All failed index: "+index);
			}
		}
*/ 

		for(int x = 0; x < allElements.size(); x++){
			login.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
			login.findElement(By.className("ui-multiselect-none")).click();        	   
			StudPF_Tasks[x] = login.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).getAttribute("title");  	   
			login.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).click();
			login.findElement(By.className("ui-multiselect-close")).click();
			Thread.sleep(2000);
			String FilterRowCount = login.findElement(By.className("reccount")).getText();
			StudPF_StudCount[x] = Integer.parseInt(FilterRowCount.replace(" Records", ""));
	//	    System.out.println("Selected "+StudPF_Tasks[x].trim()+" task and total records on Student tab are: "+StudPF_StudCount[x]);
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
		login.findElement(By.xpath("html/body/header/top/tabs/a[1]/tab")).click();
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
		//System.out.println("Total taken students count in summary tab:" +SumTakenStudent);

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
			//System.out.println("Program Funding task and student count on summary tab: "+SumPF_Tasks[row]+"--"+SumPF_StudCount[row]);
		}

		WebElement table_element3 =Tasks_table.get(0);
		List<WebElement> tr_collection1=table_element3.findElements(By.cssSelector(".tableswithbars>tbody>tr"));
		System.out.println("NUMBER OF ROWS IN GENDER AND ETHNICITY TABLE = "+tr_collection1.size());	  
		//System.out.println("GENDER AND ETHNICITY table: " +table_element3.getText());


		//Gender and gender count on summary tab..	 
		String[] SumGender= new String[tr_collection1.size()];
		int[] SumGender_StudCount = new int[tr_collection1.size()];
		try{
			for(int row = 2; row < 4;row++){
				SumGender[row] =table_element3.findElement(By.xpath("tbody/tr["+row+"]/td[1]/bar/text")).getText();
				String Taken_Stud = table_element3.findElement(By.xpath("tbody/tr["+row+"]/td[11]")).getText();
				String No_Data = table_element3.findElement(By.xpath("tbody/tr["+row+"]/td[12]")).getText();
				SumGender_StudCount[row] = (Integer.parseInt(Taken_Stud) + Integer.parseInt(No_Data));
				//   System.out.println(SumGender[row]+" student count on summary tab: "+SumGender_StudCount[row]);
			}	 
		}catch (Exception e){
			System.out.println("Gender not found");
		}

		//Ethnicity tasks and student count on summary tab..
		String[] SumEthnicityTask= new String[tr_collection1.size()+1];	
		int[] SumEthn_StudCount = new int[tr_collection1.size()+1];
		int max=tr_collection1.size()+1;

		for (int x =0;x<tr_collection1.size();x++){
			SumEthnicityTask[x] = table_element3.findElement(By.xpath("tbody/tr["+(x+1)+"]/td")).getText(); 
			 //  System.out.println("ethnitask: "+SumEthnicityTask[x].trim());
		}

		int index1=0;
		for(int a=0; a<SumEthnicityTask.length; a++){
			String value = SumEthnicityTask[a].trim();
			//  System.out.println(value);
			if(value.equals("Ethnicity")){
				index1=a;
		    // 	  System.out.println("Ethnicity index: "+index1); 
		     	 break;
			}
		}
		int EthnIndex = index1 + 2;
		String[] SumEthnicity = new String[tr_collection1.size()+1];
		for(int row = EthnIndex; row < max;row++){	    	  
			SumEthnicity[row] =table_element3.findElement(By.xpath("tbody/tr["+row+"]/td[1]/bar/text")).getText();
			String Taken_Stud = table_element3.findElement(By.xpath("tbody/tr["+row+"]/td[11]")).getText();
			String No_Data = table_element3.findElement(By.xpath("tbody/tr["+row+"]/td[12]")).getText();
			SumEthn_StudCount[row] = Integer.parseInt(Taken_Stud) + Integer.parseInt(No_Data);
	//	    System.out.println(SumEthnicity[row]+" student count on summary tab: "+SumEthn_StudCount[row]);
		}

		String[] SumGender_Ethn_Task = ObjectArrays.concat(SumGender, SumEthnicity, String.class);	            
		//removes all nulls 
		ArrayList<String> al = new ArrayList<String>(); 
		for(int i=0; i<SumGender_Ethn_Task.length; i++){ 
			if(SumGender_Ethn_Task[i]!=null) 
				al.add(SumGender_Ethn_Task[i]); 
		}
		SumGender_Ethn_Task = (String[])al.toArray(new String[al.size()]);
		//   System.out.println("-->"+Arrays.toString(SumGender_Ethn_Task));

		int[] Gender_Ethn_TaskCount = ArrayUtils.addAll(SumGender_StudCount, SumEthn_StudCount);
		List<Integer> list = new ArrayList<Integer>(); 
		for(int i=0; i<Gender_Ethn_TaskCount.length; i++){ 
			if(Gender_Ethn_TaskCount[i] !=0)
				list.add(Gender_Ethn_TaskCount[i]);	
		} 
		Gender_Ethn_TaskCount = Ints.toArray(list);

		String[] SumFilter_Task = ObjectArrays.concat(SumPF_Tasks, SumGender_Ethn_Task, String.class);	
		int[] Sum_TaskCount = ArrayUtils.addAll(SumPF_StudCount, Gender_Ethn_TaskCount);
		//  System.out.println("All Filter task and task count on summary tab----->"+Arrays.toString(SumFilter_Task)+ "----" +Arrays.toString(Sum_TaskCount));

/*		if(SumFilter_Task.length == index){
			reportsLog.info("tasks length is matching "+SumFilter_Task.length+" "+index);
		}else{
			debugLog.error("PF/Gender/Ethnicity tasks count is not matching b/w summary and student filter option for document: "+DocTitle+" Admin Mode-> "+AdminMode+" Admin Series-> "+AdminSeries+" task length on summary tab is "+SumFilter_Task.length+" and found task on student filter option is: "+index);
		}

		//verifying Program Funding, Gender and Ethnicity task count... 
		for(int task=0; task < StudPF_Tasks.length; task++){	    	  
			String StudTask = StudPF_Tasks[task].trim();
			//	  System.out.println("Printing Dereferred Item...."+StudTask);
			if (StudTask.equals("NA")) continue;	
			int StudCount = StudPF_StudCount[task];
			for(int task1=0; task1 < SumFilter_Task.length; task1++){	
				String SumTask =SumFilter_Task[task1].trim();
				if(StudTask.equalsIgnoreCase(SumTask)){
					int SumCount = Sum_TaskCount[task1];
					if(StudCount == SumCount){
						reportsLog.info("count is matching for task: "+StudTask+" and "+SumTask);
					}else{
						debugLog.error(StudTask.trim()+ "--" +SumTask.trim()+" PF/Gender/Ethnicity task count is not matching" for document: " +DocTitle+" Admin Mode-> "+AdminMode+" Admin Series-> "+AdminSeries+" Expected value on Student tab is "+StudCount+" and found value on summary tab is: "+SumCount);
					}
				}
			}  
		}	
*/	
		//Verifying data between the summary tab and Assessment tab..
				if(SumTotalTargetStud ==TotalTargetStud){
					reportsLog.info("Total target student count is matching for Document: "+DocTitle);
				}else{												
					//Total Questions : Summary tab  46 != Item Analysis tab 0
					debugLog.error("Target Student count : Summary tab "+SumTotalTargetStud+" != Assessment tab "+TotalTargetStud);
				}
				if(StudTotalTargetStud ==TotalTargetStud){
					reportsLog.info("Total target student count is matching for Document: "+DocTitle);
				}else{
					debugLog.error("Target student count : Student tab "+StudTotalTargetStud+" != Assessment tab "+TotalTargetStud);
				}
				if(SumTakenStudent == TotalTakenStud){
					reportsLog.info("Total taken student count is matching for document: "+DocTitle);
				}else{
					debugLog.error("Taken Student : Summary tab "+SumTakenStudent+" != Assesssment tab "+ TotalTakenStud);
				}
				if(SumNotTakenStudent == TotalNonTakenStud){
					reportsLog.info("Total not taken student count is matching for document: "+DocTitle);
				}else{
					debugLog.error("Not Taken Student : Summary tab "+SumNotTakenStudent+" != Assessment tab "+TotalNonTakenStud);
				}
				if(SumQsCount==DocumentTotalQuestions){
					reportsLog.info("Document Total questions are matching for document: "+DocTitle);
				}else{
					debugLog.error("Total Questions : Summary tab "+SumQsCount+" != Item Analysis tab "+DocumentTotalQuestions);
				}
				if(KentroOuterScoreValues[0]== TotalAdvanceStudents){
					reportsLog.info("Total Advance tally count is matching for Document: "+DocTitle);
				}else{
					debugLog.error("Total Advance Tally : Summary tab "+KentroOuterScoreValues[0]+" != Assessment tab "+TotalAdvanceStudents);
				}
				if(SumPassTally == PassTallyRight){
					reportsLog.info("Pass Tally count is matching for Document: "+DocTitle);
				}else{
					debugLog.error("Pass Tally : Summary tab "+SumPassTally+" != Assessment tab "+PassTallyRight);
				}
				if(KentroOuterScoreValues[2] == FailTallyRight){
					reportsLog.info("Fail Tally count is matching for Document: "+DocTitle);
				}else{
					debugLog.error("Fail Tally : Summary tab "+KentroOuterScoreValues[2]+" != Assessment tab "+FailTallyRight);
				}
				if(InKentroScoreValues[0].equals(AsmtTabValues[0])){
					reportsLog.info("Advance Plus Students count is matching for Document: "+DocTitle);
				}else{
					debugLog.error("Advance Plus : Summary tab "+InKentroScoreValues[0]+" != Assessment tab "+AsmtTabValues[0]);
				}
				if(InKentroScoreValues[1].equals(AsmtTabValues[1])){
					reportsLog.info("Advance Students count is matching for Document: "+DocTitle);
				}else{
					debugLog.error("Advance : Summary tab "+InKentroScoreValues[1]+" != Assessment tab "+AsmtTabValues[1]);
				}
				if(InKentroScoreValues[2].equals(AsmtTabValues[2])){
					reportsLog.info("Satisfactory Plus Students count is matching for Document: "+DocTitle);
				}else{
					debugLog.error("Satisfactory Plus : Student tab "+InKentroScoreValues[2]+ " != Assessment tab "+AsmtTabValues[2]);
				}
				if(InKentroScoreValues[3].equals(AsmtTabValues[3])){
					reportsLog.info("Satisfactory Students count is matching for Document: "+DocTitle);
				}else{
					debugLog.error("Satisfactory : Summary tab "+InKentroScoreValues[3]+" != assessment tab "+AsmtTabValues[3]);
				}
				if(InKentroScoreValues[4].equals(AsmtTabValues[4])){
					reportsLog.info("Satisfactory Bubble Students count is matching for Document: "+DocTitle);
				}else{
					debugLog.error("Satisfactory Bubble : Summary tab "+InKentroScoreValues[4]+" != assessment tab "+AsmtTabValues[4]);
				}
				if(InKentroScoreValues[5].equals(AsmtTabValues[5])){
					reportsLog.info("UnSatisfactory Bubble Students count is matching for Document: "+DocTitle);
				}else{
					debugLog.error("UnSatisfactory Bubble : Summary tab "+InKentroScoreValues[5]+" != Assessment tab "+AsmtTabValues[5]);
				}
				if(InKentroScoreValues[6].equals(AsmtTabValues[6])){
					reportsLog.info("UnSatisfactory Students count is matching for Document: "+DocTitle);
				}else{
					debugLog.error("UnSatisfactory : Summary tab " +InKentroScoreValues[6]+ " != Assessment tab "+AsmtTabValues[6]);
				}

				for (int temp=0;temp<AsmtTabPerValues.length;temp++){			  
					double PercDiff=Math.abs(SumTabPerValues[temp] - AsmtTabPerValues[temp]);
					if(PercDiff > 1.0){
						debugLog.error(AsmtTabPerc[temp]+ " Percentage != summary and assessment tab and difference value is: "+PercDiff);
					}else{
						reportsLog.info(AsmtTabPerc[temp]+ " Percentage is matching ");
					}
				}

				double PassPerc = Math.abs(SumPassPerc - AsmtTabPerValues1[0] );
				if(PassPerc> 1.0){
					debugLog.error("Pass Percentage : Summary tab != assessment tab and difference value is: "+PassPerc); 
				}else{
					reportsLog.info("Pass Percentage is matching");
				}

				double FailPerc = Math.abs(SumTabOverallValues[2] - AsmtTabPerValues1[1]);
				if(FailPerc > 1.0){
					debugLog.error("Fail Percentage : Summary tab != assessment tab and difference value is: "+PassPerc); 	
				}else{
					reportsLog.info("Fail Percentage is matching");
				}

				if(SumFilter_Task.length == allElements.size()){
					reportsLog.info("tasks length is matching "+SumFilter_Task.length+" "+allElements.size());
				}else{
					debugLog.error("Student filter: Student tab filter count "+allElements.size()+" != summary tab filter "+SumFilter_Task.length);
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
								debugLog.error("Filter tab task ->"+StudTask.trim()+ ": Student tab filter task "+StudCount+" != Summary tab "+SumCount);
							//	System.out.println("count is not matching for document: " +DocTitle+ "Expected value on Student tab is "+StudCount+" and found value on summary tab is: "+SumCount);
							}
						}
					}  
				}

			
		
		//	      //verifying Program Funding, Gender and Ethnicity task count... 
		//	      for(int task=0; task < StudPF_Tasks.length; task++){
		//	    	  String StudTask = StudPF_Tasks[task].trim();
		//	    	 
		//	    	  int StudCount = StudPF_StudCount[task];
		//	    	  for(int task1=0; task1 < SumFilter_Task.length; task1++){	
		//	    		  String SumTask =SumFilter_Task[task1].trim();
		//		            if(StudTask.equalsIgnoreCase(SumTask)){
		//		            	int SumCount = Sum_TaskCount[task1];
		//		            	if(StudCount == SumCount){
		//		            	   reportsLog.info("count is matching for task: "+StudTask+" and "+SumTask);
		//		            	}else{
		//			               debugLog.error(StudTask.trim()+ "--" +SumTask.trim()+" PF/Gender/Ethnicity task count is not matching for document: " +DocTitle+ " Expected value on Student tab is "+StudCount+" and found value on summary tab is: "+SumCount);
		//			               System.out.println("count is not matching for document: " +DocTitle+ "Expected value on Student tab is "+StudCount+" and found value on summary tab is: "+SumCount);
		//		            	}
		//		            }
		//	    	     }  
		//	      }		  		 		  		  
//		System.out.println("Successfully verified all the values for Document: "+DocTitle);
		DemoGraphySummaryVerify(login);
	}
				
				
//demography tab verification code..
	public void DemoGraphySummaryVerify(WebDriver login) throws InterruptedException{
		//Demographic tab details... 
		login.findElement(By.xpath("html/body/header/top/tabs/a[5]/tab")).click();
		List<WebElement> Demo = login.findElements(By.id("MaintenanceSTAARAssessmentDemogBreakdown"));	            	
		WebElement DemoData = Demo.get(2);	       
  //      System.out.println("assessment data: "+DemoData.getText());

		//Filtering and selecting Program Funding details..
		WebElement web = DemoData.findElement(By.xpath("tr/th[3]/filter/div"));
		WebElement Group= web.findElement(By.tagName("input"));
		Group.clear();
		Group.sendKeys("Program Funding");
		Group.sendKeys(Keys.ENTER);
		Thread.sleep(3000); 
		List PF = login.findElements(By.className("number"));     
		// System.out.println("total PF tasks... " +PF.size());
		WebElement task = Demo.get(3);	

		String[] DemographyPF_Task = new String[PF.size()];
		int[] DemoPF_NotTaken = new int[PF.size()];
		int[] DemoPF_Taken = new int[PF.size()];
		for(int i=1; i< PF.size();i++){
			DemographyPF_Task[i]=  task.findElement(By.xpath("tbody/tr["+i+"]/td[4]")).getText();
			String notTaken = task.findElement(By.xpath("tbody/tr["+i+"]/td[8]/left")).getText();
			DemoPF_NotTaken[i] = Integer.parseInt(notTaken);	
			String Taken = task.findElement(By.xpath("tbody/tr["+i+"]/td[7]/left")).getText();
			DemoPF_Taken[i] = Integer.parseInt(Taken.replaceAll(" of [\\d]+[A-Za-z]?", ""));
		//	System.out.println("Demo tab task: " +DemographyPF_Task[i]+" not taken student count: "+DemoPF_NotTaken[i]+ " and taken student count:  "+DemoPF_Taken[i]);
		}

		String DemoDataSet1[]={"Pass","Fail"};
		int ROWS = PF.size();
		int COLS = DemoDataSet1.length;
		int[][] rowValue = new int[ROWS][COLS];
		for(int i=0;i<PF.size()-1;i++){
			for(int j=0;j<DemoDataSet1.length;j++){
				String Demovalue = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+5)+"]/left")).getText();
				rowValue[i][j] = Integer.parseInt(Demovalue);
				//	 System.out.println(rowValue[i][j]);
			}		
		}

		String DemoDataSet2[]={"AdvancePlus","Advance","SatisfactoryPlus","Satisfactory","SatisfactoryBubble","UnSatisfactoryBubble","UnSatisfactory"};
		int COLS1 = DemoDataSet2.length;
		int[][] rowValue1 = new int[ROWS][COLS1];
		int[][] PF_passFailArray = new int[ROWS][2];
		for(int i=0;i<PF.size()-1;i++){
			int passValue = 0;
			int failValue = 0;
			for(int j=0;j<DemoDataSet2.length;j++){
				String Demovalue1 = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+9)+"]/left")).getText();
				rowValue1[i][j] = Integer.parseInt(Demovalue1);
				//System.out.println(rowValue1[i][j]);
				if(j<5){
					passValue += rowValue1[i][j];
				}else if(j<7){
					failValue += rowValue1[i][j];
				}
			}
			for(int k=0; k<2; k++){
				if(k == 0){
					PF_passFailArray[i][k] = passValue;
					//	 System.out.println("PassFailArray pass Value " + PF_passFailArray[i][k]);
				}else{
					PF_passFailArray[i][k] = failValue;
					//	 System.out.println("PassFailArray fail Value " + PF_passFailArray[i][k]);
				}					 
			}		
		}

		//verifying PF pass and fail count on demography tab..
		for(int i=1;i<DemographyPF_Task.length;i++){
			String DemographyPF_Task1 = DemographyPF_Task[i];
			for (int l = 0 ; l<rowValue[i-1].length; l++){
				if(Arrays.equals(rowValue[i-1], PF_passFailArray[i-1])){
					reportsLog.info("pass and fail values are matching for PF task.." +rowValue[i-1][l]+ " "+PF_passFailArray[i-1][l] );				
				}else{
					debugLog.error("Program Funding -> "+DemographyPF_Task1+" pass/fail "+rowValue[i-1][l]+"!= with the sum of levels on Demography tab " +PF_passFailArray[i-1][l]);
				}
			}
		}

		// Filtering and selecting gender details..s
		WebElement test = login.findElement(By.xpath("//*[@id='MaintenanceSTAARAssessmentDemogBreakdown']/tr/th[3]/filter/div/input"));
		test.clear();
		test.sendKeys("Gender");
		test.sendKeys(Keys.ENTER);
		Thread.sleep(4000); 
		List Gender = login.findElements(By.className("number"));     
		//System.out.println("total  ...: " +Gender.size());

		String[] GenderOption = new String[Gender.size()];
		int[] DemoGen_NotTaken = new int[Gender.size()];
		int[] DemoGen_Taken = new int[Gender.size()];
		for(int i=1; i< Gender.size();i++){
			GenderOption[i]=  task.findElement(By.xpath("tbody/tr["+i+"]/td[4]")).getText();
			String notTaken = task.findElement(By.xpath("tbody/tr["+i+"]/td[8]/left")).getText();
			DemoGen_NotTaken[i] = Integer.parseInt(notTaken);	
			String Taken = task.findElement(By.xpath("tbody/tr["+i+"]/td[7]/left")).getText();
			DemoGen_Taken[i] = Integer.parseInt(Taken.replaceAll(" of [\\d]+[A-Za-z]?", ""));
		//    System.out.println("Demo tab task: " +GenderOption[i]+" not taken student count : "+DemoGen_NotTaken[i]+" and taken student count: "+DemoGen_Taken[i]);
		}

		//removes all nulls 
		ArrayList<String> al = new ArrayList<String>(); 
		for(int i=0; i<GenderOption.length; i++){ 
			if(GenderOption[i]!=null) 
				al.add(GenderOption[i]); 
		}
		GenderOption = (String[])al.toArray(new String[al.size()]);
		//	System.out.println("--> "+Arrays.toString(GenderOption));

		int GenROWS = Gender.size();
		int[][] GenrowValue = new int[GenROWS][COLS];
		for(int i=0;i<Gender.size()-1;i++){
			for(int j=0;j<DemoDataSet1.length;j++){
				String Demovalue = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+5)+"]/left")).getText();
				GenrowValue[i][j] = Integer.parseInt(Demovalue);
				//	 System.out.println(GenrowValue[i][j]);
			}		
		}

		int[][] GenderRowValue1 = new int[GenROWS][COLS1];
		int[][] Gender_passFailArray = new int[GenROWS][2];
		for(int i=0;i<Gender.size()-1;i++){
			int passValue = 0;
			int failValue = 0;
			for(int j=0;j<DemoDataSet2.length;j++){
				String Demovalue1 = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+9)+"]/left")).getText();
				GenderRowValue1[i][j] = Integer.parseInt(Demovalue1);
				//  System.out.println("-->"+GenderRowValue1[i][j]);
				if(j<5){
					passValue += GenderRowValue1[i][j];
				}else if(j<7){
					failValue += GenderRowValue1[i][j];
				}
			}
			for(int k=0; k<2; k++){
				if(k == 0){
					Gender_passFailArray[i][k] = passValue;
					//	 System.out.println("Pass and Fail Values of Gender task " + Gender_passFailArray[i][k]);
				}else{
					Gender_passFailArray[i][k] = failValue;
					//	 System.out.println("Pass and Fail Values of Gender task " + Gender_passFailArray[i][k]);
				}					 
			}		
		}

		//verifying Gender pass and fail count on demography tab..
		for(int i=0;i<GenderOption.length;i++){
			String GenderOption1 = GenderOption[i];
			for (int l = 0 ; l<GenrowValue[i].length ; l++){
				if(Arrays.equals(GenrowValue[i], Gender_passFailArray[i])){
					reportsLog.info("pass and fail values are matching for gender task.."+GenrowValue[i][l]+" "+Gender_passFailArray[i][l]);				
				}else{
					debugLog.error("Gender -> "+GenderOption1+" pass/fail "+GenrowValue[i][l]+" != with the sum of levels on Demography tab "+Gender_passFailArray[i][l]);
				}
			}
		}

		// Filtering and selecting Ethnicity details..
		WebElement test1 = login.findElement(By.xpath("//*[@id='MaintenanceSTAARAssessmentDemogBreakdown']/tr/th[3]/filter/div/input"));
		test1.clear();
		test1.sendKeys("Ethnicity");
		test1.sendKeys(Keys.ENTER);
		Thread.sleep(3000); 
		List Ethnicity = login.findElements(By.className("number"));     
		//	System.out.println("total  ...: " +Ethnicity.size());

		String[] EthnicityOption = new String[Ethnicity.size()];
		int[] DemoEth_NotTaken = new int[Ethnicity.size()];
		int[] DemoEth_Taken = new int[Ethnicity.size()];
		for(int i=0; i< Ethnicity.size()-1;i++){
			EthnicityOption[i]=  task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td[4]")).getText();
			String notTaken = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td[8]/left")).getText();
			DemoEth_NotTaken[i] = Integer.parseInt(notTaken);
			String Taken = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td[7]/left")).getText();
			DemoEth_Taken[i] = Integer.parseInt(Taken.replaceAll(" of [\\d]+[A-Za-z]?", ""));
		//    System.out.println("Demo tab task: "+EthnicityOption[i] +" not taken student count: "+DemoEth_NotTaken[i]+" and taken student count: "+DemoEth_Taken[i]);
		}

		//removes all nulls 
		ArrayList<String> ethn = new ArrayList<String>(); 
		for(int i=0; i<EthnicityOption.length; i++){ 
			if(EthnicityOption[i]!=null) 
				ethn.add(EthnicityOption[i]); 
		}
		EthnicityOption = (String[])ethn.toArray(new String[ethn.size()]);
		//   System.out.println("demo ethn task--> "+Arrays.toString(EthnicityOption));

		int EthnicityROWS = Ethnicity.size();
		int[][] EthrowValue = new int[EthnicityROWS][COLS];
		for(int i=0;i<Ethnicity.size()-1;i++){
			for(int j=0;j<DemoDataSet1.length;j++){
				String Demovalue = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+5)+"]/left")).getText();
				EthrowValue[i][j] = Integer.parseInt(Demovalue);
				//System.out.println(EthrowValue[i][j]);
			}		
		}

		int[][] EthnicityRowValue1 = new int[EthnicityROWS][COLS1];
		int[][] Ethnicity_passFailArray = new int[EthnicityROWS][2];
		for(int i=0;i<Ethnicity.size()-1;i++){
			int passValue = 0;
			int failValue = 0;
			for(int j=0;j<DemoDataSet2.length;j++){
				String Demovalue1 = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+9)+"]/left")).getText();
				EthnicityRowValue1[i][j] = Integer.parseInt(Demovalue1);
				//    System.out.println(EthnicityRowValue1[i][j]);
				if(j<5){
					passValue += EthnicityRowValue1[i][j];
				}else if(j<7){
					failValue += EthnicityRowValue1[i][j];
				}
			}	
			for(int k=0; k<2; k++){
				if(k == 0){
					Ethnicity_passFailArray[i][k] = passValue;
					//	 System.out.println("Pass and Fail Values of ethnicity task " + Ethnicity_passFailArray[i][k]);
				}else{
					Ethnicity_passFailArray[i][k] = failValue;
					//	 System.out.println("Pass and Fail Values of ethnicity task " + Ethnicity_passFailArray[i][k]);
				}					 
			}	
		}

		//verifying Ethnicity pass and fail count on demography tab..
		for(int i=0;i<EthnicityOption.length;i++){
			String EthnicityOption1 = EthnicityOption[i];
			for (int l = 0 ; l<EthrowValue[i].length ; l++){
				if(Arrays.equals(EthrowValue[i], Ethnicity_passFailArray[i])){
					reportsLog.info("pass and fail values are matching for ethnicity task.."+EthrowValue[i][l]+" "+Ethnicity_passFailArray[i][l]);				
				}else{
					debugLog.error("Ethnicity -> "+EthnicityOption1+" pass/fail "+EthrowValue[i][l]+" != with the sum of levels on Demography tab "+Ethnicity_passFailArray[i][l]);
				}
			}
		}

		login.findElement(By.xpath("html/body/header/top/tabs/a[1]/tab")).click();
		System.out.println("Summary tab Clicked"); 

		//Accessing values from the passing criteria table  
		List<WebElement> Tasks_table = login.findElements(By.className("tableswithbars"));		  
		WebElement table_element1=Tasks_table.get(1);
		List<WebElement> tr_collection=table_element1.findElements(By.cssSelector(".tableswithbars>tbody>tr"));
//		System.out.println("NUMBER OF ROWS IN PROGRAM FUNDING TABLE = "+tr_collection.size());	        

		//Program Funding tasks and student count on summary tab..	 
		String[] SumPF_Tasks= new String[tr_collection.size()+1];
		int[] Sum_NotTaken = new int[tr_collection.size()+1];
		int[] SumPF_Taken = new int[tr_collection.size()+1];
		for(int row = 1;row < tr_collection.size()+1; row++){
			SumPF_Tasks[row] = table_element1.findElement(By.xpath("tbody/tr["+row+"]/td[1]/bar/text")).getText();
			String NotTaken = table_element1.findElement(By.xpath("tbody/tr["+row+"]/td[12]")).getText();
			Sum_NotTaken[row] = Integer.parseInt(NotTaken);
			String Taken =  table_element1.findElement(By.xpath("tbody/tr["+row+"]/td[11]")).getText();
			SumPF_Taken[row] = Integer.parseInt(Taken);
		//	System.out.println("Summary tab PF task: "+SumPF_Tasks[row]+" not taken: "+Sum_NotTaken[row]+" and taken count: "+SumPF_Taken[row]);
		}

		int srow = tr_collection.size();
		int scolm = 7;
		int[][] SumPF_val = new int[srow][scolm];     
		for(int i=0;i < srow;i++){
			for(int j=0;j<scolm;j++){
				String Demovalue1 = table_element1.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+3)+"]")).getText();
				SumPF_val[i][j] = Integer.parseInt(Demovalue1);
				//	System.out.println(SumPF_val[i][j]); 			    	 
			}	
			//	System.out.println("-------------");
		}

		WebElement table_element =Tasks_table.get(0);
		List<WebElement> tr_collection1=table_element.findElements(By.cssSelector(".tableswithbars>tbody>tr"));
//		System.out.println("NUMBER OF ROWS IN GENDER AND ETHNICITY TABLE = "+tr_collection1.size());	  

		//Gender and gender count on summary tab..	 
		String[] SumGender= new String[3];
		int[] SumGen_NotTaken = new int[3];
		int[] SumGen_Taken = new int[3];
		try{
			for(int row = 1; row < 3;row++){
				SumGender[row] =table_element.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[1]/bar/text")).getText();
				String NotTaken1 = table_element.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[12]")).getText();
				SumGen_NotTaken[row] = Integer.parseInt(NotTaken1); 
				String Taken = table_element.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[11]")).getText();
				SumGen_Taken[row] = Integer.parseInt(Taken); 
		//		System.out.println("Summary  tab gender: " +SumGender[row]+" not taken: "+SumGen_NotTaken[row]+" and taken student count: "+SumGen_Taken[row]);
			}	 
		}catch (Exception e){
			System.out.println("Gender not found: " +Arrays.toString(SumGender));
		}

		//removes all nulls 
		ArrayList<String> al1 = new ArrayList<String>(); 
		for(int i=0; i<SumGender.length; i++){ 
			if(SumGender[i]!=null) 
				al1.add(SumGender[i]); 
		}
		SumGender = (String[])al1.toArray(new String[al1.size()]);
		//    System.out.println("summary--> "+Arrays.toString(SumGender));

		int Genrow = tr_collection1.size();
		int[][] SumGender_val = new int[Genrow][scolm];     
		try{
			for(int i=0;i < 2;i++){
				for(int j=0;j<scolm;j++){
					String Demovalue1 = table_element.findElement(By.xpath("tbody/tr["+(i+2)+"]/td["+(j+3)+"]")).getText();
					SumGender_val[i][j] = Integer.parseInt(Demovalue1); 
					//		System.out.println(SumGender_val[i][j]); 			    	 
				}	
				//	System.out.println("------>");
			}
		}catch (Exception e){
			System.out.println("Gender not found: " +Arrays.toString(SumGender));
		}

		//Ethnicity tasks and not taken student count on summary tab..
		String[] SumEthnicity= new String[tr_collection1.size()+1];	
		int[] SumEth_NotTaken = new int[tr_collection1.size()+1];
		int[] SumEth_Taken = new int[tr_collection1.size()+1];
		int max1=  tr_collection1.size() + 1;
		for(int row = 0; row < max1-5;row++){	    	  
			SumEthnicity[row] =table_element.findElement(By.xpath("tbody/tr["+(row+5)+"]/td[1]/bar/text")).getText();	
			String NotTaken1 = table_element.findElement(By.xpath("tbody/tr["+(row+5)+"]/td[12]")).getText();
			SumEth_NotTaken[row] = Integer.parseInt(NotTaken1); 
			String Taken1 = table_element.findElement(By.xpath("tbody/tr["+(row+5)+"]/td[11]")).getText();
			SumEth_Taken[row] = Integer.parseInt(Taken1); 
	//		System.out.println(SumEthnicity[row]+"---"+SumEth_NotTaken[row]+"---"+SumEth_Taken[row]);
		}

		//removes all nulls 
		ArrayList<String> Sumethn = new ArrayList<String>(); 
		for(int i=0; i<SumEthnicity.length; i++){ 
			if(SumEthnicity[i]!=null) 
				Sumethn.add(SumEthnicity[i]); 
		}
		SumEthnicity = (String[])Sumethn.toArray(new String[Sumethn.size()]);
	//	System.out.println("sum ethn task--> "+Arrays.toString(SumEthnicity));

		int[][] SumEthnicity_val = new int[Genrow+1][scolm];     
		for(int i=0;i < max1-5;i++){
			for(int j=0;j<scolm;j++){
				String Demovalue1 = table_element.findElement(By.xpath("tbody/tr["+(i+5)+"]/td["+(j+3)+"]")).getText();
				SumEthnicity_val[i][j] = Integer.parseInt(Demovalue1); 
				//	System.out.println("ethnicity: "+SumEthnicity_val[i][j]); 			    	 
			}	
			//	System.out.println("------>");
		}

		//verifying Gender task count... 
		for(int i=1; i <GenderOption.length+1; i++){
			String DemoGenderTask = GenderOption[i-1];
			int DemoGen_TakenCount = DemoGen_Taken[i];
			int DemoGen_NotTakenCount = DemoGen_NotTaken[i];
			for(int j=1; j < SumGender.length+1; j++){	
				String SumGenderTask =SumGender[j-1];
				int SumGen_TakenCount = SumGen_Taken[j];
				int SumGen_NotTakenCount = SumGen_NotTaken[j];
				if(DemoGenderTask.equalsIgnoreCase(SumGenderTask)){
					if(Arrays.equals(GenderRowValue1[i-1], SumGender_val[j-1])){
						reportsLog.info("Matched Demography gender task: "+DemoGenderTask+" and Summary gender task:  "+ SumGenderTask);
					}else{
						debugLog.error("Gender -> "+DemoGenderTask+" student count mismatch between Demography and Summary tab");
					}
					if(DemoGen_NotTakenCount == SumGen_NotTakenCount){
						reportsLog.info("gender task not taken student count is matching for task: "+DemoGenderTask+" and "+SumGenderTask);
					}else{
						debugLog.error("Gender -> "+DemoGenderTask+" Not taken : Summary tab "+SumGen_NotTakenCount+" != Demography tab "+DemoGen_NotTakenCount);
					}
					if(DemoGen_TakenCount == SumGen_TakenCount){
						reportsLog.info("gender task taken student count is matching for task: "+DemoGenderTask+" and "+SumGenderTask);
					}else{
						debugLog.error("Gender -> "+SumGenderTask+" taken student : Summary tab "+SumGen_TakenCount+" != Demography tab "+DemoGen_TakenCount);
					}
				}
			}
		}

		//Verifying Ethnicity task..
		for(int i=0;i<EthnicityOption.length; i++){
			String DemoEthTask = EthnicityOption[i];
			int DemoEth_TakenCount = DemoEth_Taken[i] ; 
			int DemoEth_NotTakenCount = DemoEth_NotTaken[i];
			for(int j=0;j<SumEthnicity.length;j++){
				String SumEthTask =SumEthnicity[j];
				int SumEth_TakenCount = SumEth_Taken[j] ; 
				int SumEth_NotTakenCount = SumEth_NotTaken[j];
				if(DemoEthTask.equalsIgnoreCase(SumEthTask)){	
					if(Arrays.equals(EthnicityRowValue1[i], SumEthnicity_val[j])){
						reportsLog.info("Matched Demography ethnicity task:  "+DemoEthTask+" and Summary ethnicity task: "+ SumEthTask);
					}else{
						debugLog.error("Ethnicity -> "+DemoEthTask+" student count mismatch between Demography and Summary tab");
					}
					if(DemoEth_NotTakenCount == SumEth_NotTakenCount){
						reportsLog.info("Ethnicity task not taken student count is matching for task: "+DemoEthTask+" and "+SumEthTask);
					}else{
						debugLog.error("Ethnicity -> "+DemoEthTask+" Not taken student : Summary tab "+SumEth_NotTakenCount+" != Demography tab "+DemoEth_NotTakenCount);
					}
					if(DemoEth_TakenCount == SumEth_TakenCount){
						reportsLog.info("Ethnicity taken student count is matching for task: "+DemoEthTask+" and "+SumEthTask);
					}else{
						debugLog.error("Ethnicity -> "+DemoEthTask+" taken student : Summary tab"+SumEth_TakenCount+" != Demography tab "+DemoEth_TakenCount);
					}
				}
			}
		}

		//verifying Program Funding task count... 
		for(int i=1; i <DemographyPF_Task.length; i++){
			String DemoTask = DemographyPF_Task[i];
			int DemoPF_NotTakenCount = DemoPF_NotTaken[i];
			int DemoPF_TakenCount = DemoPF_Taken[i] ;
			for(int j=1; j < SumPF_Tasks.length; j++){	
				String SumTask =SumPF_Tasks[j];
				if(DemoTask.equalsIgnoreCase(SumTask)){
					int SumPF_NotTakenCount = Sum_NotTaken[j];
					int SumPF_TakenCount = SumPF_Taken[j];
					if(Arrays.equals(rowValue1[i-1], SumPF_val[j-1])){
						reportsLog.info("Matched Demography PF task: "+DemoTask+" and Summary PF task: "+ SumTask);
					}else{
						debugLog.error("Program Funding -> "+DemoTask+" student count mismatch between Demography and Summary tab");
					}		            	
					if(DemoPF_NotTakenCount == SumPF_NotTakenCount){
						reportsLog.info("PF not taken student count is matching for task: "+DemoTask+" and "+SumTask);
					}else{
						debugLog.error("Program Funding -> "+DemoTask+" Not taken: Demography tab "+DemoPF_NotTakenCount+" != Summary tab "+SumPF_NotTakenCount);
					}
					if(DemoPF_TakenCount == SumPF_TakenCount){
						reportsLog.info("PF taken student count is matching for task: "+DemoTask+" and "+SumTask);
					}else{
						debugLog.error("Program Funding -> "+DemoTask+" taken student : Demography tab "+DemoPF_TakenCount+" != summary tab "+SumPF_TakenCount);
					}
					
//      System.out.println("Printing Demo Values");
//	  for (int i=0 ; i< rowValue1[i-1].length-1; i++) System.out.println(rowValue1[i-1][i]);	            	
//	  System.out.println("Printing Summary Values");
//	  for (int i=0 ; i< SumPF_val[j-1].length-1; i++) System.out.println(SumPF_val[j-1][i]);

//					for (int i = 0 ; i<rowValue1[i-1].length ; i++){
//						  if(rowValue1[i-1][i] == SumPF_val[j-1][i]){
//							System.out.println("Matched "+rowValue1[i-1][i]+" and "+ SumPF_val[j-1][i]);
//							continue ; 
//						  }else{
//							System.out.println(" Demo "+DemoTask+"Summary Task" +SumTask+" did not match ");
//							System.out.println(" Didn't match  "+rowValue1[i-1][i]+" and "+ SumPF_val[j-1][i]);
//							break;
//						  }
//					}	
//					System.out.println("************************************************");
				}	            
			}
		}		
		System.out.println("*********Successfylly verified Demography tab data*********");	
		
		login.findElement(By.id("menubutton")).click();
		Thread.sleep(2000);
		login.findElement(By.id("ui-id-1")).click();
		Thread.sleep(2000);
	}
		
}
