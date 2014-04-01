package colors

import org.parboiled2._

class CodeParser(val input: ParserInput)
  extends Parser
  with StringBuilding {

  def Code = rule { zeroOrMore(Snippets) ~ EOI }

  def Snippets = rule {
    WhitespaceRule | CommentRule | WordRule | BracketsRule | TextRule | CharRule
  }

  def WhitespaceRule = rule { capture(Whitespace) ~> WhitespaceCode }
  def WordRule = rule { capture(Word) ~> WordCode }
  def BracketsRule = rule { capture(Brackets) ~> BracketCode }
  def TextRule = rule { capture(Text) ~> TextCode }
  def CharRule = rule { capture(ANY) ~> CharCode }
  def CommentRule = rule {
    capture(InlineComment | BlockComment) ~> CommentCode
  }

  def Word = rule { oneOrMore("a" - "z" | "A" - "Z" | "0" - "9") }
  def Whitespace = rule { oneOrMore(WhitespaceChar) }
  def InlineComment = rule { "//" ~ zeroOrMore(!EOL ~ ANY) ~ EOL }
  def BlockComment = rule { "/*" ~ zeroOrMore(!"*/" ~ ANY) ~ "*/" }
  def Text = rule {
    Quote ~ zeroOrMore(!QuoteBackslash ~ ANY | "\\" ~ ANY) ~ Quote
  }

  val WhitespaceChar = CharPredicate(" \n\r\t\f")
  val QuoteBackslash = CharPredicate("\"\\")
  val Quote          = CharPredicate("\"'")
  val Brackets       = CharPredicate("({[<>]})")
  val EOL            = CharPredicate("\n\r")
}
