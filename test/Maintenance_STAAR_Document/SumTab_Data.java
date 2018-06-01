package Maintenance_STAAR_Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.collect.ObjectArrays;
import com.google.common.primitives.Ints;

public class SumTab_Data {
	
	String sumTab[]={"SumAdvPlus","SumAdv","SumSatisPlus","SumSatisfactory","SumSatisBubble","SumUnSatisBubble","SumUnSatis"};
	String sumValues[]=new String[sumTab.length];
	double sumPerc[]=new double[sumTab.length];
	int SumNotTakenStudent, SumTakenStudent, SumTotalTargetStud;
	String[] SumFilter_Task;	
	int[] Sum_TaskCount;
	int SumPassTally, SumFailTally;
	Float SumPassPerc, SumFailPerc;	

	String[] SumPF_Tasks;
	int[] Sum_NotTaken,SumPF_Taken;
	int[][] SumPF_val;

	String[] SumGender;
	int[] SumGen_NotTaken, SumGen_Taken;
	int[][] SumGender_val;
	
	String[] SumEthnicity;
	int[] SumEthNotTaken, SumEthTaken;
	int[][] SumEthnicity_val;
	
	public void populate_SummaryTabData(WebDriver driver) throws InterruptedException{

		//Summary Tab Starts    	   
		System.out.println("***************************Summary Tab***************************");
		driver.findElement(By.xpath("html/body/header/top/tabs/a[1]/tab")).click();
		Thread.sleep(2000);
		System.out.println("Summary tab Clicked"); 

		//Accessing Accessing values from the passing criteria table  
		List<WebElement> table= driver.findElements(By.className("boxes"));		  
		WebElement table_element=table.get(0);
		WebElement table_element1=table.get(1);

		String SumTotalTargetStud1=table_element.findElement(By.xpath("tbody/tr[3]/td[2]")).getText();
		SumTotalTargetStud = Integer.parseInt(SumTotalTargetStud1);
//		System.out.println("Total Target students in Summary tab: "+SumTotalTargetStud);

//		String SumItems1 = table_element.findElement(By.xpath("tbody/tr[2]/td[2]")).getText();
//		int SumQsCount = Integer.parseInt(SumItems1.replace(" [\\d]+[A-Za-z]? ", ""));
//		System.out.println(SumItems1);

		String KentroOuterScore[]={"SumTotalAdvanceTally","SumPassTally","SumFailTally"};
		int sumTotalValue[]=new int[KentroOuterScore.length];
		Float SumTotalPerc[]=new Float[KentroOuterScore.length];	      

		for(int s=0;s<KentroOuterScore.length;s++){
			String ss=table_element1.findElement(By.xpath("tbody/tr[2]/td["+(s+1)+"]")).getText();
			if(ss.equalsIgnoreCase("N/A")){
				sumTotalValue[s] = -1;
			}else{
			sumTotalValue[s]=Integer.parseInt(ss);
			}
			
			String percValue = table_element1.findElement(By.xpath("tbody/tr[6]/td["+(s+1)+"]")).getText();
			if(percValue.equalsIgnoreCase("N/A%")){
				SumTotalPerc[s]= (float)-1;
			}else{
			SumTotalPerc[s]=Float.parseFloat(percValue.replace("%", ""));
			}
//			System.out.println("summary tab "+KentroOuterScore[s]+" total values : "+sumTotalValue[s]+" and it's percentage : "+SumTotalPerc[s]);
		}	      

		SumPassPerc = (float) 0;
		if(SumTotalPerc[0]== -1 || SumTotalPerc[1]==-1){
			SumPassPerc = (float) -1;	
		}
		else{
		 SumPassPerc = SumTotalPerc[0] + SumTotalPerc[1];			
		}
		SumFailPerc=  SumTotalPerc[2];


	    SumPassTally=0;
		if(sumTotalValue[0]==-1 || sumTotalValue[1]==-1){
			 SumPassTally =-1;	
		}else{
			 SumPassTally = sumTotalValue[0] + sumTotalValue[1];
		}
		SumFailTally = sumTotalValue[2];		
//	    System.out.println(" "+SumPassTally+" "+SumFailTally+" "+SumPassPerc+" "+SumFailPerc);

		String SumTakenStudent1 = table_element1.findElement(By.xpath("tbody/tr[2]/td[4]/u")).getText();
	    SumTakenStudent = Integer.parseInt(SumTakenStudent1);
//		System.out.println("Total taken students count in summary tab:" +SumTakenStudent);

	    SumNotTakenStudent = SumTotalTargetStud - SumTakenStudent;
		//System.out.println("Total not taken students count in summary tab: " +SumNotTakenStudent);
		
	    for(int s=0;s<sumTab.length;s++){
			String sumValues1 =table_element1.findElement(By.xpath("tbody/tr[4]/td["+(s+1)+"]")).getText();
			if(sumValues1.equalsIgnoreCase("N/A")){
				sumValues[s]="-1";
			}else{
				sumValues[s]=sumValues1;
			}
			String sumPerce1  = table_element1.findElement(By.xpath("tbody/tr[5]/td["+(s+1)+"]")).getText();
			if(sumPerce1.equalsIgnoreCase("N/A")){
				sumPerc[s]=(double)-1;
			}else{
				sumPerc[s]=Double.parseDouble(sumPerce1);
			}
//			System.out.println(sumTab[s]+" value : " +sumValues[s]+" Perc : "+sumPerc[s]);			
		}

		//Accessing values from the passing criteria table  
		List<WebElement> Tasks_table = driver.findElements(By.className("tableswithbars"));		  
		WebElement table_element2=Tasks_table.get(1);
		List<WebElement> tr_collection=table_element2.findElements(By.cssSelector(".tableswithbars>tbody>tr"));
		//System.out.println("NUMBER OF ROWS IN PROGRAM FUNDING TABLE = "+tr_collection.size());	        

		//Program Funding tasks and student count on summary tab..	 
		SumPF_Tasks= new String[tr_collection.size()+1];
		int[] SumPF_StudCount = new int[tr_collection.size()+1];
		Sum_NotTaken = new int[tr_collection.size()+1];
		SumPF_Taken = new int[tr_collection.size()+1];
		for(int row = 1;row<tr_collection.size()+1;row++){
			SumPF_Tasks[row] =table_element2.findElement(By.xpath("tbody/tr["+row+"]/td[1]/bar/text")).getText();
			String Taken_Stud = table_element2.findElement(By.xpath("tbody/tr["+row+"]/td[11]")).getText();
			String No_Data = table_element2.findElement(By.xpath("tbody/tr["+row+"]/td[12]")).getText();
			SumPF_StudCount[row] = (Integer.parseInt(Taken_Stud) + Integer.parseInt(No_Data));
			//System.out.println("Program Funding task and student count on summary tab: "+SumPF_Tasks[row]+"--"+SumPF_StudCount[row]);
			//modified code..
			Sum_NotTaken[row] = Integer.parseInt(No_Data);
			SumPF_Taken[row] = Integer.parseInt(Taken_Stud);
//			System.out.println("Summary tab PF task: "+SumPF_Tasks[row]+" not taken: "+Sum_NotTaken[row]+" taken count: "+SumPF_Taken[row]+" and total target student "+SumPF_StudCount[row]);
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
//				System.out.println(SumPF_val[i][j]); 			    	 
			}	
//				System.out.println("-------------");
		}

