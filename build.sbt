/*
 *
 *
 * -------------------------------------------------------------------------------------------------
 * Setup
 ------------------------------------------------------------------------------------------------- */
organization := "com.julianbrendl"
name := "html-haskell"
version := "0.0.1-SNAPSHOT"
scalaVersion := "2.12.3"


/* -------------------------------------------------------------------------------------------------
 * Managing dependencies
 ------------------------------------------------------------------------------------------------- */
val Http4sVersion = "0.17.6"
val Specs2Version = "4.0.2"
val LogbackVersion = "1.2.3"
val ioCircleVersion = "0.6.1"
val ScalaTagsVersion = "0.6.7"

libraryDependencies ++= Seq(
  "org.http4s"         %% "http4s-blaze-server"  % Http4sVersion,
  "org.http4s"         %% "http4s-circe"         % Http4sVersion,
  "org.http4s"         %% "http4s-dsl"           % Http4sVersion,
  "org.specs2"         %% "specs2-core"          % Specs2Version % "test",
  "ch.qos.logback"     %  "logback-classic"      % LogbackVersion,
  "io.circe"           %% "circe-parser"         % ioCircleVersion,

  // Auto-derivation of JSON codecs
  "io.circe"           %% "circe-generic"        % ioCircleVersion,

  // String interpolation to JSON model
  "io.circe"           %% "circe-literal"        % ioCircleVersion,

  // HTML for Scala
  "com.lihaoyi"        %% "scalatags"            % ScalaTagsVersion,

  // Code injection
  "org.scala-lang"     % "scala-compiler"       % scalaVersion.value
)


/* -------------------------------------------------------------------------------------------------
 * Building Jar
 ------------------------------------------------------------------------------------------------- */
assemblyJarName in assembly := s"$name.jar"
assemblyMergeStrategy in assembly := {
  case PathList("reference.conf") => MergeStrategy.concat
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}
