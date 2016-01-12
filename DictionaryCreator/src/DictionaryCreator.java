import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.gtranslate.Language;
import com.gtranslate.Translator;


public class DictionaryCreator {
	private static void create(String wordList){
		final String encoding = StandardCharsets.UTF_8.name();
		
		try {
			Scanner scanner = new Scanner(new File(wordList), encoding);
			String lastRead = new String();
			
			while (scanner.hasNextLine()) {
				String word = scanner.nextLine();
				if(!lastRead.equals(word)){
					//translate
					Translator translate = Translator.getInstance();
					String translation = translate.translate(word, Language.ENGLISH, Language.PORTUGUESE);
					System.out.println(word.toLowerCase()+"\t"+translation.toLowerCase());
				}
				lastRead = word;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Could not read file \""+ wordList +"\".");
		}
	}
	
	public static void main(String args[]){
		create("../wordlist.txt");
	}
}
