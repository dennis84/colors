package colors

import org.parboiled2._

trait CommonRules extends Parser {

  def WhitespaceRule = rule { capture(Whitespace) ~> WhitespaceCode }
  def WordRule = rule { capture(Word) ~> WordCode }
  def CharRule = rule { capture(ANY) ~> CharCode }

  def Whitespace = rule { oneOrMore(WhitespaceChar) }
  def Word = rule { oneOrMore("a" - "z" | "A" - "Z" | "0" - "9") }

  val WhitespaceChar = CharPredicate(" \n\r\t\f")
  val EOL            = CharPredicate("\n\r")
}
