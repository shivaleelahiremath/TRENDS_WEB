package Maintenance_CPALLS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Populate_MathScienceTab_Data {

      String[] filterTasks  = {"MAP","NMA"};
	  String[] readMathTabData = new String[filterTasks.length];     
	  String[] readScienceTabData = new String[filterTasks.length];
	  
	//Reading Mathmatics tab math overall NMA and MAP count..
		public void populate_MathData(WebDriver driver) throws InterruptedException{
//	        System.out.println("math tab..");		
			driver.findElement(By.xpath("html/body/header/top/tabs/a[3]/tab")).click();
			Thread.sleep(4000);
			WebDriverWait wait = new WebDriverWait(driver,6); 

			//Readig Math Overall MAP and NMA count..
			for (int i=0;i<filterTasks.length; i++){
				WebElement element = driver.findElement(By.id("Math-Overall"));
	    		Actions actions = new Actions(driver);
	    		actions.moveToElement(element);
	    		//actions.click();
	    		actions.perform();
	    		Thread.sleep(1000);
	    		
	    		driver.findElement(By.xpath("//*[@id='Math-Overall']/colorfilter/selectedcolor")).click();
	    		Thread.sleep(1000);		
//	            System.out.println("selected the filter option.");
	            WebElement fOption= driver.findElement(By.cssSelector("#Math-Overall>color[value='"+filterTasks[i]+"']"));
	            fOption.click();
	            Thread.sleep(1000);
	            String MathOverall= wait.until(ExpectedConditions.elementToBeClickable(By.className("reccount"))).getText();
	            readMathTabData[i] = MathOverall.replace(" Records", "");
//	            System.out.println("Math overall : "+readMathTabData[i]);
	    	    Thread.sleep(2000);
			}
			driver.findElement(By.xpath("//*[@id='Math-Overall']/colorfilter/selectedcolor")).click();
			Thread.sleep(1000);		
			driver.findElement(By.cssSelector("#Math-Overall>color[value='NA']")).click();
			Thread.sleep(2000);
			
		}
		
		//Reading Science tab science overall NMA and MAP count..
		public void populate_ScienceData(WebDriver driver) throws InterruptedException{
//	        System.out.println("Science tab..");		
			driver.findElement(By.xpath("html/body/header/top/tabs/a[5]/tab")).click();
			Thread.sleep(4000);
			
			WebDriverWait wait = new WebDriverWait(driver,6); 
			 for(int i=0 ; i< filterTasks.length; i++){
//		        	WebElement filterClick = filterData.findElement(By.tagName("colorfilter"));
//		    		WebElement select = filterClick.findElement(By.tagName("selectedcolor"));
//		    		select.click();
		        	
		    		driver.findElement(By.xpath("//*[@id='M6-Score']/colorfilter/selectedcolor")).click();
		    		Thread.sleep(2000);		
//		            System.out.println("selected the filter option.");	             
		            WebElement fo= driver.findElement(By.cssSelector("color[value='"+filterTasks[i]+"']"));
		            fo.click();
		            Thread.sleep(1000);
		            String MathOverall = wait.until(ExpectedConditions.elementToBeClickable(By.className("reccount"))).getText();
		            readScienceTabData[i] = MathOverall.replace(" Records", "");
//		            System.out.println(filterTasks[i]+" "+readScienceTabData[i]);
		        }
			    driver.findElement(By.xpath("//*[@id='M6-Score']/colorfilter/selectedcolor")).click();
				Thread.sleep(1000);		
				driver.findElement(By.cssSelector("#M6-Score>color[value='NA']")).click();
				Thread.sleep(2000);
				
		}

}
