package formalGrammarParsers.ebnf;

public class Rule {

	private String name;
	private RuleElement[] syntax;

	public Rule(String name, RuleElement[] syntax) {
		this.name = name;
		this.syntax = syntax;
	}
	
	/**
	 * Returns the rules' name
	 * @return
	 */
	public String getName() {
		return name;
	}

	public boolean calc(Grammar g, String text) {
		String val = recValidate(g, text, "", 0, true);
		return text.equals(val);
	}

	public String calc(Grammar g, String text, String gebildet) {
		return recValidate(g, text, gebildet, 0, false);
	}

	private String recValidate(Grammar g, String suche, String gebildet, int index, boolean toplvl) {
		
		if(!suche.matches(safeRegex(gebildet) + ".*"))
			return "";
		
		String[] values = syntax[index].calc(g, suche, gebildet);

		String temp = "";
		
		for (String s : values) {
			temp = gebildet + s;
			String regExp = safeRegex(temp);
			if (index != syntax.length - 1 || !toplvl)
				regExp += ".*";

			if (suche.matches(regExp)) {
				if (index != syntax.length - 1) {
					return s + recValidate(g, suche, temp, index + 1, toplvl);
				} else {
					return s;
				}
			}
		}

		return temp;
	}

	private String safeRegex(String regex) {
		return regex.replace("\\", "\\\\").replace("+", "\\+").replace("*", "\\*").replace("?", "\\?")
				.replace("(", "\\(").replace(")", "\\)").replace("[", "\\[").replace("]", "\\]")
				.replace("|", "\\|").replace("$", "\\$").replace("^", "\\^").replace(".", "\\.");
	}
}
