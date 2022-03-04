resolvers += Resolver.sonatypeRepo("releases")

addSbtPlugin("org.typelevel" % "sbt-typelevel-ci-release" % "0.4.6")
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.9.34")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.6")
