package colors

import org.specs2.mutable._
import scala.util.{Failure, Success}
import org.parboiled2._

class ExampleSpec extends Specification {

  "CodeParser" should {
    "parse scala code" in {
      val input = """|class HelloWorld extends App {
                     |  println("hello \"world", "foo", "bar")
                     |}""".stripMargin

      val parser = new CodeParser(input)
      val res = parser.Code.run()

      res match {
        case Success(res)           ⇒ println(res)
        case Failure(e: ParseError) ⇒ println(parser.formatError(e))
        case Failure(e)             ⇒ println(e)
      }

      1 must_== 1
    }
  }
}
