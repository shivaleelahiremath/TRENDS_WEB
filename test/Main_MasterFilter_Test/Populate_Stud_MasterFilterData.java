package Main_MasterFilter_Test;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Populate_Stud_MasterFilterData {
	
	String[] StudFilterTasks;
	int[] StudFilterTargetCount;
	
	public void populate_Stud_MasterFilterData(WebDriver driver) throws InterruptedException{
    	//selecting assessment..
		List<WebElement> Assessment= driver.findElements(By.className("go"));
		WebElement AssessmentClick=(WebElement) Assessment.get(1); 
		AssessmentClick.click();
		System.out.println("Selected document..");
		Thread.sleep(4000);

		List<WebElement> allElements = driver.findElements(By.name("multiselect_filterpicker")); 
		System.out.println("Filter button selected and Elements present in Filter button: " +allElements.size()); 
		
		StudFilterTasks = new String[allElements.size()];
		StudFilterTargetCount = new int[allElements.size()]; 
		for (int x = 0;x<2;x++){
			driver.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
			driver.findElement(By.className("ui-multiselect-none")).click();        	   
			StudFilterTasks[x] = driver.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).getAttribute("title"); 
			driver.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).click();
			driver.findElement(By.className("ui-multiselect-close")).click();
			Thread.sleep(2000);
			String FCount = driver.findElement(By.className("reccount")).getText();
			StudFilterTargetCount[x] = Integer.parseInt(FCount.replace(" Records", ""));					
			System.out.println("task: "+StudFilterTasks[x].trim() +" selected and count : "+StudFilterTargetCount[x]);
		
		}
		driver.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
		driver.findElement(By.className("ui-multiselect-none")).click();        	   
		driver.findElement(By.className("ui-multiselect-close")).click();				
		Thread.sleep(4000);
	}
	
	

}
