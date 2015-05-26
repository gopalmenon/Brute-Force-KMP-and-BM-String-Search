package menon.cs5050.assignment2;

import java.io.FileNotFoundException;
import java.util.List;

public class StringMatcherClient {

	public static final String SAMPLE_TEXT =  "TGCTGGTCTGGTTCTCTTTCTCTCCGTGACCTATAGAGCAAGGTGGAGGGGTAGGAGGGGGACACCCAGTGAAGGGTCCTTTGGCCTTGTAGTTTCTTAGAGGCTTCTTCTGGGAACATGTACTGGGAGCTGGGGTGGGTCCTGCACCTGCATGGGGCCATTTCCCTTCGTGGGCCCACAGACAACTGTTCCCCACCACGGAGGGAAGGAGACGCACAGGGCCTGGGCCTTCTTCTCTGAGAACACTCTCAAGCAGAACTCGCCGTCTTTGAAGGGTTCAAATGTGGATGGCACCACCAGGTACTCCCCAGGGGGCAGCCGGGCCCGGCCAGAGACCTCCCGCAGGTTGACGTAGGTGCTGGTGCGGGCTGAGGGCTGGTAGGCCAGGAAGAAATCCCGGCCCAAGTGTGCGTCCGTGTGACTCTCCAGCTGCACGAAACAATAAGCAGAGTCAATTTCTTGTTAAATCCTGGAAGATGAGAGCCCAAGAGTTCAGCTTTATTGTGCTGATTTAGGAATTATTGATTTTTACCATTGCACCAAGAATCAGGAGGCCGTGGATTCTGTTGTGAACTCACTGTATGTCAATCATCAAGTGTATTTTCAGTGCCTTCTGGGTGCCAGGCCCTGTTTGAGGCATTGATCTTGACTGTGTGACCTTGACCTCTGGGCCTCCCCAGTTAAACGAAGGTTGAGGGACAGGGTCTCTAGTGTGCGCTCAGCTTCTCTCTGGATATTTTTCCTCTCTAATCCAATAGGCTCTTTCATTCTGCAGCTGTCTCTGGGAGTGTGGCATTGATCTTCCCAGTACAGGCCCAAGGCTGGAGAAAAGAGCTTAAA";
	public static final String SAMPLE_PATTERN = "ccatt";
	public static final String SHAKESPEARE_WORKS = "S";
	public static final String SHAKESPEARE_WORKS_FILE = "Shakespeare.txt";
	public static final String HUMAN_DNA = "D";
	public static final String HUMAN_DNA_FILE = "HumanDNA.txt";
	public static final String SHAKESPEARE_PATTERN_FILE = "ShakespearePattern.txt";
	public static final String HUMAN_DNA_PATTERN_FILE = "HumanDNAPattern.txt";
	
	public static void main(String[] args) {
		
		String textToSearch = null, pattern = null;
		
		if (args.length > 0) {
			if (SHAKESPEARE_WORKS.equalsIgnoreCase(args[0].trim())) {
				textToSearch = getTextFromFile(SHAKESPEARE_WORKS_FILE);
				pattern = getTextFromFile(SHAKESPEARE_PATTERN_FILE);
				System.out.println("Searching Shakespeare\n");
			} else if (HUMAN_DNA.equalsIgnoreCase(args[0].trim())) {
				textToSearch = getTextFromFile(HUMAN_DNA_FILE);
				pattern = getTextFromFile(HUMAN_DNA_PATTERN_FILE);
				System.out.println("Searching Human DNA\n");
			} else {
				textToSearch = SAMPLE_TEXT;
				pattern = SAMPLE_PATTERN;
				System.out.println("Searching deafult text\n");
			}
		} else {
			textToSearch = SAMPLE_TEXT;
			pattern = SAMPLE_PATTERN;
			System.out.println("Searching deafult text\n");
		}
		
		long beforeKmpRun = System.currentTimeMillis();
		KmpStringMatcher kmpStringMatcher = new KmpStringMatcher(textToSearch, pattern);
		List<Integer> matchPositionKmp = kmpStringMatcher.match();
		long afterKmpRun = System.currentTimeMillis();
		System.out.println("Took " + (afterKmpRun - beforeKmpRun) + " ms. Match found by KMP Matcher at position " + matchPositionKmp);
		
		long beforeBmRun = System.currentTimeMillis();
		BmStringMatcher bmStringMatcher = new BmStringMatcher(textToSearch, pattern);
		List<Integer> matchPositionBm = bmStringMatcher.match();
		long afterBmRun = System.currentTimeMillis();
		System.out.println("Took " + (afterBmRun - beforeBmRun) + " ms. Match found by BM Matcher at position " + matchPositionBm);
		
		long beforeNaiveRun = System.currentTimeMillis();
		NaiveStringMatcher naiveStringMatcher = new NaiveStringMatcher(textToSearch, pattern);
		List<Integer> matchPositionNaive = naiveStringMatcher.match();
		long afterNaiveRun = System.currentTimeMillis();
		System.out.println("Took " + (afterNaiveRun - beforeNaiveRun) + " ms. Match found by Naive Matcher at position " + matchPositionNaive);
	}
	
	private static String getTextFromFile(String fileName) {
		
		StringBuffer returnValue = new StringBuffer();
		try {
			List<String> fileText = TextFileReader.getLinesFromTextFile(fileName.trim());
			for (String textLine : fileText) {
				returnValue.append(textLine);
			}
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException thrown while trying to read from file " + fileName);
			e.printStackTrace();
		}
		return returnValue.toString();
	}

}
