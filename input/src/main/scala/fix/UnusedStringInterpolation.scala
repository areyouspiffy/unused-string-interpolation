/*
rule = UnusedStringInterpolation
*/
package fix

object UnusedStringInterpolation {
  val foo = s"bar"
  val uri = f"http://localhost"
  val multiline =
    s"""foo
        bar
        baz
       """
}
