public class SubtitleFile {
	private String language;
	private String id;
	private String location;
	
	public SubtitleFile(String str){
		String[] token = str.split("_");
		language = token[0];
		id = token[1];
		String temp = id.substring(Math.max(0,id.length()-4), id.length());
		location = new StringBuilder(temp).reverse().toString();
		location = location.replaceAll("([0-9])", "$1/");
	}
	
	public String getLanguage() {
		return language;
	}

	public String getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return language+"_"+id;
	}
}
