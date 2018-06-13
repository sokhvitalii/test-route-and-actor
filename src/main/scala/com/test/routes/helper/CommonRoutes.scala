package com.test.routes.helper

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.http.scaladsl.unmarshalling.FromRequestUnmarshaller
import com.test.routes.MainRoutes
import spray.json.RootJsonFormat

import scala.concurrent.Future
import scala.util.{ Failure, Success }

trait CommonRoutes extends JsonSupport {
  implicit def system: ActorSystem
  lazy val log = Logging(system, classOf[MainRoutes])
  
  def entityForPost[T](block: T => Route)(implicit em: FromRequestUnmarshaller[T]): Route =
    concat(
      post {
        entity(as[T]) { user =>
          block(user)
        }
      })
  
  def handlingResult[T](future: Future[T])(implicit userJsonFormat: RootJsonFormat[T]): Route = {
    onComplete(future) {
      case Success(res) =>
        log.info("Success result: ", res)
        complete((StatusCodes.OK, res))
      case Failure(ex) =>
        log.error("Failure with: ", ex.getStackTrace)
        complete(StatusCodes.NotFound)
    }
  }
}
