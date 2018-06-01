package LION_Automation;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LION_SystemTest {
	
	WebDriver driver;
	AsmtTab_Data asmtTab = new AsmtTab_Data();
	Populate_StudTab_Data studTab = new Populate_StudTab_Data();
	DetailsTab_FFD_Level detailTab= new DetailsTab_FFD_Level();
	EmergingLevel_Validation emeVal= new EmergingLevel_Validation();
	suhasini_details susdetailTab= new suhasini_details();
	String sheetPath = "test/Resources/Data/LION_SpreadSheet.xls";
	String FFTask;
	String[] FFTaskData;

	@BeforeSuite
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://trends.tangosoftware.com");
	}
	
    @BeforeTest
    public void login() throws InterruptedException{
    	driver.findElement(By.id("loginEmail")).sendKeys("shivaleela@TX_BrownsvilleISD");
		driver.findElement(By.id("password")).sendKeys("Shivu123");
		driver.findElement(By.id("loginbutton")).click();
		Thread.sleep(3000);
    }
	
    @Test
    public void getData() throws InterruptedException, BiffException, IOException{
    	Workbook workbook = Workbook.getWorkbook(new File(sheetPath));
    	Sheet sht = workbook.getSheet(2);
    	int rowCount = sht.getRows();
    	int columnCount = sht.getColumns();
    	System.out.println("rowCount: "+rowCount+" columnCount: "+columnCount);
    	for(int i=0 ; i<rowCount; i++){
    		String StudentID = sht.getCell(3,1).getContents();
//			String level = sht.getCell(0, i).getContents();
//          System.out.println("level -> "+level);
//          FFTaskData = new String[9];
//    		for(int j=1; j<columnCount; j++){
//    			FFTask= sht.getCell(j, 2).getContents();
//    			System.out.println("Excel sheet data: "+FFTask);
//    			FFTaskData[j] = FFTask;
//    			System.out.println(FFTaskData[j]);
//    		}	
		    	asmtTab.populate_data(sht, driver);
		    	detailTab.populate_Details_data(StudentID, driver);
		    	emeVal.FF01_Task(sht,detailTab.FF01Data);
		    	emeVal.FF03_Task(sht,detailTab.FF03Data);
    	}
    }

//    @AfterSuite
//    public void tearDown(){
//    	driver.quit();
//    }
	

}
