package Kentro_Document;

import org.apache.log4j.Logger;

public class Kento_Validate_AsmtSum {
	public static Logger debugLog=Logger.getLogger("KENTRO_ASMT");
	public static Logger reportsLog=Logger.getLogger("reportsLog");
		
	void validateKentroPassingCriteria(String[] AsmtData, String[] SummaryData, String[] Criteria)
	{		
	   for(int i=0; i< AsmtData.length;i++){
//			String[] asmt= AsmtData; String[] summ= SummaryData; System.out.println(asmt[i]+" "+summ[i]);
			if(AsmtData[i].equals(SummaryData[i])){
//				System.out.println(Criteria[i]+" data is matching - Summary tab"+SummaryData[i]+" Assessment tab "+AsmtData[i]);
				reportsLog.info(Criteria[i]+" data is matching - Summary tab"+SummaryData[i]+" Assessment tab "+AsmtData[i]);
			}else{
//				System.out.println(Criteria[i]+" : Summary tab "+SummaryData[i]+" != Assessment tab "+AsmtData[i]);
				debugLog.error(Criteria[i]+" : Summary tab "+SummaryData[i]+" != Assessment tab "+AsmtData[i]);
			}
		}
	}

	void validateKentroPercentageCriteria(double[] AsmtData, double[] SummaryData, String[] Criteria){
		for(int i=0; i< AsmtData.length; i++){
//		    double[] asmt= AsmtData; double[] sum= SummaryData; System.out.println(asmt[i]+" "+sum[i]);
		    double PercDiff = Math.abs(SummaryData[i] - AsmtData[i]);
			if(PercDiff > 1.0){
//				System.out.println(Criteria[i]+ " Percentage summary != assessment tab and difference value is: "+PercDiff);
				debugLog.error(Criteria[i]+ " Percentage summary != assessment tab and difference value is: "+PercDiff);
			}else{
//				System.out.println(Criteria[i]+ " Percentage is matching");
				reportsLog.info(Criteria[i]+ " Percentage is matching");
			}		    
		}		
	}
	
	void validateTargetStudent(int AsmtTargetStud, int SumTargetStud){
		if(SumTargetStud == AsmtTargetStud ){
//			System.out.println("Target student count : Summary tab "+SumTargetStud+" matching Assessment tab "+AsmtTargetStud);
			reportsLog.info("Target student count : Summary tab "+SumTargetStud+" matching Assessment tab "+AsmtTargetStud);
		}else{
//			System.out.println("Target student count : Summary tab "+SumTargetStud+" != Assessment tab "+AsmtTargetStud);
			debugLog.error("Target student count : Summary tab "+SumTargetStud+" != Assessment tab "+AsmtTargetStud);
		}		
	}
	
	void validateTakenStudent(int AsmtTakenStud, int SumTakenStud){
		if(SumTakenStud == AsmtTakenStud ){
//			System.out.println("Taken student count : Summary tab "+SumTakenStud+" matching Assessment tab "+AsmtTakenStud);
			reportsLog.info("Taken student count : Summary tab "+SumTakenStud+" matching Assessment tab "+AsmtTakenStud);
		}else{
//			System.out.println("Taken student count : Summary tab "+SumTakenStud+" != Assessment tab "+AsmtTakenStud);
			debugLog.error("Taken student count : Summary tab "+SumTakenStud+" != Assessment tab "+AsmtTakenStud);
		}	
	}
	
	void validateNotTakenStudent(int AsmtNotTakenStud, int SumNotTakenStud){
		if(SumNotTakenStud == AsmtNotTakenStud ){
//			System.out.println("Not Taken student count : Summary tab "+SumNotTakenStud+" matching Assessment tab "+AsmtNotTakenStud);
			reportsLog.info("Not Taken student count : Summary tab "+SumNotTakenStud+" matching Assessment tab "+AsmtNotTakenStud);
		}else{
//			System.out.println("Not Taken student count : Summary tab "+SumNotTakenStud+" != Assessment tab "+AsmtNotTakenStud);
			debugLog.error("Not Taken student count : Summary tab "+SumNotTakenStud+" != Assessment tab "+AsmtNotTakenStud);
		}		
	}
	
	void validatePassFailCriteria(String AsmtPassTally, int SumPassTally, String AsmtFailTally, int SumFailTally){
		if(AsmtPassTally.equals(SumPassTally)){
//			System.out.println("Pass Tally : Summary tab "+SumPassTally+" != Assessment tab "+AsmtPassTally);
			debugLog.error("Pass Tally : Summary tab "+SumPassTally+" != Assessment tab "+AsmtPassTally);
		}else{
//			System.out.println("Pass Tally : Summary tab "+SumPassTally+" == Assessment tab "+AsmtPassTally);
			reportsLog.info("Pass Tally : Summary tab "+SumPassTally+" == Assessment tab "+AsmtPassTally);
		}
		if(AsmtFailTally.equals(SumFailTally)){
//			System.out.println("Fail Tally : Summary tab "+SumFailTally+" != Assessment tab "+AsmtFailTally);
			debugLog.error("Fail Tally : Summary tab "+SumFailTally+" != Assessment tab "+AsmtFailTally);
		}else{
//			System.out.println("Fail Tally : Summary tab "+SumFailTally+" == Assessment tab "+AsmtFailTally);
			reportsLog.info("Fail Tally : Summary tab "+SumFailTally+" == Assessment tab "+AsmtFailTally);
		}		
	}
	
	void validatePassFailPerce(Float AsmtPassPerc, Float SumPassPerc, Float AsmtFailPerc, Float SumFailPerc){
		Float PassPerc = Math.abs(SumPassPerc - AsmtPassPerc);
		Float FailPerc = Math.abs(SumFailPerc - AsmtFailPerc);
//		System.out.println("pass per: "+PassPerc+" fail perc: "+FailPerc);
		if(PassPerc > 1.0){
//			System.out.println("Pass Percentage : Summary tab != assessment tab and difference value is: "+PassPerc);
			debugLog.error("Pass Percentage : Summary tab != assessment tab and difference value is: "+PassPerc);
		}else{
//			System.out.println("Pass Percentage : Summary tab "+SumPassPerc+" == Assessment tab "+AsmtPassPerc);
			reportsLog.info("Pass Percentage : Summary tab "+SumPassPerc+" == Assessment tab "+AsmtPassPerc);
		}
		if(FailPerc > 1.0){
//			System.out.println("Fail Percentage : Summary tab != assessment tab and difference value is: "+FailPerc);
			debugLog.error("Fail Percentage : Summary tab != assessment tab and difference value is: "+FailPerc);
		}else{
//			System.out.println("fail percentage is matching.. "+SumFailPerc+" "+AsmtFailPerc);
			reportsLog.info("fail percentage is matching.. "+SumFailPerc+" "+AsmtFailPerc);
		}
		
	}
	
}
