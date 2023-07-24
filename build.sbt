ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"


lazy val sttpVersion = "3.8.16"
lazy val playJsonVersion = "2.10.0-RC9"
lazy val scalaLoggingVersion = "3.9.5"
lazy val logBackVersion = "1.4.8"

lazy val root = (project in file("."))
  .settings(
    name := "sttp-cookbook"
  )

libraryDependencies ++= Seq(
  // Http support
  "com.softwaremill.sttp.client3" %% "core" % sttpVersion,
  "com.typesafe.play" %% "play-json" % playJsonVersion,
  //"com.softwaremill.sttp.client3" %% "play-json" % "3.8.16"
  "com.softwaremill.sttp.client3" %% "play-json" % sttpVersion,
  // Logging support

 "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
 "ch.qos.logback" % "logback-classic" % logBackVersion


)
