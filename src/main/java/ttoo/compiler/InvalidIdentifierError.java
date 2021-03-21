package ttoo.compiler;

public class InvalidIdentifierError extends RuntimeException {
  public final String message;

  public InvalidIdentifierError(String message) {
    this.message = message;
  }
}
