name := """zx80-crazy-eights"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

resolvers ++= Seq(
  "bintray-sbt-plugin-releases" at "http://dl.bintray.com/content/sbt/sbt-plugin-releases"
)

libraryDependencies ++= Seq(
  "org.specs2" % "specs2_2.10" % "2.3.11",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.3.3",
  "org.scalatest" %% "scalatest" % "2.1.6" % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.slf4j" % "slf4j-nop" % "1.7.7",
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2")
