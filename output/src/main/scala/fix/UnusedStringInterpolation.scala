package fix

object UnusedStringInterpolation {
  val foo = "bar"
  val uri = f"http://localhost"
  val multiline =
    """foo
        bar
        baz
       """
}
