package menon.cs5050.assignment2;

import java.util.ArrayList;
import java.util.List;

/**
 * String Matcher using Knuth-Morris-Pratt string matching algorithm
 *
 */public class KmpStringMatcher extends StringMatcher {
 
	private int[] patternPrefixTable;
	
	KmpStringMatcher(String text, String pattern) {
		super(text, pattern);
		this.patternPrefixTable = new int[pattern.length()];
		populatePatternPrefixTable();
	}
	
	/**
	 * Fill the pattern prefix table with skip values
	 */
	private void populatePatternPrefixTable() {
		
		int patternLength = getPattern().length();
		this.patternPrefixTable[0] = -1;
		int shiftAmount = -1;
		
		for (int patternIndex = 1; patternIndex < patternLength; ++patternIndex) {
		
			while (shiftAmount > 0 && (Character.toLowerCase(getPattern().charAt(shiftAmount + 1)) != Character.toLowerCase(getPattern().charAt(patternIndex)))) {
				shiftAmount = this.patternPrefixTable[shiftAmount];
			}
			if (Character.toLowerCase(getPattern().charAt(shiftAmount +1)) == Character.toLowerCase(getPattern().charAt(patternIndex))) {
				shiftAmount += 1;
			}
			this.patternPrefixTable[patternIndex] = shiftAmount;
		}
		
	}

	@Override
	public List<Integer> match() {
				
		int textLength = getText().length(), patternLength = getPattern().length();
		
		if (patternLength > textLength) {
			return null;
		}
		
		int textIndex = 0, patternIndex = 0;
		List<Integer> returnValue = new ArrayList<Integer>();
		
		while (textIndex < textLength ) {
			
			while (patternIndex < patternLength) {
				
				if (textIndex == textLength) {
					break;
				}
				
				if (Character.toLowerCase(getText().charAt(textIndex)) == Character.toLowerCase(getPattern().charAt(patternIndex))) {
					//Keep advancing both pointers
					++textIndex;
					++patternIndex;
				} else if (patternIndex == 0) {
					//Advance the text string pointer 
					++textIndex;
				} else {
					//Shift the pattern based on the value in the pattern prefix table 
					patternIndex = this.patternPrefixTable[patternIndex - 1] + 1;
				}
				
			}
			
			if (patternIndex == patternLength) {
				returnValue.add(Integer.valueOf(textIndex - patternLength));
			}

			patternIndex = 0;
			
		}
		
		return returnValue;
	}

}