package Main_MasterFilter_Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.collect.ObjectArrays;
import com.google.common.primitives.Ints;

public class Populate_Sum_MasterFilterData {
	
	String[]  SumMasterFilterTasks;
	int[] SumFilterStud_TargetCount;
	
	String[] SumPF_Tasks, SumGender_Tasks, SumEthnicity_Tasks, SumFilter_Task;
	int[] SumPF_NotTaken,SumPF_Taken, SumPF_Incomplete, SumGen_NotTaken, SumGen_Taken, SumGen_Incomplete, SumEth_NotTaken, SumEth_Taken, SumEth_Incomplete, Sum_TaskCount;
	int[][] SumPF_val, SumGender_val, SumEthnicity_val;
	
      public void populate_Sum_MasterFilterData(WebDriver driver) throws InterruptedException{
		
		driver.findElement(By.xpath("html/body/header/top/tabs/a[1]/tab")).click();
		System.out.println("summary tab selected..");
		List<WebElement> allElements = driver.findElements(By.name("multiselect_filterpicker")); 
		System.out.println("Filter button selected and Elements present in Filter button: " +allElements.size()); 
		
		SumMasterFilterTasks = new String[allElements.size()];
		SumFilterStud_TargetCount = new int[allElements.size()]; 
		//Populating target student count for the selected master filter 
		for (int x = 0;x<allElements.size();x++){
			driver.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
			driver.findElement(By.className("ui-multiselect-none")).click();        	   
			SumMasterFilterTasks[x] = driver.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).getAttribute("title"); 
			driver.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).click();
			driver.findElement(By.className("ui-multiselect-close")).click();
			Thread.sleep(2000);
//			String FCount = driver.findElement(By.xpath("html/body/content/div[1]/div/div[2]/content/table/tbody/tr[2]/td[4]")).getText();
//			SumFilterStud_TargetCount[x] = Integer.parseInt(FCount.replace("\\s+", "").trim());					

			List<WebElement> profile = driver.findElements(By.className("boxes"));
			WebElement profiledata= profile.get(0);
			List<WebElement> tr=profiledata.findElements(By.cssSelector(".boxes>tbody>tr"));
//			System.out.println("NUMBER OF ROWS phase = "+tr.size()+" ");
			
			String[] phasetask= new String[tr.size()];	
			for (int data =0;data<tr.size() ;data++){
				phasetask[data] = profiledata.findElement(By.xpath("tbody/tr["+(data+1)+"]/td")).getText(); 
//				System.out.println("Target: "+phasetask[data].trim());
			}

			int in=0;
			for(int a=0; a<phasetask.length; a++){
				String value = phasetask[a].trim();
				//  System.out.println(value);
				if(value.equals("Target")){
					in=a;
//					System.out.println("Target index: "+in); 
					break;
				}
			}
			int tar = in +1;
			String FCount = driver.findElement(By.xpath("//*[@id='summaryinfo']/div[1]/content/table/tbody/tr["+tar+"]/td[2]")).getText();
			SumFilterStud_TargetCount[x] = Integer.parseInt(FCount);
			System.out.println("task: "+SumMasterFilterTasks[x].trim() +" selected and target count : "+SumFilterStud_TargetCount[x]);
			
			
			//Accessing values from the Program funding table..  
			List<WebElement> Tasks_table = driver.findElements(By.className("tableswithbars"));		  
			WebElement table_element2=Tasks_table.get(2);
			List<WebElement> tr_collection=table_element2.findElements(By.cssSelector(".tableswithbars>tbody>tr"));
			//System.out.println("NUMBER OF ROWS IN PROGRAM FUNDING TABLE = "+tr_collection.size());	        