		WebElement table_element3 =Tasks_table.get(0);
		List<WebElement> tr_collection1=table_element3.findElements(By.cssSelector(".tableswithbars>tbody>tr"));
		System.out.println("NUMBER OF ROWS IN GENDER AND ETHNICITY TABLE = "+tr_collection1.size());	  
//		System.out.println("GENDER AND ETHNICITY table: " +table_element3.getText());


		//Gender task and gender count on summary tab..	 
	    SumGender= new String[tr_collection1.size()];
		int[] SumGender_StudCount = new int[tr_collection1.size()];
		SumGen_NotTaken = new int[tr_collection1.size()];
		SumGen_Taken = new int[tr_collection1.size()];
		try{
			for(int row = 1; row < 5-2;row++){
				SumGender[row] =table_element3.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[1]/bar/text")).getText();
				String Taken_Stud = table_element3.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[11]")).getText();
				String No_Data = table_element3.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[12]")).getText();
				SumGender_StudCount[row] = (Integer.parseInt(Taken_Stud) + Integer.parseInt(No_Data));
				//System.out.println(SumGender[row]+" student count on summary tab: "+SumGender_StudCount[row]);
				//modified code..
				SumGen_NotTaken[row] = Integer.parseInt(No_Data); 
				SumGen_Taken[row] = Integer.parseInt(Taken_Stud); 
//				System.out.println("Summary tab gender: " +SumGender[row]+" not taken: "+SumGen_NotTaken[row]+" taken count: "+SumGen_Taken[row]+" and total target student "+SumGender_StudCount[row]);
			}	 
		}catch (Exception e){
			System.out.println("Gender not found");
		}

