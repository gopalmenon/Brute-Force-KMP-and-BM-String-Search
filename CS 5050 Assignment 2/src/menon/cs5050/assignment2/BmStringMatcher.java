package menon.cs5050.assignment2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BmStringMatcher extends StringMatcher {
	
	private Map<Character, Integer> badMatchShiftTable, goodSuffixShiftTable;
	
	BmStringMatcher(String text, String pattern) {
		
		super(text, pattern);
		
		this.badMatchShiftTable = new HashMap<Character, Integer>(pattern.length());
		populateBadMatchShiftTable();
		
		this.goodSuffixShiftTable = new HashMap<Character, Integer>(pattern.length());
		populateGoodSuffixShiftTable();
		
	}
	
	/**
	 * Populate the bad match character table
	 */
	private void populateBadMatchShiftTable() {
		
		int patternLength = getPattern().length();
		Character badMatchTableKey = null;
		Integer badMatchTableValue = null;
		
		for (int patternIndex = 1; patternIndex < patternLength; patternIndex++) {
		
			badMatchTableKey = Character.valueOf(Character.toLowerCase(getPattern().charAt(patternIndex)));
			badMatchTableValue = patternLength - patternIndex - 1;		
			badMatchShiftTable.put(badMatchTableKey, badMatchTableValue);
		}
	}
	
	/**
	 * Populate the good suffix shift table
	 */
	private void populateGoodSuffixShiftTable() {
		
	}
	
	/**
	 * @param mismatchedCharacter
	 * @return the greater of the two values of the bad character match shift and the good suffix shift
	 */
	private int getPatternShiftValue(char mismatchedCharacter) {
		
		Character mismatchValue = Character.valueOf(Character.toLowerCase(mismatchedCharacter));
		int badMatchShift = 0, goodSuffixShift = 0, badMatchShiftValue = 0, patternLength = getPattern().length();
		
		if (this.badMatchShiftTable.containsKey(mismatchValue)) {
			badMatchShift = this.badMatchShiftTable.get(mismatchValue).intValue();
		} else {
			badMatchShift = patternLength;
		}
		
		if (this.goodSuffixShiftTable.containsKey(mismatchValue)) {
			goodSuffixShift = this.goodSuffixShiftTable.get(mismatchValue).intValue();
		} else {
			goodSuffixShift = patternLength;
		}
		
		
		return Math.max(badMatchShift, goodSuffixShift);
			
	}

	@Override
	public List<Integer> match() {
		return null;
	}

}
