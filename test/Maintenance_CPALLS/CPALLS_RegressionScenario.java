package Maintenance_CPALLS;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CPALLS_RegressionScenario {
	
	public void greenArrow(WebDriver driver) throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		//Clicking on green arrow ..
	    List<WebElement> greenArrow= driver.findElements(By.className("go"));
	    WebElement greenArrowClick=(WebElement) greenArrow.get(1); 
	    greenArrowClick.click();
	    Thread.sleep(2000);
//	    System.out.println("Selected green arrow..");
	}
	
	public void navigateBack(WebDriver driver) throws InterruptedException{
		driver.navigate().back();
	    Thread.sleep(2000);
	}
	
    public void studentFilter(WebDriver driver, String studID) throws InterruptedException{
	    
		Thread.sleep(2000);
		WebElement studentID = driver.findElement(By.xpath("//*[@id='StudentId']/div/input"));
		studentID.sendKeys(studID);
		studentID.sendKeys(Keys.ENTER);	
	    Thread.sleep(3000);
	}
    
    public void CounsellorLogin(WebDriver driver) throws InterruptedException {
		driver.findElement(By.id("menubutton")).click();
	    Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='ui-id-1']/a")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("/html/body/header/top/tabs/a[3]/tab")).click();
	}
	
	public void staffNavigation(WebDriver driver, String Email) throws InterruptedException{
		
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[3]/tab")).click();
	    Thread.sleep(2000);
	    
		WebElement login = driver.findElement(By.xpath("//*[@id='LoginName']/div/input"));
		login.sendKeys(Email);
		login.sendKeys(Keys.ENTER);	
	    Thread.sleep(2000);

		greenArrow(driver);
	    
	    driver.findElement(By.xpath("/html/body/header/top/tabs/a[2]/tab")).click();
	    Thread.sleep(2000);  
	}

    public void assessmentFilter(WebDriver driver) throws InterruptedException {
		
		driver.findElement(By.xpath("//*[@id='radio']/label[6]/span")).click();
	    Thread.sleep(2000);	 
		
		WebElement title = driver.findElement(By.xpath("//*[@id='Assessment']/div/input"));
		title.clear();
		title.sendKeys("C-PALLS+ English BOY (2015-2016)");
		title.sendKeys(Keys.ENTER);	
	    Thread.sleep(2000);
	 	    
	    greenArrow(driver);
	    Thread.sleep(1000);

	}
    
    public void verify_GreenArrow_Click(WebDriver driver, String studID) throws InterruptedException{
       
    	driver.findElement(By.xpath("/html/body/header/top/tabs/a[1]/tab")).click();
	    Thread.sleep(1000);
	    int i=1;
	    	do{
		    driver.findElement(By.xpath("/html/body/header/top/tabs/a["+i+"]/tab")).click();
	    	i++;
		    Thread.sleep(2000);
	    	studentFilter(driver, studID);
		    Thread.sleep(2000);

	    	greenArrow(driver);
		    Thread.sleep(3000);
		    
		    //Select PM tab on history tab..
		    driver.findElement(By.xpath("//*[@id='radio']/label[2]/span")).click();
		    Thread.sleep(2000);
		    
		    try{
		    	driver.findElement(By.cssSelector(".LetterName>a>font")).click();
			    Thread.sleep(1000);
			    navigateBack(driver);
			    
			    driver.findElement(By.cssSelector(".LetterSound>a>font")).click();
			    Thread.sleep(1000);
			    navigateBack(driver);	    
		    }catch(Exception e){
//		    	System.out.println("Letter name and letter sound not found on PM tab..");
		    	
		    }	    
		    //Select PM Detail tab on history tab..
		    driver.findElement(By.xpath("//*[@id='radio']/label[3]/span")).click();  
	    }while(i<7);
	    	
    }

	public void verify_HotLink_Fun(WebDriver driver, String studID) throws InterruptedException {
		
        driver.findElement(By.xpath("/html/body/header/top/tabs/a[2]/tab")).click();
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		studentFilter(driver, studID);	
		String[] taskOptions = {"PAS-Overall","M2-S1-Score","M3-Score","Math-Overall","M6-Score"};		
		for(int i =0; i<taskOptions.length; i++ ){
			
			WebElement element = driver.findElement(By.id(taskOptions[i]));
			js.executeScript("arguments[0].scrollIntoView(true);",element);
			Thread.sleep(4000);
			
			driver.findElement(By.cssSelector("."+taskOptions[i]+".hasColor>a")).click();	
			Thread.sleep(2000);

		    navigateBack(driver);
		}
		
		String[] observableOptions = {"M5-S1-Score","M5-S2-Score","M5-S3-Score"};
		for(int i=0 ;i< observableOptions.length; i++){
//			studentFilter(driver);
			WebElement element = driver.findElement(By.id(observableOptions[i]));
			js.executeScript("arguments[0].scrollIntoView(true);",element);
			Thread.sleep(4000);
			
			driver.findElement(By.cssSelector("."+observableOptions[i]+">a>font")).click();	
			Thread.sleep(2000);

		    navigateBack(driver);
		}
		
	}

	public void verify_HistorySubTabs(WebDriver driver, String studID) throws InterruptedException {
        driver.findElement(By.xpath("/html/body/header/top/tabs/a[1]/tab")).click();
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		studentFilter(driver, studID);	
		String[] taskOptions = {"PAS-Overall","M2-S1-Score","M3-Score","Math-Overall","M6-Score"};		
		for(int i =0; i<taskOptions.length; i++ ){
			WebElement element = driver.findElement(By.id(taskOptions[i]));
			js.executeScript("arguments[0].scrollIntoView(true);",element);
			Thread.sleep(4000);
//			System.out.println("Selected : "+taskOptions[i]);
			driver.findElement(By.cssSelector("."+taskOptions[i]+".hasColor>a")).click();	
			Thread.sleep(2000);
			for(int j=1; j<=6 ;j++){
				driver.findElement(By.xpath("//*[@id='radio']/label["+j+"]/span")).click();
				Thread.sleep(2000);
			}
        driver.findElement(By.xpath("/html/body/header/top/tabs/a[2]/tab")).click();
		}
		
		String[] observableOptions = {"M5-S1-Score","M5-S2-Score","M5-S3-Score"};
		for(int i=0 ;i< observableOptions.length; i++){
//			studentFilter(driver);
			WebElement element = driver.findElement(By.id(observableOptions[i]));
			js.executeScript("arguments[0].scrollIntoView(true);",element);
			Thread.sleep(4000);
//			System.out.println("Selected : "+observableOptions[i]);
			driver.findElement(By.cssSelector("."+observableOptions[i]+">a>font")).click();	
			Thread.sleep(2000);
			for(int j=1; j<=6 ;j++){
				driver.findElement(By.xpath("//*[@id='radio']/label["+j+"]/span")).click();
				Thread.sleep(2000);
			}
	        driver.findElement(By.xpath("/html/body/header/top/tabs/a[2]/tab")).click();
		}
		
	}

	public void verify_FilterOptions(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[1]/tab")).click();
		Thread.sleep(2000);
		
		List<WebElement> allElements = driver.findElements(By.name("multiselect_filterpicker")); 
//		System.out.println("Filter button selected and Elements present in Filter button: " +allElements.size());  

		for(int x = 0; x < allElements.size(); x++){
			driver.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
			driver.findElement(By.className("ui-multiselect-none")).click();        	   
			driver.findElement(By.id("ui-multiselect-filterpicker-option-"+x+"")).click();
			driver.findElement(By.className("ui-multiselect-close")).click();
			Thread.sleep(2000);
		}
		driver.findElement(By.xpath("html/body/header/bottom/toolbar/button[1]")).click();
		driver.findElement(By.className("ui-multiselect-none")).click();        	   
		driver.findElement(By.className("ui-multiselect-close")).click();				
		Thread.sleep(4000);			
	}
	
	
}
