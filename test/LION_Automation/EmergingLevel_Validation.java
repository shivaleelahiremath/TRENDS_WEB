package LION_Automation;
import jxl.Sheet;

public class EmergingLevel_Validation {
	
/*	public void FF01_Task(String[] FFTaskData, String[] DetaillsData){
		for(int i=1; i<FFTaskData.length; i++){
			String excelData = FFTaskData[i];
//			System.out.println("excelData: "+excelData);
            for(int j=1; j<DetaillsData.length;j++){
            	String detaillsData = DetaillsData[j];
//    			System.out.println("detaillsData: "+detaillsData);
    			if(detaillsData.equalsIgnoreCase(excelData)){
    				System.out.println("data matching "+excelData+" " +detaillsData);
    			}else{
//    			    System.out.println("data not matching "+excelData+" "+ detaillsData);
    			}
            }			
		}		
	}
*/	
	//Validation of EMR FF01/Rhyming task..
	public void FF01_Task(Sheet sht, String[] fF01Data) {
        String[] FF01_Excel_Data = new String[9];
        String level = sht.getCell(0, 2).getContents();
        System.out.println("level -> "+level);
		for(int j=1; j<9; j++){
			String FFTask= sht.getCell(j, 2).getContents();
//			System.out.println("Excel sheet data: "+FFTask);
			FF01_Excel_Data[j] = FFTask;
//			System.out.println(FFTaskData[j]);
		}
		for(int i=1; i<FF01_Excel_Data.length; i++){
			String excel = FF01_Excel_Data[i];
//			System.out.println(excel);
			for(int j=1; j<fF01Data.length;j++){
	        	String detaillsData = fF01Data[j];
//				System.out.println("detaillsData: "+detaillsData);
				if(detaillsData.equalsIgnoreCase(excel)){
    				System.out.println(level+" : data matching "+excel+" " +detaillsData);
    			}else{
//    			    System.out.println("data not matching "+excelData+" "+ detaillsData);
    			}
		    }			
		}	
	}	
	
    //Validation of EMR FF03/Blending Onset-Rime task..
    public void FF03_Task(Sheet sht, String[] fF03Data){
    	String[] FF03_Excel_Data = new String[9];
        String level = sht.getCell(0, 4).getContents();
        System.out.println("level -> "+level);
		for(int j=1; j<9; j++){
			String FFTask= sht.getCell(j, 4).getContents();
//			System.out.println("Excel sheet data: "+FFTask);
			FF03_Excel_Data[j] = FFTask;
//			System.out.println("FF03_Excel_Data: "+FF03_Excel_Data[j]);
		}		
		for (int i=1; i<FF03_Excel_Data.length; i++){
			String excel = FF03_Excel_Data[i];
//			System.out.println(excel);
			for(int j=1; j<fF03Data.length;j++){
	        	String detaillsData = fF03Data[j];
//				System.out.println("FF03 detaillsData: "+detaillsData);
	        	if(detaillsData.equalsIgnoreCase(excel)){
    				System.out.println(level+" : data matching "+excel+" " +detaillsData);
    			}else{
//    			    System.out.println("data not matching "+excelData+" "+ detaillsData);
    			}
		    }
			
		}
		
    }	

}
