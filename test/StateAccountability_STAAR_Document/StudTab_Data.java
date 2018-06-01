package StateAccountability_STAAR_Document;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StudTab_Data {
	int[] StudPF_StudCount;
	String[] StudPF_Tasks;
	int	StudTotalTargetStud;
	
	public void populate_studentTabData(WebDriver driver) throws InterruptedException{
		
		//selecting assessment..
		List<WebElement> Assessment= driver.findElements(By.className("go"));
		WebElement AssessmentClick=(WebElement) Assessment.get(1); 
		AssessmentClick.click();
		System.out.println("Selected document..");
		Thread.sleep(2000);
		
		//Get total target student count on student tab.
		String StudTakenCount1 = driver.findElement(By.className("reccount")).getText();
	    StudTotalTargetStud = Integer.parseInt(StudTakenCount1.replace(" Records", ""));
//		System.out.println("target student count on student tab:  "+StudTotalTargetStud);   	   

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
			if(value.equals("Elementary")){
				index=a;
//				System.out.println("Elementary index: "+index);
			}
		}
		
		for(int x = 0; x < index; x++){
			driver.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
			driver.findElement(By.className("ui-multiselect-none")).click();        	   
			StudPF_Tasks[x] = driver.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).getAttribute("title");  	   
			driver.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).click();
			driver.findElement(By.className("ui-multiselect-close")).click();
			Thread.sleep(3000);
			String FilterRowCount = driver.findElement(By.className("reccount")).getText();
			StudPF_StudCount[x] = Integer.parseInt(FilterRowCount.replace(" Records", ""));
//		    System.out.println("Selected "+StudPF_Tasks[x].trim()+" task and total records on Student tab are: "+StudPF_StudCount[x]);
		}
		driver.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
		driver.findElement(By.className("ui-multiselect-none")).click();        	   
		driver.findElement(By.className("ui-multiselect-close")).click();
		Thread.sleep(4000);
		
	}
	
	
	
}
