package colors

import org.parboiled2._

class ScalaParser(val input: ParserInput) extends CodeParser with CommonRules {

  def Snippets = rule {
    WhitespaceRule | CommentRule | WordRule | BracketsRule | TextRule | CharRule
  }

  def WordRule = rule { capture(Word) ~> WordCode }
  def BracketsRule = rule { capture(Brackets) ~> BracketCode }
  def TextRule = rule { capture(Text) ~> TextCode }
  def CharRule = rule { capture(ANY) ~> CharCode }
  def CommentRule = rule {
    capture(InlineComment | BlockComment) ~> CommentCode
  }

  def Word = rule { oneOrMore("a" - "z" | "A" - "Z" | "0" - "9") }
  def InlineComment = rule { "//" ~ zeroOrMore(!EOL ~ ANY) ~ EOL }
  def BlockComment = rule { "/*" ~ zeroOrMore(!"*/" ~ ANY) ~ "*/" }
  def Text = rule {
    Quote ~ zeroOrMore(!QuoteBackslash ~ ANY | "\\" ~ ANY) ~ Quote
  }

  val QuoteBackslash = CharPredicate("\"\\")
  val Quote          = CharPredicate("\"'")
  val Brackets       = CharPredicate("({[<>]})")
}
