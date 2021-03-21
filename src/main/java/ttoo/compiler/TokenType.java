package ttoo.compiler;

public enum TokenType {
  LeftBrace {
    @Override
    public String toString() {
      return "{";
    }
  },
  RightBrace {
    @Override
    public String toString() {
      return "}";
    }
  },
  LeftBracket {
    @Override
    public String toString() {
      return "[";

    }
  },
  RightBracket {
    @Override
    public String toString() {
      return "]";
    }
  },
  Comma {
    @Override
    public String toString() {
      return ",";
    }
  },
  Plus {
    @Override
    public String toString() {
      return "+";
    }
  },
  Minus {
    @Override
    public String toString() {
      return "-";
    }
  },
  Slash {
    @Override
    public String toString() {
      return "/";
    }
  },
  Star {
    @Override
    public String toString() {
      return "*";
    }
  },
  Percent {
    @Override
    public String toString() {
      return "%";
    }
  },
  PercentPercent {
    @Override
    public String toString() {
      return "%%";
    }
  },
  Equal {
    @Override
    public String toString() {
      return "=";
    }
  },
  NotEqual {
    @Override
    public String toString() {
      return "!=";
    }
  },
  Bang {
    @Override
    public String toString() {
      return "!";
    }
  },
  LessThan {
    @Override
    public String toString() {
      return "<";
    }
  },
  LessThanOrEqual {
    @Override
    public String toString() {
      return "<=";
    }
  },
  GreaterThan {
    @Override
    public String toString() {
      return ">";
    }
  },
  GreaterThanOrEqual {
    @Override
    public String toString() {
      return ">=";
    }
  },
  Ampersand {
    @Override
    public String toString() {
      return "&";
    }
  },
  Pipe {
    @Override
    public String toString() {
      return "|";
    }
  },
  LeftParen {
    @Override
    public String toString() {
      return "(";
    }
  },
  RightParen {
    @Override
    public String toString() {
      return ")";
    }
  },
  Program {
    @Override
    public String toString() {
      return "program";
    }
  },
  Define {
    @Override
    public String toString() {
      return "define";
    }
  },
  Not {
    @Override
    public String toString() {
      return "not";
    }
  },
  Variable {
    @Override
    public String toString() {
      return "variable";
    }
  },
  Is {
    @Override
    public String toString() {
      return "is";
    }
  },
  Natural {
    @Override
    public String toString() {
      return "natural";
    }
  },
  Real {
    @Override
    public String toString() {
      return "real";
    }
  },
  Char {
    @Override
    public String toString() {
      return "char";
    }
  },
  Boolean {
    @Override
    public String toString() {
      return "boolean";
    }
  },
  Execute {
    @Override
    public String toString() {
      return "execute";
    }
  },
  Set {
    @Override
    public String toString() {
      return "set";
    }
  },
  Get {
    @Override
    public String toString() {
      return "get";
    }
  },
  To {
    @Override
    public String toString() {
      return "to";
    }
  },
  Put {
    @Override
    public String toString() {
      return "put";
    }
  },
  Loop {
    @Override
    public String toString() {
      return "loop";
    }
  },
  While {
    @Override
    public String toString() {
      return "while";
    }
  },
  Do {
    @Override
    public String toString() {
      return "do";
    }
  },
  True {
    @Override
    public String toString() {
      return "true";
    }
  },
  False {
    @Override
    public String toString() {
      return "false";
    }
  },
  Identifier {
    @Override
    public String toString() {
      return "identifier";
    }
  },
  Eof {
    @Override
    public String toString() {
      return "<EOF>";
    }
  };
}
