package formalGrammarParsers.ebnf;

public class Variable implements RuleElement{

	private String varName;
	
	public Variable(String varName) {
		this.varName = varName;
	}
	
	@Override
	public String[] calc(Grammar g, String suche, String gebildet) {
		Rule[] rules = g.getRegeln(varName);
		
		String[] array = new String[rules.length];
		
		for(int i = 0; i < array.length; i++) {
			array[i] = rules[i].calc(g, suche, gebildet);
		}
		
		return array;
	}

}
