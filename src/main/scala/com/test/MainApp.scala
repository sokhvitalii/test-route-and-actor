package com.test

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import akka.actor.{ ActorRef, ActorSystem }
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.test.Conf._
import com.test.actors.MainActor
import com.test.routes.MainRoutes

object MainApp extends App with MainRoutes {

  implicit val system: ActorSystem = ActorSystem(ActorConf.systemName)
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val mainActor: ActorRef = system.actorOf(MainActor.props, ActorConf.actorName)

  lazy val route: Route = routes
 
  Http().bindAndHandle(route, RouteConf.interface, RouteConf.port)

  println(s"Server online at http://${RouteConf.interface}:${RouteConf.port}/")

  Await.result(system.whenTerminated, Duration.Inf)
}
