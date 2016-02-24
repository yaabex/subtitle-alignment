package ist.yaabex.domain;

public class SubtitlePair{
	private String sourceName;
	private String targetName;
	private Subtitle source;
	private Subtitle target;
	
	public SubtitlePair(String sourceName, String targetName) {
		this.sourceName = sourceName;
		this.targetName = targetName;
	}
	
	public String getSourceName() 		{	return sourceName;	}
	public String getTargetName() 		{	return targetName;	}
	public String getName()				{	return sourceName.replaceAll("-.*\\.srt", "");}
	public Subtitle getSource()			{	return source;		}
	public Subtitle getTarget()			{	return target;		}
	public void setSource(Subtitle s)	{ source = s;			}
	public void setTarget(Subtitle t)	{ target = t;			}
}
