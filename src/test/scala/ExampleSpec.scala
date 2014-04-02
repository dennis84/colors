package colors

import org.specs2.mutable._
import org.parboiled2._

class ExampleSpec extends Specification {

  "ScalaParser" should {
    "parse scala code" in {
      val input = """|/** block comment
                     | */
                     |class HelloWorld extends App {
                     |  // inline comment
                     |  println("hello \"world", "foo", "bar")
                     |  val num = 123.32
                     |}""".stripMargin

      val output = Colors(input, "scala") {
        case WordCode(v)    ⇒ s"<word>$v</word>"
        case BracketCode(v) ⇒ s"<bracket>$v</bracket>"
        case CommentCode(v) ⇒ s"<comment>$v</comment>"
        case TextCode(v)    ⇒ s"<text>$v</text>"
        case NumberCode(v)  ⇒ s"<number>$v</number>"
        case c: Code        ⇒ c.value
      }

      println(output)

      1 must_== 1
    }
  }
}
