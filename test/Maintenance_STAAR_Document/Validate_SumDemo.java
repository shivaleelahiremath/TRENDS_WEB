package Maintenance_STAAR_Document;

import java.util.Arrays;

import org.apache.log4j.Logger;

public class Validate_SumDemo {
	public static Logger debugLog=Logger.getLogger("Main_STAAR_ASMT");
	public static Logger reportsLog=Logger.getLogger("reportsLog");

	void validateProgramFundingValue(String[] DemoPFtask, int[][] DemoPFTaskValue, String[] SumPFTasks, int[][] SumPFTasksValue)
	{
		for(int i=1; i < DemoPFtask.length; i++){
			String DemoTask = DemoPFtask[i];
			//System.out.println("Demography PF task: "+DemoTask);
			for(int j= 1; j < SumPFTasks.length; j++){	
				String SumTask =SumPFTasks[j];
				//System.out.println("Summary PF task: "+ SumTask);
				if(DemoTask.equalsIgnoreCase(SumTask)){
					if(Arrays.equals(DemoPFTaskValue[i-1], SumPFTasksValue[j-1])){
//						System.out.println("Matched Demography PF task: "+DemoTask+" "+DemoPFTaskValue[i-1]+" and Summary PF task: "+ SumTask+" "+SumPFTasksValue[j-1]);
						reportsLog.info("Matched Demography PF task: "+DemoTask+" and Summary PF task: "+ SumTask);
					}else{
//						System.out.println("Program Funding -> "+DemoTask+" student count mismatch between Demography and Summary tab");
						debugLog.error("Program Funding -> "+DemoTask+" student count mismatch between Demography and Summary tab");
					}		            
				}	            
			}
		}
	}

	void validateProgramFundingTakenStud(String[] DemoPFTask, int[] DemoTaken, String[] SumPFTask, int[] SumTaken){
		for(int i=1;i<DemoPFTask.length;i++){
			String DemoTask = DemoPFTask[i];
			int D_PFTaken= DemoTaken[i];
			for(int j=1; j<SumPFTask.length;j++){
				String SumTask =SumPFTask[j];
				int S_PFTaken = SumTaken[j];
				if(DemoTask.equalsIgnoreCase(SumTask)){
					if(D_PFTaken==S_PFTaken){
//						System.out.println("Program Funding -> "+DemoTask+" taken student: Demography tab "+D_PFTaken+" == Summary tab "+S_PFTaken);
						reportsLog.info("Program Funding -> "+DemoTask+" taken student: Demography tab "+D_PFTaken+" == Summary tab "+S_PFTaken);
					}else{
//						System.out.println("Program Funding -> "+DemoTask+" taken student: Demography tab "+D_PFTaken+" != Summary tab "+S_PFTaken);
						debugLog.error("Program Funding -> "+DemoTask+" taken student: Demography tab "+D_PFTaken+" != Summary tab "+S_PFTaken);
					}
				}
			}		
		}		
	}

	void validateProgramFundingNotTakenStud(String[] DemoPFTask, int[] DemoNotTaken, String[] SumPFTask, int[] SumNotTaken){
		for(int i=1;i<DemoPFTask.length;i++){
			String DemoTask = DemoPFTask[i];
			int D_PFNotTaken= DemoNotTaken[i];
			for(int j=1; j<SumPFTask.length;j++){
				String SumTask =SumPFTask[j];
				int S_PFNotTaken = SumNotTaken[j];
				if(DemoTask.equalsIgnoreCase(SumTask)){
					if(D_PFNotTaken==S_PFNotTaken){
//						System.out.println("Program Funding -> "+DemoTask+" not taken student: Demography tab "+D_PFNotTaken+" == Summary tab "+S_PFNotTaken);
					    reportsLog.info("Program Funding -> "+DemoTask+" not taken student: Demography tab "+D_PFNotTaken+" == Summary tab "+S_PFNotTaken);
					}else{
//						System.out.println("Program Funding -> "+DemoTask+" not taken student: Demography tab "+D_PFNotTaken+" != Summary tab "+S_PFNotTaken);
						debugLog.error("Program Funding -> "+DemoTask+" not taken student: Demography tab "+D_PFNotTaken+" != Summary tab "+S_PFNotTaken);
					}
				}
			}		
		}	
	}


