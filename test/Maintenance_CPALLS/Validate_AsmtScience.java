package Maintenance_CPALLS;

import org.apache.log4j.Logger;

public class Validate_AsmtScience {

	String[] value = {"MAP", "NMA"};

	public static Logger debugLog=Logger.getLogger("Main_STAAR_ASMT");
	public static Logger reportsLog=Logger.getLogger("reportsLog");

	void vereify_ScienceOverall(String[] Asmt_ScienceOverall, String[] Science_ScienceOverall){
		for(int i=0; i<Asmt_ScienceOverall.length;i++){
//			String[] asmtdata=Asmt_ScienceOverall;
//	    	String[] studdata = StudScienceOverall;
//			System.out.println(asmtdata[i]+"  "+studdata[i]);
			if(Asmt_ScienceOverall[i].equals(Science_ScienceOverall[i])){
				reportsLog.info("Science Overall "+value[i]+" matching b/w science "+Science_ScienceOverall[i]+" and assessment tab "+Asmt_ScienceOverall[i]);
//				System.out.println("Science Overall "+value[i]+" matching b/w science "+Science_ScienceOverall[i]+" and assessment tab "+Asmt_ScienceOverall[i]);	
			}else{
				debugLog.error("Science Overall "+value[i]+" != b/w science "+Science_ScienceOverall[i]+" and assessment tab "+Asmt_ScienceOverall[i]);
//				System.out.println("Science Overall "+value[i]+" != b/w science "+Science_ScienceOverall[i]+" and assessment tab "+Asmt_ScienceOverall[i]);	
			}
		}
	}

}
