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

  private char peek() {
    if (position + 1 >= sourceCode.length()) {
      return '\0';
    }

    return sourceCode.charAt(position + 1);
  }

  private boolean isAlpha(char character) {
    return character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z';
  }

  private boolean isAlphaOrSpecialCharacter(char character) {
    return isAlpha(character) || character == '_';
  }

  private boolean isDigit(char character) {
    return character >= '0' && character <= '9';
  }

  private String readIdentifierOrKeyword() {
    int start = position;

    while (isDigit(currentCharacter) || isAlphaOrSpecialCharacter(currentCharacter)) {
      readCharacter();
    }

    String identifierOrKeyword = sourceCode.substring(start, position - start);

    if (identifierOrKeyword.length() == 1) {
      return identifierOrKeyword;
    }

    for (int i = 0; i < identifierOrKeyword.length() - 1; i += 1) {
      char character = identifierOrKeyword.charAt(i);
      char nextCharacter = identifierOrKeyword.charAt(i + 1);

      if ((isDigit(character) || isAlphaOrSpecialCharacter(character)) && !isAlpha(nextCharacter)) {
        throw new InvalidIdentifierError(String.format("%s is not a valid identifier, %s must be followed by a letter",
            identifierOrKeyword, character));
      }
    }

    return identifierOrKeyword;
  }

  private Token tokenFromCharacter(char character) {
    final int tokenStartsAtColumn = column;

    if (character == '{') {
      return new Token(TokenType.LeftBrace, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == '}') {
      return new Token(TokenType.RightBrace, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == '[') {
      return new Token(TokenType.LeftBracket, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == ']') {
      return new Token(TokenType.RightBracket, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == ',') {
      return new Token(TokenType.Comma, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == '+') {
      return new Token(TokenType.Plus, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == '-') {
      return new Token(TokenType.Minus, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == '/') {
      return new Token(TokenType.Slash, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == '*') {
      return new Token(TokenType.Star, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == '%') {
      if (peek() == '%') {
        readCharacter();
        return new Token(TokenType.PercentPercent, new SourceSpan(line, tokenStartsAtColumn));
      }

      return new Token(TokenType.Percent, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == '=') {
      return new Token(TokenType.Equal, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == '!') {
      if (peek() == '=') {
        readCharacter();
        return new Token(TokenType.NotEqual, new SourceSpan(line, tokenStartsAtColumn));
      }
      return new Token(TokenType.Bang, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == '<') {
      if (peek() == '=') {
        readCharacter();
        return new Token(TokenType.LessThanOrEqual, new SourceSpan(line, tokenStartsAtColumn));
      }

      return new Token(TokenType.LessThan, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == '>') {
      if (peek() == '=') {
        readCharacter();
        return new Token(TokenType.GreaterThanOrEqual, new SourceSpan(line, tokenStartsAtColumn));
      }

      return new Token(TokenType.GreaterThan, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == '&') {
      return new Token(TokenType.Ampersand, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == '|') {
      return new Token(TokenType.Pipe, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == '(') {
      return new Token(TokenType.LeftParen, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (character == ')') {
      return new Token(TokenType.RightParen, new SourceSpan(line, tokenStartsAtColumn));
    }
    if (isAlphaOrSpecialCharacter(character)) {
      return Token.tokenFromIdentifierOrKeyword(readIdentifierOrKeyword(), new SourceSpan(line, tokenStartsAtColumn));
    }

    return new Token(TokenType.RightBrace, new SourceSpan(line, tokenStartsAtColumn));
  }

  private Token nextToken() {
    skipWhitespace();

    Token token = tokenFromCharacter(currentCharacter);

    readCharacter();

    return token;
  }

  public List<Token> lex() {
    List<InvalidIdentifierError> errors = new ArrayList<>();
    List<Token> tokens = new ArrayList<>();

    if (sourceCode.isEmpty()) {
      return tokens;
    }

    currentCharacter = sourceCode.charAt(0);

    while (hasCharactersToLex()) {
      try {
        tokens.add(nextToken());
      } catch (InvalidIdentifierError e) {
        errors.add(e);
      }
    }

    tokens.add(new Token(TokenType.Eof));

    return tokens;
  }
}
