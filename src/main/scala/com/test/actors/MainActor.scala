package com.test.actors

import akka.actor.{ Actor, Props }
import akka.pattern._
import com.test.comon.SiteParser
import com.test.model._

import scala.concurrent.Future

object MainActor {
  def props: Props = Props[MainActor]
}

class MainActor extends Actor with SiteParser {
  
  implicit val ec = context.dispatcher
  
  def receive: Receive = {
    case request: RequestParser => result(request) pipeTo sender
    case _ => sender() ! ResponseParserError("Wrong request")
  }
  
  def result(request: RequestParser): Future[Response] ={
    getCount(request).map {
      case Some(count) => ResponseParser(request.word, count)
      case None => ResponseParserNotFound(request.word, "Not Found")
    }.recover{
      case ex: Throwable => ResponseParserError(ex.getMessage)
    }
  }
}
