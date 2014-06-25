name := """zx80-crazy-eights"""

version := "1.1-SNAPSHOT"

scalaVersion := "2.11.1"

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.3.3",
  "com.typesafe.akka" % "akka-remote_2.11" % "2.3.3",
  "com.typesafe.akka" % "akka-slf4j_2.11" % "2.3.3",
  "org.scalatest" %% "scalatest" % "2.1.6" % "test")
