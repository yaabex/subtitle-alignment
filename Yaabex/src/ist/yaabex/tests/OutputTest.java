package ist.yaabex.tests;

import ist.yaabex.domain.Aligner;
import ist.yaabex.domain.Match;

import java.util.ArrayList;

public class OutputTest {
	private int passed = 0;
	private int failed = 0;
	private final String subtitleName;
	private ArrayList<Match> alignements;
	
	public OutputTest(final String subtitleName, Aligner aligner){
		this.subtitleName = subtitleName.replaceAll("-.*", "");
		this.alignements = aligner.getAlignments();
	}
	
	public void run(){
		ExpectedOutput eo = new ExpectedOutput(subtitleName);
		
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
		System.out.println(subtitleName+" | Passed: " + passed + " | Failed: " + failed + " | "+ (int)(passed*100/(float)(passed+failed)) + "%");
	}
}
