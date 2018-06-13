import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.talkskit",
      scalaVersion    := "2.12.6"
    )),
    name := "talkskit",
    libraryDependencies ++= Seq(
      /** @todo change spray json to circe */
      "com.typesafe.akka" %% "akka-http-spray-json" % Akka.akkaHttpVersion,
      
      Akka.http,
      "com.typesafe.akka" %% "akka-http-xml"        % Akka.akkaHttpVersion,
      Akka.stream,
      Akka.jsonCirce,
      Akka.slf4j,
      Akka.persistence,
      Akka.persistenceQuery,
      Akka.cluster,
      Akka.clusterSharding,
      Akka.parser,
      Akka.mock,
      "org.scalatra"                %% "scalatra"            % "2.6.3",
      "org.scalatra"                %% "scalatra-scalate"    % "2.6.3",
      "org.scalatra"                %% "scalatra-specs2"     % "2.6.3"  % "test",
      "org.scalatra"                %% "scalatra-atmosphere" % "2.6.3",
      "com.github.tomakehurst" % "wiremock" % "2.18.0" % "test",
      Akka.clusterTools,
      Cassandra.persistence,
      Cassandra.persistenceTest,
      
      Circe.circeCore,
      Circe.circeConfig,
      Circe.circeGeneric,
      Circe.circeParser,
      
      Logging.scalaLogging,
      Logging.logback,
      
      Akka.httpTestkit % Test,
      Akka.testKit % Test,
      Akka.streamTestkit % Test,
      
  
      Testing.scalatest,
  
      "ch.megard" %% "akka-http-cors" % "0.3.0"
    )
  )
