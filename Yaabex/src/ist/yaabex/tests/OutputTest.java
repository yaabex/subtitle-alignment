package ist.yaabex.tests;

import java.util.ArrayList;


public class OutputTest {	
	public void run(){
		/*AlignmentsLoader eo = new AlignmentsLoader(subtitleName);
		
		for(String str : eo.getExpected()){
			boolean pass = false;
			for(Match m : alignements)
				if(m.toString().equals(str)){
					pass = true;
					passed++;
					break;
				}
			if(!pass){
				System.err.println("WRONG: \""+str+"\"");
				failed++;
			}
		}
		System.out.println(subtitleName+" | Passed: " + passed + " | Failed: " + failed + " | "+ (int)(passed*100/(float)(passed+failed)) + "%");*/
	}
	
	public static void main(String[] args){
		AlignmentsReader reader = new AlignmentsReader();
		for(String filename : reader.getFilesToTest()){
			int passed = 0;
			int failed = 0;
			ArrayList<String> expected = reader.readExpected(filename);
			ArrayList<String> results = reader.readResults(filename);
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
					failed++;
				}		
			}
			System.out.println(filename+" | Passed: " + passed + " | Failed: " + failed + " | "+ (int)(passed*100/(float)(passed+failed)) + "%\n");
		}
	}
}
