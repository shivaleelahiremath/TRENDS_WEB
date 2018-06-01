package Maintenance_STAAR_Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DemoTab_Data {
	public static Logger debugLog=Logger.getLogger("MaintenanceSTAAR_ASMT");
	public static Logger reportsLog=Logger.getLogger("reportsLog");
	
	String[] DemographyPF_Task;
	int[] DemoPF_NotTaken, DemoPF_Taken;
	int[][] rowValue1;
	
	String[] GenderOption;
	int[] DemoGen_NotTaken, DemoGen_Taken;
	int[][] GenderRowValue1;
	
	String[] EthnicityOption;
	int[] DemoEth_NotTaken, DemoEth_Taken;
	int[][] EthnicityRowValue1;
	
	public void populate_DemotabData(WebDriver driver) throws InterruptedException{

		//Demographic tab details... 
		driver.findElement(By.xpath("html/body/header/top/tabs/a[5]/tab")).click();
        Thread.sleep(2000);
		List<WebElement> Demo = driver.findElements(By.id("MaintenanceSTAARAssessmentDemogBreakdown"));	            	
		WebElement DemoData = Demo.get(2);	       
//    	System.out.println("assessment data: "+DemoData.getText());
		
		//Filtering and selecting Program Funding details..
		WebElement web = DemoData.findElement(By.xpath("tr/th[3]/filter/div"));
		WebElement Group= web.findElement(By.tagName("input"));
		Group.clear();
		Group.sendKeys("Program Funding");
		Group.sendKeys(Keys.ENTER);
		Thread.sleep(2000); 
		List PF = driver.findElements(By.className("DemoGroup"));     
//	    System.out.println("total PF tasks... " +PF.size());
		WebElement task = Demo.get(3);	

		DemographyPF_Task = new String[PF.size()];
		DemoPF_NotTaken = new int[PF.size()];
		DemoPF_Taken = new int[PF.size()];
		for(int i=1; i< PF.size();i++){
			DemographyPF_Task[i]=  task.findElement(By.xpath("tbody/tr["+i+"]/td[4]")).getText();
			String Taken = task.findElement(By.xpath("tbody/tr["+i+"]/td[7]/left")).getText();
			DemoPF_Taken[i] = Integer.parseInt(Taken.replaceAll(" of [\\d]+[A-Za-z]?", ""));
			String notTaken = task.findElement(By.xpath("tbody/tr["+i+"]/td[8]/left")).getText();
			DemoPF_NotTaken[i] = Integer.parseInt(notTaken);	
			
//			System.out.println("Demo tab task: " +DemographyPF_Task[i]+" not taken student count: "+DemoPF_NotTaken[i]+ " and taken student count:  "+DemoPF_Taken[i]);
		}

		String DemoDataSet1[]={"Pass","Fail"};
		int ROWS = PF.size();
		int COLS = DemoDataSet1.length;
		int[][] rowValue = new int[ROWS][COLS];
		for(int i=0;i<PF.size()-1;i++){
			for(int j=0;j<DemoDataSet1.length;j++){
				String Demovalue = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+5)+"]/left")).getText();
				rowValue[i][j] = Integer.parseInt(Demovalue);
//		     	System.out.println(rowValue[i][j]);
			}		
		}

		String DemoDataSet2[]={"AdvancePlus","Advance","SatisfactoryPlus","Satisfactory","SatisfactoryBubble","UnSatisfactoryBubble","UnSatisfactory"};
		int COLS1 = DemoDataSet2.length;
		rowValue1 = new int[ROWS][COLS1];
		int[][] PF_passFailArray = new int[ROWS][2];
		for(int i=0;i<PF.size()-1;i++){
			int passValue = 0;
			int failValue = 0;
			for(int j=0;j<DemoDataSet2.length;j++){
				String Demovalue1= null;
				String MTDemovalue1= null;
				try{
					MTDemovalue1 = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+9)+"]/center")).getText();
				}catch (Exception e) {
					// TODO: handle exception
				}
				if(MTDemovalue1 == null){
			    Demovalue1 = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+9)+"]/left")).getText();
				rowValue1[i][j] = Integer.parseInt(Demovalue1);
				}
				else if(MTDemovalue1.equalsIgnoreCase("N/A")){
					Demovalue1 = "-1";				
				}
//				System.out.println(rowValue1[i][j]);
				if(j<5){
					passValue += rowValue1[i][j];
				}else if(j<7){
					failValue += rowValue1[i][j];
				}
			}
			for(int k=0; k<2; k++){
				if(k == 0){
					PF_passFailArray[i][k] = passValue;
//					System.out.println("PassFailArray pass Value " + PF_passFailArray[i][k]);
				}else{
					PF_passFailArray[i][k] = failValue;
//				    System.out.println("PassFailArray fail Value " + PF_passFailArray[i][k]);
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
					if(PF_passFailArray[i-1][l] <  0 ){
						debugLog.error("Program Funding -> This document has (N/A) No Attributes in Demographics");
					}else{
						debugLog.error("Program Funding -> "+DemographyPF_Task1+" pass/fail "+rowValue[i-1][l]+"!= with the sum of levels on Demography tab " +PF_passFailArray[i-1][l]);
					}			
				}
			}
		}

		// Filtering and selecting gender details..
		WebElement test = driver.findElement(By.xpath("//*[@id='MaintenanceSTAARAssessmentDemogBreakdown']/tr/th[3]/filter/div/input"));
		test.clear();
		test.sendKeys("Gender");
		test.sendKeys(Keys.ENTER);
		Thread.sleep(2000); 
		List Gender = driver.findElements(By.className("DemoGroup"));     
