package colors

case class WhitespaceCode(val value: String) extends Code

case class NumberCode(val value: String) extends Code

case class CommentCode(val value: String) extends Code

case class BracketCode(val value: String) extends Code

case class TextCode(val value: String) extends Code

case class WordCode(val value: String) extends Code

case class CharCode(val value: String) extends Code

trait Code {
  val value: String
}
