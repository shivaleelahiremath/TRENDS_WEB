package Sanity_Test;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sanity_Scenarios {
			
	public void backMain(WebDriver driver) throws InterruptedException{
		
		driver.findElement(By.id("menubutton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("ui-id-1")).click();
		Thread.sleep(2000);	
	}
	
	public void kentro_Tab(WebDriver driver) throws InterruptedException{

		System.out.println("kentro tab.");
		driver.findElement(By.xpath("//*[@id='radio']/label[1]/span")).click();
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver,8); 
//		driver.findElement(By.xpath("html/body/content/div[1]/div/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/content/div[1]/div/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img"))).click();
		Thread.sleep(2000);
		
		//select TEKS mastery tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[2]/tab")).click();
		Thread.sleep(2000);
		
		//select RC mastery tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[3]/tab")).click();
		Thread.sleep(2000);
		
		//select Item analysis tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[4]/tab")).click();
		Thread.sleep(2000);
		
		//select Demography tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[5]/tab")).click();
		Thread.sleep(2000);
		
		//select Remediation tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[6]/tab")).click();
		Thread.sleep(2000);
		
		//select summary tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[1]/tab")).click();
		Thread.sleep(2000);
		
		backMain(driver);
		
	}
	
	public void staar_Tab(WebDriver driver) throws InterruptedException{
		
		System.out.println("staar tab.");

		driver.findElement(By.xpath("//*[@id='radio']/label[2]/span")).click();
		Thread.sleep(4000);
		
		WebDriverWait wait = new WebDriverWait(driver,8); 
//		driver.findElement(By.xpath("html/body/content/div[1]/div/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/content/div[1]/div/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img"))).click();
		Thread.sleep(2000);
			
		//select TEKS mastery tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[2]/tab")).click();
		Thread.sleep(2000);
		
		//select RC mastery tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[3]/tab")).click();
		Thread.sleep(2000);
		
		//select Item analysis tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[4]/tab")).click();
		Thread.sleep(2000);
		
		//select Demography tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[5]/tab")).click();
		Thread.sleep(2000);
		
		//select Remediation tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[6]/tab")).click();
		Thread.sleep(2000);
		
		//select Rubrics tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[7]/tab")).click();
		Thread.sleep(2000);
		
		//select summary tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[1]/tab")).click();
		Thread.sleep(2000);
		
		backMain(driver);
				
	}
	
	public void telpas_Tab(WebDriver driver) throws InterruptedException{
		
		driver.findElement(By.xpath("//*[@id='radio']/label[3]/span")).click();
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver,8); 
//		driver.findElement(By.xpath("html/body/content/div[1]/div/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/content/div[1]/div/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img"))).click();
		Thread.sleep(3000);
	
		//select Item analysis tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[2]/tab")).click();
		Thread.sleep(2000);
		
		//select SE mastery tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[3]/tab")).click();
		Thread.sleep(2000);
				
		//select RC mastery tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[4]/tab")).click();
		Thread.sleep(2000);
					
		//select Demography tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[5]/tab")).click();
		Thread.sleep(2000);
				
		//select Remediation tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[6]/tab")).click();
		Thread.sleep(2000);
				
		//select summary tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[1]/tab")).click();
	    Thread.sleep(2000);
				
	    backMain(driver);
	
	}
	
	public void lion_Tab(WebDriver driver) throws InterruptedException{
		
		driver.findElement(By.xpath("//*[@id='radio']/label[4]/span")).click();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,8); 
//		driver.findElement(By.xpath("html/body/content/div[1]/div/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/content/div[1]/div/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img"))).click();
		Thread.sleep(3000);
		
		//select Instructional tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[2]/tab")).click();
		Thread.sleep(2000);
				
		//select Analysis tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[3]/tab")).click();
		Thread.sleep(2000);
						
		//select Reading tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[4]/tab")).click();
		Thread.sleep(2000);
							
		//select Foundation tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[5]/tab")).click();
		Thread.sleep(2000);
		
		//select History tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[6]/tab")).click();
		Thread.sleep(2000);
				
		//select Details tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[7]/tab")).click();
		Thread.sleep(2000);
		
		//select Summary tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[1]/tab")).click();
		Thread.sleep(2000);
		
		backMain(driver);
		
	}
	
	public void kReady_Tab(WebDriver driver) throws InterruptedException{
		
		driver.findElement(By.xpath("//*[@id='radio']/label[5]/span")).click();
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver,8); 
//		driver.findElement(By.xpath("html/body/content/div[1]/div/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/content/div[1]/div/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img"))).click();
		Thread.sleep(3000);
		
		//select Math tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[2]/tab")).click();
		Thread.sleep(2000);
				
		//select Science tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[3]/tab")).click();
		Thread.sleep(2000);
						
		//select Skill Checklist tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[4]/tab")).click();
		Thread.sleep(2000);
							
		//select Summary tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[1]/tab")).click();
		Thread.sleep(2000);
		
		backMain(driver);
		
	}
	
	public void tpri_tjl_Tab(WebDriver driver) throws InterruptedException{
		
		driver.findElement(By.xpath("//*[@id='radio']/label[6]/span")).click();
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver,8); 
//		driver.findElement(By.xpath("html/body/content/div[1]/div/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/content/div[1]/div/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img"))).click();
		Thread.sleep(3000);
		
		//select SCR tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[2]/tab")).click();
		Thread.sleep(2000);
				
		//select PA tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[3]/tab")).click();
		Thread.sleep(2000);
						
		//select GK tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[4]/tab")).click();
		Thread.sleep(2000);
							
		//select WR tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[5]/tab")).click();
		Thread.sleep(2000);
		
		//select READ tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[6]/tab")).click();
		Thread.sleep(2000);
				
		//select COMP tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[7]/tab")).click();
		Thread.sleep(2000);
		
		//select Export tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[8]/tab")).click();
		Thread.sleep(2000);
		
		//select Summary tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[1]/tab")).click();
		Thread.sleep(2000);
		
		backMain(driver);
			
	}
	
	public void cpalls_Tab(WebDriver driver) throws InterruptedException{
		
		driver.findElement(By.xpath("//*[@id='radio']/label[7]/span")).click();
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver,8); 
//		driver.findElement(By.xpath("html/body/content/div[1]/div/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/content/div[1]/div/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img"))).click();
		Thread.sleep(3000);
		
		//select PA tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[2]/tab")).click();
		Thread.sleep(2000);
				
		//select Math tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[3]/tab")).click();
		Thread.sleep(2000);
						
		//select Observables tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[4]/tab")).click();
		Thread.sleep(2000);
							
		//select Science tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[5]/tab")).click();
		Thread.sleep(2000);
		
		//select Summary tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[1]/tab")).click();
		Thread.sleep(2000);
		
		backMain(driver);
		
	}
	
	public void stateAccountability_Tab(WebDriver driver) throws InterruptedException{
		
		//Selecting STAAR tab on assessment module..
		driver.findElement(By.id("menubutton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("ui-id-10")).click();
		Thread.sleep(4000);
		
		WebDriverWait wait = new WebDriverWait(driver,8); 
//		driver.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img")).click();		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img"))).click();
		Thread.sleep(3000);
			
		//select TEKS mastery tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[3]/tab")).click();
		Thread.sleep(2000);
		
		//select RC mastery tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[4]/tab")).click();
		Thread.sleep(2000);
		
		//select Item analysis tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[5]/tab")).click();
		Thread.sleep(2000);
		
		//select Demography tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[6]/tab")).click();
		Thread.sleep(2000);
		
		//select Remediation tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[7]/tab")).click();
		Thread.sleep(2000);
		
		//select Rubrics tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[8]/tab")).click();
		Thread.sleep(2000);
		
		//select summary tab..
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[2]/tab")).click();
		Thread.sleep(2000);
		
		backMain(driver);
		
	}

}
