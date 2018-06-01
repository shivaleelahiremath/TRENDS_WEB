package Main_MasterFilter_Test;

import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class test {

	public static void main(String[] args){
		 // create multimap to store key and values
        Multimap<String, String> multiMap = ArrayListMultimap.create();
 
        // put values into map for A
        multiMap.put("A", "Apple");
        multiMap.put("A", "Aeroplane");
 
        // put values into map for B
        multiMap.put("B", "Bat");
        multiMap.put("B", "Banana");
 
        // put values into map for C
        multiMap.put("C", "Cat");
        multiMap.put("C", "Car");
 
        // retrieve and display values
        System.out.println("Fetching Keys and corresponding [Multiple] Values \n");
 
        // get all the set of keys
        Set<String> keys = multiMap.keySet();
 
        // iterate through the key set and display key and values
        for (String key : keys) {
            System.out.println("Key = " + key);
            System.out.println("Values = " + multiMap.get(key) + "\n");
        }
	}
}
