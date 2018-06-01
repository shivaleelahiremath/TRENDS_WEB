package Main_MasterFilter_Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

public class Hash_Map {
	String[] DemoFilterTasks, Demography_Task;
	int[] DemoFilterCount, Demo_NotTaken, Demo_Taken;
	int[][] rowValue1, PF_passFailArray, rowValue;

//	HashMap<String, Integer> mapTaken = new HashMap<String, Integer>();
//	HashMap<String, Integer> mapNotTaken = new HashMap<String, Integer>();
//	HashMap<String, Integer> mapRowValue= new HashMap<String, Integer>();
	
    Multimap<String, Integer> multiMap = ArrayListMultimap.create();
//  Map<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();

	
	public void populate_Demo_MasterFilterData(WebDriver driver) throws InterruptedException{

	driver.findElement(By.xpath("html/body/header/top/tabs/a[5]/tab")).click();
	Thread.sleep(2000);
    System.out.println("demo tab clicked...");
    //code to filter
    
    List<WebElement> Demo = driver.findElements(By.id("MaintenanceSTAARAssessmentDemogBreakdown"));	            	
//	WebElement DemoData = Demo.get(2);	 
    
    List<WebElement> allElements = driver.findElements(By.name("multiselect_filterpicker")); 
	System.out.println("Filter button selected and Elements present in Filter button: " +allElements.size()); 

	DemoFilterTasks = new String[allElements.size()];
	for (int x =0;x<allElements.size();x++){
		DemoFilterTasks[x] = driver.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).getAttribute("title"); 
//		System.out.println("task: "+DemoFilterTasks[x].trim());
	}
	int index=0;
	for(int a=0; a<DemoFilterTasks.length; a++){
		String value = DemoFilterTasks[a].trim();
		//  System.out.println(value);
		if(value.equals("Female")){
			index=a;
//		    System.out.println("All failed index: "+index);
		}
	}
	
//	DemoFilterCount = new int[allElements.size()]; 
	for (int x = 0;x < 2;x++){
		driver.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
		driver.findElement(By.className("ui-multiselect-none")).click();        	   
		DemoFilterTasks[x] = driver.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).getAttribute("title"); 
		driver.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).click();
		driver.findElement(By.className("ui-multiselect-close")).click();
		Thread.sleep(2000);
		
		//Modified code..
		List FOption = driver.findElements(By.className("DemoGroup"));     
//	    System.out.println("total "+FilterOption[i]+" tasks... " +FOption.size());
		WebElement task = Demo.get(3);
