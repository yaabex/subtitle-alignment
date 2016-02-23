package ist.yaabex.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConfigLoader {
	private final String filepath = "yaabex.config";
	private boolean TIME_ONLY = false; 
	private double TIME_THRESHOLD = 0.95;
	private double DISTANCE_THRESHOLD = 0.55;
	private boolean PRINT_CONFIDENCE = false; 
	private boolean USE_DICTIONARY = true;
	private String DICTIONARY_FILE = "lib/translatorEN-PT.txt";

	public ConfigLoader(){
		final String encoding = StandardCharsets.UTF_8.name();
		try {
			Scanner scanner = new Scanner(new File(filepath), encoding);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(line.matches("^\\s*#.*$|^\\s*$")){
					// Comment or empty line - IGNORE
				}else{
					String[] token = line.replaceAll("\\s", "").split("=");
					switch (token[0]) {
						case "TIME_ONLY"			: TIME_ONLY = Boolean.parseBoolean(token[1]);	break;
						case "TIME_THRESHOLD"		: TIME_THRESHOLD = Double.parseDouble(token[1]); break;
						case "DISTANCE_THRESHOLD"	: DISTANCE_THRESHOLD = Double.parseDouble(token[1]); break;
						case "PRINT_CONFIDENCE"		: PRINT_CONFIDENCE = Boolean.parseBoolean(token[1]); break;
						case "USE_DICTIONARY"		: USE_DICTIONARY = Boolean.parseBoolean(token[1]); break;
						case "DICTIONARY_FILE"		: DICTIONARY_FILE = token[1]; break;
					}
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Could not read file \""+ filepath +"\".");
		}
	}

	public boolean useTimeOnly()		{ return TIME_ONLY;			}
	public double getTimeThreshold()	{ return TIME_THRESHOLD;	}
	public double getDistanceThreshold(){ return DISTANCE_THRESHOLD;}
	public boolean printConfidence()	{ return PRINT_CONFIDENCE;	}
	public boolean useDictionary() 		{ return USE_DICTIONARY;	}
	public String getDictionaryFile() 	{ return DICTIONARY_FILE;	}
}
