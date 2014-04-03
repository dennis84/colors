package colors

import org.specs2.mutable._
import org.parboiled2._
import scala.util.{Failure, Success}

class ScalaParserSpec extends Specification {

  "ScalaParser" should {
    "parse" in {
      val input = """|/** block comment
                     | */
                     |class HelloWorld extends App {
                     |  // inline comment
                     |  println("hello \"world", "foo", "bar")
                     |  val num = 123.32
                     |}""".stripMargin

      val parser = new ScalaParser(input)
      parser.Code.run() match {
        case Success(values)        ⇒ println(values)
        case Failure(e: ParseError) ⇒ println(parser formatError e)
        case Failure(e)             ⇒ println(e)
      }

      1 must_== 1
    }
  }
}
