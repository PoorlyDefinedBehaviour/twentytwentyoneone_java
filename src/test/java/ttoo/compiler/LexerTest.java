package ttoo.compiler;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

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
    List<TestCase> testCases = Arrays.asList(new TestCase("", Arrays.asList(new Token(TokenType.Eof))),
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
            Arrays.asList(new Token(TokenType.PercentPercent, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("=", Arrays.asList(new Token(TokenType.Equal, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("!=",
            Arrays.asList(new Token(TokenType.NotEqual, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("!", Arrays.asList(new Token(TokenType.Bang, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("<", Arrays.asList(new Token(TokenType.LessThan, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("<=",
            Arrays.asList(new Token(TokenType.LessThanOrEqual, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase(">",
            Arrays.asList(new Token(TokenType.GreaterThan, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase(">=",
            Arrays.asList(new Token(TokenType.GreaterThanOrEqual, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
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

  @Test
  public void recognizesKeywords() {
    List<TestCase> testCases = Arrays.asList(
        new TestCase("program",
            Arrays.asList(new Token(TokenType.Program, new SourceSpan(1, 1)), new Token(TokenType.Eof))),

        new TestCase("define",
            Arrays.asList(new Token(TokenType.Define, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("not", Arrays.asList(new Token(TokenType.Not, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("variable",
            Arrays.asList(new Token(TokenType.Variable, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("is", Arrays.asList(new Token(TokenType.Is, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("natural",
            Arrays.asList(new Token(TokenType.Natural, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("real", Arrays.asList(new Token(TokenType.Real, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("char", Arrays.asList(new Token(TokenType.Char, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("boolean",
            Arrays.asList(new Token(TokenType.Boolean, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("execute",
            Arrays.asList(new Token(TokenType.Execute, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("set", Arrays.asList(new Token(TokenType.Set, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("get", Arrays.asList(new Token(TokenType.Get, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("to", Arrays.asList(new Token(TokenType.To, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("put", Arrays.asList(new Token(TokenType.Put, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("loop", Arrays.asList(new Token(TokenType.Loop, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("while",
            Arrays.asList(new Token(TokenType.While, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("do", Arrays.asList(new Token(TokenType.Do, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("true", Arrays.asList(new Token(TokenType.True, new SourceSpan(1, 1)), new Token(TokenType.Eof))),
        new TestCase("false",
            Arrays.asList(new Token(TokenType.False, new SourceSpan(1, 1)), new Token(TokenType.Eof))));

    for (TestCase testCase : testCases) {
      List<Token> actual = new Lexer(testCase.input).lex();

      assertTrue(actual.equals(testCase.expected));
    }
  }

  @Test
  public void keepsTrackOfLinesAndColumns() {
    List<TestCase> testCases = Arrays.asList(
        new TestCase("{}",
            Arrays.asList(new Token(TokenType.LeftBrace, new SourceSpan(1, 1)),
                new Token(TokenType.RightBrace, new SourceSpan(1, 2)), new Token(TokenType.Eof))),
        new TestCase("{\n>",
            Arrays.asList(new Token(TokenType.LeftBrace, new SourceSpan(1, 1)),
                new Token(TokenType.GreaterThan, new SourceSpan(2, 1)), new Token(TokenType.Eof))),
        new TestCase(" +", Arrays.asList(new Token(TokenType.Plus, new SourceSpan(1, 2)), new Token(TokenType.Eof))),
        new TestCase("\n\n\n +",
            Arrays.asList(new Token(TokenType.Plus, new SourceSpan(4, 2)), new Token(TokenType.Eof))),
        new TestCase("\ntrue\n\n   +", Arrays.asList(new Token(TokenType.True, new SourceSpan(2, 1)),
            new Token(TokenType.Plus, new SourceSpan(4, 4)), new Token(TokenType.Eof))));

    for (TestCase testCase : testCases) {
      List<Token> actual = new Lexer(testCase.input).lex();

      assertTrue(actual.equals(testCase.expected));
    }
  }

}
