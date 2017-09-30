package formalGrammarParsers.ebnf;

/**
 * Represents an EBNF-OR
 * @author Florian
 *
 */
public class Or implements RuleElement {

	private RuleElement element1;
	private RuleElement element2;

	public Or(RuleElement element1, RuleElement element2) {
		this.element1 = element1;
		this.element2 = element2;
	}

	@Override
	public String[] calc(Grammar g, String suche, String gebildet) {
		String[] string1 = element1.calc(g, suche, gebildet);
		String[] string2 = element2.calc(g, suche, gebildet);
		
		String[] result = new String[string1.length + string2.length];
		
		//merge arrays string1 and string2
		for(int i = 0; i < result.length; i++) {
			if(i < string1.length) {
				result[i] = string1[i];
			} else {
				result[i] = string2[i - string1.length];
			}
		}
		
		return result;
	}

}
