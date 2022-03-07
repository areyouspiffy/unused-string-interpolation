lazy val V = _root_.scalafix.sbt.BuildInfo

inThisBuild(
  List(
    tlBaseVersion := "0.1",
    scalaVersion := "2.13.8",
    organization := "com.brianmowen",
    developers := List(tlGitHubDev("areyouspiffy", "Brian Owen")),
    homepage := Some(url("https://github.com/areyouspiffy/unused-string-interpolation")),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/areyouspiffy/unused-string-interpolation/"),
        "scm:git:git@github.com:areyouspiffy/unused-string-interpolation.git"
      )
    ),
    licenses := Seq(License.Apache2),
    tlSonatypeUseLegacyHost := false,
    scalaVersion := V.scala212,
    crossScalaVersions := List(V.scala211, V.scala212, V.scala213),
    addCompilerPlugin(scalafixSemanticdb),
    scalacOptions ++= List(
      "-Yrangepos",
      "-P:semanticdb:synthetics:on",
      "-deprecation"
    )
  )
)

lazy val commonSettings =
  Seq(mimaReportBinaryIssues := {})

lazy val root = project
  .in(file("."))
  .settings(publish / skip := true)
  .settings(commonSettings)
  .aggregate(rules, input, output, tests)

lazy val rules = project
  .in(file("rules"))
  .settings(
    mimaReportBinaryIssues := {},
    moduleName := "unused-string-interpolation",
    libraryDependencies += "ch.epfl.scala" %% "scalafix-core" % V.scalafixVersion
  )
  .settings(commonSettings)

lazy val input = project
  .settings(publish / skip := true)
  .settings(commonSettings)

lazy val output = project
  .settings(publish / skip := true)
  .settings(commonSettings)

lazy val tests = project
  .settings(
    publish / skip := true,
    libraryDependencies += "ch.epfl.scala" % "scalafix-testkit" % V.scalafixVersion % Test cross CrossVersion.full,
    Compile / compile := (Compile / compile).dependsOn((input / Compile / compile)).value,
    scalafixTestkitOutputSourceDirectories := (output / Compile / sourceDirectories).value,
    scalafixTestkitInputSourceDirectories := (input / Compile / sourceDirectories).value,
    scalafixTestkitInputClasspath := (input / Compile / fullClasspath).value
  )
  .dependsOn(rules)
  .enablePlugins(ScalafixTestkitPlugin)
  .settings(commonSettings)
