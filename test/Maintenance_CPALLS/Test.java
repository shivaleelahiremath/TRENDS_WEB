package Maintenance_CPALLS;

public class Test {
	
	public static void main(String [] args){
		
		String[] value = {"GKG","G01", "G03"};
		for (int i=0 ; i<value.length; i++){
			String data = value[i];
			System.out.println(data);
			cal(data);
		}
		
		
		
		
		/*
		String text = "shivaleela S Hiremath     ...";
		System.out.println("1st method: "+text.replaceAll(" ", ""));
		System.out.println("2nd method: "+text.replaceAll("\\s", ""));
		System.out.println("3nd method: "+text.replaceAll("\\s+", ""));
		
		
		for(int i=1 ; i<=15 ;i++){
//			System.out.println(i);
			if(i % 3 == 0 && i % 5 == 0){
		    	System.out.println("BuzzFuzz");
		    }else
			if(i%3 == 0){
				System.out.println("Buzz");
		    }else
		    if(i%5 == 0){
		    	System.out.println("Fuzz");
	        }else 	        
		    {
		    	System.out.println(i);
		    }
		}
		
		//Fibonacci serioes..
		int n1=0, n2=1 , n3, count=10;
		System.out.print("fibonacci serious: "+n1+" "+ n2);
		for(int i =2; i<count; i++){
			n3= n1+n2;
			System.out.print(" "+n3);
			n1=n2;
			n2=n3;
		}

		//factorial program..
		int fact = 1, num= 5;
		for (int i=1; i<=num ; i++){
			fact = fact *1;
			System.out.println("Factorial of "+num+" is : "+fact);
			
		}
		
		
		//Prime number
		System.out.println("prime no. program...");
		
		int x = 7;
		for(int j = 2; j<=x;j++){
			if(x%j==0){
				System.out.println(x+" It's not prime number..");
			}else{
				System.out.println(x+" prime number..");
			}
			
		}
		
		
*/		
	}

	private static void cal(String data) {
		// TODO Auto-generated method stub
		switch (data){
		
		case "GKG" : System.out.println("switch case 0"); 
					for (int i=0 ; i<2 ;i++){
						
						switch(i){
						case 0 : System.out.println("entered into case 0"); break;
						case 1 : System.out.println("entered into case 1"); break;
						}
					}					
					break;
		
		case "G01" : System.out.println("switch case 1"); break;
		
		case "etc" : System.out.println("case 5"); break;
		
		default: System.out.println("data not found");
		
		}
		
	}

	
	
	

}
