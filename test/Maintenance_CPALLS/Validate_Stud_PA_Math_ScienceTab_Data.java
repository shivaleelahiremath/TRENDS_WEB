package Maintenance_CPALLS;

import org.apache.log4j.Logger;

public class Validate_Stud_PA_Math_ScienceTab_Data {

    String[] value = {"MAP", "NMA"};
    public static Logger debugLog=Logger.getLogger("Main_STAAR_ASMT");
	public static Logger reportsLog=Logger.getLogger("reportsLog");
	
	void verify_PhonoAwareness(String[] Stud_PhonoAware, String[] PA_PhonoAware){	
		for (int i=0; i<Stud_PhonoAware.length; i++){
//			String[] asmtdata=Asmt_PhonoAware;
//			String[] studdata = Stud_PhonoAware;
//			System.out.println(asmtdata[i]+"  "+studdata[i]);
			if(Stud_PhonoAware[i].equals(PA_PhonoAware[i])){
				reportsLog.info("Phonological awareness "+value[i]+" matching b/w student "+Stud_PhonoAware[i]+" and PA tab "+PA_PhonoAware[i]);
//				System.out.println("Phonological awareness "+value[i]+" matching b/w student "+Stud_PhonoAware[i]+" and PA tab "+PA_PhonoAware[i]);	
			}else{
				debugLog.error("Phonological awareness "+value[i]+" != b/w student "+Stud_PhonoAware[i]+" and PA tab "+PA_PhonoAware[i]);
//				System.out.println("Phonological awareness "+value[i]+" != b/w student "+Stud_PhonoAware[i]+" and PA tab "+PA_PhonoAware[i]);	
			}			
		}
	}
	
	void verify_RapidLetterNaming(String[] Stud_RapidLetter, String[] PA_RapidLetter){
		for(int i=0; i<Stud_RapidLetter.length; i++){
//			String[] asmtdata=Asmt_RapidLetter;
//			String[] studdata = Stud_RapidLetter;
//			System.out.println(asmtdata[i]+"  "+studdata[i]);
			if(Stud_RapidLetter[i].equals(PA_RapidLetter[i])){
				reportsLog.info("Rapid Letter Naming "+value[i]+" matching b/w student "+Stud_RapidLetter[i]+" and PA tab "+PA_RapidLetter[i]);
//				System.out.println("Rapid Letter Naming "+value[i]+" matching b/w student "+Stud_RapidLetter[i]+" and PA tab "+PA_RapidLetter[i]);	
			}else{
				debugLog.error("Rapid Letter Naming "+value[i]+" != b/w student "+Stud_RapidLetter[i]+" and PA tab "+PA_RapidLetter[i]);
//				System.out.println("Rapid Letter Naming "+value[i]+" != b/w student "+Stud_RapidLetter[i]+" and PA tab "+PA_RapidLetter[i]);	
			}
		}
	}
	
	void verify_RapidVocabNaming(String[] Stud_RapidVocabNaming, String[] PA_RapidVocabNaming){
		for(int i=0; i<Stud_RapidVocabNaming.length; i++){
//			String[] asmtdata=Asmt_RapidVocabNaming;
//	    	String[] studdata = Stud_RapidVocabNaming;
//			System.out.println(asmtdata[i]+"  "+studdata[i]);
			if(Stud_RapidVocabNaming[i].equals(PA_RapidVocabNaming[i])){
				reportsLog.info("Rapid Vocabulary Naming "+value[i]+" matching b/w student "+Stud_RapidVocabNaming[i]+" and PA tab "+PA_RapidVocabNaming[i]);
//				System.out.println("Rapid Vocabulary Naming "+value[i]+" matching b/w student "+Stud_RapidVocabNaming[i]+" and PA tab "+PA_RapidVocabNaming[i]);	
			}else{
				debugLog.error("Rapid Vocabulary Naming "+value[i]+" != b/w student "+Stud_RapidVocabNaming[i]+" and PA tab "+PA_RapidVocabNaming[i]);
//				System.out.println("Rapid Vocabulary Naming "+value[i]+" != b/w student "+Stud_RapidVocabNaming[i]+" and PA tab "+PA_RapidVocabNaming[i]);	
			}
	    }
	}
	
	void verify_MathOverall(String[] Stud_MathOverall, String[] Math_MathOverall){
		for(int i=0;i<Stud_MathOverall.length;i++){
//			String[] asmtdata=Asmt_MathOverall;
//	    	String[] studdata = Stud_MathOverall;
//			System.out.println(asmtdata[i]+"  "+studdata[i]);
			if(Stud_MathOverall[i].equals(Math_MathOverall[i])){
				reportsLog.info("Math Overall "+value[i]+" matching b/w student "+Stud_MathOverall[i]+" and Math tab "+Math_MathOverall[i]);
//				System.out.println("Math Overall "+value[i]+" matching b/w student "+Stud_MathOverall[i]+" and Math tab "+Math_MathOverall[i]);	
			}else{
				debugLog.error("Math Overall "+value[i]+" != b/w student "+Stud_MathOverall[i]+" and Math tab "+Math_MathOverall[i]);
//				System.out.println("Math Overall "+value[i]+" != b/w student "+Stud_MathOverall[i]+" and Math tab "+Math_MathOverall[i]);	
			}
		}
	}
	
	void vereify_ScienceOverall(String[] Stud_ScienceOverall, String[] Science_ScienceOverall){
		for(int i=0; i<Stud_ScienceOverall.length;i++){
//			String[] asmtdata=Asmt_ScienceOverall;
//	    	String[] studdata = StudScienceOverall;
//			System.out.println(asmtdata[i]+"  "+studdata[i]);
			if(Stud_ScienceOverall[i].equals(Science_ScienceOverall[i])){
				reportsLog.info("Science Overall "+value[i]+" matching b/w student "+Stud_ScienceOverall[i]+" and Science tab "+Science_ScienceOverall[i]);
//				System.out.println("Science Overall "+value[i]+" matching b/w student "+Stud_ScienceOverall[i]+" and Science tab "+Science_ScienceOverall[i]);	
			}else{
				debugLog.error("Science Overall "+value[i]+" != b/w student "+Stud_ScienceOverall[i]+" and Science tab "+Science_ScienceOverall[i]);
//				System.out.println("Science Overall "+value[i]+" != b/w student "+Stud_ScienceOverall[i]+" and Science tab "+Science_ScienceOverall[i]);	
			}
		}
	}
}