		//(modified) ing gender tasks count on the summary tab..
		//removes all nulls 
		ArrayList<String> al1 = new ArrayList<String>(); 
		for(int i=0; i<SumGender.length; i++){ 
			if(SumGender[i]!=null) 
				al1.add(SumGender[i]); 
		}
		SumGender = (String[])al1.toArray(new String[al1.size()]);
//		System.out.println("summary--> "+Arrays.toString(SumGender));

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
//					System.out.println(SumGender_val[i][j]); 			    	 
				}	
//					System.out.println("------>");
			}
		}catch (Exception e){
			System.out.println("Gender not found: " +Arrays.toString(SumGender));
		}

		//Ethnicity tasks and student count on summary tab..
		String[] SumEthnicityTask= new String[tr_collection1.size()+1];	
		int[] SumEthn_StudCount = new int[tr_collection1.size()+1];
//		int max=tr_collection1.size()+1;
		for (int x =0;x<tr_collection1.size();x++){
			SumEthnicityTask[x] = table_element3.findElement(By.xpath("tbody/tr["+(x+1)+"]/td")).getText(); 
//			System.out.println("ethnitask: "+SumEthnicityTask[x].trim());
		}

		int index1=0;
		for(int a=0; a<SumEthnicityTask.length; a++){
			String value = SumEthnicityTask[a].trim();
			//  System.out.println(value);
			if(value.equals("Ethnicity")){
				index1=a;
//				System.out.println("Ethnicity index: "+index1); 
				break;
			}
		}

//		int EthnIndex = index1 + 2;
		SumEthnicity = new String[tr_collection1.size()+1];
		SumEthNotTaken = new int[tr_collection1.size()+1];
		SumEthTaken = new int[tr_collection1.size()+1];
		int max1=  tr_collection1.size() + 1;

		for(int row = 0; row < max1-5;row++){	    	  
			SumEthnicity[row] =table_element3.findElement(By.xpath("tbody/tr["+(row+5)+"]/td[1]/bar/text")).getText();
			String Taken_Stud= table_element3.findElement(By.xpath("tbody/tr["+(row+5)+"]/td[11]")).getText();
			String No_Data = table_element3.findElement(By.xpath("tbody/tr["+(row+5)+"]/td[12]")).getText();
			SumEthn_StudCount[row] = Integer.parseInt(Taken_Stud) + Integer.parseInt(No_Data);
			//System.out.println(SumEthnicity[row]+" student count on summary tab: "+SumEthn_StudCount[row]);
			//modified code..
			SumEthNotTaken[row] = Integer.parseInt(No_Data); 
			SumEthTaken[row] = Integer.parseInt(Taken_Stud); 
//			System.out.println(SumEthnicity[row]+"---"+SumEthNotTaken[row]+"---"+SumEthTaken[row]+" and total count "+SumEthn_StudCount[row]);
		}

		//(modified code) reading all ethnicity tasks data on the summary tab..
		//removes all nulls 
		ArrayList<String> Sumethn = new ArrayList<String>(); 
		for(int i=0; i<SumEthnicity.length; i++){ 
			if(SumEthnicity[i]!=null) 
				Sumethn.add(SumEthnicity[i]); 
		}
		SumEthnicity = (String[])Sumethn.toArray(new String[Sumethn.size()]);
//		System.out.println("sum ethn task--> "+Arrays.toString(SumEthnicity));

		SumEthnicity_val = new int[Genrow+1][scolm];     
		for(int i=0;i < max1-5;i++){
			for(int j=0;j<scolm;j++){
				String Demovalue1 = table_element3.findElement(By.xpath("tbody/tr["+(i+5)+"]/td["+(j+3)+"]")).getText();
				if(Demovalue1.equalsIgnoreCase("N/A")){
					SumEthnicity_val[i][j]= -1;
				}else{
					SumEthnicity_val[i][j] = Integer.parseInt(Demovalue1); 
				}
//				System.out.println("ethnicity: "+SumEthnicity_val[i][j]); 			    	 
			}	
//				System.out.println("------>");
		}
		
		String[] SumGender_Ethn_Task = ObjectArrays.concat(SumGender, SumEthnicity, String.class);	            
		//removes all nulls 
		ArrayList<String> al = new ArrayList<String>(); 
		for(int i=0; i<SumGender_Ethn_Task.length; i++){ 
			if(SumGender_Ethn_Task[i]!=null) 
				al.add(SumGender_Ethn_Task[i]); 
		}
		SumGender_Ethn_Task = (String[])al.toArray(new String[al.size()]);
//	    System.out.println("-->"+Arrays.toString(SumGender_Ethn_Task));

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
		
//		System.out.println("All Filter task and task count on summary tab----->"+Arrays.toString(SumFilter_Task)+ "----" +Arrays.toString(Sum_TaskCount));
 		
	}	
	
}
