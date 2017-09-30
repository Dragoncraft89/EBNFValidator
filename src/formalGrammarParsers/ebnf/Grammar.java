package formalGrammarParsers.ebnf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Grammar {
	private Alphabet alphabet;
	
	private Rule[] regeln;
	private String startVar;
	
	public Grammar(Alphabet alphabet, Rule[] regeln, String startVar) {
		this.alphabet = alphabet;
		this.regeln = regeln;
		this.startVar = startVar;
	}
	
	public Alphabet getAlphabet() {
		return alphabet;
	}
	
	/**
	 * Validates the argument
	 * @param s
	 * @return
	 */
	public boolean validate(String s) {
		for(Rule r:regeln) {
			if(r.getName().equals(startVar)) {
				if(r.calc(this, s))
					return true;
			}
		}
		
		return false;
	}

	public Rule[] getRegeln(String varName) {
		ArrayList<Rule> regeln = new ArrayList<Rule>();

		for(Rule r:this.regeln) {
			if(r.getName().equals(varName)) {
				regeln.add(r);
			}
		}
		
		return regeln.toArray(new Rule[regeln.size()]);
	}

	public boolean validate(File file, boolean ignoreNewLines) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		StringBuilder builder = new StringBuilder();
		
		String line = reader.readLine();
		builder.append(line);
		
		while((line = reader.readLine()) != null) {
			if(!ignoreNewLines)
				builder.append("\n");
			builder.append(line);
		}
		
		reader.close();
		
		return validate(builder.toString());
	}
}
