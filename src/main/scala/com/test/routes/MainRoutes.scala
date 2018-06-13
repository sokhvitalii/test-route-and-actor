package com.test.routes

import akka.actor.ActorRef
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import akka.util.Timeout
import com.test.model.{ RequestParser, Response}
import com.test.routes.helper.CommonRoutes

import scala.concurrent.duration._

trait MainRoutes extends CommonRoutes {
  
  def mainActor: ActorRef
  
  implicit lazy val timeout = Timeout(5.seconds)
  
  lazy val routes: Route =
    pathPrefix("parser") {
      entityForPost[RequestParser] { req =>
        handlingResult((mainActor ? req).mapTo[Response])
      }
    }
}
