ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.15"

val sparkVersion = "3.0.2"

lazy val root = (project in file("."))
  .settings(
    name := "hw_1"
  )

libraryDependencies ++= Seq(
  "com.github.scopt" %% "scopt" % "4.0.1",
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core"   % "2.12.3",
  // Use the "provided" scope instead when the "compile-internal" scope is not supported
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.12.3"
)

//addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.10.0-RC1")

Compile / mainClass  := Some("SimpleApp")
assembly / mainClass := Some {
  "SimpleApp"
}

assemblyMergeStrategy / assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}