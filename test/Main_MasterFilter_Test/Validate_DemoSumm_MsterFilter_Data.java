package Main_MasterFilter_Test;

public class Validate_DemoSumm_MsterFilter_Data {
	
public void validate_DemoSumm_MasterFilter(String[] sumMasterFilter, String[] demoMasterFilter, String[] SumOption_Tasks, String[] DemoOption_Tasks, int[] sum_Taken, int[] demo_Taken){
	for(int i=0; i< sumMasterFilter.length; i++){
		String summasterfil= sumMasterFilter[i];
		System.out.println("sum master filter: "+summasterfil.trim());
		
//		for(int j=0; j<demoMasterFilter.length; j++){
			String demomasterfil = demoMasterFilter[i];
			System.out.println("demo master filter: "+demomasterfil.trim());
//				if(summasterfil.trim().equals(demomasterfil.trim())){
					System.out.println("before if statement..");
				for(int l=1; l<SumOption_Tasks.length; l++){
					System.out.println("entered for loop..");
					String sumOption = SumOption_Tasks[l];
					System.out.println("sumOption : "+sumOption.trim());
					int sumTaken = sum_Taken[l];
					System.out.println("sumTaken: "+sumTaken);	
					
					for(int m=1; m<DemoOption_Tasks.length; m++){

					String demoOption = DemoOption_Tasks[m];
					System.out.println("demoOption: "+demoOption.trim());
			     	int demoTaken = demo_Taken[m];
				    System.out.println("demoTaken: "+demoTaken);					
					    if(sumOption.equalsIgnoreCase(demoOption)){
					    	if(sumTaken==demoTaken){
						    	System.out.println(sumMasterFilter[i].trim()+" -> "+demoOption+" filter option taken student count matching.. "+sumTaken +" "+demoTaken);
					    	}else{
						    	System.out.println(sumMasterFilter[i].trim()+" -> "+demoOption+" filter option taken student count not matching.. "+sumTaken +" "+demoTaken);
					    }
					}	
				}
				}
//				}
//	 }
						
		
//		System.out.println("master filter: "+masterfil+ "sumOption: "+sumOption+"demoOption: "+demoOption+" demoTaken: "+demoTaken+" sumTaken: "+sumTaken);

	}
	
		
	}

}
