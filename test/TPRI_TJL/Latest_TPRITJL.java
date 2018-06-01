package TPRI_TJL;

import java.io.File;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//Not using this class any more..
public class Latest_TPRITJL {

	private static Logger debugLog=Logger.getLogger("STAAR_Asmt");
	private static Logger reportsLog=Logger.getLogger("reportsLog");
	WebDriver login;
	String[][] tabArray = null;
	int rowCount, colCount;
	String sheetPath = "test/Resources/Data/Test_Document.xls";

	@BeforeClass
	public void setUp() throws InterruptedException {
		//opening Chrome browser..
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		login = new ChromeDriver();				
		//Launching the application..
		login.get("http://trends.tangosoftware.com");
		//	login.get("http://devtools2.libertysol.com/TangoCentralTest");
		System.out.println("Launched Trends web link..");
		//	reportsLog.info("Trends Application launched successfully..");
	}	

	@Test
	public void Login() throws InterruptedException{
		//Login functionality..
		reportsLog.info("Testing STAAR documents for district BrownsvilleISD production..");
		login.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_BrownsvilleISD");
		login.findElement(By.id("password")).sendKeys("Shivu123");
		login.findElement(By.id("loginbutton")).click();
		reportsLog.info("Login successfully..");
		Thread.sleep(2000);
	}

	@Test
	public void getExcelData() throws Exception {
		Workbook workbk = Workbook.getWorkbook(new File(sheetPath));
		Sheet sht = workbk.getSheet("Sheet1");
		rowCount = sht.getRows(); 
		colCount = sht.getColumns();
		tabArray = new String[rowCount][colCount];
		System.out.println("erow: " + rowCount);
		System.out.println("ecol: " + colCount);
		for(int i=26; i<sht.getRows(); i++){
			String Title = sht.getCell(0, i).getContents();
			String Grade = sht.getCell(1, i).getContents();
			debugLog.error(i+"------ "+Title+" "+Grade+" --------");
			AssmentData(Title,Grade);
			debugLog.error("Completed.. "+Title+" "+Grade+" ------");
		}
	}

	//Selecting TPRI/TJL on Assessments tab.. 
	public void goTelpas() throws InterruptedException {
		List<WebElement> allHeaders= login.findElements(By.className("ui-button-text"));
		WebElement Anvalue=(WebElement) allHeaders.get(4);
		Anvalue.click();
		Thread.sleep(4000);
	}

	//Reading Tiers and Grouping letters on Assessment tab..
	public void AssmentData(String Title, String Grade) throws InterruptedException{
		goTelpas();
		List<WebElement> AssessmentList= login.findElements(By.id("MaintenanceAssessmentListGrid"));	    	   
		WebElement AsmtData=AssessmentList.get(3);
		//System.out.println("assessment data: "+AsmtData.getText());

		WebElement Title1 = login.findElement(By.xpath("//*[@id='Title']/div/input"));
		Title1.sendKeys(Title);	
		Title1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		WebElement Grade1 = login.findElement(By.xpath("//*[@id='GradeLevel']/div/input"));
		Grade1.clear();
		Grade1.sendKeys(Grade);	
		Grade1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		String AsmtTotalTargetStud =  AsmtData.findElement(By.xpath("tbody/tr[1]/td[7]/right")).getText();
	//	System.out.println("assessment complete " +AsmtTotalTargetStud );

		String TierColumnName[] = {"Tier1", "Tier2", "Tier3"};
		int[] TierValues = new int[TierColumnName.length];
		String[] TierPercen = new String[TierColumnName.length];
		for (int i=0; i < TierColumnName.length; i++){
			TierPercen[i] = AsmtData.findElement(By.xpath("tbody/tr[1]/td["+(i+9)+"]/left")).getText();
			TierValues[i] = Integer.parseInt(AsmtData.findElement(By.xpath("tbody/tr[1]/td["+(i+9)+"]/right")).getText());
		//	System.out.println("Tier percentage "+TierPercen[i]+" Tier Values "+TierValues[i]);
		}

		String GroupColumnName[] = {"GroupA", "GroupB", "GroupC", "GroupD"};
		int[] GroupValues = new int[GroupColumnName.length];
		String[] GroupPerc = new String[GroupColumnName.length];
		for (int i =0 ; i<GroupColumnName.length;i++){
			GroupPerc[i] = AsmtData.findElement(By.xpath("tbody/tr[1]/td["+(i+12)+"]/left")).getText();
			GroupValues[i] = Integer.parseInt(AsmtData.findElement(By.xpath("tbody/tr[1]/td["+(i+12)+"]/right")).getText());
		//	System.out.println("Group perc "+GroupPerc[i]+" Group value "+ GroupValues[i]);
		}
		Thread.sleep(2000);

		//WebElement asmtSel = login.findElement(By.xpath("//*[@id='MaintenanceAssessmentListGrid']/tbody/tr[1]/td[2]/img"));
		//asmtSel.click();
		login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[3]/table[1]/tbody/tr[1]/td[2]/img")).click();
		System.out.println("selected the assessment");
		Thread.sleep(5000);

		StudentTabData(TierValues, GroupValues, AsmtTotalTargetStud);
	}

