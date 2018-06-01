package Script;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class STAAR_DEMO_SAccountDoc_TS {

	private static Logger debugLog=Logger.getLogger("debugLog");
	private static Logger reportsLog=Logger.getLogger("reportsLog");
	String[][] tabArray = null;
	int rowCount, colCount;
	String sheetPath = "test/Resources/Data/Latest_STARR_Document.xls";	
	WebDriver login;

	@BeforeClass
	public void setUp() throws InterruptedException{
		//opening Chrome browser..
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		login = new ChromeDriver();				
		//Launching the application..
		login.get("http://trends.tangosoftware.com");
		System.out.println("Launched Trends web link..");
		reportsLog.info("Trends Application launched successfully..");
	}

	//Shivaleela@TX_BrownsvilleISD, Shivaleela@TX_ComalISD, TX_HarlingenCISD, TX_RioGrandeCityCISD
	@Test
	public void Login(){
		//Login functionality..
		reportsLog.info("Testing STAAR documents for district RioGrandeCityCISD production..");
		login.findElement(By.id("loginEmail")).sendKeys("Shivaleela@TX_BrownsvilleISDTest03");
		login.findElement(By.id("password")).sendKeys("Shivu123");
	    login.findElement(By.id("loginbutton")).click();
		reportsLog.info("Login successfully..");
	}

	@Test
	public void getExcelData() throws Exception {
		Workbook workbk = Workbook.getWorkbook(new File(sheetPath));
		Sheet sht = workbk.getSheet("Sheet1");
		rowCount = sht.getRows();
		colCount = sht.getColumns();
		tabArray = new String[rowCount][colCount];
//		System.out.println("erow: " + rowCount);
//		System.out.println("ecol: " + colCount);	
		for(int i= 324 ;i<sht.getRows(); i++){
			String DocTitle = sht.getCell(0, i).getContents();
			VerifyProgramFunInfo(DocTitle);
		}		
	}

	public void VerifyProgramFunInfo(String DocTitle) throws InterruptedException{
		
		//Selecting STAAR tab on assessment module..
		login.findElement(By.id("menubutton")).click();
		Thread.sleep(1000);
		login.findElement(By.id("ui-id-9")).click();
		Thread.sleep(4000);
		login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input")).click();
		WebElement element = login.findElement(By.xpath("html/body/content/div[1]/div[2]/div/div/div/div[1]/div/table/thead/tr/th[4]/filter/div/input"));
		element.sendKeys(DocTitle);
		element.sendKeys(Keys.ENTER);
		Thread.sleep(4000);

		//Selecting document..
		List mcanswers = login.findElements(By.className("number"));  
//    	System.out.println("total documents: " +mcanswers.size());
		int SelectDocu = mcanswers.size()-2;	  
		String RecCount = login.findElement(By.className("reccount")).getText();			   
		if(RecCount.equalsIgnoreCase("0 Records")){
			reportsLog.info("Document not found: "+DocTitle);
			System.out.println("Document not found: "+DocTitle);
//			element.clear();
//			Thread.sleep(6000);
		}else{	    	 	    	         	
			List<WebElement> Assessment= login.findElements(By.className("go"));
			WebElement AssessmentClick=(WebElement) Assessment.get(SelectDocu); 
			AssessmentClick.click();
			System.out.println("Selected "+DocTitle+" document..");
			Thread.sleep(2000); 

			//Demographic tab details... 
			login.findElement(By.xpath("html/body/header/top/tabs/a[6]/tab")).click();
			List<WebElement> Demo = login.findElements(By.id("AccountabilityAssessmentDemogBreakdown"));	            	
			WebElement DemoData = Demo.get(2);	       
//	        System.out.println("assessment data: "+DemoData.getText());

			//Filtering and selecting Program Funding details..
			WebElement web = DemoData.findElement(By.xpath("tr/th[3]/filter/div"));
			WebElement Group= web.findElement(By.tagName("input"));
			Group.clear();
			Group.sendKeys("Program Funding");
			Group.sendKeys(Keys.ENTER);
			Thread.sleep(3000); 
			List PF = login.findElements(By.className("number"));     
			// System.out.println("total PF tasks... " +PF.size());
			WebElement task = Demo.get(3);	

			String[] DemographyPF_Task = new String[PF.size()];
			int[] DemoPF_NotTaken = new int[PF.size()];
			int[] DemoPF_Taken = new int[PF.size()];
			for(int i=1; i< PF.size();i++){
				DemographyPF_Task[i]=  task.findElement(By.xpath("tbody/tr["+i+"]/td[4]")).getText();
				String notTaken = task.findElement(By.xpath("tbody/tr["+i+"]/td[8]/left")).getText();
				DemoPF_NotTaken[i] = Integer.parseInt(notTaken);	
				String Taken = task.findElement(By.xpath("tbody/tr["+i+"]/td[7]/left")).getText();
				DemoPF_Taken[i] = Integer.parseInt(Taken.replaceAll(" of [\\d]+[A-Za-z]?", ""));
			//	System.out.println("Demo tab task: " +DemographyPF_Task[i]+" not taken student count: "+DemoPF_NotTaken[i]+ " and taken student count:  "+DemoPF_Taken[i]);
			}

			String DemoDataSet1[]={"Pass","Fail"};
			int ROWS = PF.size();
			int COLS = DemoDataSet1.length;
			int[][] rowValue = new int[ROWS][COLS];
			for(int i=0;i<PF.size()-1;i++){
				for(int j=0;j<DemoDataSet1.length;j++){
					String Demovalue = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+5)+"]/left")).getText();
					rowValue[i][j] = Integer.parseInt(Demovalue);
					//	 System.out.println(rowValue[i][j]);
				}		
			}

			String DemoDataSet2[]={"AdvancePlus","Advance","SatisfactoryPlus","Satisfactory","SatisfactoryBubble","UnSatisfactoryBubble","UnSatisfactory"};
			int COLS1 = DemoDataSet2.length;
			int[][] rowValue1 = new int[ROWS][COLS1];
			int[][] PF_passFailArray = new int[ROWS][2];
			for(int i=0;i<PF.size()-1;i++){
				int passValue = 0;
				int failValue = 0;
				for(int j=0;j<DemoDataSet2.length;j++){
					String Demovalue1 = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+9)+"]/left")).getText();
					rowValue1[i][j] = Integer.parseInt(Demovalue1);
					//System.out.println(rowValue1[i][j]);
					if(j<5){
						passValue += rowValue1[i][j];
					}else if(j<7){
						failValue += rowValue1[i][j];
					}
				}
				for(int k=0; k<2; k++){
					if(k == 0){
						PF_passFailArray[i][k] = passValue;
						//	 System.out.println("PassFailArray pass Value " + PF_passFailArray[i][k]);
					}else{
						PF_passFailArray[i][k] = failValue;
						//	 System.out.println("PassFailArray fail Value " + PF_passFailArray[i][k]);
					}					 
				}		
			}

			//verifying pass and fail count on demography tab..
			for(int i=1;i<DemographyPF_Task.length;i++){
				String DemographyPF_Task1 = DemographyPF_Task[i];
				for (int l = 0 ; l<rowValue[i-1].length; l++){
					if(Arrays.equals(rowValue[i-1], PF_passFailArray[i-1])){
						reportsLog.info("pass and fail values are matching for PF task.." +rowValue[i-1][l]+ " "+PF_passFailArray[i-1][l] );				
					}else{
					    debugLog.error("Program Funding -> "+DemographyPF_Task1+" pass/fail values are not matching with the sum of levels on Demography tab for document: "+DocTitle+" Expected and Found value on Demography tab is : "+rowValue[i-1][l]+ " and "+PF_passFailArray[i-1][l]);
					}
				}
			}

			// Filtering and selecting gender details..s
			WebElement test = login.findElement(By.xpath("//*[@id='AccountabilityAssessmentDemogBreakdown']/tr/th[3]/filter/div/input"));
			test.clear();
			test.sendKeys("Gender");
			test.sendKeys(Keys.ENTER);
			Thread.sleep(3000); 
			List Gender = login.findElements(By.className("number"));     
			//System.out.println("total  ...: " +Gender.size());

			String[] GenderOption = new String[Gender.size()];
			int[] DemoGen_NotTaken = new int[Gender.size()];
			int[] DemoGen_Taken = new int[Gender.size()];
			for(int i=1; i< Gender.size();i++){
				GenderOption[i]=  task.findElement(By.xpath("tbody/tr["+i+"]/td[4]")).getText();
				String notTaken = task.findElement(By.xpath("tbody/tr["+i+"]/td[8]/left")).getText();
				DemoGen_NotTaken[i] = Integer.parseInt(notTaken);	
				String Taken = task.findElement(By.xpath("tbody/tr["+i+"]/td[7]/left")).getText();
				DemoGen_Taken[i] = Integer.parseInt(Taken.replaceAll(" of [\\d]+[A-Za-z]?", ""));
			//    System.out.println("Demo tab task: " +GenderOption[i]+" not taken student count : "+DemoGen_NotTaken[i]+" and taken student count: "+DemoGen_Taken[i]);
			}

			//removes all nulls 
			ArrayList<String> al = new ArrayList<String>(); 
			for(int i=0; i<GenderOption.length; i++){ 
				if(GenderOption[i]!=null) 
					al.add(GenderOption[i]); 
			}
			GenderOption = (String[])al.toArray(new String[al.size()]);
			//	System.out.println("--> "+Arrays.toString(GenderOption));

			int GenROWS = Gender.size();
			int[][] GenrowValue = new int[GenROWS][COLS];
			for(int i=0;i<Gender.size()-1;i++){
				for(int j=0;j<DemoDataSet1.length;j++){
					String Demovalue = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+5)+"]/left")).getText();
					GenrowValue[i][j] = Integer.parseInt(Demovalue);
					//	 System.out.println(GenrowValue[i][j]);
				}		
			}

			int[][] GenderRowValue1 = new int[GenROWS][COLS1];
			int[][] Gender_passFailArray = new int[GenROWS][2];
			for(int i=0;i<Gender.size()-1;i++){
				int passValue = 0;
				int failValue = 0;
				for(int j=0;j<DemoDataSet2.length;j++){
					String Demovalue1 = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+9)+"]/left")).getText();
					GenderRowValue1[i][j] = Integer.parseInt(Demovalue1);
					//  System.out.println("-->"+GenderRowValue1[i][j]);
					if(j<5){
						passValue += GenderRowValue1[i][j];
					}else if(j<7){
						failValue += GenderRowValue1[i][j];
					}
				}
				for(int k=0; k<2; k++){
					if(k == 0){
						Gender_passFailArray[i][k] = passValue;
						//	 System.out.println("Pass and Fail Values of Gender task " + Gender_passFailArray[i][k]);
					}else{
						Gender_passFailArray[i][k] = failValue;
						//	 System.out.println("Pass and Fail Values of Gender task " + Gender_passFailArray[i][k]);
					}					 
				}		
			}

			//verifying pass and fail count on demography tab..
			for(int i=0;i<GenderOption.length;i++){
				String GenderOption1 = GenderOption[i];
				for (int l = 0 ; l<GenrowValue[i].length ; l++){
					if(Arrays.equals(GenrowValue[i], Gender_passFailArray[i])){
						reportsLog.info("pass and fail values are matching for gender task.."+GenrowValue[i][l]+" "+Gender_passFailArray[i][l]);				
					}else{
						debugLog.error("Gender -> "+GenderOption1+" pass/fail values are not matching with the sum of levels on Demography tab for document:  "+DocTitle+" Expected and Found value on Demography tab is: " +GenrowValue[i][l]+" and "+Gender_passFailArray[i][l]);
					}
				}
			}

			// Filtering and selecting Ethnicity details..
			WebElement test1 = login.findElement(By.xpath("//*[@id='AccountabilityAssessmentDemogBreakdown']/tr/th[3]/filter/div/input"));
			test1.clear();
			test1.sendKeys("Ethnicity");
			test1.sendKeys(Keys.ENTER);
			Thread.sleep(3000); 
			List Ethnicity = login.findElements(By.className("number"));     
			//	System.out.println("total  ...: " +Ethnicity.size());

			String[] EthnicityOption = new String[Ethnicity.size()];
			int[] DemoEth_NotTaken = new int[Ethnicity.size()];
			int[] DemoEth_Taken = new int[Ethnicity.size()];
			for(int i=0; i< Ethnicity.size()-1;i++){
				EthnicityOption[i]=  task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td[4]")).getText();
				String notTaken = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td[8]/left")).getText();
				DemoEth_NotTaken[i] = Integer.parseInt(notTaken);
				String Taken = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td[7]/left")).getText();
				DemoEth_Taken[i] = Integer.parseInt(Taken.replaceAll(" of [\\d]+[A-Za-z]?", ""));
			//    System.out.println("Demo tab task: "+EthnicityOption[i] +" not taken student count: "+DemoEth_NotTaken[i]+" and taken student count: "+DemoEth_Taken[i]);
			}

			//removes all nulls 
			ArrayList<String> ethn = new ArrayList<String>(); 
			for(int i=0; i<EthnicityOption.length; i++){ 
				if(EthnicityOption[i]!=null) 
					ethn.add(EthnicityOption[i]); 
			}
			EthnicityOption = (String[])ethn.toArray(new String[ethn.size()]);
			//   System.out.println("demo ethn task--> "+Arrays.toString(EthnicityOption));

			int EthnicityROWS = Ethnicity.size();
			int[][] EthrowValue = new int[EthnicityROWS][COLS];
			for(int i=0;i<Ethnicity.size()-1;i++){
				for(int j=0;j<DemoDataSet1.length;j++){
					String Demovalue = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+5)+"]/left")).getText();
					EthrowValue[i][j] = Integer.parseInt(Demovalue);
					//System.out.println(EthrowValue[i][j]);
				}		
			}

			int[][] EthnicityRowValue1 = new int[EthnicityROWS][COLS1];
			int[][] Ethnicity_passFailArray = new int[EthnicityROWS][2];
			for(int i=0;i<Ethnicity.size()-1;i++){
				int passValue = 0;
				int failValue = 0;
				for(int j=0;j<DemoDataSet2.length;j++){
					String Demovalue1 = task.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+9)+"]/left")).getText();
					EthnicityRowValue1[i][j] = Integer.parseInt(Demovalue1);
					//    System.out.println(EthnicityRowValue1[i][j]);
					if(j<5){
						passValue += EthnicityRowValue1[i][j];
					}else if(j<7){
						failValue += EthnicityRowValue1[i][j];
					}
				}	
				for(int k=0; k<2; k++){
					if(k == 0){
						Ethnicity_passFailArray[i][k] = passValue;
						//	 System.out.println("Pass and Fail Values of ethnicity task " + Ethnicity_passFailArray[i][k]);
					}else{
						Ethnicity_passFailArray[i][k] = failValue;
						//	 System.out.println("Pass and Fail Values of ethnicity task " + Ethnicity_passFailArray[i][k]);
					}					 
				}	
			}

			//verifying pass and fail count on demography tab..
			for(int i=0;i<EthnicityOption.length;i++){
				String EthnicityOption1 = EthnicityOption[i];
				for (int l = 0 ; l<EthrowValue[i].length ; l++){
					if(Arrays.equals(EthrowValue[i], Ethnicity_passFailArray[i])){
						reportsLog.info("pass and fail values are matching for ethnicity task.."+EthrowValue[i][l]+" "+Ethnicity_passFailArray[i][l]);				
					}else{
						debugLog.error("Ethnicity -> "+EthnicityOption1+" pass/fail values are not matching with the sum of levels on Demography tab for document:  "+DocTitle+"Expected and Found value on Demography tab is: "+EthrowValue[i][l]+" and "+Ethnicity_passFailArray[i][l]);
					}
				}
			}

			login.findElement(By.xpath("html/body/header/top/tabs/a[2]/tab")).click();
			System.out.println("Summary tab Clicked"); 

			//Accessing values from the passing criteria table  
			List<WebElement> Tasks_table = login.findElements(By.className("tableswithbars"));		  
			WebElement table_element1=Tasks_table.get(1);
			List<WebElement> tr_collection=table_element1.findElements(By.cssSelector(".tableswithbars>tbody>tr"));
	//		System.out.println("NUMBER OF ROWS IN PROGRAM FUNDING TABLE = "+tr_collection.size());	        

			//Program Funding tasks and student count on summary tab..	 
			String[] SumPF_Tasks= new String[tr_collection.size()+1];
			int[] Sum_NotTaken = new int[tr_collection.size()+1];
			int[] SumPF_Taken = new int[tr_collection.size()+1];
			for(int row = 1;row < tr_collection.size()+1; row++){
				SumPF_Tasks[row] = table_element1.findElement(By.xpath("tbody/tr["+row+"]/td[1]/bar/text")).getText();
				String NotTaken = table_element1.findElement(By.xpath("tbody/tr["+row+"]/td[12]")).getText();
				Sum_NotTaken[row] = Integer.parseInt(NotTaken);
				String Taken =  table_element1.findElement(By.xpath("tbody/tr["+row+"]/td[11]")).getText();
				SumPF_Taken[row] = Integer.parseInt(Taken);
			//	System.out.println("Summary tab PF task: "+SumPF_Tasks[row]+" not taken: "+Sum_NotTaken[row]+" and taken count: "+SumPF_Taken[row]);
			}

			int srow = tr_collection.size();
			int scolm = 7;
			int[][] SumPF_val = new int[srow][scolm];     
			for(int i=0;i < srow;i++){
				for(int j=0;j<scolm;j++){
					String Demovalue1 = table_element1.findElement(By.xpath("tbody/tr["+(i+1)+"]/td["+(j+3)+"]")).getText();
					SumPF_val[i][j] = Integer.parseInt(Demovalue1);
					//	System.out.println(SumPF_val[i][j]); 			    	 
				}	
				//	System.out.println("-------------");
			}

			WebElement table_element =Tasks_table.get(0);
			List<WebElement> tr_collection1=table_element.findElements(By.cssSelector(".tableswithbars>tbody>tr"));
	//		System.out.println("NUMBER OF ROWS IN GENDER AND ETHNICITY TABLE = "+tr_collection1.size());	  

			//Gender and gender count on summary tab..	 
			String[] SumGender= new String[3];
			int[] SumGen_NotTaken = new int[3];
			int[] SumGen_Taken = new int[3];
			try{
				for(int row = 1; row < 3;row++){
					SumGender[row] =table_element.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[1]/bar/text")).getText();
					String NotTaken1 = table_element.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[12]")).getText();
					SumGen_NotTaken[row] = Integer.parseInt(NotTaken1); 
					String Taken = table_element.findElement(By.xpath("tbody/tr["+(row+1)+"]/td[11]")).getText();
					SumGen_Taken[row] = Integer.parseInt(Taken); 
			//		System.out.println("Summary  tab gender: " +SumGender[row]+" not taken: "+SumGen_NotTaken[row]+" and taken student count: "+SumGen_Taken[row]);
				}	 
			}catch (Exception e){
				System.out.println("Gender not found: " +Arrays.toString(SumGender));
			}

			//removes all nulls 
			ArrayList<String> al1 = new ArrayList<String>(); 
			for(int i=0; i<SumGender.length; i++){ 
				if(SumGender[i]!=null) 
					al1.add(SumGender[i]); 
			}
			SumGender = (String[])al1.toArray(new String[al1.size()]);
			//    System.out.println("summary--> "+Arrays.toString(SumGender));

			int Genrow = tr_collection1.size();
			int[][] SumGender_val = new int[Genrow][scolm];     
			try{
				for(int i=0;i < 2;i++){
					for(int j=0;j<scolm;j++){
						String Demovalue1 = table_element.findElement(By.xpath("tbody/tr["+(i+2)+"]/td["+(j+3)+"]")).getText();
						SumGender_val[i][j] = Integer.parseInt(Demovalue1); 
						//		System.out.println(SumGender_val[i][j]); 			    	 
					}	
					//	System.out.println("------>");
				}
			}catch (Exception e){
				System.out.println("Gender not found: " +Arrays.toString(SumGender));
			}

			//Ethnicity tasks and not taken student count on summary tab..
			String[] SumEthnicity= new String[tr_collection1.size()+1];	
			int[] SumEth_NotTaken = new int[tr_collection1.size()+1];
			int[] SumEth_Taken = new int[tr_collection1.size()+1];
			int max1=  tr_collection1.size() + 1;
			for(int row = 0; row < max1-5;row++){	    	  
				SumEthnicity[row] =table_element.findElement(By.xpath("tbody/tr["+(row+5)+"]/td[1]/bar/text")).getText();	
				String NotTaken1 = table_element.findElement(By.xpath("tbody/tr["+(row+5)+"]/td[12]")).getText();
				SumEth_NotTaken[row] = Integer.parseInt(NotTaken1); 
				String Taken1 = table_element.findElement(By.xpath("tbody/tr["+(row+5)+"]/td[11]")).getText();
				SumEth_Taken[row] = Integer.parseInt(Taken1); 
		//		System.out.println(SumEthnicity[row]+"---"+SumEth_NotTaken[row]+"---"+SumEth_Taken[row]);
			}

			//removes all nulls 
			ArrayList<String> Sumethn = new ArrayList<String>(); 
			for(int i=0; i<SumEthnicity.length; i++){ 
				if(SumEthnicity[i]!=null) 
					Sumethn.add(SumEthnicity[i]); 
			}
			SumEthnicity = (String[])Sumethn.toArray(new String[Sumethn.size()]);
		//	System.out.println("sum ethn task--> "+Arrays.toString(SumEthnicity));

			int[][] SumEthnicity_val = new int[Genrow+1][scolm];     
			for(int i=0;i < max1-5;i++){
				for(int j=0;j<scolm;j++){
					String Demovalue1 = table_element.findElement(By.xpath("tbody/tr["+(i+5)+"]/td["+(j+3)+"]")).getText();
					SumEthnicity_val[i][j] = Integer.parseInt(Demovalue1); 
					//	System.out.println("ethnicity: "+SumEthnicity_val[i][j]); 			    	 
				}	
				//	System.out.println("------>");
			}

			//verifying Gender task count... 
			for(int i=1; i <GenderOption.length+1; i++){
				String DemoGenderTask = GenderOption[i-1];
				int DemoGen_TakenCount = DemoGen_Taken[i];
				int DemoGen_NotTakenCount = DemoGen_NotTaken[i];
				for(int j=1; j < SumGender.length+1; j++){	
					String SumGenderTask =SumGender[j-1];
					int SumGen_TakenCount = SumGen_Taken[j];
					int SumGen_NotTakenCount = SumGen_NotTaken[j];
					if(DemoGenderTask.equalsIgnoreCase(SumGenderTask)){
						if(Arrays.equals(GenderRowValue1[i-1], SumGender_val[j-1])){
							reportsLog.info("Matched Demography gender task: "+DemoGenderTask+" and Summary gender task:  "+ SumGenderTask);
						}else{
							debugLog.error("Gender -> "+DemoGenderTask+" student count mismatch between Demography and Summary tab for document: " +DocTitle);
						}
						if(DemoGen_NotTakenCount == SumGen_NotTakenCount){
							reportsLog.info("gender task not taken student count is matching for task: "+DemoGenderTask+" and "+SumGenderTask);
						}else{
							debugLog.error("Gender -> "+DemoGenderTask+" Not taken student count mismatch between Demography and Summary tab for document: " +DocTitle+ " Expected value on Student tab is "+DemoGen_NotTakenCount+" and found value on summary tab is: "+SumGen_NotTakenCount);
						}
						if(DemoGen_TakenCount == SumGen_TakenCount){
							reportsLog.info("gender task taken student count is matching for task: "+DemoGenderTask+" and "+SumGenderTask);
						}else{
							debugLog.error("Gender -> "+SumGenderTask+" taken student count is mismatch between Demography and Summary tab for document: " +DocTitle+ " Expected value on Student tab is "+DemoGen_TakenCount+" and found value on summary tab is: "+SumGen_TakenCount);
						}
					}
				}
			}

			//Verifying Ethnicity task..
			for(int i=0;i<EthnicityOption.length; i++){
				String DemoEthTask = EthnicityOption[i];
				int DemoEth_TakenCount = DemoEth_Taken[i] ; 
				int DemoEth_NotTakenCount = DemoEth_NotTaken[i];
				for(int j=0;j<SumEthnicity.length;j++){
					String SumEthTask =SumEthnicity[j];
					int SumEth_TakenCount = SumEth_Taken[j] ; 
					int SumEth_NotTakenCount = SumEth_NotTaken[j];
					if(DemoEthTask.equalsIgnoreCase(SumEthTask)){	
						if(Arrays.equals(EthnicityRowValue1[i], SumEthnicity_val[j])){
							reportsLog.info("Matched Demography ethnicity task:  "+DemoEthTask+" and Summary ethnicity task: "+ SumEthTask);
						}else{
							debugLog.error("Ethnicity -> "+DemoEthTask+" student count mismatch between Demography and Summary tab for document: " +DocTitle);
						}
						if(DemoEth_NotTakenCount == SumEth_NotTakenCount){
		                    reportsLog.info("Ethnicity task not taken student count is matching for task: "+DemoEthTask+" and "+SumEthTask);
						}else{
							debugLog.error("Ethnicity -> "+DemoEthTask+" Not taken student count mismatch between Demography and Summary tab for document: " +DocTitle+ " Expected value on Student tab is "+DemoEth_NotTakenCount+" and found value on summary tab is: "+SumEth_NotTakenCount);
						}
						if(DemoEth_TakenCount == SumEth_TakenCount){
							reportsLog.info("Ethnicity taken student count is matching for task: "+DemoEthTask+" and "+SumEthTask);
						}else{
							debugLog.error("Ethnicity -> "+DemoEthTask+" taken student count mismatch between Demography and Summary tab for document: " +DocTitle+ " Expected value on Student tab is "+DemoEth_TakenCount+" and found value on summary tab is: "+SumEth_TakenCount);
						}
					}
				}
			}

			//verifying Program Funding task count... 
			for(int i=1; i <DemographyPF_Task.length; i++){
				String DemoTask = DemographyPF_Task[i];
				int DemoPF_NotTakenCount = DemoPF_NotTaken[i];
				int DemoPF_TakenCount = DemoPF_Taken[i] ;
				for(int j=1; j < SumPF_Tasks.length; j++){	
					String SumTask =SumPF_Tasks[j];
					if(DemoTask.equalsIgnoreCase(SumTask)){
						int SumPF_NotTakenCount = Sum_NotTaken[j];
						int SumPF_TakenCount = SumPF_Taken[j];
						if(Arrays.equals(rowValue1[i-1], SumPF_val[j-1])){
							reportsLog.info("Matched Demography PF task: "+DemoTask+" and Summary PF task: "+ SumTask);
						}else{
							debugLog.error("Program Funding -> "+DemoTask+" student count mismatch between Demography and Summary tab for document: " +DocTitle);
						}		            	
						if(DemoPF_NotTakenCount == SumPF_NotTakenCount){
					    	reportsLog.info("PF not taken student count is matching for task: "+DemoTask+" and "+SumTask);
						}else{
							debugLog.error("Program Funding -> "+DemoTask+" Not taken student count mismatch between Demography and Summary tab for document: " +DocTitle+ " Expected value on Student tab is "+DemoPF_NotTakenCount+" and found value on summary tab is: "+SumPF_NotTakenCount);
						}
						if(DemoPF_TakenCount == SumPF_TakenCount){
							reportsLog.info("PF taken student count is matching for task: "+DemoTask+" and "+SumTask);
						}else{
							debugLog.error("Program Funding -> "+DemoTask+" taken student count mismatch between Demography and Summary tab for document: " +DocTitle+ "Expected value on Student tab is "+DemoPF_TakenCount+" and found value on summary tab is: "+SumPF_TakenCount);
						}
						
//	      System.out.println("Printing Demo Values");
//		  for (int i=0 ; i< rowValue1[i-1].length-1; i++) System.out.println(rowValue1[i-1][i]);	            	
//		  System.out.println("Printing Summary Values");
//		  for (int i=0 ; i< SumPF_val[j-1].length-1; i++) System.out.println(SumPF_val[j-1][i]);

//						for (int i = 0 ; i<rowValue1[i-1].length ; i++){
//							  if(rowValue1[i-1][i] == SumPF_val[j-1][i]){
//								System.out.println("Matched "+rowValue1[i-1][i]+" and "+ SumPF_val[j-1][i]);
//								continue ; 
//							  }else{
//								System.out.println(" Demo "+DemoTask+"Summary Task" +SumTask+" did not match ");
//								System.out.println(" Didn't match  "+rowValue1[i-1][i]+" and "+ SumPF_val[j-1][i]);
//								break;
//							  }
//						}	
//						System.out.println("************************************************");
					}	            
				}
			}		
    		System.out.println("*********Successfylly verified Demography tab data*********");
		}		
	}
	
//	@AfterClass
//	public void tearDown() throws InterruptedException{
//		login.findElement(By.id("gearbutton")).click();
//		Thread.sleep(2000);
//		login.findElement(By.id("signoutbutton")).click();
//		login.quit();
//	}
}


