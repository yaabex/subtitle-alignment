package ist.yaabex;

import ist.yaabex.domain.Aligner;
import ist.yaabex.domain.SubtitlePair;
import ist.yaabex.io.ConfigLoader;
import ist.yaabex.io.SubtitleReader;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Yaabex {
	public static void main(String[] args){
		SubtitleReader reader = new SubtitleReader();
		
		while(reader.hasMorePairs()){
			ConfigLoader config = new ConfigLoader();
			SubtitlePair pair = reader.readPair();
			
			Aligner aligner = new Aligner(pair, config);
			aligner.align();
			
			
			PrintWriter writer;
			try {
				writer = new PrintWriter("results/"+pair.getName()+".txt", "UTF-8");
				writer.println(aligner.toString());
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				System.err.println("ERROR: Couldn't save output of "+pair.getName());
			}			
		}
	}
}
