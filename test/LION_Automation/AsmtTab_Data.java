package LION_Automation;

import java.util.List;

import jxl.Sheet;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AsmtTab_Data {

    public void populate_data(Sheet sht, WebDriver driver) throws InterruptedException{
    	
    	String startCode = sht.getCell(0,1).getContents();
		String benchmark = sht.getCell(1,1).getContents();
		String grade = sht.getCell(2,1).getContents();
		
    	//Selecting STAAR tab on assessment module..html/body/header/bottom/toolbar/left/div/label[2]/span
    	driver.findElement(By.xpath("html/body/header/bottom/toolbar/left/div/label[4]/span")).click();  
   		Thread.sleep(6000);
   		
	 	WebElement Grade = driver.findElement(By.xpath(".//*[@id='GradeLevel']/div/input"));
	 	Grade.clear();
	  	Grade.sendKeys(grade);
	  	Grade.sendKeys(Keys.ENTER);
	  	Thread.sleep(3000);
      
		WebElement StartCode = driver.findElement(By.xpath(".//*[@id='StartCode']/div/input"));
		StartCode.clear();
		StartCode.sendKeys(startCode); 
	    StartCode.sendKeys(Keys.ENTER);
	 	Thread.sleep(2000);
				
	 	WebElement BM_PM = driver.findElement(By.xpath(".//*[@id='GfEnable']/div/input"));
	 	BM_PM.clear();
	 	BM_PM.sendKeys(benchmark); 
	 	BM_PM.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
			  	 	
    	//selecting assessment..
    	List<WebElement> Assessment= driver.findElements(By.className("go"));
    	WebElement AssessmentClick=(WebElement) Assessment.get(1); 
    	AssessmentClick.click();
    	System.out.println("Selected document..");
    	Thread.sleep(4000);
		
	}
}
