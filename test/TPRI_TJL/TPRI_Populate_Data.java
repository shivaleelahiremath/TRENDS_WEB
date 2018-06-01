package TPRI_TJL;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

       
public class TPRI_Populate_Data{
	
	    String[] StudentData = {"Tier", "GroupLetter", "GroupFactor"};
        String AsmtTotalTargetStud, StudTotalTargetStud;
        String[] TierPercen, GroupPerc, StudentMainData, ScrMainData, PAMainData, GKMainData, WRMainData, ReadMainData, CompMainData ;
        int[] TierValues, GroupValues, TierStudRec, GroupStudRec;
        
		public void studentFilter(WebDriver driver) throws InterruptedException{
			WebElement stdID = driver.findElement(By.xpath("//*[@id='StudentId']/div/input"));
			stdID.sendKeys("42168");	
			stdID.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
		}

		//Reading Tiers and Grouping letters on Assessment tab..
		public void AssmentData(WebDriver driver ,String Title ,String Grade) throws InterruptedException{

			driver.findElement(By.xpath("//*[@id='radio']/label[5]/span")).click();
		    Thread.sleep(2000);	
		    
			List<WebElement> AssessmentList= driver.findElements(By.id("MaintenanceAssessmentListGrid"));	    	   
			WebElement AsmtData=AssessmentList.get(3);
			//System.out.println("assessment data: "+AsmtData.getText());

			WebElement Title1 = driver.findElement(By.xpath("//*[@id='Title']/div/input"));
			Title1.sendKeys(Title);	
			Title1.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			WebElement Grade1 = driver.findElement(By.xpath("//*[@id='GradeLevel']/div/input"));
			Grade1.clear();
			Grade1.sendKeys(Grade);	
			Grade1.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			AsmtTotalTargetStud =  AsmtData.findElement(By.xpath("tbody/tr[1]/td[7]/right")).getText();
//			System.out.println("assessment complete " +AsmtTotalTargetStud);

			String TierColumnName[] = {"Tier1", "Tier2", "Tier3"};
			TierValues = new int[TierColumnName.length];
			TierPercen = new String[TierColumnName.length];
			for (int i=0; i < TierColumnName.length; i++){
				TierPercen[i] = AsmtData.findElement(By.xpath("tbody/tr[1]/td["+(i+9)+"]/left")).getText();
				TierValues[i] = Integer.parseInt(AsmtData.findElement(By.xpath("tbody/tr[1]/td["+(i+9)+"]/right")).getText());
//				System.out.println("Tier percentage "+TierPercen[i]+" Tier Values "+TierValues[i]);
			}

			String GroupColumnName[] = {"GroupA", "GroupB", "GroupC", "GroupD"};
			GroupValues = new int[GroupColumnName.length];
			GroupPerc = new String[GroupColumnName.length];
			for (int i =0 ; i<GroupColumnName.length;i++){
				GroupPerc[i] = AsmtData.findElement(By.xpath("tbody/tr[1]/td["+(i+12)+"]/left")).getText();
				GroupValues[i] = Integer.parseInt(AsmtData.findElement(By.xpath("tbody/tr[1]/td["+(i+12)+"]/right")).getText());
//				System.out.println("Group perc "+GroupPerc[i]+" Group value "+ GroupValues[i]);
			}
			Thread.sleep(2000);

			driver.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img")).click();
			System.out.println("selected the assessment");
			Thread.sleep(5000);

		}
		
