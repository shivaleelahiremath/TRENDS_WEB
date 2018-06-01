package LION_Automation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class suhasini_details {

	
public void populate_Details_data(WebDriver login) throws InterruptedException {
	
	// TODO Auto-generated method stub
	
		
login.findElement(By.xpath("/html/body/header/top/tabs/a[7]/tab")).click();
	
	Thread.sleep(4000);
	for(int y=21;y<26;y++)
	{
		
		
	
	WebElement studentID = login.findElement(By.xpath(".//*[@id='StudentId']/div/input"));
		
studentID.sendKeys("1602342");
		
studentID.sendKeys(Keys.ENTER);
		
Thread.sleep(2000);
		
	//	1602342
	
JavascriptExecutor js = (JavascriptExecutor) login;
		
WebElement element = login.findElement(By.id("FF2_Score_Total"));
//WebElement element = login.findElement(By.cssSelector("#MaintenanceLionAssessmentFFDetails > tr:nth-child(1) > td.FF2_Score_Total.hasColor"));

		js.executeScript("arguments[0].scrollIntoView(true);",element);
	
	Thread.sleep(2000);

	
	WebElement data2= login.findElement(By.xpath("//*[@id='MaintenanceLionAssessmentFFDetails']/tr/td["+y+"]/a"));
	System.out.println(" -> "+data2.getText());
	data2.click();
	Thread.sleep(2000);
		
		
List<WebElement> data = login.findElements(By.id("MaintenanceLionAssessmentVocabularyBreakDown"));
	
	WebElement listdata= data.get(3);
//		
//System.out.println(listdata.getText());
	
	List<WebElement> tr_collection=listdata.findElements(By.cssSelector("#MaintenanceLionAssessmentVocabularyBreakDown>tr"));

//		List<WebElement> tr_collection1=listdata.findElements(By.xpath(".//*[@id='MaintenanceLionAssessmentVocabularyBreakDown']/tr"));

//		System.out.println("--> "+tr_collection.size()+"  "+tr_collection1.size());

		
int row = tr_collection.size();
		
int col = 8;
		
String[][] answerText = new String[row][col];
		
String[][] answerScore = new String[row][col];
       
 Map<String,String> answerData = new HashMap<String, String>();

		
for(int i=0; i<row;i++){
	
		for(int j=0;j<col;j=j+2){
				
answerText[i][j] = listdata.findElement(By.xpath(".//*[@id='MaintenanceLionAssessmentVocabularyBreakDown']/tr["+(i+1)+"]/td["+(j+13)+"]")).getText();
		
		answerScore[i][j] = listdata.findElement(By.xpath(".//*[@id='MaintenanceLionAssessmentVocabularyBreakDown']/tr["+(i+1)+"]/td["+(j+14)+"]/right")).getText();

//				System.out.println("--> " +answerText[i][j]+ " --> "+answerScore[i][j]);
			
	answerData.put(answerText[i][j], answerScore[i][j]);
		
	}	

		
}
		
		
for (String key : answerData.keySet()) {
	  String[] Data1=new String[7];
		if (answerData.get(key).equalsIgnoreCase("1")) {
		    System.out.println("Correct Answers: "+key);
			for(int i=0;i<7;i++)
			{
		  
			Data1[i]=key;
//			System.out.println("Data1:"+Data[i]);
			}
//			int x=0;
//			if(Data[x]==Data1[x])
//			{
//				System.out.println("data matches");
//				x++;
//			}
//			else
//				
//			{System.out.println("Data not matches");
//				
//			}
			}
		}
login.findElement(By.xpath("/html/body/header/top/tabs/a[7]/tab")).click();
Thread.sleep(4000);

		}
		

	
	

}
}