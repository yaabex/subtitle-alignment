import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;


public class MovieLibrary extends TreeMap<String, ArrayList<SubtitleFile>>{
	private static final long serialVersionUID = 1L;
	private long numSubtitles = 0;
	
	public ArrayList<SubtitleFile> put(String key, String value) {
		key = key.replaceAll("\"","");
		if(containsKey(key))
			get(key).add(new SubtitleFile(value));
		else{
			ArrayList<SubtitleFile> subtitles = new ArrayList<SubtitleFile>();
			subtitles.add(new SubtitleFile(value));
			put(key, subtitles);
		}
		numSubtitles++;
		return get(key);
	}
	
	@Override
	public String toString() {
		String str = new String();
		for(String movie : keySet()){
			str += movie+": ";
			for(SubtitleFile srt : get(movie))
				str+=srt.toString()+" ";
			str += "\n";
		}
		str += "\n#Movies: "+keySet().size()+"\n";
		str += "#Subtitles: "+numSubtitles+"\n";
		return str;
	}
	
	public void organizeInFolders(){
		final String originalPath = "/mnt/8AEC8364EC834981/Data/export_por_eng/files/";
		
		try {
			Runtime.getRuntime().exec("mkdir SubtitleLibrary");
			for(String movie : keySet()){
				String movieDir = movie.replaceAll(" ", "");
				Runtime.getRuntime().exec("mkdir SubtitleLibrary/"+movieDir);
				System.out.println("created dir: "+movieDir);
				for(SubtitleFile srt : get(movie))
					Runtime.getRuntime().exec("cp "+originalPath + srt.getLocation() + srt.getId()+".gz" +
											  " SubtitleLibrary/"+movieDir+"/"+srt.getLanguage()+"_"+srt.getId()+".gz");
			}
		} catch (IOException e) {}
	}
}
