package Maintenance_CPALLS;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AsmtTab_Data {
	
	String totalTaken;
	String[] PhonoAware = {"PhonoAware_MAP", "PhonoAware_NMA"};
	String[] readPhonoAwareData = new String[PhonoAware.length];
	
	String[] RapidLetter = {"RapidLetter_MAP", "RapidLetter_NMA"};
	String[] readRapidLetterData = new String[RapidLetter.length];
	
	String[] RapidVocabNaming = {"RapidVocabNaming_MAP","RapidVocabNaming_NMA"};
	String[] readRapidVocabNamingData = new String[RapidVocabNaming.length];

	String[] Mathmatics = {"Mathmatics_MAP","Mathmatics_NMA"};
	String[] readMathmaticsData = new String[Mathmatics.length];

	String[] Science = {"Science_MAP","Science_NMA"};	
	String[] readScienceData = new String[Science.length];
	
	public void filter_Asmttab(String DocTitle, WebDriver driver) throws InterruptedException{
		
		WebDriverWait wait = new WebDriverWait(driver, 10); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='radio']/label[6]/span"))).click();
		Thread.sleep(2000);	
		
		//Selecting year..
		driver.findElement(By.xpath("html/body/header/bottom/toolbar/form/span/span[1]/span/span[1]")).click();
	    driver.findElement(By.xpath("html/body/span[2]/span/span[2]/ul/li[2]")).click();
		Thread.sleep(2000);

		WebElement title = driver.findElement(By.xpath("//*[@id='Assessment']/div/input"));
		title.clear();
		title.sendKeys(DocTitle);
		title.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	}
	
	public void populate_AsmtTabData(WebDriver driver) throws InterruptedException{
		
		List<WebElement> data = driver.findElements(By.id("MaintenanceAssessmentListGrid"));
		WebElement data1 = data.get(3);
//		System.out.println(data1.getText());	
		
		String totalTaken1= data1.findElement(By.xpath("tbody/tr/td[4]")).getText();
		String regex = "(?<=[\\d])(,)(?=[\\d])";
	    Pattern p = Pattern.compile(regex);
	    String str = totalTaken1;
	    Matcher m = p.matcher(str);
	    str = m.replaceAll("");
//	    System.out.println(str);	
	    totalTaken =str;
//		System.out.println("Total taken: "+totalTaken);
		
		//Reading assessment tab data..
		for(int i=0 ; i<PhonoAware.length ;i++){
			readPhonoAwareData[i] = data1.findElement(By.xpath("tbody/tr/td["+(5+i)+"]/right")).getText();
//			System.out.println("PhonoAware : "+readPhonoAwareData[i]);		
		}
		
		for(int i=0 ; i<RapidLetter.length ;i++){
			readRapidLetterData[i] = data1.findElement(By.xpath("tbody/tr/td["+(7+i)+"]/right")).getText();
//			System.out.println("RapidLetter : "+readRapidLetterData[i]);		
		}
		
		for(int i=0 ; i<RapidVocabNaming.length ;i++){
			readRapidVocabNamingData[i] = data1.findElement(By.xpath("tbody/tr/td["+(9+i)+"]/right")).getText();
//			System.out.println("RapidVocabNaming : "+readRapidVocabNamingData[i]);		
		}
		
		for(int i=0 ; i<Mathmatics.length ;i++){
			readMathmaticsData[i] = data1.findElement(By.xpath("tbody/tr/td["+(11+i)+"]/right")).getText();
//			System.out.println("Mathmatics : "+readMathmaticsData[i]);		
		}
		
		for(int i=0 ; i<Science.length ;i++){
			readScienceData[i] = data1.findElement(By.xpath("tbody/tr/td["+(13+i)+"]/right")).getText();
//			System.out.println("Science : "+readScienceData[i]);		
		}
		
		//Selecting the assessment..
		List<WebElement> Assessment= driver.findElements(By.className("go"));
		WebElement AssessmentClick=(WebElement) Assessment.get(1); 
		AssessmentClick.click();
		System.out.println("Selected document..");
		Thread.sleep(4000);
		
	}

}
