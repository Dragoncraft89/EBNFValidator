package formalGrammarParsers.ebnf;

public class Alphabet {
	private char[] chars;
	
	public Alphabet(char[] chars) {
		this.chars = chars;
	}
	
	/**
	 * Checks if char is part of the Alphabet
	 * @param c
	 * @return
	 */
	public boolean isInAlphabet(char c) {
		for(int i = 0; i < chars.length; i++){
			if(chars[i] == c)
				return true;
		}
		
		return false;
	}

	/**parses an Alphabet from a String sourcey<br>
	 * Example: {012} is an alphabet with characters: 0, 1 and 2
	*/
	public static Alphabet fromString(String alphabetString) {
		alphabetString = alphabetString.trim();
		
		if(!alphabetString.matches("\\{.+?\\}"))
			throw new ParsingException("Expected alphabet, got: " + alphabetString);
		
		
		char[] chars = alphabetString.substring(1, alphabetString.length() - 1).toCharArray();
		
		return new Alphabet(chars);
	}
}
