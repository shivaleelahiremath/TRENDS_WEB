package Script;

public class CommonClass {
	
	String intVerify(int val1,int val2){		
		String returnvalue;
		if(val1==val2){
			returnvalue="passed";
		}
		else{
			returnvalue="Failed "+(val1)+" != "+(val2);
		}
			return returnvalue;		
	}	
	
	String strVerify(String s1,String s2){		
		String returnvalue;		
		if(s1.equals(s2)){
			returnvalue="Passed";
		}
		else{
			returnvalue="Failed "+s1+" != "+s2;
		}
	return returnvalue;				
	}
	
	String intstringVerify(int i1,String s1){		
		Integer i=i1;
		String s=i.toString();
		String returnvalue;		
		if(s.equals(s1)){
			returnvalue="Passed";
		}else{
			returnvalue="Failed "+s+" != "+s1;
		}
		return returnvalue;
	}
	
	int sumOfString(String s1,String s2){		
		return (Integer.parseInt(s1)+Integer.parseInt(s2));		
	}
    
	int sumOfString(int s1,int s2){		
		return (s1+s2);		
	}
}

/*
 * log4j.rootLogger=TRACE, stdout

log4j.appender.debugLog=org.apache.log4j.FileAppender
log4j.appender.debugLog.File=logs/debug.log
log4j.appender.debugLog.layout=org.apache.log4j.PatternLayout
log4j.appender.debugLog.layout.ConversionPattern=%d - %c - %p - %m%n

log4j.appender.reportsLog=org.apache.log4j.FileAppender
log4j.appender.reportsLog.File=logs/reports.log
log4j.appender.reportsLog.layout=org.apache.log4j.PatternLayout
log4j.appender.reportsLog.layout.ConversionPattern=%d - %c - %p - %m%n

log4j.category.debugLogger=TRACE, debugLog
log4j.additivity.debugLogger=false

log4j.category.reportsLogger=DEBUG, reportsLog
log4j.additivity.reportsLogger=false



try{
    WebElement date = driver.findElement(By.linkText(Utility.getSheetData(path, 7, 1, 2)));
    date.click();
}
catch(org.openqa.selenium.StaleElementReferenceException ex)
{
    log.debug("Exception in finding date");
    log.debug(e);
    WebElement date = driver.findElement(By.linkText(Utility.getSheetData(path, 7, 1, 2)));
    //WebElement date = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[6]/td[4]/a/font"));
    date.click();
}

 */
