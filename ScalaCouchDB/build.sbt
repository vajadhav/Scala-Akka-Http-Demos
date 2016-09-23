name := "ScalaCouchDBDemo"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-actor_2.11" % "2.3.15",
  "com.typesafe.akka" % "akka-agent_2.11" % "2.3.15",
  "com.ibm" % "couchdb-scala_2.11" % "0.7.2",
  "org.log4s" % "log4s_2.11" % "1.3.0",
  "ch.qos.logback" % "logback-classic" % "1.1.7"
)
    