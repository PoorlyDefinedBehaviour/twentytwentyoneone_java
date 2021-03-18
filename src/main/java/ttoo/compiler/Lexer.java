package ttoo.compiler;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
  private final String sourceCode;
  private int position;
  private char currentCharacter;
  private int line;
  private int column;

  public Lexer(String sourceCode) {
    this.sourceCode = sourceCode;
    this.position = 0;
    this.line = 1;
    this.column = 1;
    this.currentCharacter = '\0';
  }

  private boolean hasCharactersToLex() {
    return position < sourceCode.length();
  }

  private void readCharacter() {
    if (!hasCharactersToLex()) {
      currentCharacter = '\0';
      return;
    }

    currentCharacter = sourceCode.charAt(position);

    column += 1;

    if (currentCharacter == '\n') {
      line += 1;
      column = 0;
    }

    position += 1;
  }

  private void skipWhitespace() {
    while (currentCharacter == '\n' || currentCharacter == ' ' || currentCharacter == '\r'
        || currentCharacter == '\t') {
      readCharacter();
    }
  }

  private SourceSpan currentSourceSpan() {
    return new SourceSpan(line, column);
  }

  private char peek() {
    if (position + 1 >= sourceCode.length()) {
      return '\0';
    }

    return sourceCode.charAt(position + 1);
  }

  private Token tokenFromCharacter(char character) {
    if (character == '{') {
      return new Token(TokenType.LeftBrace, currentSourceSpan());
    }
    if (character == '}') {
      return new Token(TokenType.RightBrace, currentSourceSpan());
    }
    if (character == '[') {
      return new Token(TokenType.LeftBracket, currentSourceSpan());
    }
    if (character == ']') {
      return new Token(TokenType.RightBracket, currentSourceSpan());
    }
    if (character == ',') {
      return new Token(TokenType.Comma, currentSourceSpan());
    }
    if (character == '+') {
      return new Token(TokenType.Plus, currentSourceSpan());
    }
    if (character == '-') {
      return new Token(TokenType.Minus, currentSourceSpan());
    }
    if (character == '/') {
      return new Token(TokenType.Slash, currentSourceSpan());
    }
    if (character == '*') {
      return new Token(TokenType.Star, currentSourceSpan());
    }
    if (character == '%') {
      if (peek() == '%') {
        readCharacter();
        return new Token(TokenType.PercentPercent, currentSourceSpan());
      }

      return new Token(TokenType.Percent, currentSourceSpan());
    }
    if (character == '=') {
      return new Token(TokenType.Equal, currentSourceSpan());
    }
    if (character == '!') {
      if (peek() == '=') {
        readCharacter();
        return new Token(TokenType.NotEqual, currentSourceSpan());
      }
      return new Token(TokenType.Bang, currentSourceSpan());
    }
    if (character == '<') {
      if (peek() == '=') {
        readCharacter();
        return new Token(TokenType.LessThanOrEqual, currentSourceSpan());
      }

      return new Token(TokenType.LessThan, currentSourceSpan());
    }
    if (character == '>') {
      if (peek() == '=') {
        readCharacter();
        return new Token(TokenType.GreaterThanOrEqual, currentSourceSpan());
      }

      return new Token(TokenType.GreaterThan, currentSourceSpan());
    }
    if (character == '&') {
      return new Token(TokenType.Ampersand, currentSourceSpan());
    }
    if (character == '|') {
      return new Token(TokenType.Pipe, currentSourceSpan());
    }
    if (character == '(') {
      return new Token(TokenType.LeftParen, currentSourceSpan());
    }
    if (character == ')') {
      return new Token(TokenType.RightParen, currentSourceSpan());
    }

    return new Token(TokenType.RightBrace, currentSourceSpan());
  }

  private Token nextToken() {
    skipWhitespace();

    Token token = tokenFromCharacter(currentCharacter);

    readCharacter();

    return token;
  }

  public List<Token> lex() {
    List<Token> tokens = new ArrayList<>();

    if (sourceCode.isEmpty()) {
      return tokens;
    }

    currentCharacter = sourceCode.charAt(0);

    while (hasCharactersToLex()) {
      tokens.add(nextToken());
    }

    tokens.add(new Token(TokenType.Eof));

    return tokens;
  }
}
