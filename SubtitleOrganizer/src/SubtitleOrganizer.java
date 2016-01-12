import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class SubtitleOrganizer {
	private static final int MAX_ID = 4000;
	private static final int MOVIE_ID = 0;
	private static final int SUB_ID = 1;
	private static final int SUB_LANG = 2;
	private static final int MOVIE_NAME = 7;
	private static final String filename = "export.txt";
	
	private static void read(MovieLibrary library){
		String encoding = StandardCharsets.UTF_8.name();
		try {
			Scanner scanner = new Scanner(new File(filename), encoding);	
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(!line.isEmpty() && line.contains("srt")){
					String[] info = line.split("\t");
					if(Integer.parseInt(info[MOVIE_ID]) > MAX_ID)
						break;
					library.put(info[MOVIE_NAME], info[SUB_LANG]+"_"+info[SUB_ID]);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Could not read file \""+ filename +"\".");
		}
	}
	
	public static void main(String[] args){
		MovieLibrary library = new MovieLibrary();
		read(library);
		//library.organizeInFolders();
		System.out.println(library.toString());
	}
}
