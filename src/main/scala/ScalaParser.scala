package colors

import org.parboiled2._

class ScalaParser(val input: ParserInput) extends CodeParser with CommonRules {

  def Snippets = rule {
    WhitespaceRule | CommentRule | NumberRule | TitleRule | WordRule |
    BracketsRule | TextRule | CharRule
  }

  def TitleRule = rule { capture(("A" - "Z") ~ Word) ~> TitleCode }
  def TextRule = rule { capture(Text) ~> TextCode }
  def CommentRule = rule {
    capture(InlineComment | BlockComment) ~> CommentCode
  }

  def InlineComment = rule { "//" ~ zeroOrMore(!EOL ~ ANY) ~ EOL }
  def BlockComment = rule { "/*" ~ zeroOrMore(!"*/" ~ ANY) ~ "*/" }
  def Text = rule {
    Quote ~ zeroOrMore(!QuoteBackslash ~ ANY | "\\" ~ ANY) ~ Quote
  }

  val QuoteBackslash = CharPredicate("\"\\")
  val Quote          = CharPredicate("\"'")
}
