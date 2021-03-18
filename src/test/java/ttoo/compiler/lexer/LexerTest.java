package ttoo.compiler.lexer;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ttoo.compiler.Lexer;
import ttoo.compiler.SourceSpan;
import ttoo.compiler.Token;
import ttoo.compiler.TokenType;

class TestCase {
  public final String input;
  public final List<Token> expected;

  public TestCase(String input, List<Token> expected) {
    this.input = input;
    this.expected = expected;
  }
}

public class LexerTest {
  @Test
  public void recognizesSimpleTokens() {
    List<TestCase> testCases = Arrays.asList(
        new TestCase("{",
            Arrays.asList(new Token(TokenType.LeftBrace, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("}",
            Arrays.asList(new Token(TokenType.RightBrace, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("[",
            Arrays.asList(new Token(TokenType.LeftBracket, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("]",
            Arrays.asList(new Token(TokenType.RightBracket, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase(",", Arrays.asList(new Token(TokenType.Comma, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("+", Arrays.asList(new Token(TokenType.Plus, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("-", Arrays.asList(new Token(TokenType.Minus, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("/", Arrays.asList(new Token(TokenType.Slash, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("*", Arrays.asList(new Token(TokenType.Star, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("%", Arrays.asList(new Token(TokenType.Percent, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("%%",
            Arrays.asList(new Token(TokenType.PercentPercent, new SourceSpan(1, 2)), new Token(TokenType.Eof))),
        new TestCase("=", Arrays.asList(new Token(TokenType.Equal, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("!=",
            Arrays.asList(new Token(TokenType.NotEqual, new SourceSpan(1, 2)), new Token(TokenType.Eof))),
        new TestCase("!", Arrays.asList(new Token(TokenType.Bang, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("<", Arrays.asList(new Token(TokenType.LessThan, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("<=",
            Arrays.asList(new Token(TokenType.LessThanOrEqual, new SourceSpan(1, 2)), new Token(TokenType.Eof))),
        new TestCase(">",
            Arrays.asList(new Token(TokenType.GreaterThan, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase(">=",
            Arrays.asList(new Token(TokenType.GreaterThanOrEqual, new SourceSpan(1, 2)), new Token(TokenType.Eof))),
        new TestCase("&",
            Arrays.asList(new Token(TokenType.Ampersand, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("|", Arrays.asList(new Token(TokenType.Pipe, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("(",
            Arrays.asList(new Token(TokenType.LeftParen, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase(")",
            Arrays.asList(new Token(TokenType.RightParen, new SourceSpan(1, 1)), new Token(TokenType.Eof))));

    for (TestCase testCase : testCases) {
      List<Token> actual = new Lexer(testCase.input).lex();

      assertTrue(actual.equals(testCase.expected));
    }
  }
}
