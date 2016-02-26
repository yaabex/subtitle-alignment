package ist.yaabex.tests;

import java.util.ArrayList;


public class OutputTest {	
	public static void main(String[] args){
		AlignmentsReader reader = new AlignmentsReader();
		for(String filename : reader.getFilesToTest()){
			int passed = 0;
			int failed = 0;
			int notFound = 0;
			ArrayList<String> expected = reader.readExpected(filename);
			ArrayList<String> results = reader.readResults(filename);
			ArrayList<String> failedList = new ArrayList<String>();
			if(expected.isEmpty() || results.isEmpty())
				continue;
			for(String str : expected){
				boolean pass = false;
				for(String m : results)
					if(m.equals(str)){
						pass = true;
						passed++;
						break;
					}
				if(!pass){
					System.err.println("WRONG: \""+str+"\"");
					failedList.add(str.replaceAll("\\s*---.*$", ""));
					failed++;
				}		
			}
			for(String str : failedList)
				for(String m : results)
					if(m.contains(str) && !m.matches(".*--- .+"))
						notFound++;
			System.out.println(filename+" | Passed: " + passed + " | Failed: " + failed + " | "+ (int)(passed*100/(float)(passed+failed)) + "%");
			System.out.println("\tNot Found: "+ notFound+"\n");
		}
	}
}