	//verifying Gender task count... 
	void validateGender(String[] GenderOption, int[][] GenderRowValue1, String[] SumGender, int[][] SumGender_val)
	{	
		for(int i=1; i <GenderOption.length+1; i++){
			String DemoGenderTask = GenderOption[i-1];
			for(int j=1; j < SumGender.length+1; j++){	
				String SumGenderTask =SumGender[j-1];
				if(DemoGenderTask.equalsIgnoreCase(SumGenderTask)){
					if(Arrays.equals(GenderRowValue1[i-1], SumGender_val[j-1])){
//						System.out.println("Matched Demography gender task: "+DemoGenderTask+" and Summary gender task:  "+ SumGenderTask);
						reportsLog.info("Matched Demography gender task: "+DemoGenderTask+" and Summary gender task:  "+ SumGenderTask);
					}else{
//						System.out.println("Gender -> "+DemoGenderTask+" student count mismatch between Demography and Summary tab");
						debugLog.error("Gender -> "+DemoGenderTask+" student count mismatch between Demography and Summary tab");
					}
				}
			}
		}

	}

	void validateTakenGender(String[] GenderOption, int[] DemoGen_Taken, String[] SumGender, int[] SumGen_Taken)
	{	
		for(int i=1; i <GenderOption.length+1; i++){
			String DemoGenderTask = GenderOption[i-1];
			int DemoGen_TakenCount = DemoGen_Taken[i];
			for(int j=1; j < SumGender.length+1; j++){	
				String SumGenderTask =SumGender[j-1];
				int SumGen_TakenCount = SumGen_Taken[j];
				//System.out.println(DemoGen_TakenCount+"  "+SumGen_TakenCount);
				if(DemoGenderTask.equalsIgnoreCase(SumGenderTask)){
					if(DemoGen_TakenCount == SumGen_TakenCount){
//						System.out.println("gender task taken student count is matching for task: "+DemoGenderTask+" and "+SumGenderTask);
						reportsLog.info("gender task taken student count is matching for task: "+DemoGenderTask+" and "+SumGenderTask);
					}else{
//						System.out.println("Gender -> "+SumGenderTask+" taken student : Summary tab "+SumGen_TakenCount+" != Demography tab "+DemoGen_TakenCount);
						debugLog.error("Gender -> "+SumGenderTask+" taken student : Summary tab "+SumGen_TakenCount+" != Demography tab "+DemoGen_TakenCount);
					}
				}
			}
		}
	}

	void validateNotTakenGender(String[] GenderOption, int[] DemoNotTaken, String[] SumGender, int[] SumNotTaken)
	{
		for(int i=1; i <GenderOption.length+1; i++){
			String DemoGenderTask = GenderOption[i-1];
			int DemoGen_NotTakenCount = DemoNotTaken[i];
			for(int j=1; j < SumGender.length+1; j++){	
				String SumGenderTask =SumGender[j-1];
				int SumGen_NotTakenCount = SumNotTaken[j];
				if(DemoGenderTask.equalsIgnoreCase(SumGenderTask)){
					if(DemoGen_NotTakenCount == SumGen_NotTakenCount){
//						System.out.println("gender task not taken student count is matching for task: "+DemoGenderTask+" and "+SumGenderTask);
						reportsLog.info("gender task not taken student count is matching for task: "+DemoGenderTask+" and "+SumGenderTask);
					}else{
//						System.out.println("Gender -> "+DemoGenderTask+" Not taken : Summary tab "+SumGen_NotTakenCount+" != Demography tab "+DemoGen_NotTakenCount);
						debugLog.error("Gender -> "+DemoGenderTask+" Not taken : Summary tab "+SumGen_NotTakenCount+" != Demography tab "+DemoGen_NotTakenCount);
					}
				}
			}
		}		
	}

