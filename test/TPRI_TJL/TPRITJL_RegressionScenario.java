package TPRI_TJL;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TPRITJL_RegressionScenario{
	
//	String Student_ID = "42168";
	
	public void greenArrow(WebDriver driver) throws InterruptedException{
		//Clicking on green arrow ..
	    List<WebElement> greenArrow= driver.findElements(By.className("go"));
	    WebElement greenArrowClick=(WebElement) greenArrow.get(1); 
	    greenArrowClick.click();
	    Thread.sleep(2000);
//	    System.out.println("Selected green arrow..");
	}
	
	public void studentFilter(WebDriver driver, String studentid) throws InterruptedException{
	    
		Thread.sleep(1000);
		WebElement studentID = driver.findElement(By.xpath("//*[@id='StudentId']/div/input"));
		studentID.sendKeys(studentid);
		studentID.sendKeys(Keys.ENTER);	
	    Thread.sleep(3000);
	}
	
	public void navigateBack(WebDriver driver) throws InterruptedException{
		driver.navigate().back();
	    Thread.sleep(3000);
	}
	
	public void CounsellorLogin(WebDriver driver) throws InterruptedException {
		driver.findElement(By.id("menubutton")).click();
	    Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='ui-id-1']/a")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("/html/body/header/top/tabs/a[3]/tab")).click();
	}

	public void NavigateStaff_Tab(WebDriver driver, String Email) throws InterruptedException {

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
		
		driver.findElement(By.xpath("//*[@id='radio']/label[5]/span")).click();
	    Thread.sleep(2000);	  
		
		WebElement title = driver.findElement(By.xpath("//*[@id='Title']/div/input"));
		title.sendKeys("TPRI BOY Benchmarks");
		title.sendKeys(Keys.ENTER);	
	    Thread.sleep(2000);
	    
	    WebElement grade = driver.findElement(By.xpath("//*[@id='GradeLevel']/div/input"));
	    grade.clear();
	    grade.sendKeys("01");
	    grade.sendKeys(Keys.ENTER);	
	    Thread.sleep(2000);
	    
	    greenArrow(driver);
	    Thread.sleep(1000);

	}

	public void studentTab(WebDriver driver, String studentid) throws InterruptedException {

		studentFilter(driver, studentid);
	    
	    driver.findElement(By.xpath("//*[@id='MaintenanceTPRIAssessmentStudentMastery']/tr[1]/td[5]/a/font")).click();
	    Thread.sleep(1000);
	    navigateBack(driver);
	    
		greenArrow(driver);
	    Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id='radio']/label[2]/span")).click();
	    Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id='radio']/label[3]/span")).click();
	    Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id='radio']/label[4]/span")).click();
	    Thread.sleep(1000);
	}

	public void SCRTab(WebDriver driver, String studentid) throws InterruptedException {

		driver.findElement(By.xpath("/html/body/header/top/tabs/a[3]/tab")).click();
        studentFilter(driver, studentid);
	    driver.findElement(By.xpath("//*[@id='MaintenanceTPRIAssessmentScreening']/tr[1]/td[5]/a/font")).click();
	    Thread.sleep(2000);
	    navigateBack(driver);
	}

	public void PATab(WebDriver driver, String studentid) throws InterruptedException {
		
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[3]/tab")).click();
		studentFilter(driver, studentid);
	    driver.findElement(By.xpath("//*[@id='MaintenanceTPRIAssessmentPhonemicAwareness']/tr[1]/td[5]/a/font")).click();
	    Thread.sleep(2000);
	    navigateBack(driver);
	}

	public void GKTab(WebDriver driver, String studentid) throws InterruptedException {

		driver.findElement(By.xpath("/html/body/header/top/tabs/a[4]/tab")).click();
		studentFilter(driver, studentid);
	    driver.findElement(By.xpath("//*[@id='MaintenanceTPRIAssessmentGraphophonemicKnowledge']/tr[1]/td[5]/a/font")).click();
	    Thread.sleep(2000);
	    navigateBack(driver);
	}

	public void WRTab(WebDriver driver, String studentid) throws InterruptedException {

		driver.findElement(By.xpath("/html/body/header/top/tabs/a[5]/tab")).click();
		studentFilter(driver, studentid);
	    driver.findElement(By.xpath("//*[@id='MaintenanceTPRIAssessmentWordReading']/tr[1]/td[5]/a/font")).click();
	    Thread.sleep(2000);
	    navigateBack(driver);

	}

	public void READTab(WebDriver driver, String studentid ) throws InterruptedException {
		
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[6]/tab")).click();
		studentFilter(driver, studentid);
	    driver.findElement(By.xpath("//*[@id='MaintenanceTPRIAssessmentOralReading']/tr[1]/td[5]/a/font")).click();
	    Thread.sleep(2000);
	    navigateBack(driver);
		
	}

	public void COMPTab(WebDriver driver, String studentid) throws InterruptedException {

		driver.findElement(By.xpath("/html/body/header/top/tabs/a[7]/tab")).click();
		studentFilter(driver, studentid);
	    driver.findElement(By.xpath("//*[@id='MaintenanceTPRIAssessmentComprehension']/tr[1]/td[5]/a/font")).click();
	    Thread.sleep(2000);
	    navigateBack(driver);
	}

	public void SummTab(WebDriver driver) throws InterruptedException {
		
		driver.findElement(By.xpath("/html/body/header/top/tabs/a[1]/tab")).click();
		List<WebElement> allElements = driver.findElements(By.name("multiselect_filterpicker")); 
		System.out.println("Filter button selected and Elements present in Filter button: " +allElements.size());  

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
