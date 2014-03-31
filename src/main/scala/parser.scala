package colors

import org.parboiled2._

class CodeParser(val input: ParserInput)
  extends Parser
  with StringBuilding {

  def Code = rule { zeroOrMore(Snippets) ~ EOI }

  def Snippets = rule {
    WhitespaceRule | WordRule | ParensRule | TextRule | CharRule
  }

  def WhitespaceRule = rule { capture(Whitespace) ~> WhitespaceCode }
  def WordRule = rule { capture(Word) ~> WordCode }
  def ParensRule = rule { capture(Parens) ~> ParensCode }
  def TextRule = rule { capture(Text) ~> TextCode }
  def CharRule = rule { capture(ANY) ~> CharCode }

  def Word = rule { oneOrMore("a" - "z" | "A" - "Z" | "0" - "9") }
  def Whitespace = rule { oneOrMore(WhitespaceChar) }
  def Text = rule { Quote ~ Chars ~ Quote }
  def Chars = rule { zeroOrMore(Char) }
  def Char = rule { !QuoteBackslash ~ ANY ~ append() }

  val WhitespaceChar = CharPredicate(" \n\r\t\f")
  val QuoteBackslash = CharPredicate("\"\\")
  val Quote          = CharPredicate("\"'")
  def Parens         = CharPredicate("({[]})")
}
