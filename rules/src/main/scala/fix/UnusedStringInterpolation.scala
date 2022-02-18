package fix

import scalafix.v1._
import scala.meta._

class UnusedStringInterpolation extends SemanticRule("UnusedStringInterpolation") {

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect { case v @ Term.Interpolate(Name("s"), Lit.String(s) :: Nil, Nil) =>
      Patch.replaceTree(v, s""""$s"""")
    }.foldLeft(Patch.empty)(_ + _)
  }

}