package formalGrammarParsers.ebnf;

public class Option implements RuleElement {

	private RuleElement element;

	public Option(RuleElement element) {
		this.element = element;
	}

	@Override
	public String[] calc(Grammar g, String suche, String gebildet) {
		String[] berechnung = element.calc(g, suche, gebildet);
		
		String[] r�ckgabe = new String[berechnung.length + 1];
		
		for(int i = 0; i < berechnung.length; i++) {
			r�ckgabe[i] = berechnung[i];
		}
		
		r�ckgabe[r�ckgabe.length - 1] = "";
		
		return r�ckgabe;
	}

}
