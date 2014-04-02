package colors

import org.parboiled2._

trait CodeParser extends Parser with StringBuilding {

  val input: ParserInput

  def Code = rule { zeroOrMore(Snippets) ~ EOI }

  def Snippets: Rule1[Code]
}
