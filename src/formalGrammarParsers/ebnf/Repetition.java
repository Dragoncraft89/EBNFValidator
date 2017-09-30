package formalGrammarParsers.ebnf;

import java.util.ArrayList;

public class Repetition implements RuleElement {

	private RuleElement element;

	public Repetition(RuleElement element) {
		this.element = element;
	}

	@Override
	public String[] calc(Grammar g, String suche, String gebildet) {
		ArrayList<String> strings = new ArrayList<String>();
		
		recCalc(strings, gebildet, suche, g, "");

		//0 times is valid in EBNF
		strings.add("");
		return strings.toArray(new String[strings.size()]);
	}
	
	//Looks ahead and lists all possibilities, until it can no longer be repeated
	private void recCalc(ArrayList<String> strings, String rootString, String search, Grammar g, String check) {
		String test = "";
		String[] elements = element.calc(g, search, rootString + check);
		
		for(String s:elements) {
			test = check + s;
			//Does it match the string to validate? (search)
			if(!test.equals("") && search.matches(safeRegex(rootString + test) + ".*")) {
				recCalc(strings, rootString, search, g, test);
				
				strings.add(test);
			}
		}
	}
	


	private String safeRegex(String regex) {
		return regex.replace("\\", "\\\\").replace("+", "\\+").replace("*", "\\*").replace("?", "\\?")
				.replace("(", "\\(").replace(")", "\\)").replace("[", "\\[").replace("]", "\\]")
				.replace("|", "\\|").replace("$", "\\$").replace("^", "\\^").replace(".", "\\.");
	}
}
