package menon.cs5050.assignment2;

import java.util.List;

public class StringMatcherClient {

	public static final String TEXT = "atcgcacattatacattattatacat";
	public static final String SHEEP = "attataca";
	
	public static void main(String[] args) {
		
		KmpStringMatcher kmpStringMatcher = new KmpStringMatcher(TEXT, SHEEP);
		List<Integer> matchPosition = kmpStringMatcher.match();
		System.out.println("Match found at position " + matchPosition);
	}

}