		public void StudentMainData(WebDriver driver) throws InterruptedException {
		
			//Get total target student count on student tab.
			String StudTakenCount1 = driver.findElement(By.className("reccount")).getText();
			StudTotalTargetStud = StudTakenCount1.replace(" Records", "");
//			System.out.println("--> "+StudTotalTargetStud);
			Thread.sleep(2000);

			WebDriverWait wait = new WebDriverWait(driver,6); 

			String TierVal[] = {"1","2","3"};
			TierStudRec = new int[TierVal.length];
			for(int i= 0; i<TierVal.length;i++){
				WebElement Group = driver.findElement(By.xpath("//*[@id='Tier']/div/input"));
				Group.clear();
				Thread.sleep(2000);
				Group.sendKeys(TierVal[i]);
				Group.sendKeys(Keys.ENTER);
				Thread.sleep(4000); 
				String TierRec = wait.until(ExpectedConditions.elementToBeClickable(By.className("reccount"))).getText();
				TierStudRec[i] = Integer.parseInt(TierRec.replace(" Records", ""));
//			    System.out.println("total Tier "+TierVal[i]+" count... " +TierStudRec[i]);			
			}
			WebElement Groupcl = driver.findElement(By.xpath("//*[@id='Tier']/div/input"));
			Groupcl.clear();
			Groupcl.sendKeys("All");
			Groupcl.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			
			String[] GroupVal = {"TierGroupA", "TierGroupB", "TierGroupC", "TierGroupD"};
			GroupStudRec = new int[GroupVal.length];
			for(int i=0; i<GroupVal.length; i++){
//		    		WebElement select = filterClick.findElement(By.tagName("selectedcolor"));
//		    		select.click();		        	
		    		driver.findElement(By.xpath("//*[@id='TierGroup']/colorfilter/selectedcolor")).click();
		    		Thread.sleep(2000);		
		            WebElement fo= driver.findElement(By.cssSelector("color[value='"+GroupVal[i]+"']"));
		            fo.click();
		            Thread.sleep(4000);
					String GroupRec = driver.findElement(By.className("reccount")).getText();
//	                String GroupRec = wait.until(ExpectedConditions.elementToBeClickable(By.className("reccount"))).getText();
		            GroupStudRec[i] = Integer.parseInt(GroupRec.replace(" Records", ""));;
				    System.out.println("total Group "+GroupVal[i]+" count... " +GroupStudRec[i]);
		        }
			    driver.findElement(By.xpath("//*[@id='TierGroup']/colorfilter/selectedcolor")).click();
				Thread.sleep(1000);		
				driver.findElement(By.cssSelector("#TierGroup>color[value='NA']")).click();
				Thread.sleep(2000);
			
	/*	    String GroupVal[]= {"A","B","C","D"};
			GroupStudRec = new int[GroupVal.length];
			for(int x=0; x<GroupVal.length;x++){
				WebElement GroupData = driver.findElement(By.xpath("//*[@id='TierGroup']/div/input"));
			//  WebElement GroupData = login.findElement(By.xpath("//*[@id='MaintenanceTPRIAssessmentStudentMastery']/tr/th[9]/filter/div/input"));
				GroupData.clear();
				Thread.sleep(4000);
				GroupData.sendKeys(GroupVal[x]);
				if(GroupVal[x]=="A"){
					Thread.sleep(2000);
//					login.findElement(By.xpath("html/body/ul[4]/li[1]/a")).click();
					driver.findElement(By.xpath("//*[@id='ui-id-129']/a")).click();
				}
				GroupData.sendKeys(Keys.ENTER);
				Thread.sleep(5000);
				String GroupRec = driver.findElement(By.className("reccount")).getText();
				GroupStudRec[x] = Integer.parseInt(GroupRec.replace(" Records", ""));
			    System.out.println("total Group "+GroupVal[x]+" count... " +GroupStudRec[x]);
			}	 
			WebElement Groupcl = driver.findElement(By.xpath("//*[@id='TierGroup']/div/input"));
			Groupcl.clear();
			Groupcl.sendKeys("All");
			Groupcl.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
	*/		
			studentFilter(driver);
			
			List<WebElement> AssessmentList= driver.findElements(By.id("MaintenanceTPRIAssessmentStudentMastery"));	    	   
			WebElement AsmtData=AssessmentList.get(3);
		 // System.out.println("assessment data: "+AsmtData.getText());
					
			StudentMainData=new String[StudentData.length];
			for (int i =0; i< StudentData.length ;i++){
				StudentMainData[i] = AsmtData.findElement(By.xpath("tbody/tr[1]/td["+(i+9)+"]")).getText();
			}
			System.out.println("Student tab data   "+StudentMainData[0]+" "+StudentMainData[1]+" "+StudentMainData[2]);
		}

		public void ScrTabData(WebDriver driver) throws InterruptedException{	
			driver.findElement(By.xpath("/html/body/header/top/tabs/a[2]/tab")).click();
			Thread.sleep(2000);
			studentFilter(driver);
			List<WebElement> AssessmentList= driver.findElements(By.id("MaintenanceTPRIAssessmentScreening"));	    	   
			WebElement AsmtData=AssessmentList.get(3);
		 // System.out.println("assessment data: "+AsmtData.getText());		
			Thread.sleep(2000);
		 // System.out.println("assessment data: "+AsmtData.getText());
			ScrMainData=new String[StudentData.length];
			for (int i =0; i< StudentData.length ;i++){
				ScrMainData[i] = AsmtData.findElement(By.xpath("tbody/tr[1]/td["+(i+7)+"]")).getText();
			}
//			System.out.println("SCR tab data  "+ScrMainData[0]+" "+ScrMainData[1]+" "+ScrMainData[2]);
		}
		
