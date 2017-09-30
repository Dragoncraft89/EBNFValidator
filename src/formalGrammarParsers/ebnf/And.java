package formalGrammarParsers.ebnf;

public class And implements RuleElement {
	private RuleElement element1;
	private RuleElement element2;

	public And(RuleElement element1, RuleElement element2) {
		this.element1 = element1;
		this.element2 = element2;
	}

	@Override
	public String[] calc(Grammar g, String suche, String gebildet) {
		String[] s1 = element1.calc(g, suche, gebildet);
		String[][] s2 = new String[s1.length][];
		
		int length = 0;
		
		for(int i = 0; i < s2.length; i++) {
			s2[i] = element2.calc(g, suche, gebildet + s1[i]);
			length += s2[i].length;
		}
		
		String[] result = new String[length];
		
		int index = 0;
		for(int i = 0; i < s1.length; i++) {
			for(int j = 0; j < s2[i].length; j++) {
				result[index] = s1[i] + s2[i][j];
				index++;
			}
		}
		
		return result;
	}

}