	public void StudentTabData(int[] assessmentTabTier, int[] assessmentTabGroup, String assessmentTargetStd) throws InterruptedException{

		//Get total target student count on student tab.
		String StudTakenCount1 = login.findElement(By.className("reccount")).getText();
		int StudTotalTargetStud = Integer.parseInt(StudTakenCount1.replace(" Records", ""));
//		System.out.println("--> "+StudTotalTargetStud);
		Thread.sleep(2000);

		String TierVal[] = {"1","2","3"};
		int[] TierStudRec = new int[TierVal.length];
		for(int i= 0; i<TierVal.length;i++){
			WebElement Group = login.findElement(By.xpath("//*[@id='Tier']/div/input"));
			Group.clear();
			Thread.sleep(2000);
			Group.sendKeys(TierVal[i]);
			Group.sendKeys(Keys.ENTER);
			Thread.sleep(5000); 
			String TierRec = login.findElement(By.className("reccount")).getText();
			TierStudRec[i] = Integer.parseInt(TierRec.replace(" Records", ""));
		//	System.out.println("Total Tier "+TierVal[i]+" count... " +TierStudRec[i]);
		}
		WebElement Tiercl = login.findElement(By.xpath("//*[@id='Tier']/div/input"));
		Tiercl.clear();
		Tiercl.sendKeys("All");
		Tiercl.sendKeys(Keys.ENTER);
		Thread.sleep(6000);

		
		
		String GroupVal[]= {"B","C","D"};
		int[] GroupStudRec = new int[GroupVal.length];
		for(int x=0; x<GroupVal.length;x++){
			WebElement GroupData = login.findElement(By.xpath("//*[@id='TierGroup']/div/input"));
			//  WebElement GroupData = login.findElement(By.xpath("//*[@id='MaintenanceTPRIAssessmentStudentMastery']/tr/th[9]/filter/div/input"));
			GroupData.clear();
			Thread.sleep(2000);
			GroupData.sendKeys(GroupVal[x]);
			if(GroupVal[x]=="A"){
				Thread.sleep(1000);
				login.findElement(By.xpath("html/body/ul[4]/li[2]/a")).click();
			}
			GroupData.sendKeys(Keys.ENTER);
			Thread.sleep(4000);
			String GroupRec = login.findElement(By.className("reccount")).getText();
			GroupStudRec[x] = Integer.parseInt(GroupRec.replace(" Records", ""));
		//	System.out.println("total Group "+GroupVal[x]+" count... " +GroupStudRec[x]);
		}	 
		WebElement Groupcl = login.findElement(By.xpath("//*[@id='TierGroup']/div/input"));
		Groupcl.clear();
		Groupcl.sendKeys("All");
		Groupcl.sendKeys(Keys.ENTER);
		Thread.sleep(3000);  

		if (assessmentTargetStd.equals(StudTotalTargetStud)){
			debugLog.error("Target Student count : Student tab "+StudTotalTargetStud+" != Assessment tab "+assessmentTargetStd);
		}else{
		//	System.out.println("Target Student count : Student tab "+StudTotalTargetStud+" matching Assessment tab "+assessmentTargetStd);
		}
		
		compareTierData(assessmentTabTier, TierStudRec);
		compareGroupData(assessmentTabGroup, GroupStudRec);

		login.findElement(By.id("menubutton")).click();
		Thread.sleep(2000);
		login.findElement(By.id("ui-id-1")).click();
		Thread.sleep(2000);
        System.out.println("completed..");
	}

	//Comparing tier data between Assessment and Student tab.. 
	public void compareTierData(int[] assessmentTabTier, int[] studentTabTier)
	{	
		int j = 0;
		String[] tierName = {"Tier1", "Tier2", "Tier3"};
		for(int i=0; i<assessmentTabTier.length; i++)
		{
			//for(int j=0; j<studentTabTier.length; j++)
			while(j < studentTabTier.length)
			{
				if((assessmentTabTier[i]) == studentTabTier[j])
				{
			   //	System.out.println(tierName[i] + " mathed b/w assessment and student tab" +assessmentTabTier[i]+" "+studentTabTier[j]);
				}
				else
				{					
					debugLog.error(tierName[i] + " : Assessment tab "+assessmentTabTier[i]+" != with the student tab "+studentTabTier[j]);
				}
				j++;
				break;
			}
		}
	}

	//Comparing Group data between Assessment and Student tab..
	public void compareGroupData(int[] assessmentTabGroup, int[] studentTabGroup)
	{		
		int j=0;
		String[] groupName = {"Group A", "Group B", "Group C", "Group D"};
		for(int i=0; i<assessmentTabGroup.length; i++){
			//for(int j=0; j<studentTabGroup.length; j++){
			while(j < studentTabGroup.length)
			{
				if(assessmentTabGroup[i] == studentTabGroup[j]){
				//	System.out.println(groupName[i] +" mathed  b/w assessment and student tab for " +assessmentTabGroup[i]+" "+studentTabGroup[j]);
				}else{
					debugLog.error(groupName[i] +" : Assessment tab "+assessmentTabGroup[i]+" != with the student tab "+studentTabGroup[j]);
				}
				j++;
				break;
			}
		}
	}
}
