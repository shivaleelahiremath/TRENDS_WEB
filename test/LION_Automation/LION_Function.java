package LION_Automation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LION_Function {
	
	WebDriver driver;
	
	public void StudentFilter() throws InterruptedException{
		WebElement studentID = driver.findElement(By.xpath(".//*[@id='StudentId']/div/input"));
		studentID.sendKeys("4302182");
		studentID.sendKeys(Keys.ENTER); 
		Thread.sleep(2000);		
		//1602342
		//4302182
		
	}
	
	public void rhyming() throws InterruptedException{
		
		driver.findElement(By.cssSelector("#MaintenanceLionAssessmentFFDetails>tr>td.FF1_Score_Total.hasColor>a")).click();
		Thread.sleep(2000);
		List<WebElement> data = driver.findElements(By.id("MaintenanceLionAssessmentCore"));
		WebElement listdata= data.get(3);
//		System.out.println(listdata.getText());`
		List<WebElement> tr_collection=listdata.findElements(By.cssSelector("#MaintenanceLionAssessmentCore>tr"));
//		List<WebElement> tr_collection1=listdata.findElements(By.xpath(".//*[@id='MaintenanceLionAssessmentVocabularyBreakDown']/tr"));
//		System.out.println("--> "+tr_collection.size()+"  "+tr_collection1.size());

		int row = tr_collection.size();
		int col = 6;
		String[][] answerText = new String[row][col];
		String[][] answerScore = new String[row][col];
        Map<String,String> answerData = new HashMap<String, String>();
		WebDriverWait wait = new WebDriverWait(driver,6); 
       // Get FF01 task...
		for(int i=0; i<row;i++){
			for(int j=0;j<col;j=j+2){
				answerText[i][j] = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='MaintenanceLionAssessmentCore']/tr["+(i+1)+"]/td["+(j+12)+"]"))).getText();
				answerScore[i][j] = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='MaintenanceLionAssessmentCore']/tr["+(i+1)+"]/td["+(j+13)+"]/right"))).getText();
//				System.out.println("--> " +answerText[i][j]+ " --> "+answerScore[i][j]);
				answerData.put(answerText[i][j], answerScore[i][j]);
			}	
		}
		
	}

}
