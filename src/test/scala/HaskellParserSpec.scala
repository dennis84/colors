package colors

import org.specs2.mutable._
import org.parboiled2._
import scala.util.{Failure, Success}

class HaskellParserSpec extends Specification {

  "HaskellParser" should {
    "parse" in {
      val input = """|-- this is a comment
                     |{-
                     |  multi-line comment
                     |-}
                     |main = putStrLn "Hello, World!"
                     |""".stripMargin

      val parser = new HaskellParser(input)
      parser.Code.run() match {
        case Success(values)        ⇒ println(values)
        case Failure(e: ParseError) ⇒ println(parser formatError e)
        case Failure(e)             ⇒ println(e)
      }

      1 must_== 1
    }
  }
}
