package Maintenance_CPALLS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Populate_PATab_Data {
	
	String[] filterTasks  = {"MAP", "NMA"};
	String[] readPAPhonoAwareData = new String[filterTasks.length];
	String[] readPARapidLetterData = new String[filterTasks.length];
	String[] readPARapidVocabNamingData = new String[filterTasks.length];

	//Reading PA tab overall NMA and MAP count..
	public void populate_PAData(WebDriver driver) throws InterruptedException{
//		System.out.println("PA tab..");		
		driver.findElement(By.xpath("html/body/header/top/tabs/a[2]/tab")).click();
		Thread.sleep(4000);

		WebDriverWait wait = new WebDriverWait(driver,6); 

		//Reading PA Screener Overall MAP and NMA count..
		for(int i=0 ; i< filterTasks.length; i++){
			//        	WebElement filterClick = filterData.findElement(By.tagName("colorfilter"));
			//    		WebElement select = filterClick.findElement(By.tagName("selectedcolor"));
			//    		select.click();

			WebElement element = driver.findElement(By.id("PAS-Overall"));
			Actions actions = new Actions(driver);
			actions.moveToElement(element);
			//actions.click();
			actions.perform();
			Thread.sleep(3000);//*[@id="PAS-Overall"]/colorfilter/selectedcolor

			driver.findElement(By.xpath("//*[@id='PAS-Overall']/colorfilter/selectedcolor")).click();
			Thread.sleep(1000);		
//			System.out.println("selected the filter option.");
			WebElement fOption= driver.findElement(By.cssSelector("#PAS-Overall>color[value='"+filterTasks[i]+"']"));
			fOption.click();
			Thread.sleep(2000);
			String PAOverall= wait.until(ExpectedConditions.elementToBeClickable(By.className("reccount"))).getText();
			readPAPhonoAwareData[i] = PAOverall.replace(" Records", "");
//			System.out.println("PA overall Screener :  "+readPAPhonoAwareData[i]);
			Thread.sleep(2000);
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='PAS-Overall']/colorfilter/selectedcolor")).click();
		Thread.sleep(1000);		
		driver.findElement(By.cssSelector("#PAS-Overall>color[value='NA']")).click();
		Thread.sleep(2000);

		//Reading Rapid Letter Naming MAP and NMA count..
		for (int i=0;i<filterTasks.length; i++){
			WebElement element = driver.findElement(By.id("M2-S1-Score"));
			Actions actions = new Actions(driver);
			actions.moveToElement(element);
			//actions.click();
			actions.perform();
			Thread.sleep(1000);

			driver.findElement(By.xpath("//*[@id='M2-S1-Score']/colorfilter/selectedcolor")).click();
			Thread.sleep(1000);		
//			System.out.println("selected the filter option.");
			WebElement fOption= driver.findElement(By.cssSelector("#M2-S1-Score>color[value='"+filterTasks[i]+"']"));
			fOption.click();
			Thread.sleep(1000);
			String RapidLetter = wait.until(ExpectedConditions.elementToBeClickable(By.className("reccount"))).getText();
			readPARapidLetterData[i] = RapidLetter.replace(" Records", "");
//			System.out.println("Rapid letter naming:  "+readPARapidLetterData[i]);
			Thread.sleep(2000);
		}
		driver.findElement(By.xpath("//*[@id='M2-S1-Score']/colorfilter/selectedcolor")).click();
		Thread.sleep(1000);		
		driver.findElement(By.cssSelector("#M2-S1-Score>color[value='NA']")).click();
		Thread.sleep(2000);

		//Reading Rapid Vocabulary Naming MAP and NMA count..
		for (int i=0;i<filterTasks.length; i++){
			WebElement element = driver.findElement(By.id("M3-Score"));
			Actions actions = new Actions(driver);
			actions.moveToElement(element);
			//actions.click();
			actions.perform();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='M3-Score']/colorfilter/selectedcolor")).click();
			Thread.sleep(1000);		
//			System.out.println("selected the filter option.");
			WebElement fOption= driver.findElement(By.cssSelector("#M3-Score>color[value='"+filterTasks[i]+"']"));
			fOption.click();
			Thread.sleep(1000);
			String RapidVocab = wait.until(ExpectedConditions.elementToBeClickable(By.className("reccount"))).getText();
			readPARapidVocabNamingData[i] = RapidVocab.replace(" Records", "");
//			System.out.println("Rapid vocabulary naming:  "+readPARapidVocabNamingData[i]);
			Thread.sleep(2000);
		}
		driver.findElement(By.xpath("//*[@id='M3-Score']/colorfilter/selectedcolor")).click();
		Thread.sleep(1000);		
		driver.findElement(By.cssSelector("#M3-Score>color[value='NA']")).click();
		Thread.sleep(2000);

	}
}
