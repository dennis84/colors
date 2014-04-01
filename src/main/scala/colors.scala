package colors

object Colors {

  def apply(code: String)(f: PartialFunction[Code, String]) = for {
    res ← new CodeParser(code).Code.run()
  } yield res.collect(f).mkString
}
