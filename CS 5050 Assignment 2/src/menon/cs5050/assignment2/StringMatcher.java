package menon.cs5050.assignment2;

import java.util.List;

public abstract class StringMatcher {

	private String text;
	private String pattern;
	
	public String getText() {
		return text;
	}

	public String getPattern() {
		return pattern;
	}
	
	StringMatcher(String text, String pattern) {
		this.text = text;
		this.pattern = pattern;
	}
	
	public abstract List<Integer> match();

}
