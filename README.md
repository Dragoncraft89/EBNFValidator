# EBNFValidator
A tool that validates a string trough a given EBNF-rules file

Example:
```java
import java.io.File;
import java.io.IOException;
import formalGrammarParsers.ebnf.GrammarParser;

public class Test {
	public static void main(String[] args) throws IOException {
		GrammarParser parser = new GrammarParser(new File("ebnf.txt"));
		
		System.out.println(parser.parse().validate(new File("test.txt"), false));
	}
}
```
This example validates a file called test.txt with the rules stated in ebnf.txt
