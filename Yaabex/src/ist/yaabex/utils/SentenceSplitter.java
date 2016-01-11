package ist.yaabex.utils;

import java.util.ArrayList;

public class SentenceSplitter {
	public ArrayList<String> split(String text){
		ArrayList<String> sentences = new ArrayList<String>();
		
		String substring = new String();
		String punctuationRegex = ".*[\\.!?]+ ";
		String exceptionsRegex = ".*(Mrs?|Dr|Av|St|Sra?|D|R|Exmo?|\\.[A-Z]). ";
		int beginIndex = 0;
		
		for(int i=0; i<text.length(); i++){
			substring += text.charAt(i);
			if(substring.matches(punctuationRegex) && !substring.matches(exceptionsRegex)){
				for(i++;i<text.length() && text.charAt(i) == '.'; i++){} //read trough reticences	
				sentences.add(text.substring(beginIndex, i));
				beginIndex = i;
			}
		}
		sentences.add(text.substring(beginIndex, text.length()));
		
		return sentences;
	}	
}
