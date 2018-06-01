package Maintenance_CPALLS;

import org.apache.log4j.Logger;

public class Validate_AsmtMath {
	
	String[] value = {"MAP", "NMA"};
	public static Logger debugLog=Logger.getLogger("Main_STAAR_ASMT");
	public static Logger reportsLog=Logger.getLogger("reportsLog");
	
	void verify_MathOverall(String[] Asmt_MathOverall, String[] Math_MathOverall){
		for(int i=0;i<Asmt_MathOverall.length;i++){
//			String[] asmtdata=Asmt_MathOverall;
//	    	String[] studdata = Stud_MathOverall;
//			System.out.println(asmtdata[i]+"  "+studdata[i]);
			if(Asmt_MathOverall[i].equals(Math_MathOverall[i])){
				reportsLog.info("Math Overall "+value[i]+" matching b/w mathematics "+Math_MathOverall[i]+" and assessment tab "+Asmt_MathOverall[i]);
//				System.out.println("Math Overall "+value[i]+" matching b/w mathematics "+Math_MathOverall[i]+" and assessment tab "+Asmt_MathOverall[i]);	
			}else{
				debugLog.error("Math Overall "+value[i]+" != b/w mathematics "+Math_MathOverall[i]+" and assessment tab "+Asmt_MathOverall[i]);
//				System.out.println("Math Overall "+value[i]+" != b/w mathematics "+Math_MathOverall[i]+" and assessment tab "+Asmt_MathOverall[i]);	
			}
		}
	}
}

