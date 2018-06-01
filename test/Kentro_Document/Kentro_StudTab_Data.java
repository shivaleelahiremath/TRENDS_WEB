package Kentro_Document;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Kentro_StudTab_Data {
	int[] StudPF_StudCount;
	String[] StudPF_Tasks;
	int	StudTargetStudent;
	
	public void populate_studentTabData(WebDriver driver) throws InterruptedException{
		//selecting assessment..
		List<WebElement> Assessment= driver.findElements(By.className("go"));
		WebElement AssessmentClick=(WebElement) Assessment.get(1); 
		AssessmentClick.click();
		System.out.println("Selected document..");
		Thread.sleep(2000);

		//Selecting total target student on student tab..		
		String StudTotalStud = driver.findElement(By.className("reccount")).getText();
		StudTargetStudent = Integer.parseInt(StudTotalStud.replace(" Records", ""));
//		System.out.println("StudTotalStud : "+StudTargetStudent);

		//PF and gender filter functionality..
		List<WebElement> allElements = driver.findElements(By.name("multiselect_filterpicker")); 
		System.out.println("Filter button selected and Elements present in Filter button: " +allElements.size());  

	    StudPF_StudCount = new int[allElements.size()];
		StudPF_Tasks = new String[allElements.size()];

		for (int x =0;x<allElements.size();x++){
			StudPF_Tasks[x] = driver.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).getAttribute("title"); 
			// System.out.println("task: "+StudPF_Tasks[x].trim());
		}
		int index=0;
		for(int a=0; a<StudPF_Tasks.length; a++){
			String value = StudPF_Tasks[a].trim();
			//  System.out.println(value);
			if(value.equals("All Failed")){
				index=a;
//			    System.out.println("All failed index: "+index);
			}
		}

		for(int x = 0; x <= index; x++){
			driver.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
			driver.findElement(By.className("ui-multiselect-none")).click();        	   
			StudPF_Tasks[x] = driver.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).getAttribute("title");  	   
			driver.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).click();
			driver.findElement(By.className("ui-multiselect-close")).click();
			Thread.sleep(2000);
			String FilterRowCount = driver.findElement(By.className("reccount")).getText();
			StudPF_StudCount[x] = Integer.parseInt(FilterRowCount.replace(" Records", ""));
//			System.out.println("Selected "+StudPF_Tasks[x].trim()+" task and total records on Student tab are: "+StudPF_StudCount[x]);
		}
		driver.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
		driver.findElement(By.className("ui-multiselect-none")).click();        	   
		driver.findElement(By.className("ui-multiselect-close")).click();				
		Thread.sleep(4000);
	
		
		/*//redaing total count on item analysis tab..(pending..)
		 
		List<WebElement> sel = driver.findElements(By.id("MaintenanceAssessmentStudentMastery"));	            	
		WebElement sel1 = sel.get(4);
		List<WebElement> Student= sel1.findElements(By.className("greenarrow"));
		System.out.println("total students: " +Student.size());
		WebElement StudentClick=(WebElement) Student.get(1); 
		StudentClick.click();
		
				// Selecting Item Analysis tab...
//				List<WebElement> sel = driver.findElements(By.id("MaintenanceAssessmentStudentMastery"));	            	
//				WebElement sel1 = sel.get(3);
//				List<WebElement> Student= sel1.findElements(By.className("greenarrow"));
//				System.out.println("total students: " +Student.size());
//				WebElement StudentClick=(WebElement) Student.get(2); 
//				StudentClick.click();

				((WebElement) driver.findElements(By.xpath("//*[@id='MaintenanceAssessmentStudentMastery']/tr[1]/td[2]/img"))).click();

				// 				driver.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]")).click();	
//				Thread.sleep(4000); 

				//*[@id="MaintenanceAssessmentStudentMastery"]/tr[1]/td[2]/img
//				List<WebElement> Student= driver.findElements(By.className("greenarrow"));
//				WebElement StudentClick=(WebElement) Student.get(1); 
//				StudentClick.click();
//				Thread.sleep(2000); 

				//Selecting total questions on Item Analysis tab... 	   
				String QuestionCount = driver.findElement(By.className("reccount")).getText();
				int DocumentTotalQuestions = Integer.parseInt(QuestionCount.replace(" Records", ""));
				System.out.println(" Total Questions on Item Analysis tab : "+DocumentTotalQuestions);
		 */
		
	}

}
