package menon.cs5050.assignment2;

import java.util.ArrayList;
import java.util.List;

public class KmpStringMatcher extends StringMatcher {
 
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
		for (int j = 1; j < patternLength; j++) {
		
			int i = this.patternPrefixTable[j - 1];
			while ((getPattern().charAt(j) != getPattern().charAt(i + 1)) && i >= 0) {
				i = this.patternPrefixTable[i];
			}
			if (getPattern().charAt(j) == getPattern().charAt(i + 1)) {
				this.patternPrefixTable[j] = i + 1;
			} else {
				this.patternPrefixTable[j] = -1;
			}
		}
	}
	
	@Override
	public List<Integer> match() {
		
		int textIndex = 0, patternIndex = 0;
		int textLength = getText().length();
		int patternLength = getPattern().length();
		
		List<Integer> returnValue = new ArrayList<Integer>();
		
		while (textIndex < textLength ) {
			
			while (patternIndex < patternLength) {
				
				if (textIndex == textLength) {
					break;
				}
				
				if (getText().charAt(textIndex) == getPattern().charAt(patternIndex)) {
					++textIndex;
					++patternIndex;
				} else if (patternIndex == 0) {
					++textIndex;
				} else {
					patternIndex = this.patternPrefixTable[patternIndex - 1] + 1;
				}
				
			}
			
			returnValue.add(Integer.valueOf((patternIndex == patternLength) ? (textIndex - patternLength) : -1));
			patternIndex = 0;
			
		}
		
		return returnValue;
	}

}