//		System.out.println("--> "+task.getText());
		Demography_Task = new String[FOption.size()];
		Demo_NotTaken = new int[FOption.size()];
		Demo_Taken = new int[FOption.size()];
		for(int j=1; j< FOption.size();j++){	
			Demography_Task[j] = driver.findElement(By.xpath("//*[@id='MaintenanceSTAARAssessmentDemogBreakdown']/tr["+j+"]/td[4]")).getText();
//			System.out.println(Demography_Task[j]);
//			Demography_Task[j]=  task.findElement(By.xpath("tbody/tr["+j+"]/td[4]")).getText();
			String notTaken = driver.findElement(By.xpath("//*[@id='MaintenanceSTAARAssessmentDemogBreakdown']/tr["+j+"]/td[8]/left")).getText();
			Demo_NotTaken[j] = Integer.parseInt(notTaken);	
			String Taken = driver.findElement(By.xpath("//*[@id='MaintenanceSTAARAssessmentDemogBreakdown']/tr["+j+"]/td[7]/left")).getText();
			Demo_Taken[j] = Integer.parseInt(Taken.replaceAll(" of [\\d]+[A-Za-z]?", ""));
//			System.out.println("Demo tab task: " +Demography_Task[j]+" not taken student count: "+Demo_NotTaken[j]+ " and taken student count:  "+Demo_Taken[j]);
						
//			Hashtable<String, Integer> source = new Hashtable<String, Integer>();
			
	        multiMap.put(Demography_Task[j], Demo_Taken[j]);
	        multiMap.put(Demography_Task[j], Demo_NotTaken[j]);

	        Set<String> keys = multiMap.keySet();
	        
	        //iterate through the key set and display key and values
	        for (String key : keys) {
	            System.out.println("Key = " + key);
	            System.out.println("Values = " + multiMap.get(key) + "\n");
	        } 
	        
//			mapTaken.put(Demography_Task[j], Demo_Taken[j]);
//			mapNotTaken.put(Demography_Task[j], Demo_NotTaken[j]);

	/*		Set set = mapTaken.entrySet();
		      Iterator iterator = set.iterator();
		      while(iterator.hasNext()) {
		         Map.Entry mentry = (Map.Entry)iterator.next();
		         System.out.println("key is: "+ mentry.getKey() + " & taken value is: "+mentry.getValue());
		      }
		      
		      Set set1 = mapNotTaken.entrySet();
		      Iterator iterator1 = set1.iterator();
		      while(iterator1.hasNext()) {
		         Map.Entry mentry = (Map.Entry)iterator1.next();
		         System.out.println("key is: "+ mentry.getKey() + " & not taken value is: "+mentry.getValue());
		      }
		*/     
			
			String DemoDataSet1[]={"Pass","Fail"};
			int ROWS = FOption.size();
			int COLS = DemoDataSet1.length;
			rowValue = new int[ROWS][COLS];
			for(int l=0;l<DemoDataSet1.length;l++){
				String Demovalue = driver.findElement(By.xpath("//*[@id='MaintenanceSTAARAssessmentDemogBreakdown']/tr["+j+"]/td["+(l+5)+"]/left")).getText();
				rowValue[j][l] = Integer.parseInt(Demovalue);
//		     	System.out.println("pass and fail val : "+rowValue[j][l]);
			}
			
			String DemoDataSet2[]={"AdvancePlus","Advance","SatisfactoryPlus","Satisfactory","SatisfactoryBubble","UnSatisfactoryBubble","UnSatisfactory"};
			int COLS1 = DemoDataSet2.length;
			rowValue1 = new int[ROWS][COLS1];
			PF_passFailArray = new int[ROWS][2];
				int passValue = 0;
				int failValue = 0;
				for(int m=0;m<DemoDataSet2.length;m++){
					String Demovalue1= null;
					String MTDemovalue1= null;
					try{
						MTDemovalue1 = driver.findElement(By.xpath("//*[@id='MaintenanceSTAARAssessmentDemogBreakdown']/tr["+j+"]/td["+(m+9)+"]/center")).getText();
					}catch (Exception e) {
						// TODO: handle exception
					}
					if(MTDemovalue1 == null){
				    Demovalue1 = driver.findElement(By.xpath("//*[@id='MaintenanceSTAARAssessmentDemogBreakdown']/tr["+j+"]/td["+(m+9)+"]/left")).getText();
					rowValue1[j][m] = Integer.parseInt(Demovalue1);
					}
					else if(MTDemovalue1.equalsIgnoreCase("N/A")){
						Demovalue1 = "-1";				
					}
//					System.out.println(rowValue1[j][m]);
//					mapRowValue.put(Demography_Task[j], rowValue1[j][m]);
					if(m<5){
						passValue += rowValue1[j][m];
					}else if(m<7){
						failValue += rowValue1[j][m];
					}
				}
				for(int k=0; k<2; k++){
					if(k == 0){
						PF_passFailArray[j][k] = passValue;
//						System.out.println("PassFailArray pass Value " + PF_passFailArray[i][k]);
					}else{
						PF_passFailArray[j][k] = failValue;
//					    System.out.println("PassFailArray fail Value " + PF_passFailArray[i][k]);
					}					 
				}
				
//				System.out.println("completed for loop..");
							
		}	
		
		}
	driver.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
	driver.findElement(By.className("ui-multiselect-none")).click();        	   
	driver.findElement(By.className("ui-multiselect-close")).click();				
	Thread.sleep(4000);
			
}

	void print(HashMap<String, Integer> mapTaken, HashMap<String, Integer> mapNotTaken, HashMap<String, Integer> mapRowValue){
		System.out.println("taken data: "+mapTaken);
		System.out.println("Not Taken data: "+mapNotTaken);	
		System.out.println("row data: "+mapRowValue);	
	}
	
}
