package colors

import org.parboiled2._

class HaskellParser(val input: ParserInput)
  extends CodeParser
  with CommonRules {

  def Snippets = rule {
    WhitespaceRule | CommentRule | WordRule | TextRule | CharRule
  }

  def TextRule = rule { capture(Text) ~> TextCode }
  def CommentRule = rule {
    capture(InlineComment | NestedComment) ~> CommentCode
  }

  def Text = rule {
    Quote ~ zeroOrMore(!QuoteBackslash ~ ANY | "\\" ~ ANY) ~ Quote
  }

  def InlineComment = rule { "--" ~ zeroOrMore(!EOL ~ ANY) ~ EOL }
  def NestedComment = rule { "{-" ~ zeroOrMore(!"-}" ~ ANY) ~ "-}" }

  val QuoteBackslash = CharPredicate("\"\\")
  val Quote          = CharPredicate("\"'")
}
