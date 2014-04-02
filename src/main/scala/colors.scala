package colors

import scala.util.{Failure, Success}

object Colors {

  lazy val parsers = Map(
    "scala" -> ((c: String) ⇒ new ScalaParser(c)))

  def apply(
    code: String,
    lang: String
  )(f: PartialFunction[Code, String]) = (for {
    parser ← parsers get (lang) map (Success(_)) getOrElse Failure(
      throw new Exception("Cannot find a parser for given language"))
    res ← parser(code).Code.run()
  } yield res.collect(f).mkString) getOrElse (code)
}
