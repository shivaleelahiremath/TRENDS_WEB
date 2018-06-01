package Script;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ThirdTest {
	
WebDriver login;
	
	@Test
	public void setUp() throws InterruptedException{
		//opening Chrome browser..
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		login = new ChromeDriver();
		
		//Launching the application..
	//	login.get("http://www.tangosoftware.com/trendsReloaded");
		login.get("http://ec2-184-73-167-15.compute-1.amazonaws.com/trendsReloaded");
		System.out.println("Launched Trends web link..");
		
		//Login functionality..
	    login.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_BrownsvilleISD");
	    login.findElement(By.id("password")).sendKeys("Shivu123");
	    login.findElement(By.xpath("html/body/form/button")).click();
	
	    //Selecting STAAR tab on assessment module..
	    login.findElement(By.xpath("html/body/header/bottom/toolbar/left/div/label[2]/span")).click(); 
	    	    
		List<WebElement> AssessmentList= login.findElements(By.id("MaintenanceAssessmentListGrid"));	    	   
    	WebElement xyz=AssessmentList.get(3);
    //	System.out.println("assessment length: "+xyz.getText());
 	 // 	String DocumentTitle= xyz.findElement(By.xpath("tr[1]/td[4]")).getText();
    	String DocumentTitle= "G04 Writing EN M STAAR 2014";
 	  	System.out.println("Document name: "+DocumentTitle);   
        login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input")).click();
        WebElement element = login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input"));
	    element.sendKeys(DocumentTitle);
        element.sendKeys(Keys.ENTER);
        Thread.sleep(4000);	   
	    //Selecting document..	   
  	    System.out.println("***************************Assessment Tab Data***************************");	 
  	    String AsmtTabPerc1[]={"PassPerc","FailPerc"};
	    Double AsmtTabPerValues1[]=new Double[AsmtTabPerc1.length];	      
	    for(int m=0; m <AsmtTabPerc1.length ;m++){
	    	  String AsmtPer = xyz.findElement(By.xpath("tr[1]/td["+(m+10)+"]/left")).getText();
	    	  AsmtTabPerValues1[m]=Double.parseDouble(AsmtPer);
	    }  	   
        System.out.println("Pass and Fail perc on assessment tab--------"+AsmtTabPerValues1[0]+" "+AsmtTabPerValues1[1]);
      
  	    String AsmtTabPerc[]={"AdvancePlusPerc","AdvancedPerc","SatisfactoryPlusPerc","SatisfactoryPerc","SatisfactoryBubblePerc","UnSatisfactoryBubblePerc","UnSatisfactoryPerc"};
	    Double AsmtTabPerValues[]=new Double[AsmtTabPerc.length];	      
	    for(int m=0; m <AsmtTabPerc.length ;m++){
	    	  String AsmtPer = xyz.findElement(By.xpath("tr[1]/td["+(m+14)+"]/left")).getText();
	    	  AsmtTabPerValues[m]=Double.parseDouble(AsmtPer);
	    }  	   
        System.out.println("Adv+, Adv ... perc on assessment tab--------"+AsmtTabPerValues[0]+" "+AsmtTabPerValues[1]+" "+AsmtTabPerValues[2]);
      
        List<WebElement> Assessment= login.findElements(By.className("go"));
	    WebElement AssessmentClick=(WebElement) Assessment.get(0); 
	    AssessmentClick.click();
	    System.out.println("Selected "+DocumentTitle+" document..");
	    Thread.sleep(4000); 	  
        System.out.println("***************************Summary Tab***************************");
	      login.findElement(By.xpath("html/body/header/top/tabs/a[1]/tab")).click();
		  List<WebElement> table= login.findElements(By.className("boxes"));		  
	      WebElement table_element1=table.get(1);
	      
	      List<WebElement> PF_table= login.findElements(By.className("tableswithbars"));		  
 		  WebElement table_element=PF_table.get(1);
		
		  List<WebElement> tr_collection=table_element.findElements(By.cssSelector(".tableswithbars>tbody>tr"));
	      System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
	       String Table_Value = table_element.findElement(By.xpath("tbody/tr[2]/td[1]/bar/text")).getText();
	       System.out.println("table value : "+Table_Value);
	      
	      
	     String SumTabOverallPerc[]={"SumAdvancePerc","SumPassPerc","SumFailPerc"};
		 Float SumTabOverallValues[]=new Float[SumTabOverallPerc.length];	      
		      for(int x=0;x<SumTabOverallPerc.length;x++){
		    	  String percValue = table_element1.findElement(By.xpath("tbody/tr[6]/td["+(x+1)+"]")).getText();
		    	  SumTabOverallValues[x]=Float.parseFloat(percValue.replace("%", ""));
		      }
		 System.out.println("Advance, Pass and Fail percentage on summary tab-----"+SumTabOverallValues[0]+" "+SumTabOverallValues[1]+" "+SumTabOverallValues[2]);
		
		 Double SumPassPerc = (double) (SumTabOverallValues[0] + SumTabOverallValues[1]);
		 System.out.println("Sum pass per: "+Math.round(SumPassPerc));
		 
	     String SumTabPerc[]={"SumAdvancePlusPerc","SumAdvancedPerc","SumSatisfactoryPlusPerc","SumSatisfactoryPerc","SumSatisfactoryBubblePerc","SumUnSatisfactoryBubblePerc","SumUnSatisfactoryPerc"};		   
		   Double SumTabPerValues[]=new Double[SumTabPerc.length];	   
		      for(int n=0;n<SumTabPerc.length;n++){
		    	String s  = table_element1.findElement(By.xpath("tbody/tr[5]/td["+(n+1)+"]")).getText();
		    	SumTabPerValues[n]=Double.parseDouble(s);
		  }
		 System.out.println("Advance+, Advance.. percentage on summary tab-----"+SumTabPerValues[0]+" "+SumTabPerValues[1]+" "+SumTabPerValues[2]);
		 		 
		 for (int temp=0;temp<AsmtTabPerValues.length;temp++){			  
			  double PercDiff=Math.abs(SumTabPerValues[temp] - AsmtTabPerValues[temp]);
			  if(PercDiff > 1.0){
				 System.out.println(AsmtTabPerc[temp]+ "Percentage is not matching for document: "+DocumentTitle+" and difference value is: "+PercDiff); 
			  }else{
					 System.out.println("difference value-------"+PercDiff); 
					 System.out.println(AsmtTabPerc[temp]+ " Percentage is matching for Document: "+DocumentTitle);
			  }
		  }
		 		 
		 double PassPerc = Math.abs(SumPassPerc - AsmtTabPerValues1[0] );
		 if(PassPerc> 1.0){
			 System.out.println("Pass Percentage is not matching for document: "+DocumentTitle+" and difference value is: "+PassPerc); 
		 }else{
			 System.out.println("difference value-------"+PassPerc); 
			 System.out.println("Pass Percentage is matching for Document: "+DocumentTitle);
		 }
		 
		 double FailPerc = Math.abs(SumTabOverallValues[2] - AsmtTabPerValues1[1]);
		 if(FailPerc > 1.0){
			 System.out.println("Fail Percentage is not matching for document: "+DocumentTitle+" and difference value is: "+PassPerc); 			
		 }else{
			 System.out.println("difference value-------"+PassPerc); 
			 System.out.println("Fail Percentage is matching for Document: "+DocumentTitle);
		 }
		 
		 
	      	 		 
/*	   List mcanswers = login.findElements(By.className("number"));    
	   System.out.println("total documents: " +mcanswers.size());		   
	   int DocuSelect = mcanswers.size()-2;	   
	   System.out.println(" document row value:"+DocuSelect);	   
	   int rowCount = mcanswers.size()-1;
	   System.out.println("row no.:" +rowCount);
	   String RecCount = login.findElement(By.className("reccount")).getText();	   
       if(RecCount.equalsIgnoreCase("0 Records")){    	   
    	   System.out.println("document not found..");
       }else{
    	   List<WebElement> Assessment= login.findElements(By.className("go"));
    	   WebElement AssessmentClick=(WebElement) Assessment.get(DocuSelect); 
    	   AssessmentClick.click();
    	   System.out.println("Selected "+DocTitle+" document..");
    	   Thread.sleep(4000); 	
       }
*/
//	   WebElement GreenArrow = login.findElement(By.className("greenarrow"));
//	   System.out.println("Green Arrow Size: " +GreenArrow.getSize());	   
	}

}
