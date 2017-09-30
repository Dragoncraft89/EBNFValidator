package formalGrammarParsers.ebnf;

/**
 * This class represents a literal value, e.g. the character 'a'
 * @author Florian
 *
 */
public class Value implements RuleElement{

	private char value;

	public Value(char c) {
		this.value = c;
	}
	
	@Override
	public String[] calc(Grammar g, String suche, String gebildet) {
		return new String[]{String.valueOf(value)};
	}

}
