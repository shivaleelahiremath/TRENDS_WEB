package Script;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Kentro_AsmtTab_Data {
	
//	public String[] PassFail = {"Pass", "Fail"}; 
//	public String[] PassFailvalue = new String[PassFail.length];
//	public String[] PassFailPerc = new String[PassFail.length];
    String AsmtPassTally, AsmtFailTally;
	Float AsmtPassPerc, AsmtFailPerc;
    String[] otherTab = {"AdvPlus","Adv","SatisPlus","Satis","SatisBubble","UnSatisBubble","UnSatis"};
    String[] otherTabValue = new String[otherTab.length];
	public double[] otherTabPerc = new double[otherTab.length];
	int  AsmttotalTaken, AsmtnotTaken, AsmttotalTargetstud;
	
	public void filter_AsmtTab(String DocTitle, String AssessmentCode, WebDriver driver) throws InterruptedException{

	/*	//select the less than 90 days data on assessment tab..
		WebElement selDays = driver.findElement(By.xpath("//*[@id='ADF_Default_Dates']/div[1]/div/input"));
		selDays.clear();
		selDays.sendKeys("<= 90 Days");
		selDays.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		*/
		
		//selecting date on assessment tab...		
		driver.findElement(By.id("ADF_Calendar_Default_Button")).click();
		Thread.sleep(2000);
		for (int i=0 ;i<7;i++){
		driver.findElement(By.xpath("html/body/div[3]/div[2]/div[1]/div/div/a[1]/span")).click();
		Thread.sleep(500);
		}
		//selecting prvious month date (i.e jan 01 2015)
		driver.findElement(By.xpath("html/body/div[3]/div[2]/div[1]/div/table/tbody/tr[1]/td[5]/a")).click();
		//selecting current date on the data picker
//		driver.findElement(By.xpath("html/body/div[3]/div[2]/div[2]/div/table/tbody/tr[5]/td[3]/a")).click();
		driver.findElement(By.id("ADF_Dialog_Go_Button")).click();
		Thread.sleep(3000);
		
		//selecting document and code..
		WebElement title= driver.findElement(By.xpath("//*[@id='Title']/div/input"));
		title.sendKeys(DocTitle);
		title.sendKeys(Keys.ENTER);
		Thread.sleep(3000);

		WebElement asmtCode= driver.findElement(By.xpath("//*[@id='AssessmentCode']/div/input"));
		asmtCode.sendKeys(AssessmentCode);
		asmtCode.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	}
	
	public void populate_AsmtTabData(WebDriver driver) throws InterruptedException{
		//
		List<WebElement> data = driver.findElements(By.id("MaintenanceAssessmentListGrid"));
		WebElement data1 = data.get(3);
		//	System.out.println(data1.getText());
		
		//Reading Pass and fail value data
		AsmtPassTally = data1.findElement(By.xpath("tbody/tr[1]/td[13]/right")).getText();
		AsmtFailTally = data1.findElement(By.xpath("tbody/tr[1]/td[14]/right")).getText();
		
		String AsmtPassper = data1.findElement(By.xpath("tbody/tr[1]/td[13]/left")).getText();
		String AsmtFailper = data1.findElement(By.xpath("tbody/tr[1]/td[14]/left")).getText();
		AsmtPassPerc = Float.parseFloat(AsmtPassper);
		AsmtFailPerc = Float.parseFloat(AsmtFailper);

//		for(int i=0; i<PassFail.length;i++){
//			PassFailvalue[i]= data1.findElement(By.xpath("tbody/tr[1]/td["+(i+13)+"]/right")).getText();
//			PassFailPerc[i]= data1.findElement(By.xpath("tbody/tr[1]/td["+(i+13)+"]/left")).getText();
//				System.out.println(PassFail[i]+ "Value : "+PassFailvalue[i]+" Perc : "+PassFailPerc[i]);
//		}

		//Reading Adv, Adv+ etc.. data..
		for(int i=0; i<otherTab.length;i++){
			otherTabValue[i] = data1.findElement(By.xpath("tbody/tr[1]/td["+(i+17)+"]/right")).getText();
			String per = data1.findElement(By.xpath("tbody/tr[1]/td["+(i+17)+"]/left")).getText();
			otherTabPerc[i]= Double.parseDouble(per);
//			System.out.println(otherTab[i]+" value : " +otherTabValue[i]+ " Perc : "+otherTabPerc[i]);			
		}	
		
	    String AsmttotalTaken1 = data1.findElement(By.xpath("tbody/tr[1]/td[15]")).getText();
	    AsmttotalTaken = Integer.parseInt(AsmttotalTaken1);
	    
	    String AsmtnotTaken1 = data1.findElement(By.xpath("tbody/tr[1]/td[16]")).getText();
	    AsmtnotTaken = Integer.parseInt(AsmtnotTaken1);
	    
		AsmttotalTargetstud = AsmttotalTaken+ AsmtnotTaken;
//	    System.out.println("totalTaken : "+AsmttotalTaken+" notTaken : "+AsmtnotTaken+" totalTargetstud :  "+AsmttotalTargetstud);
		 
	}
	
}