			//Program Funding tasks and student count on summary tab..	 
			SumPF_Tasks= new String[tr_collection.size()+1];
			int[] SumPF_StudCount = new int[tr_collection.size()+1];
			SumPF_NotTaken = new int[tr_collection.size()+1];
			SumPF_Taken = new int[tr_collection.size()+1];
			for(int row = 1;row<tr_collection.size()+1;row++){
				SumPF_Tasks[row] =table_element2.findElement(By.xpath("tbody/tr["+row+"]/td[1]/bar/text")).getText();
				String Incomplete = table_element2.findElement(By.xpath("tbody/tr["+row+"]/td[10]")).getText();
				String Taken_Stud = table_element2.findElement(By.xpath("tbody/tr["+row+"]/td[11]")).getText();
				String No_Data = table_element2.findElement(By.xpath("tbody/tr["+row+"]/td[12]")).getText();
				SumPF_StudCount[row] = (Integer.parseInt(Incomplete) + Integer.parseInt(Taken_Stud) + Integer.parseInt(No_Data));
				//System.out.println("Program Funding task and student count on summary tab: "+SumPF_Tasks[row]+"--"+SumPF_StudCount[row]);
				//modified code..
				SumPF_NotTaken[row] = Integer.parseInt(No_Data) + Integer.parseInt(Incomplete);
				SumPF_Taken[row] = Integer.parseInt(Taken_Stud);
				System.out.println("Summary tab PF task: "+SumPF_Tasks[row]+" not taken: "+SumPF_NotTaken[row]+" taken count: "+SumPF_Taken[row]+" and total target student "+SumPF_StudCount[row]);
			}

			//(Modified) reading PF tasks count on the summary tab..
			int srow = tr_collection.size();
			int scolm = 7;
			SumPF_val = new int[srow][scolm];     
			for(int i=0;i < srow;i++){
				for(int j=0;j<scolm;j++){
					String Demovalue1 = table_element2.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+3)+"]")).getText();
					if(Demovalue1.equalsIgnoreCase("N/A")){
						SumPF_val[i][j] = -1;
					}else{
					SumPF_val[i][j] = Integer.parseInt(Demovalue1);
					}
					System.out.println(SumPF_val[i][j]); 			    	 
				}	
					System.out.println("-------------");
			}

			WebElement table_element3 =Tasks_table.get(1);
			List<WebElement> tr_collection1 = table_element3.findElements(By.cssSelector(".tableswithbars>tbody>tr"));
			System.out.println("NUMBER OF ROWS IN GENDER AND ETHNICITY TABLE = "+tr_collection1.size());	  
//			System.out.println("GENDER AND ETHNICITY table: " +table_element3.getText());

			//Gender task and gender count on summary tab..	 
		    SumGender_Tasks= new String[tr_collection1.size()];
			int[] SumGender_StudCount = new int[tr_collection1.size()];
			SumGen_NotTaken = new int[tr_collection1.size()];
			SumGen_Taken = new int[tr_collection1.size()];
			try{
				for(int row = 1; row < 5-2; row++){
					SumGender_Tasks[row] = table_element3.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[1]/bar/text")).getText();
					String Incomplete = table_element3.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[10]")).getText();
					String Taken_Stud = table_element3.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[11]")).getText();
					String No_Data = table_element3.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[12]")).getText();
					SumGender_StudCount[row] = (Integer.parseInt(Incomplete) + Integer.parseInt(Taken_Stud) + Integer.parseInt(No_Data));
					//System.out.println(SumGender[row]+" student count on summary tab: "+SumGender_StudCount[row]);
					//modified code..
					SumGen_NotTaken[row] = Integer.parseInt(Incomplete) + Integer.parseInt(No_Data); 
					SumGen_Taken[row] = Integer.parseInt(Taken_Stud); 
					System.out.println("Summary tab gender: " +SumGender_Tasks[row]+" not taken: "+SumGen_NotTaken[row]+" taken count: "+SumGen_Taken[row]+" and total target student "+SumGender_StudCount[row]);
				}	 
			}catch (Exception e){
				System.out.println("Gender not found");
			}

			//(modified) ing gender tasks count on the summary tab..
			//removes all nulls 
			ArrayList<String> al1 = new ArrayList<String>(); 
			for(int i=0; i<SumGender_Tasks.length; i++){ 
				if(SumGender_Tasks[i]!=null) 
					al1.add(SumGender_Tasks[i]); 
			}
			SumGender_Tasks = (String[])al1.toArray(new String[al1.size()]);
			System.out.println("summary--> "+Arrays.toString(SumGender_Tasks));

			int Genrow = tr_collection1.size();
			SumGender_val = new int[Genrow][scolm];     
			try{
				for(int i=0;i < 2;i++){
					for(int j=0;j<scolm;j++){
						String Demovalue1 = table_element3.findElement(By.xpath("tbody/tr["+(i+2)+"]/td["+(j+3)+"]")).getText();
						if(Demovalue1.equalsIgnoreCase("N/A")){
							SumGender_val[i][j] = -1;
						}else{
							SumGender_val[i][j] = Integer.parseInt(Demovalue1); 
						}
						System.out.println(SumGender_val[i][j]); 			    	 
					}	
						System.out.println("------>");
				}
			}catch (Exception e){
				System.out.println("Gender not found: " +Arrays.toString(SumGender_Tasks));
			}

			//Ethnicity tasks and student count on summary tab..
			String[] SumEthnicityTask= new String[tr_collection1.size()+1];	
			int[] SumEthn_StudCount = new int[tr_collection1.size()+1];
