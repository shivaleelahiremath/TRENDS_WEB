package TPRI_TJL;

public class CompareData {

	//Comparing tier data between Assessment and Student tab.. 
	public void compareTierData(int[] assessmentTabTier, int[] studentTabTier)
	{	
		int j = 0;
		String[] tierName = {"Tier1", "Tier2", "Tier3"};
		for(int i=0; i<assessmentTabTier.length; i++)
		{
			//for(int j=0; j<studentTabTier.length; j++)
			while(j < studentTabTier.length)
			{
				if((assessmentTabTier[i]) == studentTabTier[j])
				{
					System.out.println(tierName[i] + " Mathed.." +assessmentTabTier[i]+" "+studentTabTier[j]);
				}
				else
				{
					System.out.println(tierName[i] + " data != b/w assessment and stuent tab "+assessmentTabTier[i]+" "+studentTabTier[j]);
				}
				j++;
				break;
			}
		}
	}

	//Comparing Group data between Assessment and Student tab..
	public void compareGroupData(int[] assessmentTabGroup, int[] studentTabGroup)
	{		
		int j=0;
		String[] groupName = {"Group A", "Group B", "Group C", "Group D"};
		for(int i=0; i<assessmentTabGroup.length; i++){
			//for(int j=0; j<studentTabGroup.length; j++){
			while(j < studentTabGroup.length)
			{
				if(assessmentTabGroup[i] == studentTabGroup[j]){
					System.out.println(groupName[i] +" Mathed.." +assessmentTabGroup[i]+" "+studentTabGroup[j]);
				}else{
					System.out.println(groupName[i] +" data != b/w assessment and stuent tab "+assessmentTabGroup[i]+" "+studentTabGroup[j]);
				}
				j++;
				break;
			}
		}
	}

    //Comparing target student count between assessment and student tab..
	public void compareTargetStudent(String studTotalTargetStud, String asmtTotalTargetStud) {

		if(studTotalTargetStud.equals(asmtTotalTargetStud)){
			System.out.println("target student count match."+studTotalTargetStud+" "+asmtTotalTargetStud);
		}else{
			System.out.println("target student count not match."+studTotalTargetStud+" "+asmtTotalTargetStud);

		}
	}
	
	//Comparing Tier, Group and GroupingFactor data between Student and SCR tab..
	public void compareScrData(String[] StudentTab, String[] ScrTab){
		int j=0;
		String[] DataName = {"Tier","Group","GroupingFactor"};
		for(int i=0; i<StudentTab.length; i++){
			while(j<ScrTab.length){
				if(StudentTab[i].equals(ScrTab[j])){
					System.out.println(DataName[i]+ " Matched b/w student and SCR tab " +StudentTab[i]+ " "+ScrTab[j]);
				}else{
					System.out.println(DataName[i]+ " data != b/w student and SCR tab " +StudentTab[i]+ " "+ScrTab[j]);
				}
				j++;
				break;
			}
		}
	}

	//Comparing Tier, Group and GroupingFactor data between Student and PA tab..
	public void comparePAData(String[] StudentTab, String[] PATab){
		int j=0;
		String[] DataName = {"Tier","Group","GroupingFactor"};
		for(int i=0; i<StudentTab.length; i++){
			while(j<PATab.length){
				if(StudentTab[i].equals(PATab[j])){
					System.out.println(DataName[i]+ " Matched b/w student and PA tab " +StudentTab[i]+ " "+PATab[j]);
				}else{
					System.out.println(DataName[i]+ " data != b/w student and PA tab " +StudentTab[i]+ " "+PATab[j]);
				}
				j++;
				break;
			}
		}
	}
	
	//Comparing Tier, Group and GroupingFactor data between Student and GK tab..
		public void compareGKData(String[] StudentTab, String[] GKTab){
			int j=0;
			String[] DataName = {"Tier","Group","GroupingFactor"};
			for(int i=0; i<StudentTab.length; i++){
				while(j<GKTab.length){
					if(StudentTab[i].equals(GKTab[j])){
						System.out.println(DataName[i]+ " Matched b/w student and GK tab " +StudentTab[i]+ " "+GKTab[j]);
					}else{
						System.out.println(DataName[i]+ " data != b/w student and GK tab " +StudentTab[i]+ " "+GKTab[j]);
					}
					j++;
					break;
				}
			}
		}
		
		
		//Comparing Tier, Group and GroupingFactor data between Student and WR tab..
				public void compareWRData(String[] StudentTab, String[] WRTab){
					int j=0;
					String[] DataName = {"Tier","Group","GroupingFactor"};
					for(int i=0; i<StudentTab.length; i++){
						while(j<WRTab.length){
							if(StudentTab[i].equals(WRTab[j])){
								System.out.println(DataName[i]+ " Matched b/w student and WR tab" +StudentTab[i]+ " "+WRTab[j]);
							}else{
								System.out.println(DataName[i]+ " data != b/w student and WR tab " +StudentTab[i]+ " "+WRTab[j]);
							}
							j++;
							break;
						}
					}
				}
				
				//Comparing Tier, Group and GroupingFactor data between Student and READ tab..
				public void compareReadData(String[] StudentTab, String[] ReadTab){
					int j=0;
					String[] DataName = {"Tier","Group","GroupingFactor"};
					for(int i=0; i<StudentTab.length; i++){
						while(j<ReadTab.length){
							if(StudentTab[i].equals(ReadTab[j])){
								System.out.println(DataName[i]+ " Matched b/w student and READ tab" +StudentTab[i]+ " "+ReadTab[j]);
							}else{
								System.out.println(DataName[i]+ " data != b/w student and READ tab " +StudentTab[i]+ " "+ReadTab[j]);
							}
							j++;
							break;
						}
					}
				}
				
				//Comparing Tier, Group and GroupingFactor data between Student and COMP tab..
				public void compareCompData(String[] StudentTab, String[] CompTab){
					int j=0;
					String[] DataName = {"Tier","Group","GroupingFactor"};
					for(int i=0; i<StudentTab.length; i++){
						while(j<CompTab.length){
							if(StudentTab[i].equals(CompTab[j])){
								System.out.println(DataName[i]+ " Matched b/w student and COMP tab" +StudentTab[i]+ " "+CompTab[j]);
							}else{
								System.out.println(DataName[i]+ " data != b/w student and COMP tab " +StudentTab[i]+ " "+CompTab[j]);
							}
							j++;
							break;
						}
					}
				}

				


}
