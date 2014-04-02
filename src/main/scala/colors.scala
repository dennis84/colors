package colors

object Colors {

  lazy val parsers = Map(
    "scala" -> ((c: String) ⇒ new ScalaParser(c)))

  def apply(
    code: String,
    lang: String
  )(f: PartialFunction[Code, String]) = for {
    parser ← parsers.get(lang)
  } yield for {
    res ← parser(code).Code.run()
  } yield res.collect(f).mkString
}
