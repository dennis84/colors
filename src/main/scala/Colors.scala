package colors

import scala.util.{Failure, Success}

object Colors {

  lazy val parsers = Map(
    "scala"   -> ((c: String) ⇒ new ScalaParser(c)),
    "haskell" -> ((c: String) ⇒ new HaskellParser(c)),
    "lisp"    -> ((c: String) ⇒ new LispParser(c)))

  def apply(
    code: String,
    lang: String
  )(f: PartialFunction[Code, String]) = (for {
    parser ← parsers get lang map (Success(_)) getOrElse Failure(
      throw new Exception("Cannot find a parser for given language"))
    res ← parser(code).Code.run()
  } yield (res.toList collect f).mkString) getOrElse code
}
