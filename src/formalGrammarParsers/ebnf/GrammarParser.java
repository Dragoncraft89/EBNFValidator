package formalGrammarParsers.ebnf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GrammarParser {
	private BufferedReader in;
	
	public GrammarParser(File file) throws IOException {
		in = new BufferedReader(new FileReader(file));
	}
	
	public GrammarParser(InputStream in) {
		this.in = new BufferedReader(new InputStreamReader(in));
	}
	
	/**
	 * Parses the file
	 * @return
	 * @throws IOException
	 */
	public Grammar parse() throws IOException {
		//read alphabet String
		String alphabetString = in.readLine().trim();
		
		//skip empty lines
		while(alphabetString.equals("")) {
			alphabetString = in.readLine().trim();
		}
		
		//create Alphabet
		Alphabet alphabet = Alphabet.fromString(alphabetString);
		
		//read Start variable
		String start = in.readLine().trim();
		
		while(start.equals("")) {
			start = in.readLine().trim();
		}
		
		if(start.toLowerCase().matches("start[ \t]*=.+?")) {
			int pos = start.indexOf('=');
			start = start.substring(pos + 1).trim();
		} else {
			throw new ParsingException("Expected START=VARIABLENAME, got: " + start);
		}
		
		
		//parse rules
		ArrayList<Rule> regeln = new ArrayList<>();
		
		String name = "";
		int c;
		while((c = in.read()) != -1) {
			switch(c) {
			case '=':
				regeln.add(readRule(alphabet, name.trim(), in));
				name = "";
				break;
			case '\r'://skip unimportant characters
			case '\n':
			case '\t':
			case ' ':
				break;
			//read rules' name
			default:name += (char)c;break;
			}
		}
		return new Grammar(alphabet, regeln.toArray(new Rule[regeln.size()]),start);
	}
	
	private Rule readRule(Alphabet alphabet, String name, BufferedReader reader) throws IOException {
		int c;
		
		ArrayList<RuleElement> regeln = new ArrayList<>();
		
		String varName = "";
		while((c = reader.read()) != -1) {
			switch(c) {
			case ';':
				regeln.add(RuleElement.parse(alphabet, varName.trim()));
				return new Rule(name, regeln.toArray(new RuleElement[regeln.size()]));
			case ',':
				regeln.add(RuleElement.parse(alphabet, varName.trim()));
				varName = "";
				break;
			case '[':
				varName += "[" + readToCharacter(']', reader);
				break;
			case '{':
				varName += "{" + readToCharacter('}', reader);
				break;
			case '\r':
			case '\n':
			case '\t':
			case ' ':
				break;
			default:
				varName += (char) c;
			}
		}
		
		throw new ParsingException("End of rule should be indicated by \';\'\r\nUnexpected end of stream");
	}
	
	private String readToCharacter(char character, BufferedReader reader) throws IOException {
		String s = "";
		
		int c;
		do {
			c = reader.read();
			
			s += (char) c;
		}while(c != character);
		
		return s;
	}
}
