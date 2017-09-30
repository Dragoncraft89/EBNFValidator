package formalGrammarParsers.ebnf;

/**
 * Base class for all Elements of the EBNF-language
 * @author Florian
 *
 */
public interface RuleElement {
	public String[] calc(Grammar g, String suche, String gebildet);

	public static RuleElement parse(Alphabet alphabet, String varName) {
		if(varName.matches("([\'\"])[^\"\']\\1")) {
			char c = varName.charAt(1);
			if(!alphabet.isInAlphabet(c))
				throw new ParsingException("\'" + c + "\' is not an element in the given alphabet");
			
			return new Value(c);
		}
		
		if(varName.matches("\\[.+?\\]")) {
			return new Option(parse(alphabet, varName.substring(1, varName.length() - 1)));
		}
		
		if(varName.matches("\\{.+?\\}")) {
			return new Repetition(parse(alphabet, varName.substring(1, varName.length() - 1)));
		}
		
		if(varName.matches("(.+?,)+.+?")) {
			int index = findNotInBrackets(varName, ',');

			if(index != -1)
				return new And(parse(alphabet, varName.substring(0, index)), parse(alphabet, varName.substring(index + 1)));
		}
		
		if(varName.matches(".+?\\|.+?")) {
			int index = findNotInBrackets(varName, '|');
			
			if(index != -1)
				return new Or(parse(alphabet, varName.substring(0, index)), parse(alphabet, varName.substring(index + 1)));
		}
		
		return new Variable(varName);
	}
	
	static int findNotInBrackets(String suche, char c) {
		char[] array = suche.toCharArray();
		
		int depth = 0;
		
		for(int i = 0; i < array.length; i++) {
			if(array[i] == '(' || array[i] == '[' || array[i] == '{') {
				depth++;
			}else if(array[i] == ')' || array[i] == ']' || array[i] == '}') {
				depth--;
			}else if(array[i] == c && depth == 0) {
				return i;
			}
		}
		
		return -1;
	}
}
