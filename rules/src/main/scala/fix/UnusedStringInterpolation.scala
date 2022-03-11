package fix

import scalafix.v1._
import scala.meta._

class UnusedStringInterpolation extends SemanticRule("UnusedStringInterpolation") {

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree
      .collect { case v @ Term.Interpolate(Name("s"), lit @ Lit.String(s) :: Nil, Nil) =>
        (lit.head.pos.start - (v.pos.start + 1)) match {
          case 1 => Patch.replaceTree(v, """"""" + s + """"""")
          case 3 => Patch.replaceTree(v, """"""""" + s + """"""""")
          case _ => Patch.empty
        }
      }
      .foldLeft(Patch.empty)(_ + _)
  }

}
