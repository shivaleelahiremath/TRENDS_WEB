package StateAccountability_STAAR_Document;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class AsmtTab_Data {
	public static Logger debugLog=Logger.getLogger("SA_STAAR_ASMT");

	String AsmtPassTally, AsmtFailTally;
	Float AsmtPassPerc, AsmtFailPerc;
	String[] otherTab = {"AdvPlus","Adv","SatisPlus","Satis","SatisBubble","UnSatisBubble","UnSatis"};
	String[] otherTabValue = new String[otherTab.length];
	public double[] otherTabPerc = new double[otherTab.length];
	int  AsmttotalTaken, AsmtnotTaken, AsmttotalTargetstud;
	String[][] tabArray = null;
	int rowCount, colCount;
	int SelectDocu;
	String RecCount;
	
	public void filter_AsmtTab(String DocTitle, String AdminCode,String AdminSeries,String AdminMode,String Language, String Target, String Subject, WebDriver driver) throws InterruptedException
	{

	    //For counsellor driver
//		driver.findElement(By.id("menubutton")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.id("ui-id-1")).click();
//		Thread.sleep(2000);	
//		driver.findElement(By.xpath("html/body/header/top/tabs/a[3]/tab")).click();
		
		//Filtering the year wise data..
//		login.findElement(By.xpath("html/body/header/bottom/toolbar/form/div[1]/a/span[1]")).click();
//	    login.findElement(By.xpath("html/body/div[21]/ul/li[5]/div")).click();
//	    Thread.sleep(4000);
//		System.out.println("selected 2014 filtered..");

		//Selecting STAAR tab on assessment module..
		driver.findElement(By.id("menubutton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ui-id-10")).click();
		Thread.sleep(3000);

//		driver.findElement(By.xpath("//*[@id='STAARAssessments']/tr/th[7]/filter/div/input")).click();
		WebElement element = driver.findElement(By.xpath("//*[@id='Title']/div/input"));
		element.sendKeys(DocTitle);  
		element.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		List<WebElement> Demo1 = driver.findElements(By.id("STAARAssessments"));	            	
		WebElement DemoData = Demo1.get(2);	       
//		System.out.println("assessment data: "+DemoData.getText());

		WebElement ACode = DemoData.findElement(By.xpath("tr/th[4]/filter/div"));
		WebElement ACode1= ACode.findElement(By.tagName("input"));
		ACode1.clear();
		ACode1.sendKeys(AdminCode);
		ACode1.sendKeys(Keys.ENTER);
		Thread.sleep(3000);	
		try{
			WebElement popup = driver.findElement(By.className("ui-tooltip-content"));
			if(popup.isDisplayed())
				System.out.println("Admin code "+AdminCode+" didn't match");
			    missingDocument(driver);
		}catch (Exception e) {
			//System.out.println("Admin code "+AdminCode+" match");
		}

		WebElement ASer = DemoData.findElement(By.xpath("tr/th[5]/filter/div"));
		WebElement ASer1= ASer.findElement(By.tagName("input"));
		ASer1.clear();
		ASer1.sendKeys(AdminSeries);
		ASer1.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		try{
			WebElement popup1 = driver.findElement(By.className("ui-tooltip-content"));
			if(popup1.isDisplayed())
				System.out.println("Admin Series "+AdminSeries+" didn't match");
			    missingDocument(driver);
		}catch (Exception e) {
			//System.out.println("Admin code "+AdminCode+" match");
		}

		WebElement AMode = DemoData.findElement(By.xpath("tr/th[6]/filter/div"));
		WebElement AMode1= AMode.findElement(By.tagName("input"));
		AMode1.clear();
		AMode1.sendKeys(AdminMode);
		AMode1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		try{
			WebElement popup1 = driver.findElement(By.className("ui-tooltip-content"));
			if(popup1.isDisplayed())
				System.out.println("Admin Series "+AdminMode+" didn't match");
			    missingDocument(driver);
		}catch (Exception e) {
			//System.out.println("Admin code "+AdminCode+" match");
		}

//		WebElement Lang = DemoData.findElement(By.xpath("tr/th[8]/filter/div"));
//		WebElement Lang1= Lang.findElement(By.tagName("input"));
//		Lang1.clear();
//		Lang1.sendKeys(Language);
//		Lang1.sendKeys(Keys.ENTER);
//		Thread.sleep(2000);

		WebElement Tar = DemoData.findElement(By.xpath("tr/th[9]/filter/div"));
		WebElement Target1= Tar.findElement(By.tagName("input"));
		Target1.clear();
		Target1.sendKeys(Target);
		Target1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		WebElement Sub = DemoData.findElement(By.xpath("tr/th[11]/filter/div"));
		WebElement Sub1= Sub.findElement(By.tagName("input"));
		Sub1.clear();
		Sub1.sendKeys(Subject);
		Sub1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id='Title']/left")).click();
		Thread.sleep(3000);
//		driver.findElement(By.xpath("//*[@id='STAARAssessments']/tr/th[7]/main/left")).click();
//		Thread.sleep(3000);

		//Selecting document..
		List<WebElement> sel = driver.findElements(By.id("STAARAssessments"));	            	
		WebElement sel1 = sel.get(5);	   
		List mcanswers = sel1.findElements(By.className("number"));    
		//System.out.println("total documents: " +mcanswers.size());
		rowCount = mcanswers.size()-1;
		SelectDocu = mcanswers.size()-1;

		RecCount = driver.findElement(By.className("reccount")).getText();			   
//		if(RecCount.equalsIgnoreCase("0 Records")){
//			missingDocument(driver);
//		}else{	
//			System.out.println("Document found..");
//		}
	}

	public void missingDocument(WebDriver driver) throws InterruptedException{
		System.out.println("Document not found..");
		debugLog.error("Document not found..");
//		driver.findElement(By.id("menubutton")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.id("ui-id-1")).click();
//		Thread.sleep(3000);
	}

	public void SkipTest(String DocTitle, WebDriver driver) throws InterruptedException{
		System.out.println("Skipped testing..");
		driver.findElement(By.id("menubutton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ui-id-1")).click();
		Thread.sleep(3000);
	}

	public void populate_AsmtTabData(WebDriver driver){
		//
		List<WebElement> data = driver.findElements(By.id("STAARAssessments"));
		WebElement data1 = data.get(3);
		//	System.out.println(data1.getText());

		//Reading Pass and fail value data
		AsmtPassTally = data1.findElement(By.xpath("tbody/tr[1]/td[14]/right")).getText();
		AsmtFailTally = data1.findElement(By.xpath("tbody/tr[1]/td[15]/right")).getText();

		String AsmtPassper = data1.findElement(By.xpath("tbody/tr[1]/td[14]/left")).getText();
		String AsmtFailper = data1.findElement(By.xpath("tbody/tr[1]/td[15]/left")).getText();
		AsmtPassPerc = Float.parseFloat(AsmtPassper);
		AsmtFailPerc = Float.parseFloat(AsmtFailper);

//		System.out.println(AsmtPassTally+" "+AsmtFailTally+" "+AsmtPassPerc+" "+AsmtFailPerc);

		String AsmttotalTaken1 = data1.findElement(By.xpath("tbody/tr[1]/td[16]")).getText();
		AsmttotalTaken = Integer.parseInt(AsmttotalTaken1);

		String AsmtnotTaken1 = data1.findElement(By.xpath("tbody/tr[1]/td[17]")).getText();
		AsmtnotTaken = Integer.parseInt(AsmtnotTaken1);

		AsmttotalTargetstud = AsmttotalTaken+ AsmtnotTaken;
//		System.out.println("totalTaken : "+AsmttotalTaken+" notTaken : "+AsmtnotTaken+" totalTargetstud :  "+AsmttotalTargetstud);

		//for(int i=0; i<PassFail.length;i++){
		//	PassFailvalue[i]= data1.findElement(By.xpath("tbody/tr[1]/td["+(i+13)+"]/right")).getText();
		//	PassFailPerc[i]= data1.findElement(By.xpath("tbody/tr[1]/td["+(i+13)+"]/left")).getText();
		//	System.out.println(PassFail[i]+ "Value : "+PassFailvalue[i]+" Perc : "+PassFailPerc[i]);
		//	}

		//Reading Adv, Adv+ etc.. data..
		for(int i=0; i<otherTab.length;i++){
			String testNAValue = null;
			try{
				testNAValue = data1.findElement(By.xpath("tbody/tr["+rowCount+"]/td["+(i+18)+"]/center")).getText();
			}
			catch(Exception e){
				/*System.out.println("Assessement Tab Adv, AdvP Percentage..");*/
			}
			if(testNAValue == null) {
				otherTabValue[i] = data1.findElement(By.xpath("tbody/tr[1]/td["+(i+18)+"]/right")).getText();
				String per = data1.findElement(By.xpath("tbody/tr[1]/td["+(i+18)+"]/left")).getText();
				otherTabPerc[i]= Double.parseDouble(per);
//				System.out.println(otherTab[i]+" value : " +otherTabValue[i]+ " Perc : "+otherTabPerc[i]);	
			}
			else if(testNAValue.equalsIgnoreCase("N/A")){
				otherTabPerc[i]= -1.0;
				otherTabValue[i]= "-1";
			}
		}
	
	}

}