	void validateEthnicity(String[] DemoEthnOption, int[][] DemoEthnValue, String[] SumEthnOption, int[][] SumEthnValue){
		for(int i=0;i<DemoEthnOption.length; i++){
			String DemoEthTask = DemoEthnOption[i];
			for(int j=0;j<SumEthnOption.length;j++){
				String SumEthTask =SumEthnOption[j];
				if(DemoEthTask.equalsIgnoreCase(SumEthTask)){	
					if(Arrays.equals(DemoEthnValue[i], SumEthnValue[j])){
//						System.out.println("Matched Demography ethnicity task:  "+DemoEthTask+" and Summary ethnicity task: "+ SumEthTask);
						reportsLog.info("Matched Demography ethnicity task:  "+DemoEthTask+" and Summary ethnicity task: "+ SumEthTask);
					}else{
//						System.out.println("Ethnicity -> "+DemoEthTask+" student count mismatch between Demography and Summary tab");
						debugLog.error("Ethnicity -> "+DemoEthTask+" student count mismatch between Demography and Summary tab");
					}
				}
			}
		}
	}

	void validateEthnicityTakenStud(String[] DemoEthnOption, int[] DemoEthTaken, String[] SumEthnOption, int[] SumEthTaken){
		for(int i=0;i<DemoEthnOption.length; i++){
			String DemoEthTask = DemoEthnOption[i];
			int DemoEth_TakenCount = DemoEthTaken[i]; 
			for(int j=0;j<SumEthnOption.length;j++){
				String SumEthTask =SumEthnOption[j];
				int SumEth_TakenCount = SumEthTaken[j] ; 
				if(DemoEthTask.equalsIgnoreCase(SumEthTask)){	
					if(DemoEth_TakenCount == SumEth_TakenCount){
//						System.out.println("Ethnicity task taken student count is matching for task: "+DemoEthTask+" and "+SumEthTask);
						reportsLog.info("Ethnicity task taken student count is matching for task: "+DemoEthTask+" and "+SumEthTask);
					}else{					
//						System.out.println("Ethnicity -> "+SumEthTask+" taken student : Summary tab "+SumEth_TakenCount+" != Demography tab "+DemoEth_TakenCount);
						debugLog.error("Ethnicity -> "+DemoEthTask+" taken student : Summary tab "+SumEth_TakenCount+" != Demography tab "+DemoEth_TakenCount);
					}				
				}
			}
		}
	}
	
	void validateEthnicityNotTakenStud(String[] DemoEthnOption, int[] DemoEthNotTaken, String[] SumEthnOption, int[] SumEthNotTaken){
		for(int i=0;i<DemoEthnOption.length; i++){
			String DemoEthTask = DemoEthnOption[i];
			int DemoEth_NotTakenCount = DemoEthNotTaken[i];
			for(int j=0;j<SumEthnOption.length;j++){
				String SumEthTask =SumEthnOption[j];
				int SumEth_NotTakenCount = SumEthNotTaken[j];
//				System.out.println(SumEth_NotTakenCount);
				if(DemoEthTask.equalsIgnoreCase(SumEthTask)){	
					if(DemoEth_NotTakenCount == SumEth_NotTakenCount){
//						System.out.println("Ethnicity taken student count is matching for task: "+DemoEthTask+" and "+SumEthTask);
//						System.out.println("Ethnicity -> "+DemoEthTask+" not taken student : Summary tab"+SumEth_NotTakenCount+" == Demography tab "+DemoEth_NotTakenCount);
						reportsLog.info("Ethnicity taken student count is matching for task: "+DemoEthTask+" and "+SumEthTask);
					}else{
//						System.out.println("Ethnicity -> "+DemoEthTask+" not taken student : Summary tab "+SumEth_NotTakenCount+" != Demography tab "+DemoEth_NotTakenCount);
						debugLog.error("Ethnicity -> "+DemoEthTask+" not taken student : Summary tab "+SumEth_NotTakenCount+" != Demography tab "+DemoEth_NotTakenCount);
					}
				}
			}
		}
	}
}