//			int max = tr_collection1.size()+1;
			for (int n =0;n<tr_collection1.size();n++){
				SumEthnicityTask[n] = table_element3.findElement(By.xpath("tbody/tr["+(n+1)+"]/td")).getText(); 
				System.out.println("ethnitask: "+SumEthnicityTask[n].trim());
			}

			int index1=0;
			for(int a=1; a<SumEthnicityTask.length; a++){
				String value = SumEthnicityTask[a].trim();
				//  System.out.println(value);
				if(value.equals("Ethnicity")){
					index1=a;
					System.out.println("Ethnicity index: "+index1); 
					break;
				}
			}

			int EthnIndex = index1 + 1;
			SumEthnicity_Tasks = new String[tr_collection1.size()+1];
			SumEth_NotTaken = new int[tr_collection1.size()+1];
			SumEth_Taken = new int[tr_collection1.size()+1];
			int max1=  tr_collection1.size() + 1;
			System.out.println(max1);
			for(int row = 0; row < max1 - 5;row++){ 
				SumEthnicity_Tasks[row] =table_element3.findElement(By.xpath("tbody/tr["+(row+EthnIndex)+"]/td[1]/bar/text")).getText();
				String Incomplete = table_element3.findElement(By.xpath("tbody/tr["+(row+EthnIndex)+"]/td[10]")).getText();
				String Taken_Stud= table_element3.findElement(By.xpath("tbody/tr["+(row+EthnIndex)+"]/td[11]")).getText();
				String No_Data = table_element3.findElement(By.xpath("tbody/tr["+(row+EthnIndex)+"]/td[12]")).getText();
				SumEthn_StudCount[row] =Integer.parseInt(Incomplete) + Integer.parseInt(Taken_Stud) + Integer.parseInt(No_Data);
				//System.out.println(SumEthnicity[row]+" student count on summary tab: "+SumEthn_StudCount[row]);
				//modified code..
				SumEth_NotTaken[row] = Integer.parseInt(Incomplete) + Integer.parseInt(No_Data); 
				SumEth_Taken[row] = Integer.parseInt(Taken_Stud); 
				System.out.println(SumEthnicity_Tasks[row]+"---"+SumEth_NotTaken[row]+"---"+SumEth_Taken[row]+" and total count "+SumEthn_StudCount[row]);
			}

			//(modified code) reading all ethnicity tasks data on the summary tab..
			//removes all nulls 
			ArrayList<String> Sumethn = new ArrayList<String>(); 
			for(int i=0; i<SumEthnicity_Tasks.length; i++){ 
				if(SumEthnicity_Tasks[i]!=null) 
					Sumethn.add(SumEthnicity_Tasks[i]); 
			}
			SumEthnicity_Tasks = (String[])Sumethn.toArray(new String[Sumethn.size()]);
			System.out.println("sum ethn task--> "+Arrays.toString(SumEthnicity_Tasks));

			SumEthnicity_val = new int[Genrow+1][scolm];     
			for(int i=0;i <= max1-5;i++){
				for(int j=0;j<scolm;j++){
					String Demovalue1 = table_element3.findElement(By.xpath("tbody/tr["+(i+4)+"]/td["+(j+3)+"]")).getText();
					if(Demovalue1.equalsIgnoreCase("N/A")){
						SumEthnicity_val[i][j]= -1;
					}else{
						SumEthnicity_val[i][j] = Integer.parseInt(Demovalue1); 
					}
					System.out.println("ethnicity: "+SumEthnicity_val[i][j]); 			    	 
				}	
					System.out.println("------>");
			}
			
			
			String[] SumGender_Ethn_Task = ObjectArrays.concat(SumGender_Tasks, SumEthnicity_Tasks, String.class);	            
			//removes all nulls 
			ArrayList<String> al = new ArrayList<String>(); 
			for(int i=0; i<SumGender_Ethn_Task.length; i++){ 
				if(SumGender_Ethn_Task[i]!=null) 
					al.add(SumGender_Ethn_Task[i]); 
			}
			SumGender_Ethn_Task = (String[])al.toArray(new String[al.size()]);
