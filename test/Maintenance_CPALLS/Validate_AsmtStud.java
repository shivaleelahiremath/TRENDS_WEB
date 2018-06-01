package Maintenance_CPALLS;

import org.apache.log4j.Logger;

public class Validate_AsmtStud {
	
	String[] value = {"MAP", "NMA"};
	public static Logger debugLog=Logger.getLogger("CPALLS_WEB");
	public static Logger reportsLog=Logger.getLogger("reportsLog");
		
	void verify_TargetStudCount(String Asmt_TargetCount, String Stud_TargetCount){
         if(Asmt_TargetCount.equals(Stud_TargetCount)){
        	 reportsLog.info("Target Student count matching b/w assessment "+Asmt_TargetCount+" and student "+Stud_TargetCount);
//        	 System.out.println("Target Student count matching b/w assessment "+Asmt_TargetCount+" and student "+Stud_TargetCount);    	 
         }else{
        	 debugLog.error("Target Student count != b/w assessment "+Asmt_TargetCount+" and student "+Stud_TargetCount);
//        	 System.out.println("Target Student count != b/w assessment "+Asmt_TargetCount+" and student "+Stud_TargetCount);    	 
         }	
	}
	
	void verify_PhonoAwareness(String[] Asmt_PhonoAware, String[] Stud_PhonoAware){	
		for (int i=0; i<Asmt_PhonoAware.length; i++){
//			String[] asmtdata=Asmt_PhonoAware;
//			String[] studdata = Stud_PhonoAware;
//			System.out.println(asmtdata[i]+"  "+studdata[i]);
			if(Asmt_PhonoAware[i].equals(Stud_PhonoAware[i])){
				reportsLog.info("Phonological awareness "+value[i]+" matching b/w student "+Stud_PhonoAware[i]+" and assessment tab "+Asmt_PhonoAware[i]);
//				System.out.println("Phonological awareness "+value[i]+" matching b/w student "+Stud_PhonoAware[i]+" and assessment tab "+Asmt_PhonoAware[i]);	
			}else{
				debugLog.error("Phonological awareness "+value[i]+" != b/w student "+Stud_PhonoAware[i]+" and assessment tab "+Asmt_PhonoAware[i]);
//				System.out.println("Phonological awareness "+value[i]+" != b/w student "+Stud_PhonoAware[i]+" and assessment tab "+Asmt_PhonoAware[i]);	
			}			
		}
	}
	
	void verify_RapidLetterNaming(String[] Asmt_RapidLetter, String[] Stud_RapidLetter){
		for(int i=0; i<Asmt_RapidLetter.length; i++){
//			String[] asmtdata=Asmt_RapidLetter;
//			String[] studdata = Stud_RapidLetter;
//			System.out.println(asmtdata[i]+"  "+studdata[i]);
			if(Asmt_RapidLetter[i].equals(Stud_RapidLetter[i])){
				reportsLog.info("Rapid Letter Naming "+value[i]+" matching b/w student "+Stud_RapidLetter[i]+" and assessment tab "+Asmt_RapidLetter[i]);
//				System.out.println("Rapid Letter Naming "+value[i]+" matching b/w student "+Stud_RapidLetter[i]+" and assessment tab "+Asmt_RapidLetter[i]);	
			}else{
				debugLog.error("Rapid Letter Naming "+value[i]+" != b/w student "+Stud_RapidLetter[i]+" and assessment tab "+Asmt_RapidLetter[i]);
//				System.out.println("Rapid Letter Naming "+value[i]+" != b/w student "+Stud_RapidLetter[i]+" and assessment tab "+Asmt_RapidLetter[i]);	
			}
		}
	}
	
	void verify_RapidVocabNaming(String[] Asmt_RapidVocabNaming, String[] Stud_RapidVocabNaming){
		for(int i=0; i<Asmt_RapidVocabNaming.length; i++){
//			String[] asmtdata=Asmt_RapidVocabNaming;
//	    	String[] studdata = Stud_RapidVocabNaming;
//			System.out.println(asmtdata[i]+"  "+studdata[i]);
			if(Asmt_RapidVocabNaming[i].equals(Stud_RapidVocabNaming[i])){
				reportsLog.info("Rapid Vocabulary Naming "+value[i]+" matching b/w student "+Stud_RapidVocabNaming[i]+" and assessment tab "+Asmt_RapidVocabNaming[i]);
//				System.out.println("Rapid Vocabulary Naming "+value[i]+" matching b/w student "+Stud_RapidVocabNaming[i]+" and assessment tab "+Asmt_RapidVocabNaming[i]);	
			}else{
				debugLog.error("Rapid Vocabulary Naming "+value[i]+" != b/w student "+Stud_RapidVocabNaming[i]+" and assessment tab "+Asmt_RapidVocabNaming[i]);
//				System.out.println("Rapid Vocabulary Naming "+value[i]+" != b/w student "+Stud_RapidVocabNaming[i]+" and assessment tab "+Asmt_RapidVocabNaming[i]);	
			}
	    }
	}
	
	void verify_MathOverall(String[] Asmt_MathOverall, String[] Stud_MathOverall){
		for(int i=0;i<Asmt_MathOverall.length;i++){
//			String[] asmtdata=Asmt_MathOverall;
//	    	String[] studdata = Stud_MathOverall;
//			System.out.println(asmtdata[i]+"  "+studdata[i]);
			if(Asmt_MathOverall[i].equals(Stud_MathOverall[i])){
				reportsLog.info("Math Overall "+value[i]+" matching b/w student "+Stud_MathOverall[i]+" and assessment tab "+Asmt_MathOverall[i]);
//				System.out.println("Math Overall "+value[i]+" matching b/w student "+Stud_MathOverall[i]+" and assessment tab "+Asmt_MathOverall[i]);	
			}else{
				debugLog.error("Math Overall "+value[i]+" != b/w student "+Stud_MathOverall[i]+" and assessment tab "+Asmt_MathOverall[i]);
//				System.out.println("Math Overall "+value[i]+" != b/w student "+Stud_MathOverall[i]+" and assessment tab "+Asmt_MathOverall[i]);	
			}
		}
	}
	
	void vereify_ScienceOverall(String[] Asmt_ScienceOverall, String[] Stud_ScienceOverall){
		for(int i=0; i<Asmt_ScienceOverall.length;i++){
//			String[] asmtdata=Asmt_ScienceOverall;
//	    	String[] studdata = StudScienceOverall;
//			System.out.println(asmtdata[i]+"  "+studdata[i]);
			if(Asmt_ScienceOverall[i].equals(Stud_ScienceOverall[i])){
				reportsLog.info("Science Overall "+value[i]+" matching b/w student "+Stud_ScienceOverall[i]+" and assessment tab "+Asmt_ScienceOverall[i]);
//				System.out.println("Science Overall "+value[i]+" matching b/w student "+Stud_ScienceOverall[i]+" and assessment tab "+Asmt_ScienceOverall[i]);	
			}else{
				debugLog.error("Science Overall "+value[i]+" != b/w student "+Stud_ScienceOverall[i]+" and assessment tab "+Asmt_ScienceOverall[i]);
//				System.out.println("Science Overall "+value[i]+" != b/w student "+Stud_ScienceOverall[i]+" and assessment tab "+Asmt_ScienceOverall[i]);	
			}
		}
	}
}
