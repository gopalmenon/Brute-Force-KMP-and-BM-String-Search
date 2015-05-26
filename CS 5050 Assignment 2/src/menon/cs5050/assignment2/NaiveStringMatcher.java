package menon.cs5050.assignment2;

import java.util.ArrayList;
import java.util.List;

/**
 * String Matcher using naive algorithm 
 *
 */public class NaiveStringMatcher extends StringMatcher {

	NaiveStringMatcher(String text, String pattern) {
		super(text, pattern);
	}

	@Override
	public List<Integer> match() {

		int textLength = getText().length(), patternLength = getPattern().length();
		
		if (patternLength > textLength) {
			return null;
		}
		
		List<Integer> returnValue = new ArrayList<Integer>();

		//Loop through the text string, with the pattern position changing from text start and advancing one character at a time
		for (int patternStartingPosition = 0; patternStartingPosition <= textLength - patternLength; ++patternStartingPosition) {

			for (int patternIndex = 0; patternIndex < patternLength; ++patternIndex) {
				
				if (Character.toLowerCase(getPattern().charAt(patternIndex)) != Character.toLowerCase(getText().charAt(patternStartingPosition + patternIndex))) {
					break;
				}
				
				if (patternIndex == patternLength - 1) {
					returnValue.add(Integer.valueOf(patternStartingPosition));
				}
			}
		
		}
		
		return returnValue;
		
	}

}