//		    System.out.println("-->"+Arrays.toString(SumGender_Ethn_Task));

			int[] Gender_Ethn_TaskCount = ArrayUtils.addAll(SumGender_StudCount, SumEthn_StudCount);
			List<Integer> list = new ArrayList<Integer>(); 
			for(int i=0; i<Gender_Ethn_TaskCount.length; i++){ 
				if(Gender_Ethn_TaskCount[i] !=0)
					list.add(Gender_Ethn_TaskCount[i]);	
			} 
			Gender_Ethn_TaskCount = Ints.toArray(list);

		    String[] SumFilter_Task1 = ObjectArrays.concat(SumPF_Tasks, SumGender_Ethn_Task, String.class);
		    ArrayList<String> al3 = new ArrayList<String>(); 
			for(int i=0; i<SumFilter_Task1.length; i++){ 
				if(SumFilter_Task1[i]!=null) 
					al3.add(SumFilter_Task1[i]); 
			}
			SumFilter_Task = (String[])al3.toArray(new String[al.size()]);
		        
			int[] Sum_TaskCount1 = ArrayUtils.addAll(SumPF_StudCount, Gender_Ethn_TaskCount);
			List<Integer> list1 = new ArrayList<Integer>(); 
			for(int i=0; i<Sum_TaskCount1.length; i++){ 
				if(Sum_TaskCount1[i] !=0)
					list1.add(Sum_TaskCount1[i]);	
			} 
			Sum_TaskCount = Ints.toArray(list1);
			
			System.out.println("All Filter task and task count on summary tab----->"+Arrays.toString(SumFilter_Task)+ "----" +Arrays.toString(Sum_TaskCount));
	 			
			int[] Gender_Ethn_TakenCount = ArrayUtils.addAll(SumGen_Taken, SumEth_Taken);
			List<Integer> Tlist = new ArrayList<Integer>(); 
			for(int i=0; i<Gender_Ethn_TakenCount.length; i++){ 
				if(Gender_Ethn_TakenCount[i] !=0)
					Tlist.add(Gender_Ethn_TakenCount[i]);	
			} 
			Gender_Ethn_TakenCount = Ints.toArray(Tlist);
			
			int[] PF_Gender_Ethn_TakenCount = ArrayUtils.addAll(SumPF_Taken, Gender_Ethn_TakenCount);
			List<Integer> takenList = new ArrayList<Integer>(); 
			for(int i=0; i<PF_Gender_Ethn_TakenCount.length; i++){ 
				if(PF_Gender_Ethn_TakenCount[i] !=0)
					takenList.add(PF_Gender_Ethn_TakenCount[i]);	
			} 
			PF_Gender_Ethn_TakenCount = Ints.toArray(takenList);
			System.out.println("taken student count.."+Arrays.toString(PF_Gender_Ethn_TakenCount));
			
//			int[] SumPF_NotTaken,SumPF_Taken, SumPF_Incomplete, SumGen_NotTaken, SumGen_Taken, SumGen_Incomplete, SumEth_NotTaken, SumEth_Taken, SumEth_Incomplete;

			int[] Gender_Ethn_NotTakenCount = ArrayUtils.addAll(SumGen_NotTaken, SumEth_NotTaken);
			List<Integer> Nlist = new ArrayList<Integer>(); 
			for(int i=1; i<Gender_Ethn_NotTakenCount.length; i++){ 
				if(Gender_Ethn_NotTakenCount[i] !=0)
					Nlist.add(Gender_Ethn_NotTakenCount[i]);	
			} 
			Gender_Ethn_NotTakenCount = Ints.toArray(Nlist);
			
			int[] PF_Gender_Ethn_NotTakenCount = ArrayUtils.addAll(SumPF_NotTaken, Gender_Ethn_NotTakenCount);
			System.out.println("not taken student count.."+Arrays.toString(PF_Gender_Ethn_NotTakenCount));

			List<Integer> notTakenList = new ArrayList<Integer>(); 
			for(int i=0; i<PF_Gender_Ethn_NotTakenCount.length; i++){ 
				if(PF_Gender_Ethn_NotTakenCount[i] !=0)
					notTakenList.add(PF_Gender_Ethn_NotTakenCount[i]);	
			} 
			PF_Gender_Ethn_NotTakenCount = Ints.toArray(notTakenList);
			System.out.println("not taken student count.."+Arrays.toString(PF_Gender_Ethn_NotTakenCount));		
		}
	}
	

}
