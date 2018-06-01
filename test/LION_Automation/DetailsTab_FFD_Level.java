package LION_Automation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Emerging series..

public class DetailsTab_FFD_Level {
	
	String[] FF01Data, FF03Data;
	String[] FFD_Level = {"FF0_Score_Total","FF1_Score_Total","FF2_Score_Total","FF3_Score_Total","FF4_Score_Total","FF5_Score_Total","FF6_Score_Total","FF7_Score_Total","FF8_Score_Total","FF9_Score_Total"};

	public void populate_Details_data(String StudentID, WebDriver driver) throws InterruptedException {
		
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[7]/tab")).click();
		Thread.sleep(2000);
		
		WebElement studentID = driver.findElement(By.xpath(".//*[@id='StudentId']/div/input"));
		studentID.sendKeys(StudentID);
		studentID.sendKeys(Keys.ENTER); 
		Thread.sleep(2000);				
			
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.id("FF1_Score_Total"));
		js.executeScript("arguments[0].scrollIntoView(true);",element);
		Thread.sleep(2000);

		String level = driver.findElement(By.xpath("//*[@id='FF1_Score_Total']/left")).getText();
		System.out.println("Selected: "+level);
		
//		driver.findElement(By.xpath("//*[@id='MaintenanceLionAssessmentFFDetails']/tr/td["+(l+20)+"]/a")).click();
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
		
		int index =1;
		FF01Data = new String[9];
		for (String key : answerData.keySet()){
			if (answerData.get(key).equalsIgnoreCase("1")){
//			    System.out.println("Correct Answers: FF01 "+key);
				FF01Data[index]= key;
//		    	System.out.println(FF01Data[index]);
				index++;		   
			}			 
		}	
		
		driver.navigate().back();
		Thread.sleep(4000);
		
		WebElement element1 = driver.findElement(By.id("FF3_Score_Total"));
		js.executeScript("arguments[0].scrollIntoView(true);",element1);
		Thread.sleep(2000);

		String level1 = driver.findElement(By.xpath("//*[@id='FF3_Score_Total']/left")).getText();
		System.out.println("Selected: "+level1);
		
//		driver.findElement(By.xpath("//*[@id='MaintenanceLionAssessmentFFDetails']/tr/td["+(l+20)+"]/a")).click();
		driver.findElement(By.cssSelector("#MaintenanceLionAssessmentFFDetails>tr>td.FF3_Score_Total.hasColor>a")).click();
		Thread.sleep(2000);

		//FF03 task
		int FF03col = 6;
		String[][] FF03answerText = new String[row][FF03col];
		String[][] FF03answerScore = new String[row][FF03col];
        Map<String,String> FF03answerData = new HashMap<String, String>();
		for(int i=0; i<row;i++){
			for(int j=0;j<col;j=j+2){
				FF03answerText[i][j] = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='MaintenanceLionAssessmentCore']/tr["+(i+1)+"]/td["+(j+12)+"]"))).getText();
				FF03answerScore[i][j] = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='MaintenanceLionAssessmentCore']/tr["+(i+1)+"]/td["+(j+13)+"]/right"))).getText();
//				System.out.println("--> " +answerText[i][j]+ " --> "+answerScore[i][j]);
				FF03answerData.put(FF03answerText[i][j], FF03answerScore[i][j]);
			}	
		}

		int index1=1;
		FF03Data = new String[9];
		for (String key : FF03answerData.keySet()){
			if (FF03answerData.get(key).equalsIgnoreCase("1")){
//			    System.out.println("Correct Answers: FF03 "+key);
			    FF03Data[index1]= key;
//		    	System.out.println(FF03Data[index1]);
		    	index1++;	
			}			 
		}
		
//		driver.findElement(By.xpath("/html/body/header/top/tabs/a[7]/tab")).click();
		Thread.sleep(3000);		
		
		
		
	}

	

}
