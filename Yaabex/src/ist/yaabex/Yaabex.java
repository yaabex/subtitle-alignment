package ist.yaabex;

import ist.yaabex.domain.Aligner;
import ist.yaabex.domain.SubtitlePair;
import ist.yaabex.io.ConfigLoader;
import ist.yaabex.io.SubtitleReader;
import ist.yaabex.tests.OutputTest;

public class Yaabex {
	public static void main(String[] args){
		SubtitleReader reader = new SubtitleReader();
		
		while(reader.hasMorePairs()){
			ConfigLoader config = new ConfigLoader();
			SubtitlePair pair = reader.readPair();
			
			//System.out.println(pair.getSource());
			
			Aligner aligner = new Aligner(pair, config);
			aligner.align();
			
			//System.out.println(aligner.toString());
			
			OutputTest test = new OutputTest(pair.getSourceName(), aligner);
			test.run();
		}
	}
}
