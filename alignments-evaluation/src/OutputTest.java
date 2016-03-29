import java.util.ArrayList;

public class OutputTest {
	public static void main(String[] args){
		final boolean DEBUG = false;
		AlignmentsReader reader = new AlignmentsReader();
		
		System.out.printf("%-18s\tTP\tFP\tTN\tFN", "");
		//System.out.printf("\tP\tR\tFm");
		System.out.println("");
		for(String filename : reader.getFilesToTest()){
			int truePositive = 0;
			int falsePositive = 0;
			int trueNegative = 0;
			int falseNegative = 0;
			ArrayList<String> expected = reader.readExpected(filename);
			ArrayList<String> results = reader.readResults(filename);
			if(expected.isEmpty() || results.isEmpty())
				continue;
			
			/*for(String reference : expected){
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
					//System.out.println("FP: "+reference);
					notFound.add(reference);
					//falsePositive++;
				}
			}
			for(String reference : notFound){
				boolean fn = true;
				for(String output : results){
					if(output.contains(getSource(reference))){
						System.out.println("FP: "+reference);
						falsePositive++;
						fn = false;
						break;
					}
				}
				if(fn){
					System.out.println("FN: "+reference);
					falseNegative++;
				}
			}*/
			
			// if is found alignment
				// target is empty = TN
				// target is not empty = TP
			// if is not found alignment
				// source is found, but target is empty  = FN
				// source is found, but target not match = FP
			// source not found, BUT expected target is empty = TN
			// source not found, AND expected target is not empty = FN
			for(String reference : expected){
				boolean found = false;
				for(String output : results){
					if(output.contains(getSource(reference))){	// alignments are found
						if(reference.equals(output)){			// alignments match
							if(getTarget(reference).isEmpty())		// but N:0
								trueNegative++;
							else
								truePositive++;
						}else{									// alignments don't match
							if(getTarget(output).isEmpty()){			// but N:0
								if(DEBUG) System.out.println("FN: "+reference);
								falseNegative++;
							}else{
								if(DEBUG) System.out.println("FP: "+reference);
								falsePositive++;
							}
						}
						found = true;
						break;
					}					
				}
				if(!found)
					if(getTarget(reference).isEmpty()){
						if(DEBUG) System.out.println("TN: "+reference);
						trueNegative++;
					}else{
						if(DEBUG) System.out.println("FN: "+reference);
						falseNegative++;
					}
			}
				
			float precision	= (truePositive*100)/(float)(truePositive+falsePositive);
			float recall 	= (truePositive*100)/(float)(truePositive+falseNegative);
			float fm 		= 2*(precision*recall)/(precision+recall);

			
			System.out.printf("%-18s\t%2d\t%2d\t%2d\t%2d", filename.replaceAll("\\.txt", ""), truePositive, falsePositive, trueNegative, falseNegative);
			//System.out.println("\t" + (truePositive + falsePositive + trueNegative + falseNegative - expected.size()));
			//System.out.printf("\t%.01f\t%.01f\t%.01f",precision, recall, fm);
			System.out.println(DEBUG?"\n":"");
		}
		
	}
	
	private static String getSource(String alignment){
		return alignment.replaceAll("\\s*---.*", "");
	}
	
	private static String getTarget(String alignment){
		return alignment.replaceAll(".*---\\s+", "");
	}
}
