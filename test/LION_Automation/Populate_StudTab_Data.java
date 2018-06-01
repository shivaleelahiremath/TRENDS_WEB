package LION_Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Populate_StudTab_Data {
	
	String Word_Know, series;
	String[] FicLevel;
	String[] Fic_Score_Total;
	
	public void populate_Stud_data(WebDriver driver) throws InterruptedException{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//Filtering the student ID..
		
		WebElement studentID = driver.findElement(By.xpath(".//*[@id='StudentId']/div/input"));
		studentID.sendKeys("1602508");
		studentID.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		Word_Know = driver.findElement(By.xpath(".//*[@id='MaintenanceLionAssessmentStudentMastery']/tr[1]/td[14]")).getText();
		System.out.println("Word_Know : "+Word_Know);		
		Thread.sleep(1000);

		WebElement element = driver.findElement(By.id("FictionStoryNumber"));
//		Actions actions = new Actions(driver);
//		actions.moveToElement(element);
//		actions.click();
//		actions.perform();
		js.executeScript("arguments[0].scrollIntoView(true);",element);
		Thread.sleep(1000);
		
		String FictionLevel1 = driver.findElement(By.xpath(".//*[@id='MaintenanceLionAssessmentStudentMastery']/tr[1]/td[16]")).getText();
		System.out.println("FictionLevel1 : "+FictionLevel1);	
		
		String FictionLevel2 = driver.findElement(By.xpath(".//*[@id='MaintenanceLionAssessmentStudentMastery']/tr[2]/td[16]")).getText();
		System.out.println("FictionLevel2 : "+FictionLevel2);	

//		for(int i=1; i<3; i++){
//			FicLevel[i] = driver.findElement(By.xpath(".//*[@id='MaintenanceLionAssessmentStudentMastery']/tr["+i+"]/td[16]")).getText();
//			System.out.println("FictionLevel : "+FicLevel[i]);	
//		}
		
		WebElement element1 = driver.findElement(By.id("Series"));
		Actions actions1 = new Actions(driver);
		actions1.moveToElement(element1);
		actions1.perform();
		Thread.sleep(1000);
		series = driver.findElement(By.xpath(".//*[@id='MaintenanceLionAssessmentStudentMastery']/tr[1]/td[19]")).getText();
		System.out.println("series : "+series);
		
		WebElement element2 = driver.findElement(By.id("FictionVocabulary_Score_Total"));
		js.executeScript("arguments[0].scrollIntoView(true);",element2);		
		for (int i=1; i<2; i++){
			Fic_Score_Total[i]= driver.findElement(By.xpath(".//*[@id='MaintenanceLionAssessmentStudentMastery']/tr[1]/td["+(i+20)+"]")).getText();
		}
		
		String Fic_Voc_Score= driver.findElement(By.xpath(".//*[@id='MaintenanceLionAssessmentStudentMastery']/tr[1]/td[19]")).getText();


	}
	
	
	
	

}
