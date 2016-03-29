import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AlignmentsReader{
	private final String encoding = StandardCharsets.UTF_8.name();
	//private final String resultsPath = "results/";
	private final String resultsPath = "results-tiedemann/";
	private final String expectedPath = "expected/";
	private ArrayList<String> testingList = new ArrayList<String>();
	
	public AlignmentsReader(){
		File expectedFolder = new File(expectedPath);
		for(File file : expectedFolder.listFiles())
			if(file.isFile())
				testingList.add(file.toString().replaceAll(".*/(.*)$", "$1"));
		Collections.sort(testingList);
	}
	
	public ArrayList<String> readExpected(String filename){
		ArrayList<String> alignments = new ArrayList<String>();
		filename = expectedPath + filename;
		
		try {
			Scanner scanner = new Scanner(new File(filename), encoding);
			while (scanner.hasNextLine()) {
				alignments.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Could not read file \""+ filename +"\".");
		}
		return alignments;
	}
	
	public ArrayList<String> readResults(String filename){
		ArrayList<String> alignments = new ArrayList<String>();
		filename = resultsPath + filename;
		
		try {
			Scanner scanner = new Scanner(new File(filename), encoding);
			while (scanner.hasNextLine()) {
				alignments.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("Could not read file \""+ filename +"\".");
		}
		return alignments;
	}
	
	public ArrayList<String> getFilesToTest(){
		return testingList;
	}
	
	public void printFilesList(){
		for(String f: testingList)
			System.out.println(f);
	}
}
