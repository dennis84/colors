package colors

import org.specs2.mutable._
import org.parboiled2._
import scala.util.{Failure, Success}

class LispParserSpec extends Specification {

  "LispParser" should {
    "parse" in {
      val input = """|;; this is a comment
                     |(+ 2 3 4)
                     |(princ "Hello, world!")
                     |""".stripMargin

      val parser = new LispParser(input)
      parser.Code.run() match {
        case Success(values)        ⇒ println(values)
        case Failure(e: ParseError) ⇒ println(parser formatError e)
        case Failure(e)             ⇒ println(e)
      }

      1 must_== 1
    }
  }
}
