package fix

import scalafix.testkit._
import org.scalatest.funsuite.AnyFunSuiteLike

class RuleSuite extends scalafix.testkit.AbstractSemanticRuleSuite with AnyFunSuiteLike  {
  runAllTests()
}