		public void PATabData(WebDriver driver) throws InterruptedException{	
			driver.findElement(By.xpath("/html/body/header/top/tabs/a[3]/tab")).click();
			Thread.sleep(2000);
			studentFilter(driver);
			List<WebElement> AssessmentList= driver.findElements(By.id("MaintenanceTPRIAssessmentPhonemicAwareness"));	    	   
			WebElement AsmtData=AssessmentList.get(3);
		 // System.out.println("assessment data: "+AsmtData.getText());		
			Thread.sleep(2000);
		 // System.out.println("assessment data: "+AsmtData.getText());
			PAMainData=new String[StudentData.length];
			for (int i =0; i< StudentData.length ;i++){
				PAMainData[i] = AsmtData.findElement(By.xpath("tbody/tr[1]/td["+(i+7)+"]")).getText();
			}
//			System.out.println("PA tab data  "+PAMainData[0]+" "+PAMainData[1]+" "+PAMainData[2]);
		}
		
		public void GKTabData(WebDriver driver) throws InterruptedException{
			driver.findElement(By.xpath("/html/body/header/top/tabs/a[4]/tab")).click();
			Thread.sleep(2000);
			studentFilter(driver);
			List<WebElement> AssessmentList= driver.findElements(By.id("MaintenanceTPRIAssessmentGraphophonemicKnowledge"));	    	   
			WebElement AsmtData=AssessmentList.get(3);
		 // System.out.println("assessment data: "+AsmtData.getText());
			GKMainData=new String[StudentData.length];
			for (int i =0; i< StudentData.length ;i++){
				GKMainData[i] = AsmtData.findElement(By.xpath("tbody/tr[1]/td["+(i+7)+"]")).getText();
			}
//			System.out.println("GK tab data  "+GKMainData[0]+" "+GKMainData[1]+" "+GKMainData[2]);
		}
		
		public void WRTabData(WebDriver driver) throws InterruptedException{
			driver.findElement(By.xpath("/html/body/header/top/tabs/a[5]/tab")).click();
			Thread.sleep(2000);
			studentFilter(driver);
			List<WebElement> AssessmentList= driver.findElements(By.id("MaintenanceTPRIAssessmentWordReading"));	    	   
			WebElement AsmtData=AssessmentList.get(3);
		 // System.out.println("assessment data: "+AsmtData.getText());
		    WRMainData=new String[StudentData.length];
			for (int i =0; i< StudentData.length ;i++){
				WRMainData[i] = AsmtData.findElement(By.xpath("tbody/tr[1]/td["+(i+7)+"]")).getText();
			}
			System.out.println("WR tab data  "+WRMainData[0]+" "+WRMainData[1]+" "+WRMainData[2]);

		}
		
		public void ReadTabData(WebDriver driver) throws InterruptedException{
			driver.findElement(By.xpath("/html/body/header/top/tabs/a[6]/tab")).click();
			Thread.sleep(2000);
			studentFilter(driver);
			List<WebElement> AssessmentList= driver.findElements(By.id("MaintenanceTPRIAssessmentOralReading"));	    	   
			WebElement AsmtData=AssessmentList.get(3);
		 // System.out.println("assessment data: "+AsmtData.getText());
			ReadMainData=new String[StudentData.length];
			for (int i =0; i< StudentData.length ;i++){
				ReadMainData[i] = AsmtData.findElement(By.xpath("tbody/tr[1]/td["+(i+7)+"]")).getText();
			}
			System.out.println("Read tab data  "+ReadMainData[0]+" "+ReadMainData[1]+" "+ReadMainData[2]);	
		}
				
		public void CompTabData(WebDriver driver) throws InterruptedException{
			driver.findElement(By.xpath("/html/body/header/top/tabs/a[7]/tab")).click();
			Thread.sleep(2000);
			studentFilter(driver);
			List<WebElement> AssessmentList= driver.findElements(By.id("MaintenanceTPRIAssessmentComprehension"));	    	   
			WebElement AsmtData=AssessmentList.get(3);
		 // System.out.println("assessment data: "+AsmtData.getText());
			CompMainData=new String[StudentData.length];
			for (int i =0; i< StudentData.length ;i++){
				CompMainData[i] = AsmtData.findElement(By.xpath("tbody/tr[1]/td["+(i+7)+"]")).getText();
			}
			System.out.println("Comp tab data  "+CompMainData[0]+" "+CompMainData[1]+" "+CompMainData[2]);	

		}

}
