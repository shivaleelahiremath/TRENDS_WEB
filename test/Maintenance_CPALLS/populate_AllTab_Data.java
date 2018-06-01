package Maintenance_CPALLS;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class populate_AllTab_Data {
	
    String[] filterTasks  = {"MAP", "NMA"};
    String[] readStudPhonoAwareData = new String[filterTasks.length];
    String[] readStudRapidLetterData = new String[filterTasks.length];
    String[] readStudRapidVocabNamingData = new String[filterTasks.length];
    String[] readStudMathmaticsData = new String[filterTasks.length];
    String[] readStudScienceData = new String[filterTasks.length];
    
    
    String[] readScienceTabData = new String[filterTasks.length];
    
    public void populate_StudData(WebDriver driver) throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("student tab..");
	
//		List<WebElement> filter = driver.findElements(By.id("PAS-Overall"));
//		WebElement filterData = filter.get(1);
//		System.out.println(filterData.getText());
		
//		WebElement filter = driver.findElement(By.id("PAS-Overall"));
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", filter);
//		Thread.sleep(2000);
//		driver.executeScript("document.getElementById('PAS-Overall').scrollIntoView(true);");        
       
		WebDriverWait wait = new WebDriverWait(driver,6); 
/*
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
            System.out.println("selected the filter option.");
            WebElement fOption= driver.findElement(By.cssSelector("#PAS-Overall>color[value='"+filterTasks[i]+"']"));
            fOption.click();
            readStudPhonoAwareData[i] = wait.until(ExpectedConditions.elementToBeClickable(By.className("reccount"))).getText();
    	    System.out.println("PA overall Screener :  "+readStudPhonoAwareData[i]);
    	    Thread.sleep(2000);
        }
		Thread.sleep(1000);
    	driver.findElement(By.xpath("//*[@id='PAS-Overall']/colorfilter/selectedcolor")).click();
		Thread.sleep(1000);		
		driver.findElement(By.cssSelector("#PAS-Overall>color[value='All']")).click();
		Thread.sleep(2000);
	*/	
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
            System.out.println("selected the filter option.");
            WebElement fOption = driver.findElement(By.cssSelector("#M2-S1-Score>color[value='"+filterTasks[i]+"']"));
            fOption.click();
            String RapidLetter = wait.until(ExpectedConditions.elementToBeClickable(By.className("reccount"))).getText();
            readStudPhonoAwareData[i] = RapidLetter.replace(" Records", "");
            System.out.println("PA overall Screener :  "+readStudPhonoAwareData[i]);
		}
		driver.findElement(By.xpath("//*[@id='PAS-Overall']/colorfilter/selectedcolor")).click();
		Thread.sleep(1000);		
		driver.findElement(By.cssSelector("#PAS-Overall>color[value='All']")).click();
		Thread.sleep(2000);
		
        /*
         
        List<WebElement> option = driver.findElements(By.tagName("colorlist"));
        WebElement op= option.get(0);
        System.out.println("tag text"+op.getText());
        
        List<WebElement> s1= driver.findElements(By.cssSelector(".ui-front.ui-menu.ui-widget.scorecolor"));
        WebElement s2 = s1.get(0);
        List<WebElement> Colors = s2.findElements(By.tagName("color"));
        System.out.println("-->1 "+s2.getText());
        System.out.println("-->2 "+Colors.size());
        System.out.println("selected..");
        Thread.sleep(2000);
        
        List<WebElement> s3= driver.findElements(By.cssSelector(".ui-front.ui-menu.ui-widget.scorecolor"));
        WebElement s4 = s3.get(0);
        List<WebElement> c1 = s4.findElements(By.tagName("color"));
        
   */     
        System.out.println("completed ....");
        
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", t);
//
//		WebElement th = t.findElement(By.id("PAS-Overall"));
//		th.findElement(By.tagName("selectedcolor")).click();
		
	/*	List<WebElement> ele12 = driver.findElements(By.id("MaintenanceSTAARAssessmentStudentMastery")); 
		WebElement t = ele12.get(1);
		List<WebElement> list1=t.findElements(By.tagName("th"));
		System.out.println("Headers available = " +list1.size());

		List<String> ColumnHeader = new ArrayList<String>(); 
		for(int w=1; w<list1.size()-2; w++){
//		System.out.println("header: "+w);
		List<WebElement> list11=t.findElements(By.tagName("th"));
		WebElement testvalue = list11.get(w+1).findElement(By.tagName("main"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", testvalue);
		String colmnHead = testvalue.findElement(By.tagName("left")).getText();
		String colmnHead1 = colmnHead.replaceAll("\n", " ");
		ColumnHeader.add(colmnHead1);
		//System.out.println("----------------"+colmnHead1);
		WebElement web=t.findElement(By.xpath("thead/tr/th["+(w+2)+"]"));
		List<WebElement> val = driver.findElements(By.tagName("colorlist"));
		//System.out.println("-->"+val.size());
		
		if(colmnHead1.equals("2015 STAAR Level")){
		web.findElement(By.tagName("selectedcolor")).click();
		System.out.println("clicked..");
		Thread.sleep(2000);
		
		String colorlist = driver.findElement(By.tagName("colorlist[8]")).getText();
		System.out.println("colorlist: "+colorlist);
		}
		}
		*/		 
	}
	

	//Reading PA tab science overall NMA and MAP count..
	public void populate_PAData(WebDriver driver) throws InterruptedException{
        System.out.println("student tab..");		
		driver.findElement(By.xpath("html/body/header/top/tabs/a[3]/tab")).click();
		Thread.sleep(4000);

	}

	//Reading Mathmatics tab math overall NMA and MAP count..
	public void populate_MathData(WebDriver driver) throws InterruptedException{
        System.out.println("student tab..");		
		driver.findElement(By.xpath("html/body/header/top/tabs/a[4]/tab")).click();
		Thread.sleep(4000);
		
		//*[@id="Math-Overall"]/colorfilter/selectedcolor

	}
	
	//Reading Science tab science overall NMA and MAP count..
	public void populate_ScienceData(WebDriver driver) throws InterruptedException{
        System.out.println("Science tab..");		
		driver.findElement(By.xpath("html/body/header/top/tabs/a[5]/tab")).click();
		Thread.sleep(4000);
		
		WebDriverWait wait = new WebDriverWait(driver,6); 
		 for(int i=0 ; i< filterTasks.length; i++){
//	        	WebElement filterClick = filterData.findElement(By.tagName("colorfilter"));
//	    		WebElement select = filterClick.findElement(By.tagName("selectedcolor"));
//	    		select.click();
	        	
	    		driver.findElement(By.xpath("//*[@id='M6-Score']/colorfilter/selectedcolor")).click();
	    		Thread.sleep(2000);		
//	            System.out.println("selected the filter option.");	             
	            WebElement fo= driver.findElement(By.cssSelector("color[value='"+filterTasks[i]+"']"));
	            fo.click();
	            Thread.sleep(4000);
	            readScienceTabData[i] = wait.until(ExpectedConditions.elementToBeClickable(By.className("reccount"))).getText();
	    	    System.out.println(filterTasks[i]+" "+readScienceTabData[i]);
	        }
	}
}
