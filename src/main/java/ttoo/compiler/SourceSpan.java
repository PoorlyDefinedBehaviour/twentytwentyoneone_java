package ttoo.compiler;

public class SourceSpan {
  public final int line;
  public final int column;

  public SourceSpan(int line, int column) {
    this.line = line;
    this.column = column;
  }

  @Override
  public boolean equals(Object obj) {
    SourceSpan other = (SourceSpan) obj;

    return line == other.line && column == other.column;
  }

  @Override
  public String toString() {
    return String.format("SourceSpan(%s, %s)", line, column);
  }
}
