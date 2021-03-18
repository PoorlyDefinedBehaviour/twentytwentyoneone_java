package ttoo;

import ttoo.compiler.Lexer;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    System.out.println(new Lexer("}").lex());
  }
}
