import java.util.ArrayList;

public class OutputTest {	
	public static void main(String[] args){
		AlignmentsReader reader = new AlignmentsReader();
		for(String filename : reader.getFilesToTest()){
			int truePositive = 0;
			int falsePositive = 0;
			int trueNegative = 0;
			int falseNegative = 0;
			ArrayList<String> expected = reader.readExpected(filename);
			ArrayList<String> results = reader.readResults(filename);
			if(expected.isEmpty() || results.isEmpty())
				continue;
			/*for(String str : expected){
				boolean pass = false;
				for(String m : results)
					if(m.equals(str)){
						pass = true;
						truePositive++;
						break;
					}
				if(!pass){
					System.err.println("WRONG: \""+str+"\"");
					failedList.add(str.replaceAll("\\s*---.*$", ""));
					falsePositive++;
				}		
			}
			for(String str : failedList)
				for(String m : results)
					if(m.contains(str) && !m.matches(".*--- .+"))
						falseNegative++;
			*/
			for(String reference : expected){
				boolean exists = false;
				for(String output : results){
					if(getSource(reference).equals(getSource(output))){
						exists = true;
						if(getTarget(output).isEmpty())
							if(reference.equals(output))
								trueNegative++;
							else{
								System.out.println("FN: "+reference);
								//System.out.println("<< "+output);
								falseNegative++;
							}
						else
							if(reference.equals(output))
								truePositive++;
							else{
								System.out.println("FP: "+reference);
								//System.out.println("< "+output);
								falsePositive++;
							}
						break;
					}
				}
				if(!exists){
					System.out.println("FP: "+reference);
					falsePositive++;
				}
			}
			float precision	= (truePositive*100)/(float)(truePositive+falsePositive);
			float recall 	= (truePositive*100)/(float)(truePositive+falseNegative);
			float fm 		= 2*(precision*recall)/(precision+recall); 
			System.out.printf("%s | TP: %d | FP: %d | TN: %d | FN: %d | P=%.01f | R=%.01f | Fm=%.01f\n\n\n", filename, truePositive, falsePositive, trueNegative, falseNegative, precision, recall, fm);
		}
		
	}
	
	private static String getSource(String alignment){
		return alignment.replaceAll("\\s*---.*", "");
	}
	
	private static String getTarget(String alignment){
		return alignment.replaceAll(".*---\\s+", "");
	}
}
