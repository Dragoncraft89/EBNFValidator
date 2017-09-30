package formalGrammarParsers.ebnf;

import java.io.File;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		GrammarParser parser = new GrammarParser(new File("ebnf.txt"));
		
		System.out.println(parser.parse().validate(new File("test.txt"), false));
	}
}
