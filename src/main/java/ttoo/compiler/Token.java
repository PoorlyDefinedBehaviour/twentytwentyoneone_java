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
}
