package colors

import org.parboiled2._

class HaskellParser(val input: ParserInput) extends CodeParser with CommonRules {

  def Snippets = rule {
    WhitespaceRule
  }
}
