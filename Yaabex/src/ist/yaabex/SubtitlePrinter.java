package ist.yaabex;

import ist.yaabex.domain.SubtitlePair;
import ist.yaabex.io.SubtitleReader;

public class SubtitlePrinter {
	public static void main(String[] args){
		SubtitleReader reader = new SubtitleReader();
		
		while(reader.hasMorePairs()){
			SubtitlePair pair = reader.readPair();
			
			System.out.println(pair.getSource());
		}
	}
}
