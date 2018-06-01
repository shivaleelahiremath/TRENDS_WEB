package Script;

import java.util.Arrays;

import org.apache.log4j.Logger;

public class Kentro_Validation_StudSum {
	public static Logger debugLog=Logger.getLogger("KENTRO_ASMT");
	public static Logger reportsLog=Logger.getLogger("reportsLog");
	
	void validateTargetStudent1(int StudTargetStud, int AsmtTargetStud){
		if(StudTargetStud == AsmtTargetStud ){
//			System.out.println("Target student count : Student tab "+StudTargetStud+" matching Assessment tab "+AsmtTargetStud);
			reportsLog.info("Target student count : Student tab "+StudTargetStud+" matching Assessment tab "+AsmtTargetStud);
		}else{
//			System.out.println("Target student count : Student tab "+StudTargetStud+" != Assessment tab "+AsmtTargetStud);
			debugLog.error("Target student count : Student tab "+StudTargetStud+" != Assessment tab "+AsmtTargetStud);
		}		
	}
		
	void validateFilterTaskData(String[] StudPF_Task, int[] StudPF_StudCount, String[] SumFilter_Task, int[] Sum_TaskCount){
		for(int i=0; i < StudPF_Task.length; i++){
			String StudTask = getFilterName(StudPF_Task[i].trim());
			int StudCount = StudPF_StudCount[i];
			System.out.println("StudTask: "+StudTask);
			for(int j=0; j< SumFilter_Task.length; j++){	
				String SumTask =SumFilter_Task[j].trim();
				System.out.println("SumTask: "+SumTask);
				if(StudTask.equalsIgnoreCase(SumTask)){
					int SumCount = Sum_TaskCount[j];
					if(StudCount == SumCount){
//						System.out.println("count is matching for : "+StudTask+" and "+SumTask+" Student tab "+StudCount+" Summary tab "+SumCount);
						reportsLog.info("count is matching for : "+StudTask+" and "+SumTask);
					}else{
						debugLog.error("Filter tab task ->"+StudTask.trim()+ ": Student tab filter task "+StudCount+" != Summary tab "+SumCount);
//						System.out.println(StudTask.trim()+ ": Student tab "+StudCount+" != Summary tab "+SumCount);
					}
				}
			}  
		}
	}
		
	public static String getFilterName(String strKey)
	{
		String retKey = null;
		switch(strKey){
		case "EcDi": //  System.out.println("Economically Disadvantaged");
			retKey = "Economically Disadvantaged"; break;

		case "Ti1A": //  System.out.println("Title 1 A");
			retKey = "Title 1 A"; break;

		case "LEP": // System.out.println("Limited English Proficient");
			retKey = "Limited English Proficient"; break;

		case "Migr": // System.out.println("Migrant");
			retKey = "Migrant"; break;

		case "ESL": // System.out.println("English as a Second Language");
			retKey = "English as a Second Language"; break;

		case "GTEd": //System.out.println("Gifted and Talented");
			retKey = "Gifted and Talented"; break;

		case "AtRk":// System.out.println("At Risk");
			retKey = "At Risk"; break;

		case "SpEd": //System.out.println("Special Education");
			retKey = "Special Education"; break;

		case "CtEd": //System.out.println("Career and Technology");
			retKey = "Career and Technology"; break;

		case "GI9": //System.out.println("State Accountable");
			retKey = "State Accountable"; break;

			//		case "": retKey = "Bilingual Education";
			//		System.out.println("Bilingual Education"); break;

		case "Female": retKey = "Female"; break;

		case "Male": retKey = "Male"; break;

		case "Asian or Pacific Islander": retKey="Asian or Pacific Islander"; break;

		case "Black (Non Hispanic)": retKey ="Black (Non Hispanic)"; break;

		case "American Indian or Alaska Native": retKey= "American Indian or Alaska Native"; break;

		case "White (Non Hispanic)": retKey ="White (Non Hispanic)"; break;

		case "Hispanic": retKey = "Hispanic"; break;

		default: retKey = "NA"; break;

		}		
		return retKey;	
	}

}
