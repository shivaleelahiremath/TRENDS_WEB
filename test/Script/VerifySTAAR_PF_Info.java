package Script;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.ObjectArrays;
import com.google.common.primitives.Ints;

public class VerifySTAAR_PF_Info {
	
	private static Logger debugLog=Logger.getLogger("debugLog");
	private static Logger reportsLog=Logger.getLogger("reportsLog");
	String[][] tabArray = null;
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
			//	login.get("http://trends.tangosoftware.com");
				System.out.println("Launched Trends web link..");
				reportsLog.info("Trends Application launched successfully..");
	}
	
	//Shivaleela@TX_BrownsvilleISD, Shivaleela@TX_ComalISD, TX_HarlingenCISD, TX_RioGrandeCityCISD
	@Test
	public void Login(){
		//Login functionality..
		reportsLog.info("Testing STAAR documents for district BrownsvilleISD production..");
	    login.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_BrownsvilleISD");
	    login.findElement(By.id("password")).sendKeys("Shivu123");
	    login.findElement(By.xpath("html/body/form/button")).click();
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
		for(int i= 0 ;i<sht.getRows(); i++){
			String DocTitle = sht.getCell(0, i).getContents();
			VerifyProgramFunInfo(DocTitle);
		}		
	}
	
	public void VerifyProgramFunInfo(String DocTitle) throws InterruptedException{
	       //Selecting STAAR tab on assessment module..
		   login.findElement(By.xpath("html/body/header/bottom/toolbar/left/div/label[2]/span")).click();  
		   login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input")).click();
	       WebElement element = login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input"));
		   element.sendKeys(DocTitle);
	       element.sendKeys(Keys.ENTER);
		   Thread.sleep(4000);
		   
		   //Selecting document..
		   List mcanswers = login.findElements(By.className("number"));  
	//    	System.out.println("total documents: " +mcanswers.size());
		   int SelectDocu = mcanswers.size()-2;	  
		   String RecCount = login.findElement(By.className("reccount")).getText();			   
	       if(RecCount.equalsIgnoreCase("0 Records")){
	    	   reportsLog.info("Document not found: "+DocTitle);
	    	   System.out.println("Document not found: "+DocTitle);
	    	   element.clear();
	    	   Thread.sleep(6000);
	       }else{	    	 	    	         	
           List<WebElement> Assessment= login.findElements(By.className("go"));
    	   WebElement AssessmentClick=(WebElement) Assessment.get(SelectDocu); 
    	   AssessmentClick.click();
    	   System.out.println("Selected "+DocTitle+" document..");
    	   Thread.sleep(2000);
		    
  //  	   WebElement ul = login.findElement(By.xpath("html/body/div[2]/ul"));
  // 	   System.out.println("list data :"+ul.getText());
    	   System.out.println("Filter option functionality..");
    	   List<WebElement> allElements = login.findElements(By.name("multiselect_filterpicker")); 
           System.out.println("Elements present in Filter button: " +allElements.size());  
           
 /*          int[] StudPF_StudCount = new int[allElements.size()];
           String[] StudPF_Tasks = new String[allElements.size()];
           for(int x = 0; x < allElements.size(); x++){
        	   login.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
        	   login.findElement(By.className("ui-multiselect-none")).click();        	   
        	   StudPF_Tasks[x] = login.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).getAttribute("title");  	   
               login.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).click();
   	           login.findElement(By.className("ui-multiselect-close")).click();
   	           String FilterRowCount = login.findElement(By.className("reccount")).getText();
   	           StudPF_StudCount[x] = Integer.parseInt(FilterRowCount.replace(" Records", ""));
   	        // System.out.println("Selected "+StudPF_Tasks[x].trim()+" task and total records on Student tab are: "+StudPF_StudCount[x]);
           }
    	   login.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
    	   login.findElement(By.className("ui-multiselect-none")).click();        	   
	       login.findElement(By.className("ui-multiselect-close")).click();
   
*/			List<WebElement> ele1 = login.findElements(By.id("MaintenanceAssessmentStudentMastery"));	        
			WebElement t = ele1.get(1);	
			List<WebElement> list1=t.findElements(By.tagName("th"));
			System.out.println("Headers available = " +list1.size());
			
			List<String> ColumnHeader = new ArrayList<String>(); 
			for(int w=1; w<list1.size()-2; w++){
				WebElement testvalue = list1.get(w+1).findElement(By.tagName("main"));
				((JavascriptExecutor) login).executeScript("arguments[0].scrollIntoView(true);", testvalue);
				String colmnHead = testvalue.findElement(By.tagName("left")).getText();
				String colmnHead1 = colmnHead.replaceAll("\n", " ");
				ColumnHeader.add(colmnHead1);		
			//	System.out.println("----------------"+colmnHead1);
				WebElement web=t.findElement(By.xpath("tr/th["+(w+2)+"]"));
				
				List<WebElement> val = login.findElements(By.tagName("colorlist"));
				System.out.println("-->"+val.size());
				if(colmnHead1.equals("Latest STAAR")){
					web.findElement(By.tagName("selectedcolor")).click();
					System.out.println("clicked ..");
					Thread.sleep(2000);		
//					String latsetVal = login.findElement(By.xpath("//colorlist[9]")).getText();
//					System.out.println(latsetVal);
					List<WebElement> cl = login.findElements(By.xpath("//colorlist[9]"));
					WebElement colorlist = cl.get(0);
					List<WebElement> cl1 =colorlist.findElements(By.tagName("color"));
					System.out.println("size available = "+cl1.size());
					int[] Stud_LS_Records = new int[cl1.size()];
                    String[] LS_Task = new String[cl1.size()];
					for(int i=1;i < cl1.size();i++){
				    Thread.sleep(2000);
					web.findElement(By.tagName("selectedcolor")).click();
					LS_Task[i] = login.findElement(By.xpath("//colorlist[9]")).getText();
					System.out.println("Latest Staar options: "+LS_Task[i]);
					login.findElement(By.xpath("//colorlist[6]/color["+i+"]")).click();
					String DQ = login.findElement(By.className("reccount")).getText();
				    Stud_LS_Records[i] = Integer.parseInt(DQ.replace(" Records", ""));
					System.out.println("Stud_DQ_Records: "+Stud_LS_Records[i]); 
					Thread.sleep(2000);
					}
				}					
			}	      
		        
          login.findElement(By.xpath("html/body/header/top/tabs/a[1]/tab")).click();
 		   System.out.println("Summary tab Clicked"); 
    
 		  //Accessing values from the passing criteria table  
 		  List<WebElement> Tasks_table = login.findElements(By.className("tableswithbars"));		  
 		  WebElement table_element1=Tasks_table.get(1);
		  List<WebElement> tr_collection=table_element1.findElements(By.cssSelector(".tableswithbars>tbody>tr"));
	      System.out.println("NUMBER OF ROWS IN PROGRAM FUNDING TABLE = "+tr_collection.size());	        
	      
	      //Program Funding tasks and student count on summary tab..	 
	      String[] SumPF_Tasks= new String[tr_collection.size()];
	      int[] SumPF_StudCount = new int[tr_collection.size()];
	      for(int row = 0;row<tr_collection.size();row++){
	      SumPF_Tasks[row] =table_element1.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[1]/bar/text")).getText();
	      String Taken_Stud = table_element1.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[11]")).getText();
	      String No_Data = table_element1.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[12]")).getText();
	      SumPF_StudCount[row] = (Integer.parseInt(Taken_Stud) + Integer.parseInt(No_Data));
	 //   System.out.println("Program Funding task and student count on summary tab: "+SumPF_Tasks[row]+"--"+SumPF_StudCount[row]);
	      }
	      
	      WebElement table_element =Tasks_table.get(0);
		  List<WebElement> tr_collection1=table_element.findElements(By.cssSelector(".tableswithbars>tbody>tr"));
	      System.out.println("NUMBER OF ROWS IN GENDER AND ETHNICITY TABLE = "+tr_collection1.size());	  
	      
	      //Gender and gender count on summary tab..	 
	      String[] SumGender= new String[tr_collection1.size()];
	      int[] SumGender_StudCount = new int[tr_collection1.size()];
	      try{
	      for(int row = 2; row < 4;row++){
	      SumGender[row] =table_element.findElement(By.xpath("tbody/tr["+row+"]/td[1]/bar/text")).getText();
	      String Taken_Stud = table_element.findElement(By.xpath("tbody/tr["+row+"]/td[11]")).getText();
	      String No_Data = table_element.findElement(By.xpath("tbody/tr["+row+"]/td[12]")).getText();
	      SumGender_StudCount[row] = (Integer.parseInt(Taken_Stud) + Integer.parseInt(No_Data));
	      System.out.println(SumGender[row]+" student count on summary tab: "+SumGender_StudCount[row]);
	      }	 
	      }catch (Exception e){
             System.out.println("Gender not found: " +Arrays.toString(SumGender));
	      }

	      //Ethnicity tasks and student count on summary tab..
	      String[] SumEthnicity= new String[tr_collection1.size()+1];	
	      int[] SumEthn_StudCount = new int[tr_collection1.size()+1];
	      int max=tr_collection1.size()+1;
	      for(int row = 5; row < max;row++){	    	  
	      SumEthnicity[row] =table_element.findElement(By.xpath("tbody/tr["+row+"]/td[1]/bar/text")).getText();
	      String Taken_Stud = table_element.findElement(By.xpath("tbody/tr["+row+"]/td[11]")).getText();
	      String No_Data = table_element.findElement(By.xpath("tbody/tr["+row+"]/td[12]")).getText();
	      SumEthn_StudCount[row] = Integer.parseInt(Taken_Stud) + Integer.parseInt(No_Data);
	      System.out.println(SumEthnicity[row]+" student count on summary tab: "+SumEthn_StudCount[row]);
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
	       	      
	      List<WebElement> QuintileDist= login.findElements(By.id("QuintileBarChart"));		  
 		  WebElement qd_table=QuintileDist.get(0);
 		  System.out.println("qd table: "+qd_table.getText());
 		  for(int i=1;i<=5;i++){
 			  String qt_Count = qd_table.findElement(By.xpath("count["+i+"]")).getText();
 			  System.out.println("qt data: "+qt_Count);
 		  }     
	     
	      //verifying Program Funding task count... 
/*	      for(int task=0; task < StudPF_Tasks.length; task++){
	    	  String StudTask = StudPF_Tasks[task].trim();
	    	  int StudCount = StudPF_StudCount[task];
	    	  for(int task1=0; task1 < SumFilter_Task.length; task1++){	
	    		  String SumTask =SumFilter_Task[task1].trim();
		            if(StudTask.equalsIgnoreCase(SumTask)){
		            	int SumCount = Sum_TaskCount[task1];
		            	if(StudCount == SumCount){
		            		System.out.println("count is matching for task: "+StudTask+" and "+SumTask);
		            		System.out.println("Expected is "+ StudCount+" and found is: "+SumCount);
		            	}else{
			               debugLog.error("count is not matching for document: " +DocTitle+ "Expected value on Student tab is "+StudCount+" and found value on summary tab is: "+SumCount);
			               System.out.println("count is not matching for document: " +DocTitle+ "Expected value on Student tab is "+StudCount+" and found value on summary tab is: "+SumCount);
		            	}
		            }else{
		                System.out.println("task is not matching..Expected is:"+StudTask.trim()+" and found is: "+SumTask);
		         }
	    	  }  
	        }	   */
	        login.findElement(By.id("menubutton")).click();
	        Thread.sleep(2000);
		    login.findElement(By.id("ui-id-1")).click();
		    Thread.sleep(2000);	    	      
	}		
  }
}


