package formalGrammarParsers.ebnf;

public class Option implements RuleElement {

	private RuleElement element;

	public Option(RuleElement element) {
		this.element = element;
	}

	@Override
	public String[] calc(Grammar g, String suche, String gebildet) {
		String[] berechnung = element.calc(g, suche, gebildet);
		
		String[] result = new String[berechnung.length + 1];
		
		for(int i = 0; i < berechnung.length; i++) {
			result[i] = berechnung[i];
		}
		
		result[result.length - 1] = "";
		
		return result;
	}

}
