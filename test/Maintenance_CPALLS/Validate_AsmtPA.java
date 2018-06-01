package Maintenance_CPALLS;

import org.apache.log4j.Logger;

public class Validate_AsmtPA {

	String[] value = {"MAP", "NMA"};
	public static Logger debugLog=Logger.getLogger("Main_STAAR_ASMT");
	public static Logger reportsLog=Logger.getLogger("reportsLog");
	
	
	void verify_PhonoAwareness(String[] Asmt_PhonoAware, String[] PA_PhonoAware){	
		for (int i=0; i<Asmt_PhonoAware.length; i++){
//			String[] asmtdata=Asmt_PhonoAware;
//			String[] studdata = PA_PhonoAware;
//			System.out.println(asmtdata[i]+"  "+studdata[i]);
			if(Asmt_PhonoAware[i].equals(PA_PhonoAware[i])){
				reportsLog.info("Phonological awareness "+value[i]+" matching b/w PA "+PA_PhonoAware[i]+" and assessment tab "+Asmt_PhonoAware[i]);
//				System.out.println("Phonological awareness "+value[i]+" matching b/w PA "+PA_PhonoAware[i]+" and assessment tab "+Asmt_PhonoAware[i]);	
			}else{
				debugLog.error("Phonological awareness "+value[i]+" != b/w PA "+PA_PhonoAware[i]+" and assessment tab "+Asmt_PhonoAware[i]);
//				System.out.println("Phonological awareness "+value[i]+" != b/w PA "+PA_PhonoAware[i]+" and assessment tab "+Asmt_PhonoAware[i]);	
			}			
		}
	}
	
	void verify_RapidLetterNaming(String[] Asmt_RapidLetter, String[] PA_RapidLetter){
		for(int i=0; i<Asmt_RapidLetter.length; i++){
//			String[] asmtdata=Asmt_RapidLetter;
//			String[] studdata = PA_RapidLetter;
//			System.out.println(asmtdata[i]+"  "+studdata[i]);
			if(Asmt_RapidLetter[i].equals(PA_RapidLetter[i])){
				reportsLog.info("Rapid Letter Naming "+value[i]+" matching b/w PA "+PA_RapidLetter[i]+" and assessment tab "+Asmt_RapidLetter[i]);
//				System.out.println("Rapid Letter Naming "+value[i]+" matching b/w PA "+PA_RapidLetter[i]+" and assessment tab "+Asmt_RapidLetter[i]);	
			}else{
				debugLog.error("Rapid Letter Naming "+value[i]+" != b/w PA "+PA_RapidLetter[i]+" and assessment tab "+Asmt_RapidLetter[i]);
//				System.out.println("Rapid Letter Naming "+value[i]+" != b/w PA "+PA_RapidLetter[i]+" and assessment tab "+Asmt_RapidLetter[i]);	
			}
		}
	}
	
	void verify_RapidVocabNaming(String[] Asmt_RapidVocabNaming, String[] PA_RapidVocabNaming){
		for(int i=0; i<Asmt_RapidVocabNaming.length; i++){
//			String[] asmtdata=Asmt_RapidVocabNaming;
//	    	String[] studdata = PA_RapidVocabNaming;
//			System.out.println(asmtdata[i]+"  "+studdata[i]);
			if(Asmt_RapidVocabNaming[i].equals(PA_RapidVocabNaming[i])){
				reportsLog.info("Rapid Vocabulary Naming "+value[i]+" matching b/w PA "+PA_RapidVocabNaming[i]+" and assessment tab "+Asmt_RapidVocabNaming[i]);
//				System.out.println("Rapid Vocabulary Naming "+value[i]+" matching b/w PA "+PA_RapidVocabNaming[i]+" and assessment tab "+Asmt_RapidVocabNaming[i]);	
			}else{
				debugLog.error("Rapid Vocabulary Naming "+value[i]+" != b/w PA "+PA_RapidVocabNaming[i]+" and assessment tab "+Asmt_RapidVocabNaming[i]);
//				System.out.println("Rapid Vocabulary Naming "+value[i]+" != b/w PA "+PA_RapidVocabNaming[i]+" and assessment tab "+Asmt_RapidVocabNaming[i]);	
			}
	    }
	}
	
}
