package colors

import org.parboiled2._

trait CommonRules extends Parser {

  def WhitespaceRule = rule { capture(Whitespace) ~> WhitespaceCode }
  def BracketsRule = rule { capture(Brackets) ~> BracketCode }
  def WordRule = rule { capture(Word) ~> WordCode }
  def CharRule = rule { capture(ANY) ~> CharCode }
  def NumberRule = rule { capture(Number) ~> NumberCode }

  def Whitespace = rule { oneOrMore(WhitespaceChar) }
  def Word = rule { oneOrMore("a" - "z" | "A" - "Z" | "0" - "9") }
  def Number = rule { oneOrMore(CharPredicate.Digit) }

  val WhitespaceChar = CharPredicate(" \n\r\t\f")
  val Brackets       = CharPredicate("({[<>]})")
  val EOL            = CharPredicate("\n\r")
}
