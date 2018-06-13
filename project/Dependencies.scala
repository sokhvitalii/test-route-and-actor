import sbt.Keys._
import sbt._

object Dependencies {
  
  object Testing {
    val scalatest = "org.scalatest" %% "scalatest" % "3.0.5"
    val inMemoryJournal = "com.github.dnvriend" %% "akka-persistence-inmemory" % "2.5.1.1"
  }
  
  object Circe {
    val circeVersion = "0.9.1"
    val circeConfig = "io.circe" %% "circe-config" % "0.4.1"
    val circeGeneric = "io.circe" %% "circe-generic" % circeVersion
    val circeCore = "io.circe" %% "circe-core" % circeVersion
    val circeParser = "io.circe" %% "circe-parser" % circeVersion
  }
  
  object Akka {
    val akkaVersion = "2.5.9"
    val akkaHttpVersion = "10.1.0-RC2"

    val core = "com.typesafe.akka" %% "akka-actor" % akkaVersion
    val stream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
    val http = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
    val jsonCirce = "de.heikoseeberger" %% "akka-http-circe" % "1.20.0-RC1"
    val slf4j = "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
    val persistence = "com.typesafe.akka" %% "akka-persistence" % akkaVersion
    val parser = "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.22"
    val mock = "org.mockito" % "mockito-all" % "1.8.4"
    val scalatra = "org.scalatra" %% "scalatra" % "2.6.3"
    
    val persistenceQuery = "com.typesafe.akka" %% "akka-persistence-query" % akkaVersion
    val cluster = "com.typesafe.akka" %% "akka-cluster" % akkaVersion
    val clusterSharding = "com.typesafe.akka" %% "akka-cluster-sharding" % akkaVersion
    val clusterTools = "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion
    
    val httpTestkit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion
    val testKit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion
    val streamTestkit = "com.typesafe.akka" %% "akka-testkit" % Akka.akkaVersion
  }
  
  object Cassandra {
    val cassandraPersistenceVersion = "0.83"
    
    val persistence = "com.typesafe.akka" %% "akka-persistence-cassandra" % cassandraPersistenceVersion
    val persistenceTest = "com.typesafe.akka" %% "akka-persistence-cassandra-launcher" % cassandraPersistenceVersion
    
  }
  
  object Logging {
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
    val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
  }
  
}
