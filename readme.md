# unused-string-interpolation

This scalafix will remove the `s` interpolator if it is not provided any arguments (i.e. it is unused). The scala compiler will do this anyway -- this scalafix just makes it consistent. 

For example, this:

```
object Example {
  val foo = s"bar"
}
```

will become this 

```
object Example {
  val foo = "bar"
}
```
