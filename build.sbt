name := """zx80-crazy-eights"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-actor_2.11" % "2.3.3",
  "com.typesafe.akka" % "akka-remote_2.11" % "2.3.3",
  "org.scalatest" %% "scalatest" % "2.1.6" % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.slf4j" % "slf4j-nop" % "1.7.7",
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2")
