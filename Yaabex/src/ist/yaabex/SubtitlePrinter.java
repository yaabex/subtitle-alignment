package ist.yaabex;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import ist.yaabex.domain.SubtitlePair;
import ist.yaabex.io.SubtitleReader;

public class SubtitlePrinter {
	public static void main(String[] args){
		SubtitleReader reader = new SubtitleReader();
		
		while(reader.hasMorePairs()){
			SubtitlePair pair = reader.readPair();
			
			PrintWriter writer;
			try {
				writer = new PrintWriter("sentences/"+pair.getSourceName()+".txt", "UTF-8");
				writer.println(pair.getSource());
				writer.close();
				
				writer = new PrintWriter("sentences/"+pair.getTargetName()+".txt", "UTF-8");
				writer.println(pair.getTarget());
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				System.err.println("ERROR: Couldn't save output of "+pair.getName());
			}	
		}
	}
}
