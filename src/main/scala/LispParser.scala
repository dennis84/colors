package colors

import org.parboiled2._

class LispParser(val input: ParserInput) extends CodeParser with CommonRules {

  def Snippets = rule {
    WhitespaceRule | CommentRule | WordRule | BracketsRule | TextRule | CharRule
  }

  def TextRule = rule { capture(Text) ~> TextCode }
  def CommentRule = rule {
    capture(";;" ~ zeroOrMore(!EOL ~ ANY) ~ EOL) ~> CommentCode
  }

  def Text = rule {
    Quote ~ zeroOrMore(!QuoteBackslash ~ ANY | "\\" ~ ANY) ~ Quote
  }

  val QuoteBackslash = CharPredicate("\"\\")
  val Quote          = CharPredicate("\"'")
}
