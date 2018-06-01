package Main_MasterFilter_Test;

import org.apache.log4j.Logger;

public class Validate_StudSumm_MasterFilter_Data {
	
	public static Logger debugLog=Logger.getLogger("Main_STAAR_ASMT");
	public static Logger reportsLog=Logger.getLogger("reportsLog");
	
	public void validate_StudSumm_MasterFilter(int[] Stud_MasterFilterTargetCount, int[] Sum_MasterFilterTargetCount, int[] Sum_FilterTargetCount , String[] MasterFilterTask){
		for(int i=0; i<Stud_MasterFilterTargetCount.length; i++){
			String filet = MasterFilterTask[i];
			System.out.println(filet.trim());

			if(Stud_MasterFilterTargetCount[i] ==Sum_MasterFilterTargetCount[i]){
				System.out.println(MasterFilterTask[i].trim()+" task target student count matching: " +Stud_MasterFilterTargetCount[i]+ "  "+Sum_MasterFilterTargetCount[i]);
//				debugLog.error(MasterFilterTask[i].trim()+" task target student count matching: " +Stud_MasterFilterTargetCount[i]+ "  "+Sum_MasterFilterTargetCount[i]);
			}else{
				System.out.println(MasterFilterTask[i].trim()+" task target student count not matching: " +Stud_MasterFilterTargetCount[i]+ "  "+Sum_MasterFilterTargetCount[i]);
//				debugLog.error(MasterFilterTask[i].trim()+" task target student count not matching: " +Stud_MasterFilterTargetCount[i]+ "  "+Sum_MasterFilterTargetCount[i]);
			}
			if(Stud_MasterFilterTargetCount[i] ==Sum_FilterTargetCount[i]){
				System.out.println(MasterFilterTask[i].trim()+" task1 target student count matching: " +Stud_MasterFilterTargetCount[i]+ "  "+Sum_FilterTargetCount[i]);
//				debugLog.error(MasterFilterTask[i].trim()+" task target student count matching: " +Stud_MasterFilterTargetCount[i]+ "  "+Sum_FilterTargetCount[i]);
			}else{
				System.out.println(MasterFilterTask[i].trim()+" task1 target student count not matching: " +Stud_MasterFilterTargetCount[i]+ "  "+Sum_FilterTargetCount[i]);
//				debugLog.error(MasterFilterTask[i].trim()+" task target student count not matching: " +Stud_MasterFilterTargetCount[i]+ "  "+Sum_FilterTargetCount[i]);
			}
		}		
	}

	
}
