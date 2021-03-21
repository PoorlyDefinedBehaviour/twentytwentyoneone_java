package ttoo.compiler;

public class Token {
  public final TokenType type;
  public final SourceSpan sourceSpan;

  public Token(TokenType type, SourceSpan sourceSpan) {
    this.type = type;
    this.sourceSpan = sourceSpan;
  }

  public Token(TokenType type) {
    this.type = type;
    this.sourceSpan = new SourceSpan(-1, -1);
  }

  @Override
  public boolean equals(Object obj) {
    Token other = (Token) obj;

    return type == other.type && sourceSpan.equals(other.sourceSpan);
  }

  @Override
  public String toString() {
    if (type == TokenType.Eof) {
      return String.format("Token(%s)", type.toString());
    }

    return String.format("Token(%s, %s)", type.toString(), sourceSpan.toString());
  }

  public static Token tokenFromIdentifierOrKeyword(String identifierOrKeyword, SourceSpan sourceSpan) {
    identifierOrKeyword = identifierOrKeyword.toLowerCase();

    if (identifierOrKeyword == "program")
      return new Token(TokenType.Program, sourceSpan);
    if (identifierOrKeyword == "define")
      return new Token(TokenType.Define, sourceSpan);
    if (identifierOrKeyword == "not")
      return new Token(TokenType.Not, sourceSpan);
    if (identifierOrKeyword == "variable")
      return new Token(TokenType.Variable, sourceSpan);
    if (identifierOrKeyword == "is")
      return new Token(TokenType.Is, sourceSpan);
    if (identifierOrKeyword == "natural")
      return new Token(TokenType.Natural, sourceSpan);
    if (identifierOrKeyword == "real")
      return new Token(TokenType.Real, sourceSpan);
    if (identifierOrKeyword == "char")
      return new Token(TokenType.Char, sourceSpan);
    if (identifierOrKeyword == "boolean")
      return new Token(TokenType.Boolean, sourceSpan);
    if (identifierOrKeyword == "execute")
      return new Token(TokenType.Execute, sourceSpan);
    if (identifierOrKeyword == "set")
      return new Token(TokenType.Set, sourceSpan);
    if (identifierOrKeyword == "get")
      return new Token(TokenType.Get, sourceSpan);
    if (identifierOrKeyword == "to")
      return new Token(TokenType.To, sourceSpan);
    if (identifierOrKeyword == "put")
      return new Token(TokenType.Put, sourceSpan);
    if (identifierOrKeyword == "loop")
      return new Token(TokenType.Loop, sourceSpan);
    if (identifierOrKeyword == "while")
      return new Token(TokenType.While, sourceSpan);
    if (identifierOrKeyword == "do")
      return new Token(TokenType.Do, sourceSpan);
    if (identifierOrKeyword == "true")
      return new Token(TokenType.True, sourceSpan);
    if (identifierOrKeyword == "false")
      return new Token(TokenType.False, sourceSpan);

    return new Token(TokenType.Identifier, sourceSpan);
  }
}
