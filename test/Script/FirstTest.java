package Script;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


public class FirstTest {
	WebDriver login;
	
	@Test
	public void setUp() throws InterruptedException{
		//opening Chrome browser..
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		login = new ChromeDriver();
		
		//Launching the application..
		login.get("http://www.tangosoftware.com/trendsReloaded");
	//	login.get("http://ec2-184-73-167-15.compute-1.amazonaws.com/trendsReloaded");
		System.out.println("Launched Trends web link..");
		
		//Login functionality..
	    login.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_BrownsvilleISD");
	    login.findElement(By.id("password")).sendKeys("Shivu123");
	    login.findElement(By.xpath("html/body/form/button")).click();

	
	    //Selecting STAAR tab on assessment module..
	    login.findElement(By.xpath("html/body/header/bottom/toolbar/left/div/label[2]/span")).click();   	    	   
			   
	   String DocTitle = "Algebra I EOC 2014";
//	   login.findElement(By.className("freetext")).click();
//     WebElement element =login.findElement(By.className("freetext"));
//     login.findElement(By.cssSelector(".ui-widget.ui-widget-content.ui-corner-all")).click();
//     WebElement element =login.findElement(By.cssSelector(".ui-widget.ui-widget-content.ui-corner-all"));
       
       login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input")).click();
       WebElement element = login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input"));
	   element.sendKeys(DocTitle);
       element.sendKeys(Keys.ENTER);
	   Thread.sleep(4000);
	   
	    //Selecting document..
	   List mcanswers = login.findElements(By.className("number"));    
	   System.out.println("total documents: " +mcanswers.size());	
	   
//	   List<WebElement> Assessment= login.findElements(By.className("go"));
//	   WebElement AssessmentClick=(WebElement) Assessment.get(0); 
//	   AssessmentClick.click();
//	   System.out.println("Selected "+DocTitle+" document..");
//	   Thread.sleep(2000); 	
//	   
	   WebElement GreenArrow = login.findElement(By.className("greenarrow"));
	   System.out.println("Green Arrow Size: " +GreenArrow.getSize());
	   GreenArrow.click();
	   
	   if(GreenArrow.isDisplayed()){
	   System.out.println("Green arrow found");
		   
	   String DocumentTitle =login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[4]")).getText();
	   System.out.println("Document Title is: " +DocumentTitle);
	   
	   String PassTallyRight =login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[10]/right")).getText();
//	   String ListSatisfactory =login.findElement(By.cssSelector(".PassTally>right")).getText();
	   System.out.println("Pass Tally right: " +PassTallyRight);
	   
	   String FailTallyRight =login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[11]/right")).getText();
	   System.out.println("Fail Tally right: " +FailTallyRight);
	   
	   String TotalTakenStud = login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[12]")).getText();
	   System.out.println("Total Taken students: "+TotalTakenStud);
	   
	   String TotalNonTakenStud = login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[13]")).getText();
	   System.out.println("Total Not Taken Students: " +TotalNonTakenStud); 
	   
	   int TotalTargetStud = Integer.parseInt(TotalTakenStud)+Integer.parseInt(TotalNonTakenStud);
	   System.out.println("Total Target students in Assessment tab: "+TotalTargetStud);
	   
	   String AdvancePlus = login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[14]/right")).getText();
	   System.out.println("Advance Plus students count in Assessment tab: "+AdvancePlus);
	   
	   String Advance = login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[15]/right")).getText();
	   System.out.println("Advance Plus students count in Assessment tab: "+Advance);
	   
	   int TotalAdvanceStudents = Integer.parseInt(AdvancePlus)+Integer.parseInt(Advance);
	   System.out.println("Total Advance students in Assessment tab: "+TotalAdvanceStudents);
	   
	   String SatisfactoryPlus= login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[16]/right")).getText();
	   System.out.println("Advance Plus students count in Assessment tab: "+SatisfactoryPlus);
	   
	   String Satisfactory = login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[17]/right")).getText();
	   System.out.println("Satisfactory students count in Assessment tab: "+Satisfactory);
	   
	   String SatisfacrotyBubble=login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[18]/right")).getText();
	   System.out.println("UnSatisfactory Bubble students count in Assessment tab: "+SatisfacrotyBubble);
	   
	   String UnSatisfacrotyBubble=login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[19]/right")).getText();
	   System.out.println("UnSatisfactory Bubble students count in Assessment tab: "+UnSatisfacrotyBubble);
	   
	   String UnSatisfacroty=login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[20]/right")).getText();
	   System.out.println("UnSatisfactory Bubble students count in Assessment tab: "+UnSatisfacroty);
	   
	   String Incomplete =login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[21]")).getText();
	   System.out.println("UnSatisfactory Bubble students count in Assessment tab: "+Incomplete);

	   //Get left side percentage criteria..
	   String PassTallyLeft1 =login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[10]/left")).getText();
	 //String PassTallyLeft = PassTallyLeft1.concat("%");
	   float PassTallyLeft = Float.parseFloat(PassTallyLeft1);
	   System.out.println("Pass Tally Percentage: " +PassTallyLeft);
	   
	   String FailTallyLeft =login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[11]/left")).getText();
	   System.out.println("Fail Tally percentage: " +FailTallyLeft);	   
	   	
	   List<WebElement> Assessment= login.findElements(By.className("go"));
	   WebElement AssessmentClick=(WebElement) Assessment.get(0); 
	   AssessmentClick.click();
	   System.out.println("Selected "+DocTitle+" document..");
	   Thread.sleep(4000); 	
	    
      login.findElement(By.xpath("html/body/header/top/tabs/a[1]/tab")).click();
	  System.out.println("Summary tab Clicked"); 
	  WebElement table_element = login.findElement(By.className("boxes"));
//	  System.out.println("table: " +table_element.getText());
      List<WebElement> tr_collection=table_element.findElements(By.cssSelector(".boxes>tbody>tr"));
      System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
  //  List<WebElement> td_collection=table_element.findElements(By.cssSelector(".boxes>tbody>tr[1]/td"));    
  //  System.out.println("NUMBER OF Columns IN THIS TABLE = "+td_collection.size());    

      
      String SumTotalAdvanceTally1 =login.findElement(By.xpath("html/body/content/div[1]/div/div[2]/content/table/tbody/tr[2]/td[1]")).getText();
      int SumTotalAdvanceTally = Integer.parseInt(SumTotalAdvanceTally1);
	  System.out.println("Summary tab Advance Pass Tally: "+SumTotalAdvanceTally);
	  
      String SumPassTally =login.findElement(By.xpath("html/body/content/div[1]/div/div[2]/content/table/tbody/tr[2]/td[2]")).getText();
    //String SummarySatisfactory =login.findElement(By.cssSelector(".boxes>tbody>tr[2]>td[2]")).getText();
	  System.out.println("Summary tab Pass Tally: "+SumPassTally);
	  
	  String SumFailTally =login.findElement(By.xpath("html/body/content/div[1]/div/div[2]/content/table/tbody/tr[2]/td[3]")).getText();
	  System.out.println("Summary tab Fail Tally: "+SumFailTally);
	  
	  String SumTotalTargetStud1=login.findElement(By.xpath("html/body/content/div[1]/div/div[1]/content/table/tbody/tr[3]/td[2]")).getText();
	  int SumTotalTargetStud = Integer.parseInt(SumTotalTargetStud1);
      System.out.println("Total Target students in Summary tab: "+SumTotalTargetStud);	
      
      String SumAdvancePlus=login.findElement(By.xpath("html/body/content/div[1]/div/div[2]/content/table/tbody/tr[4]/td[1]")).getText();
      System.out.println("Advance Plus students count in summary tab "+SumAdvancePlus);
      
      String SumAdvance=login.findElement(By.xpath("html/body/content/div[1]/div/div[2]/content/table/tbody/tr[4]/td[2]")).getText();
      System.out.println("Advance students count in summary tab "+SumAdvance);
      
      String SumSatisfactoryPlus = login.findElement(By.xpath("html/body/content/div[1]/div/div[2]/content/table/tbody/tr[4]/td[3]")).getText();
      System.out.println("Satisfactory Plus students count in Summary tab: "+SumSatisfactoryPlus);
     
      String SumSatisfactory = login.findElement(By.xpath("html/body/content/div[1]/div/div[2]/content/table/tbody/tr[4]/td[4]")).getText();
      System.out.println("Satisfactory students count in Summary tab: "+SumSatisfactory);
      
      String SumSatisfactoryBubble = login.findElement(By.xpath("html/body/content/div[1]/div/div[2]/content/table/tbody/tr[4]/td[5]")).getText();
      System.out.println("Satisfactory students count in Summary tab: "+SumSatisfactoryBubble);
      
      String SumUnSatisfactoryBubble = login.findElement(By.xpath("html/body/content/div[1]/div/div[2]/content/table/tbody/tr[4]/td[6]")).getText();
      System.out.println("UnSatisfactory Bubble students count in Summary tab: "+SumUnSatisfactoryBubble);
      
      String SumUnSatisfactory = login.findElement(By.xpath("html/body/content/div[1]/div/div[2]/content/table/tbody/tr[4]/td[7]")).getText();
      System.out.println("UnSatisfactory Bubble students count in Summary tab: "+SumUnSatisfactory);
      
	  String SumPassPerc1=login.findElement(By.xpath("html/body/content/div[1]/div/div[2]/content/table/tbody/tr[6]/td[2]")).getText();	
	  float  round= Float.parseFloat(SumPassPerc1.replace("%", ""));
    //System.out.println("Summary tab Pass Percentage: " +SumPassPerc.replace("%", ""));
	  float SumPassPerc = Round(round,1);
	  System.out.println("Rounded pass percent: " +SumPassPerc);

//	  String SumFailPerc1=login.findElement(By.xpath("html/body/content/div[1]/div/div[2]/content/table/tbody/tr[6]/td[3]")).getText();
////  System.out.println("Summary tab Fail Percentage: " +SumFailPerc.replace("%", ""));
//	  float  roundFail= Float.parseFloat(SumFailPerc1.replace("%", ""));
//    float SumFailPerc = Round(roundFail,1);
//	  System.out.println("Rounded fail data: " +SumFailPerc);
	  
//    34.4 and 34.35%, 65.6 and 65.65%	
	  
      Assert.assertEquals(SumTotalTargetStud, TotalTargetStud, "Total target student count is not matching..");
	  Assert.assertEquals(SumTotalAdvanceTally, TotalAdvanceStudents, "Total Advance tally count is not matching..");
      Assert.assertEquals(SumPassTally, PassTallyRight, "Pass Tally count is not matching..");
      Assert.assertEquals(SumFailTally, FailTallyRight, "Fail Tally count is not matching..");   
      Assert.assertEquals(SumAdvancePlus, AdvancePlus, "Advance Plus Students count is not matching..");
      Assert.assertEquals(SumAdvance, Advance, "Advance Students count is not matching..");
      Assert.assertEquals(SumSatisfactoryPlus, Advance, "Satisfactory Plus Students count is not matching..");
      Assert.assertEquals(SumSatisfactory, Satisfactory, "Satisfactory Students count is not matching..");
      Assert.assertEquals(SumSatisfactoryBubble, SatisfacrotyBubble, "Satisfactory Bubble Students count is not matching..");      
      Assert.assertEquals(SumUnSatisfactoryBubble, UnSatisfacrotyBubble, "UnSatisfactory Bubble Students count is not matching..");
      Assert.assertEquals(SumUnSatisfactory, UnSatisfacroty, "UnSatisfactory Students count is not matching..");

 //     Assert.assertEquals(SumPassPerc, PassTallyLeft, "Pass Tally Percentgae count is not matching..");
   // Assert.assertEquals(SumFailPerc, FailTallyLeft, "Fail Tally Percentage count is  matching..");
       }else{
    	   System.out.println("Green arrow not found");
       }	   
	}	
	
	@AfterClass 
	public void afterTest() {
			//login.quit();
    }
	
	public static float Round(float num, int Rpl) {
		  float p = (float)Math.pow(10,Rpl);
		  num = num * p;
		  float tmp = Math.round(num);
		  return (float)tmp/p;
		  }
}

