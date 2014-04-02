package colors

import org.parboiled2._

trait CommonRules extends Parser {

  def WhitespaceRule = rule { capture(Whitespace) ~> WhitespaceCode }

  def Whitespace = rule { oneOrMore(WhitespaceChar) }

  val WhitespaceChar = CharPredicate(" \n\r\t\f")
  val EOL            = CharPredicate("\n\r")
}