//		System.out.println("total  ...: " +Gender.size());

		GenderOption = new String[Gender.size()];
		DemoGen_NotTaken = new int[Gender.size()];
		DemoGen_Taken = new int[Gender.size()];
		for(int i=1; i< Gender.size();i++){
			GenderOption[i]=  task.findElement(By.xpath("tbody/tr["+i+"]/td[4]")).getText();
			String notTaken = task.findElement(By.xpath("tbody/tr["+i+"]/td[8]/left")).getText();
			DemoGen_NotTaken[i] = Integer.parseInt(notTaken);	
			String Taken = task.findElement(By.xpath("tbody/tr["+i+"]/td[7]/left")).getText();
			DemoGen_Taken[i] = Integer.parseInt(Taken.replaceAll(" of [\\d]+[A-Za-z]?", ""));
//		    System.out.println("Demo tab task: " +GenderOption[i]+" not taken student count : "+DemoGen_NotTaken[i]+" and taken student count: "+DemoGen_Taken[i]);
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

		GenderRowValue1 = new int[GenROWS][COLS1];
		int[][] Gender_passFailArray = new int[GenROWS][2];
		for(int i=0;i<Gender.size()-1;i++){
			int passValue = 0;
			int failValue = 0;
			for(int j=0;j<DemoDataSet2.length;j++){
				String Demovalue1= null;
				String tNAdeValue= null;
				try{
					tNAdeValue = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+9)+"]/center")).getText();
				}catch (Exception e) {
					// TODO: handle exception
				}
				if(tNAdeValue == null){
					Demovalue1 = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+9)+"]/left")).getText();
				    GenderRowValue1[i][j] = Integer.parseInt(Demovalue1);
					//  System.out.println("-->"+GenderRowValue1[i][j]);
				}else{
					GenderRowValue1[i][j] = -1;
				}
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
					if(Gender_passFailArray[i][l] <  0 ){
						debugLog.error("Gender -> This document has (N/A) No Attributes in Demographics");
					}else{
						debugLog.error("Gender -> "+GenderOption1+" pass/fail "+GenrowValue[i][l]+" != with the sum of levels on Demography tab "+Gender_passFailArray[i][l]);
					}			
				}
			}
		}

		// Filtering and selecting Ethnicity details..
		WebElement test1 = driver.findElement(By.xpath("//*[@id='MaintenanceSTAARAssessmentDemogBreakdown']/tr/th[3]/filter/div/input"));
		test1.clear();
		test1.sendKeys("Ethnicity");
		test1.sendKeys(Keys.ENTER);
		Thread.sleep(2000); 
		List Ethnicity = driver.findElements(By.className("DemoGroup"));     
//		System.out.println("total  ...: " +Ethnicity.size());

		EthnicityOption = new String[Ethnicity.size()];
		DemoEth_NotTaken = new int[Ethnicity.size()];
		DemoEth_Taken = new int[Ethnicity.size()];
		for(int i=0; i< Ethnicity.size()-1;i++){
			EthnicityOption[i]=  task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td[4]")).getText();
			String notTaken = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td[8]/left")).getText();
			DemoEth_NotTaken[i] = Integer.parseInt(notTaken);
			String Taken = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td[7]/left")).getText();
			DemoEth_Taken[i] = Integer.parseInt(Taken.replaceAll(" of [\\d]+[A-Za-z]?", ""));
//		    System.out.println("Demo tab task: "+EthnicityOption[i] +" not taken student count: "+DemoEth_NotTaken[i]+" and taken student count: "+DemoEth_Taken[i]);
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

        EthnicityRowValue1 = new int[EthnicityROWS][COLS1];
		int[][] Ethnicity_passFailArray = new int[EthnicityROWS][2];
		for(int i=0;i<Ethnicity.size()-1;i++){
			int passValue = 0;
			int failValue = 0;
			for(int j=0;j<DemoDataSet2.length;j++){
				String tNADemoValue = null;
				String Demovalue1 =null;
				try{ 
					tNADemoValue = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+9)+"]/center")).getText();
				}catch (Exception e) {
					// TODO: handle exception
				}
				if (tNADemoValue == null){
					Demovalue1 = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+9)+"]/left")).getText();
					EthnicityRowValue1[i][j] = Integer.parseInt(Demovalue1);
					//    System.out.println(EthnicityRowValue1[i][j]);
				}else{
					EthnicityRowValue1[i][j] = -1;
				}
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
					if(Ethnicity_passFailArray[i][l] <  0 ){
						debugLog.error("Ethnicity -> This document has (N/A) No Attributes in Demographics");
					}else{
						debugLog.error("Ethnicity -> "+EthnicityOption1+" pass/fail "+EthrowValue[i][l]+" != with the sum of levels on Demography tab "+Ethnicity_passFailArray[i][l]);
					}			
				}
			}
		}

	}
}
